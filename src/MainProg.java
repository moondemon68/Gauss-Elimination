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
        System.out.print("Masukin pilihan: ");
        int menu = in.nextInt();
        if (menu == 1) {
            System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("file");
            } else {
                System.out.println("keyboard");
                M.BacaMatriks();
            }
        } else if (menu == 2) {
            System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("file");
            } else {
                System.out.println("keyboard");
                //BacaMatriks(M);
            }
        } else if (menu == 3) {
            System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("file");
            } else {
                System.out.println("keyboard");
                //BacaMatriks(M);
            }
        } else if (menu == 4) {
            System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("file");
            } else {
                System.out.println("keyboard");
                //BacaMatriks(M);
            }
        } else if (menu == 5) {
            System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("file");
            } else {
                System.out.println("keyboard");
                //BacaMatriks(M);
            }
        } else if (menu == 6) {
            System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("file");
            } else {
                System.out.println("keyboard");
                //BacaMatriks(M);
            }
        } else if (menu == 7) {
            System.exit(0);
        }
    }
}