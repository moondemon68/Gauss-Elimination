import java.util.*;

class MainProg {

    /*
    ----- MENU -----
    1. Sistem Persamaan Linier
    2. Determinan
    3. Matriks balikan
    4. Matriks kofaktor
    5. Adjoin
    6. Interpolasi Polinom
    7. Keluar
    ----- SUBMENU ----
    1. Metode Eliminasi Gauss
    2. Metode Eliminasi Gauss-Jordan
    3. Metode Matriks balikan
    4. Kaidah Crammer
    */

    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        matriks M = new matriks();
        System.out.print("Masukkan pilihan: ");
        int menu = in.nextInt();
        if (menu == 1) { //Sistem Persamaan Linier
            System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("file");
                System.out.print("Nama file: ");
                String filename;
                filename = in.next();
                M.BacaMatriksFile(filename);
                M.TulisMatriks();
            } else {
                System.out.println("keyboard");
                M.BacaMatriks();
                M.TulisMatriks();
            }
        } else if (menu == 2) { //Determinan
            System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("file");
            } else {
                System.out.println("keyboard");
                M.BacaMatriksPersegi();
                M.TulisMatriks();
            }
        } else if (menu == 3) { //Matriks balikan
            System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("file");
            } else {
                System.out.println("keyboard");
                M.BacaMatriksPersegi();
                M.TulisMatriks();
            }
        } else if (menu == 4) { //Matriks kofaktor
            System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("file");
            } else {
                System.out.println("keyboard");
                //BacaMatriks(M);
            }
        } else if (menu == 5) { //Adjoin
            System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("file");
            } else {
                System.out.println("keyboard");
                //BacaMatriks(M);
            }
        } else if (menu == 6) { //Interpolasi Polinom
            System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("file");
            } else {
                System.out.println("keyboard");
                //BacaMatriks(M);
            }
        } else if (menu == 7) { //Keluar
            System.exit(0);
        }
    }
}