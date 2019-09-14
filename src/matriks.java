import java.io.File;
import java.util.*;
import java.io.*;

public class matriks {

    int baris;
    int kolom;
    double [][] Mat = new double[105][105];

    //Definisi & Konstruktor Tipe Data matriks
    matriks() {
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                this.Mat[i][j] = 0.0;
            }
        }
        this.baris=0;
        this.kolom=0;
    }

    //Method untuk baca matriks
    public void BacaMatriks(){

        Scanner in = new Scanner (System.in);

        System.out.println("Masukkan jumlah baris: ");
        this.baris = in.nextInt();
        System.out.println("Masukkan jumlah kolom: ");
        this.kolom = in.nextInt();

        for(int i=1; i<=this.baris; i++){
            for(int j=1; j<=this.kolom+1; j++ ){
                this.Mat[i][j] = in.nextDouble();
            }
        }
    }
    public void BacaMatriksFile(String filename){
        //File file = new File(filename);
        Scanner in = new Scanner (filename);
        this.baris = in.nextInt();
        this.kolom = in.nextInt();
        for(int i=1; i<=this.baris; i++){
            for(int j=1; j<=this.kolom+1; j++){
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
            for(int j=1; j<=this.kolom+1; j++ ){
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
                System.out.printf("%.2f ", this.Mat[i][j]);
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

    //Method untuk mengubah matriks hasil OBE (sampai Echelon Form)
    public void EchelonForm() {
        for (int i=1;i<=this.baris;i++) {
            if (this.Mat[i][i] == 0) {
                for (int j=i+1;j<=this.baris;j++) {
                    if (this.Mat[j][i] != 0) {
                        this.TukerBaris(i, j);
                        break;
                    }
                }
            }
            this.KaliBaris(i, 1/this.Mat[i][i]);
            for (int j=i+1;j<=this.baris;j++) {
                this.TambahBaris(j, i, -1 * this.Mat[j][i] / this.Mat[i][i]);
            }
        }
    }

    //Method untuk mengubah matriks menjadi reduced echelon form
    public void ReducedEchelonForm() {
        this.EchelonForm();
        for (int i=this.baris;i>=1;i--) {
            for (int j=i-1;j>=1;j--) {
                this.TambahBaris(j, i, -1 * this.Mat[j][i]);
            }
        }
    }

    //Fungsi yang mengembalikan nilai determinan sebuah matriks persegi
    public double Determinant() {
        double ret = 1;
        for (int i=1;i<=this.baris;i++) {
            if (this.Mat[i][i] == 0) {
                for (int j=i+1;j<=this.baris;j++) {
                    if (this.Mat[j][i] != 0) {
                        this.TukerBaris(i, j);
                        ret *= -1;
                        break;
                    }
                }
            }
            ret *= this.Mat[i][i];
            this.KaliBaris(i, 1/this.Mat[i][i]);
            for (int j=i+1;j<=this.baris;j++) {
                this.TambahBaris(j, i, -1 * this.Mat[j][i] / this.Mat[i][i]);
            }
        }
        for (int i=1;i<=this.baris;i++) {
            ret *= this.Mat[i][i];
        }
        return ret;
    }
}
// javac *.java && java MainProg
