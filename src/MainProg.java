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
        int menu = -1;
        while (menu != 7) {
            System.out.println();
            System.out.println("---- MENU ----");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks balikan");
            System.out.println("4. Matriks kofaktor");
            System.out.println("5. Adjoin");
            System.out.println("6. Interpolasi Polinom");
            System.out.println("7. Keluar\n");
            System.out.print("Masukkan pilihan: ");
            menu = in.nextInt();
            if (menu == 1) {
                System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
                int file = in.nextInt();
                if (file == 1) {
                    System.out.print("Masukkan Nama File : ");
                    try {
                        M.BacaFileMatriks();
                        M.TukerBaris(1,3);
                        M.TulisMatriks();
                        M.KaliBaris(1,3);
                        M.TulisMatriks();
                        M.TambahBaris(1,3,4);
                        M.TulisMatriks();
                    } catch(Exception e) {
                        System.out.println("Terjadi kesalahan dalam nama file");
                    }
                } else {
                    System.out.println("keyboard");
                    M.BacaMatriks();
                    M.TulisMatriks();
                }
            } else if (menu == 2) {
                System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
                int file = in.nextInt();
                if (file == 1) {
                    System.out.print("Masukkan Nama File : ");
                } else {
                    System.out.println("keyboard");
                    //BacaMatriks(M);
                }
            } else if (menu == 3) {
                System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
                int file = in.nextInt();
                if (file == 1) {
                    System.out.print("Masukkan Nama File : ");
                } else {
                    System.out.println("keyboard");
                    //BacaMatriks(M);
                }
            } else if (menu == 4) {
                System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
                int file = in.nextInt();
                if (file == 1) {
                    System.out.print("Masukkan Nama File : ");
                } else {
                    System.out.println("keyboard");
                    //BacaMatriks(M);
                }
            } else if (menu == 5) {
                System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
                int file = in.nextInt();
                if (file == 1) {
                    System.out.print("Masukkan Nama File : ");
                } else {
                    System.out.println("keyboard");
                    //BacaMatriks(M);
                }
            } else if (menu == 6) {
                System.out.print("Baca pake file?\n0 = tidak\n1 = ya\n");
                int file = in.nextInt();
                if (file == 1) {
                    System.out.print("Masukkan Nama File : ");
                    try {
                        M.bacaInterpolasiFile();
                        M.TulisMatriks();
                    } catch(Exception e) {
                        System.out.println("Terjadi kesalahan dalam nama file");
                    }
                } else {
                    System.out.println("keyboard");
                    M.bacaInterpolasi();
                    M.TulisMatriks();
                }
            } else if (menu == 7) {
                System.exit(0);
            } else {
                System.out.println("Masukan salah!");
            }
        }
    }
}
