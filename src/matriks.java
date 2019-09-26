import java.io.*;
import java.lang.*;
import java.util.*;

public class matriks {

    int baris;
    int kolom;
    double [][] Mat = new double[205][205];

    //Definisi & Konstruktor Tipe Data matriks
    matriks() {
        for (int i = 1; i <= 200; i++) {
            for (int j = 1; j <= 200; j++) {
                this.Mat[i][j] = -999;
            }
        }
        this.baris = 0;
        this.kolom = 0;
    }

    //Method untuk baca matriks
    public void BacaMatriks(){

        Scanner in = new Scanner (System.in);

        System.out.print("Masukkan jumlah baris: ");
        this.baris = in.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        this.kolom = in.nextInt();

        System.out.println("Masukkan Matriks : ");
        for(int i=1; i<=this.baris; i++){
            for(int j=1; j<=this.kolom; j++ ){
                this.Mat[i][j] = in.nextDouble();
            }
        }
    }

    //Method untuk baca matriks persegi
    public void BacaMatriksPersegi(){

        Scanner in = new Scanner (System.in);

        System.out.print("Masukkan jumlah baris dan kolom : ");
        this.baris = in.nextInt();
        this.kolom = this.baris;
        for(int i=1; i<=this.baris; i++){
            for(int j=1; j<=this.kolom; j++ ){
                this.Mat[i][j] = in.nextDouble();
            }
        }
    }

    //Method untuk baca matriks lewat file
    public void BacaFileMatriks() throws Exception {

        Scanner in = new Scanner (System.in);
        String namaFile = in.nextLine();
        namaFile = "../test/" + namaFile + ".txt";
        FileReader fr = new FileReader(namaFile);
        int i;
        String str = "";
        while ((i = fr.read()) != -1) {
            str += (char) i;
        }

        str = str.trim();
        int row = 1, col = 1;
        // Mencari banyaknya baris
        for (i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\n') {
                row++;
            }
        }

        // Mencari banyaknya kolom
        i = 0;
        str += '\n';
        while (str.charAt(i) != '\n') {
            if (str.charAt(i) == ' ') {
                col++;
                while (str.charAt(i) == ' ') {
                    i++;
                }
            }
            i++;
        }
        this.baris = row;
        this.kolom = col;

        // Melakukan assignment matriks
        String cur = "";
        int x = 1, y = 1;
        i = 0;
        while (i < str.length()) {

            if (str.charAt(i) == ' ') continue;

            while (str.charAt(i) != ' ' && str.charAt(i) != '\n') {
                cur += str.charAt(i);
                i++;
            }

            Double ts = Double.parseDouble(cur);

            this.Mat[x][y++] = Double.parseDouble(cur);
            cur = "";

            if (str.charAt(i) == '\n') {
                x++;
                y = 1;
            }
            i++;
        }
    }

    //Method untuk tulis matriks
    public void TulisMatriks(){

        for (int i = 1; i <= this.baris ; i++) {
            for(int j = 1 ; j <= this.kolom; j++) {
                System.out.printf("%.3f ", this.Mat[i][j] + 0.0);
            }
            System.out.println();
        }

    }


    // Metode untuk write file
    public void TulisFile(int type, double hasil, String solusi) {
        // I.S : Type dan hasil sudah valid
        // type = 1 untuk menulis matriks
        // type = 2 untuk menulis hasil (bisa berupa determinan ataupun hasil interpolasi)
        Scanner in = new Scanner (System.in);
        System.out.print("Masukkan nama hasil output file yang diinginkan : ");
        String namaFile = in.nextLine();
        namaFile = "../test/" + namaFile + ".txt";
        Formatter x = new Formatter();
        try {
            x = new Formatter(namaFile);
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat membuat hasil output file");
        }
        String hasilString = Double.toString(hasil) + "\n";
        if (type == 1) {
            hasilString = "";
            for (int i = 1; i <= this.baris; i++) {
                for (int j = 1; j <= this.kolom; j++) {
                    hasilString += Double.toString(this.Mat[i][j]) + (j == this.kolom ? "\n" : " ");
                }
            }
            x.format("%s", hasilString);
        } else if (type == 2) {
            x.format("%s", hasilString);
        } else if (type == 3) {
            x.format("%s", solusi);
        }
        x.close();
        System.out.println("Output berhasil disimpan!");
    }


    public double hasilInterpolasi(int n, double x) {
        double cur = 1;
        double ret = 0;
        for (int i = 0; i <= n; i++) {
            ret += cur * this.Mat[i + 1][this.kolom];
            cur *= x;
        }
        return ret;
    }

    // Method untuk membaca interpolasi
    public void bacaInterpolasi() {
        System.out.println();
        System.out.print("Masukkan nilai n : ");
        Scanner in = new Scanner (System.in);
        int n = in.nextInt();
        this.baris = n + 1;
        this.kolom = n + 2;
        Double a, b;
        for (int i = 1; i <= n + 1; i++) {
            System.out.print("Masukkan x y untuk (x" + (i - 1) + ", y" + (i - 1) + ") : ");
            a = in.nextDouble();
            b = in.nextDouble();
            Double cur = 1.0;
            for (int j = 1; j <= n + 1; j++) {
                this.Mat[i][j] = cur;
                cur *= a;
            }
            this.Mat[i][n + 2] = b;
        }

        this.ReducedEchelonForm();
        for (int i = 1; i <= this.baris; i++) {
            if (this.Mat[i][this.kolom] != 0) {
                boolean notValid = true;
                for (int j = 1; j <= this.kolom - 1; j++) {
                    if (this.Mat[i][j] != 0) {
                        notValid = false;
                        break;
                    }
                }
                if (notValid) {
                    System.out.println("Tidak terdapat polinomial yang sesuai");
                    return;
                }
            }
        }

        String hasilOutput = "";
        System.out.println("Polinomial yang dihasilkan adalah : ");
        boolean operand = false;
        for (int i = 1; i <= this.baris; i++) {
            Double cur = this.Mat[i][this.kolom];
            if (cur < 0) {
                if (i > 1 && operand) {
                    System.out.print(" - ");
                    hasilOutput += " - ";
                    cur = -cur;
                }
            } else if (cur == 0) {
                continue;
            } else {
                if (i > 1 && operand) {
                    System.out.print(" + ");
                    hasilOutput += " + ";
                }
            }
            System.out.printf("%.3f", cur);
            hasilOutput += Double.toString(cur);
            if (i > 1) {
                System.out.print("x^(" + (i - 1) + ")");
                hasilOutput += "x^(" + Integer.toString(i - 1) + ")";
            }
            if (!operand) operand = true;
        }
        System.out.println();
        hasilOutput += "\n";

        System.out.print("Masukkan nilai x untuk menaksir nilai fungsi : ");
        double x = in.nextDouble();
        double hasil = this.hasilInterpolasi(n, x);
        System.out.println("Hasil dari interpolasinya adalah : " + hasil);

        hasilOutput += "Hasil dari interpolasi " + Double.toString(x) + " adalah : " + Double.toString(hasil);

        while (true) {
            System.out.println();
            System.out.println("Simpan hasil ke dalam suatu file txt ?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            System.out.print("Pilih nomor jawaban : ");
            int number = in.nextInt();
            if (number == 1) {
                this.TulisFile(3, -1, hasilOutput);
            } else if (number != 2) {
                System.out.println("Masukkan harus antara 1 - 2");
                continue;
            }
            break;
        }
    }

    // Method untuk membaca interpolasi lewat file
    public void bacaInterpolasiFile() throws Exception {
        Scanner in = new Scanner (System.in);
        String namaFile = in.nextLine();
        namaFile = "../test/" + namaFile + ".txt";
        FileReader fr = new FileReader(namaFile);
        String str = "";
        int cc;
        while ((cc = fr.read()) != -1) {
            str += (char) cc;
        }
        str = str.trim();
        str += '\n';
        int n = -1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\n') n++;
        }
        this.baris = n + 1;
        this.kolom = n + 2;
        int x = 1;
        for (int i = 0; i < str.length(); i++) {
            String c1 = "", c2 = "";
            while (str.charAt(i) != ' ') {
                c1 += str.charAt(i);
                i++;
            }
            while (str.charAt(i) == ' ') i++;
            while (str.charAt(i) != '\n') {
                c2 += str.charAt(i);
                i++;
            }
            Double cur = 1.0;
            Double a = Double.parseDouble(c1);
            Double b = Double.parseDouble(c2);
            for (int j = 1; j <= n + 1; j++) {
                this.Mat[x][j] = cur;
                cur *= a;
            }
            this.Mat[x][n + 2] = b;
            x++;
        }

        this.ReducedEchelonForm();
        for (int i = 1; i <= this.baris; i++) {
            if (this.Mat[i][this.kolom] != 0) {
                boolean notValid = true;
                for (int j = 1; j <= this.kolom - 1; j++) {
                    if (this.Mat[i][j] != 0) {
                        notValid = false;
                        break;
                    }
                }
                if (notValid) {
                    System.out.println("Tidak terdapat polinomial yang sesuai");
                    return;
                }
            }
        }

        String hasilOutput = "";
        System.out.println("Polinomial yang dihasilkan adalah : ");
        boolean operand = false;
        for (int i = 1; i <= this.baris; i++) {
            Double cur = this.Mat[i][this.kolom];
            if (cur < 0) {
                if (i > 1 && operand) {
                    System.out.print(" - ");
                    hasilOutput += " - ";
                    cur = -cur;
                }
            } else if (cur == 0) {
                continue;
            } else {
                if (i > 1 && operand) {
                    System.out.print(" + ");
                    hasilOutput += " + ";
                }
            }
            System.out.printf("%.3f", cur);
            hasilOutput += Double.toString(cur);
            if (i > 1) {
                System.out.print("x^(" + (i - 1) + ")");
                hasilOutput += "x^(" + Integer.toString(i - 1) + ")";
            }
            if (!operand) operand = true;
        }
        System.out.println();
        hasilOutput += "\n";

        System.out.print("Masukkan nilai x untuk menaksir nilai fungsi : ");
        double xx = in.nextDouble();
        double hasil = this.hasilInterpolasi(n, xx);
        System.out.printf("Hasil dari interpolasinya adalah : %.0f",hasil);
        hasilOutput += "Hasil dari interpolasi nilai " + Double.toString(xx) + " adalah :\n";
        hasilOutput += Double.toString(hasil);
        while (true) {
            System.out.println();
            System.out.println("Simpan hasil ke dalam suatu file txt ?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            System.out.print("Pilih nomor jawaban : ");
            int number = in.nextInt();
            if (number == 1) {
                this.TulisFile(3, -1, hasilOutput);
            } else if (number != 2) {
                System.out.println("Masukkan harus antara 1 - 2");
                continue;
            }
            break;
        }
    }

    // Fungsi untuk mengeluarkan hasil transpos matriks
    public matriks Transpose() {

        matriks transposeM = new matriks();
        transposeM.baris = this.kolom;
        transposeM.kolom = this.baris;

        for(int i = 1; i <= this.kolom ; i++) {
            for(int j=1; j <= this.baris ; j++) {
                transposeM.Mat[i][j] = this.Mat[j][i];
            }
        }

        return transposeM;

    }

    //Method untuk menukar baris a dan b
    public void TukerBaris(int a, int b) {
        for (int i=1;i<=this.kolom;i++) {
            this.Mat[0][i] = this.Mat[a][i];
            this.Mat[a][i] = this.Mat[b][i];
            this.Mat[b][i] = this.Mat[0][i];
        }
    }


    //Method untuk mengalikan baris a sebesar x kali
    public void KaliBaris(int a, double x) {
        for (int i=1;i<=this.kolom;i++) {
            this.Mat[a][i] = this.Mat[a][i] * x;
        }
    }

    //Method untuk menambahkan baris a sejumlah baris b dikali x
    public void TambahBaris(int a, int b, double x) {
        for (int i=1;i<=this.kolom;i++) {
            this.Mat[a][i] = this.Mat[a][i] + this.Mat[b][i] * x;
        }
    }

    //Fungsi yang mengembalikan indeks angka 1 paling kiri dari baris a
    public int LeftestOne(int a) {
        for (int i=1;i<this.kolom;i++) {
            if (Math.abs(this.Mat[a][i]-1) <= 0.0001) return i;
        }
        return -1;
    }
    public int LeftestOneKoef(int a) {
        for (int i=1;i<=this.kolom;i++) {
            if (Math.abs(this.Mat[a][i]-1) <= 0.0001) return i;
        }
        return -1;
    }

    //Method untuk mengubah matriks hasil OBE (sampai Echelon Form)
    public void EchelonForm() {
        int geser = 0;
        for (int i=1;i<=this.baris;i++) {
            if (this.Mat[i][i + geser] == 0) {
                boolean tuker = false;
                for (int j=i+1;j<=this.baris;j++) {
                    if (this.Mat[j][i] != 0) {
                        this.TukerBaris(i, j);
                        tuker = true;
                        break;
                    }
                }
                if (tuker == false) {
                    geser++;
                    i--;
                    continue;
                }
            }
            this.KaliBaris(i, 1/this.Mat[i][i + geser]);
            for (int j=i+1;j<=this.baris;j++) {
                this.TambahBaris(j, i, -1 * this.Mat[j][i + geser] / this.Mat[i][i + geser]);
            }
        }
        for (int i=1;i<=this.baris;i++) {
            int p=0;
            for (int j=1;j<=this.kolom;j++) {
                if (this.Mat[i][j] != 0) {
                    p = j;
                    break;
                }
            }
            if (p == this.kolom-1) {
                this.KaliBaris(i, 1/this.Mat[i][p]);
            }
        }
    }

    //Method untuk mengubah matriks menjadi reduced echelon form
    public void ReducedEchelonForm() {
        this.EchelonForm();
        for (int i=this.baris;i>=1;i--) {
            int palingkiri = this.LeftestOne(i);
            if (palingkiri == -1) continue;
            for (int j=i-1;j>=1;j--) {
                this.TambahBaris(j, i, -1 * this.Mat[j][palingkiri]);
            }
        }
    }

    public matriks copy() {
        matriks ret = new matriks();
        ret.baris = this.baris;
        ret.kolom = this.kolom;
        for (int i = 1; i <= this.baris; i++) {
            for (int j = 1; j <= this.kolom; j++) {
                ret.Mat[i][j] = this.Mat[i][j];
            }
        }
        return ret;
    }

    //Fungsi yang mengembalikan nilai determinan sebuah matriks persegi
    public double Determinant() {
        double ret = 1;
        matriks M2 = new matriks();
        M2.baris = this.baris;
        M2.kolom = this.kolom;
        for(int i=1; i<=this.baris; i++){
            for(int j=1; j<=this.kolom; j++){
                M2.Mat[i][j] = this.Mat[i][j];
            }
        }

        for (int i=1;i<=M2.baris;i++) {
            if (M2.Mat[i][i] == 0) {
                for (int j=i+1;j<=M2.baris;j++) {
                    if (M2.Mat[j][i] != 0) {
                        M2.TukerBaris(i, j);
                        ret *= -1;
                        break;
                    }
                }
            }
            if (M2.Mat[i][i] == 0) continue;
            ret *= M2.Mat[i][i];
            M2.KaliBaris(i, 1/M2.Mat[i][i]);
            for (int j=i+1;j<=M2.baris;j++) {
                M2.TambahBaris(j, i, -1 * M2.Mat[j][i] / M2.Mat[i][i]);
            }
        }
        for (int i=1;i<=M2.baris;i++) {
            ret *= M2.Mat[i][i];
        }
        return ret;
    }

    //Method untuk merubah matriks menjadi balikannya
    public void Inverse(){

        if (this.Determinant() == 0) {
            System.out.println("Matriks ini tidak memiliki invers.");
            return;
        }

        matriks M2 = new matriks();
        M2.baris = this.baris;
        M2.kolom = this.kolom;
        for(int i=1; i<=this.baris; i++){
            for(int j=1; j<=this.kolom; j++){
                if(i==j) M2.Mat[i][j] = 1;
                else M2.Mat[i][j] = 0;
            }
        }
        int geser = 0;
        double tmp;
        for (int i=1;i<=this.baris;i++) {
            if (this.Mat[i][i + geser] == 0) {
                boolean tuker = false;
                for (int j=i+1;j<=this.baris;j++) {
                    if (this.Mat[j][i] != 0) {
                        this.TukerBaris(i, j);
                        M2.TukerBaris(i, j);
                        tuker = true;
                        break;
                    }
                }
                if (tuker == false) {
                    geser++;
                    i--;
                    continue;
                }
            }
            tmp = 1/this.Mat[i][i + geser];
            this.KaliBaris(i, tmp);
            M2.KaliBaris(i, tmp);
            for (int j=i+1;j<=this.baris;j++) {
                tmp = -1 * this.Mat[j][i + geser] / this.Mat[i][i + geser];
                this.TambahBaris(j, i, tmp);
                M2.TambahBaris(j, i, tmp);
            }
        }
        for (int i=this.baris;i>=1;i--) {
            int palingkiri = this.LeftestOneKoef(i);
            if (palingkiri == -1) continue;
            for (int j=i-1;j>=1;j--) {
                tmp = -1 * this.Mat[j][palingkiri];
                this.TambahBaris(j, i, tmp);
                M2.TambahBaris(j, i, tmp);
            }
        }
        for(int i=1; i<=this.baris; i++){
            for(int j=1; j<=this.kolom; j++){
                this.Mat[i][j] = M2.Mat[i][j];
            }
        }
    }


    //Fungsi untuk menghasilkan matriks persegi dari suatu augmented matriks
    //yang diubah kolom ke-a nya dengan kolom paling kanan (digunakan untuk Cramer)
        public matriks MatrixKolom(int a) {
        matriks ret = new matriks();
        ret.baris = this.baris;
        ret.kolom = this.kolom - 1;
        for (int i = 1; i <= ret.baris; i++) {
            for (int j = 1; j <= ret.kolom; j++) {
                if (j == a) {
                    ret.Mat[i][j] = this.Mat[i][this.kolom];
                } else {
                    ret.Mat[i][j] = this.Mat[i][j];
                }
            }
        }
        return ret;
    }

    //Method untuk melakukan kaidah cramer
    //I.S : Augmented Matrix
    public void Cramer() {
        matriks persegi = new matriks();
        persegi.baris = this.baris;
        persegi.kolom = this.kolom - 1;

        for (int i = 1; i <= persegi.baris; i++) {
            for (int j = 1; j <= persegi.kolom; j++) {
                persegi.Mat[i][j] = this.Mat[i][j];
            }
        }

        Double detPersegi = persegi.Determinant();

        if (detPersegi == 0 || detPersegi.isNaN()) {
            System.out.println("Tidak terdapat solusi dari metode cramer");
            return;
        }

        String hasilSolusi = "";
        for (int i = 1; i <= persegi.kolom; i++) {
            matriks persegiKeI = MatrixKolom(i);
            Double curDet = persegiKeI.Determinant();
            System.out.print("x" + i + " = ");
            hasilSolusi += "x" + Integer.toString(i) + " = ";
            double cur = curDet / detPersegi;
            System.out.printf("%.3f\n", cur);
            hasilSolusi += Double.toString(cur);
        }
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Simpan hasil ke dalam suatu file txt ?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            System.out.print("Pilih nomor jawaban : ");
            int number = in.nextInt();
            if (number == 1) {
                this.TulisFile(3, -1, hasilSolusi);
            } else if (number != 2) {
                System.out.println("Masukkan harus antara 1 - 2");
                continue;
            }
            break;
        }
    }

    //Fungsi untuk mengembalikan determinan matriks persegi yang akan digunakan untuk kofaktor
    public double detMatriksEx(int a, int b) {
        matriks ret = new matriks();
        ret.baris = this.baris - 1;
        ret.kolom = this.kolom - 1;
        int x = 1, y = 1;
        for (int i = 1; i <= this.baris; i++) {
            if (i == a) continue;
            for (int j = 1; j <= this.kolom; j++) {
                if (j == b) continue;
                ret.Mat[x][y++] = this.Mat[i][j];
            }
            y = 1;
            x++;
        }
        return ret.Determinant();
    }

    //Fungsi untuk menhasilkan matriks kofaktor
    public matriks buatKofaktor() {
        matriks ret = new matriks();
        ret.baris = this.baris;
        ret.kolom = this.kolom;
        for (int i = 1; i <= ret.baris; i++) {
            for (int j = 1; j <= ret.kolom; j++) {
                Double cur = 1.0;
                if ((i + j) % 2 == 1) cur *= -1;
                ret.Mat[i][j] = cur * detMatriksEx(i, j);
                if (ret.Mat[i][j] != ret.Mat[i][j]) ret.Mat[i][j] = 0;
            }
        }
        return ret;
    }

    //fungsi untuk menghasilkan matriks adjoin
    public matriks buatAdjoin() {
        matriks ret = this.buatKofaktor();
        return ret.Transpose();
    }

    // fungsi untuk mengalikan 2 matriks
    public matriks KaliMatriks(matriks M1, matriks M2) {
        matriks ret = new matriks();
        ret.baris = M1.baris;
        ret.kolom = M2.kolom;
        for (int i=1;i<=M1.baris;i++) {
            for (int j=1;j<=M2.kolom;j++) {
                ret.Mat[i][j] = 0;
                for (int k=1;k<=M1.kolom;k++) {
                    ret.Mat[i][j] += M1.Mat[i][k] * M2.Mat[k][j];
                }
            }
        }
        return ret;
    }

    // fungsi untuk mencari solusi dengan cara matriks balikan
    public matriks CaraBalikan() {
        matriks MA = new matriks();
        matriks MB = new matriks();
        MA.baris = this.baris;
        MA.kolom = this.kolom - 1;
        MB.baris = this.baris;
        MB.kolom = 1;
        for (int i=1;i<=MA.baris;i++) {
            for (int j=1;j<=MA.kolom;j++) {
                MA.Mat[i][j] = this.Mat[i][j];
            }
        }
        for (int i=1;i<=MB.baris;i++) {
            MB.Mat[i][1] = this.Mat[i][this.kolom];
        }
        matriks Sol = new matriks();
        matriks MAInv = new matriks();
        if ((MAInv.Determinant() - 0.0) <= 0.0000001) {
            System.out.println("Matriks koefisien tidak memiliki inverse");
            return Sol;
        } else {
            MAInv = MA;
            MAInv.Inverse();
            System.out.println("\nInverse dari matriks koefisien:");
            MAInv.TulisMatriks();
            Sol = KaliMatriks(MAInv, MB);
            return Sol;
        }
    }

    public void tulisMatriksSolusi() {
        for (int i = 1; i <= this.baris; i++) {
            System.out.print("x" + i + " = ");
            System.out.printf("%.3f\n", this.Mat[i][1]);
        }
    }

    public void BuatMatriksSolusi() {
        this.ReducedEchelonForm();
        Integer [] piv = new Integer[100];
        Integer [][] id = new Integer[100][100];
        Double [][] mul = new Double[100][100];
        int NoSolution = 0;
        for (int i=1;i<=this.baris;i++) {
            int pivot = LeftestOne(i), cnt=0;
            piv[i] = pivot;
            if (pivot == -1) {
                if (Math.abs(this.Mat[i][this.kolom] - 0.0) <= 0.000001) {
                    continue;
                } else {
                    NoSolution = 1;
                    break;
                }
            } else {
                for (int j=pivot+1;j<this.kolom;j++) {
                    if (this.Mat[i][j] != 0) {
                        cnt++;
                        id[i][cnt] = j;
                        mul[i][cnt] = -1 * this.Mat[i][j];
                    }
                }
                id[i][0] = cnt;
                mul[i][0] = this.Mat[i][this.kolom];
            }
        }
        if (NoSolution == 1) System.out.println("Tidak ada solusi");
        else {
            Double [] sol = new Double[100];
            for (int i=0;i<=99;i++) sol[i] = (double) -123456;
            System.out.println("\nDidapat Solusi :");
            String hasilSolusi = "";
            for (int i=1;i<=this.baris;i++) {
                if (piv[i] == -1) continue;
                System.out.printf("x%d = %.3f", piv[i], mul[i][0]);
                sol[piv[i]] = mul[i][0];
                hasilSolusi += "x" + Integer.toString(piv[i]) + " = " + Double.toString(mul[i][0]);
                for (int j=1;j<=id[i][0];j++) {
                    if (mul[i][j] > 0) {
                        System.out.print(" + ");
                        hasilSolusi += " + ";
                    } else {
                        System.out.print(" - ");
                        hasilSolusi += " - ";
                        mul[i][j] *= -1;
                    }
                    System.out.printf("%.3fx%d", mul[i][j], id[i][j]);
                    hasilSolusi += Double.toString(mul[i][j]) + "x" + Integer.toString(id[i][j]);
                }
                System.out.printf("\n");
                hasilSolusi += "\n";
            }
            boolean nosol = false;
            for (int i=1;i<this.kolom;i++) {
                if (sol[i] == (double) -123456) {
                    nosol = true;
                    System.out.printf("x%d ",i);
                    hasilSolusi += "x" + Integer.toString(i) + " ";
                }
            }
            if (nosol == true) {
                System.out.printf("variabel bebas.\n");
                hasilSolusi += "variabel bebas.\n";
            }
            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.println();
                System.out.println("Simpan hasil ke dalam suatu file txt ?");
                System.out.println("1. Ya");
                System.out.println("2. Tidak");
                System.out.print("Pilih nomor jawaban : ");
                int number = in.nextInt();
                if (number == 1) {
                    this.TulisFile(3, -1, hasilSolusi);
                } else if (number != 2) {
                    System.out.println("Masukkan harus antara 1 - 2");
                    continue;
                }
                break;
            }
        }
    }

    public matriks caraBalikanAdjoin() {
        // I. S : Determinan matriks tidak 0
        matriks ret = new matriks();
        ret = this.copy();
        Double kdet = 1 / this.Determinant();
        ret = ret.buatAdjoin();
        for (int i = 1; i <= ret.baris; i++) {
            for (int j = 1; j <= ret.kolom; j++) {
                ret.Mat[i][j] *= kdet;
            }
        }
        return ret;
    }
}

// javac *.java && java MainProg
