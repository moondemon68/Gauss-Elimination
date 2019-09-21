import java.io.File;
import java.util.*;
import java.io.*;

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
        this.baris=0;
        this.kolom=0;
    }

    //Method untuk baca matriks
    public void BacaMatriks(){

        Scanner in = new Scanner (System.in);

        System.out.print("Masukkan jumlah baris: ");
        this.baris = in.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        this.kolom = in.nextInt();

        for(int i=1; i<=this.baris; i++){
            for(int j=1; j<=this.kolom; j++ ){
                this.Mat[i][j] = in.nextDouble();
            }
        }
    }

    //Method untuk baca matriks persegi
    public void BacaMatriksPersegi(){

        Scanner in = new Scanner (System.in);

        System.out.println("Masukkan jumlah baris dan kolom: ");
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
        namaFile += ".txt";
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
                System.out.printf("%.2f ", this.Mat[i][j] + 0.0);
            }
            System.out.println();
        }

    }

    // Method untuk membaca interpolasi
    public void bacaInterpolasi() {
        Scanner in = new Scanner (System.in);
        int n = in.nextInt();
        this.baris = n + 1;
        this.kolom = n + 2;
        Double a, b;
        for (int i = 1; i <= n + 1; i++) {
            a = in.nextDouble();
            b = in.nextDouble();
            Double cur = 1.0;
            for (int j = 1; j <= n + 1; j++) {
                this.Mat[i][j] = cur;
                cur *= a;
            }
            this.Mat[i][n + 2] = b;
        }
    }

    // Method untuk membaca interpolasi lewat file
    public void bacaInterpolasiFile() throws Exception {
        Scanner in = new Scanner (System.in);
        String namaFile = in.nextLine();
        namaFile += ".txt";
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
        for (int i=1;i<=this.kolom;i++) {
            if (this.Mat[a][i] == 1) return i;
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

        if(this.Determinant()==0) {
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
            int palingkiri = this.LeftestOne(i);
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
            System.out.println("Tidak ada solusi");
            return;
        }

        for (int i = 1; i <= persegi.kolom; i++) {
            matriks persegiKeI = MatrixKolom(i);
            Double curDet = persegiKeI.Determinant();
            System.out.print("Solusi x" + i + " adalah ");
            System.out.printf("%.2f\n", curDet / detPersegi);
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

}

// javac *.java && java MainProg
