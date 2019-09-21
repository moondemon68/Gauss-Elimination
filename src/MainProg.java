import java.util.*;

class MainProg {



    public static void main(String[] args) {

        start();

        Scanner in = new Scanner (System.in);
        matriks M = new matriks();
        int menu = -1;

        while (menu != 7) {
            tampilMenu();
            menu = in.nextInt();
            if (menu == 1) { // Sistem Persamaan Linier
                subMenu(0);
                inputMatriks(M, 1);
            } else if (menu == 2) { // Determinan
                subMenu(1);
                inputMatriks(M, 2);
            } else if (menu == 3) { // Matriks balikan
                subMenu(1);
                inputMatriks(M, 2);
            } else if (menu == 4) { // Matriks kofaktor
                subMenu(1);
                inputMatriks(M, 2);
            } else if (menu == 5) { // Adjoin
                subMenu(1);
                inputMatriks(M, 2);
            } else if (menu == 6) { // Interpolasi Polinom
                inputInterpolasi(M);
            } else if (menu == 7) { // Keluar
                System.out.println("Terima kasih sudah menggunakan program ini :)");
                System.exit(0);
            } else {
                System.out.println("Masukkan harus di antara 1 - 7");
            }
        }
    }

    public static void start() {
        System.out.println();
        System.out.println("Selamat datang di Program Sistem Persamaan Linier, Determinan, dan Aplikasinya!");
        System.out.println("Created by : HMF Algeorithm");
        System.out.println();
        System.out.println("Silahkan memulai program dengan memilih menu!");
    }

    public static void tampilMenu() {
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
    }

    public static void subMenu(int x) {
        System.out.println();
        System.out.println("---- Silahkan pilih metode penyelesaian ----");
        System.out.println("1. Metode Eliminiasi Gauss");
        System.out.println("2. Metode Eliminiasi Gauss-Jordan");
        if (x == 1) return;
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Kaidah Cramer");
    }

    public static void inputMatriks(matriks M, int t) {
        // type 1 artinya persegi panjang
        // type 2 artinya persegi
        while (true) {
            Scanner in = new Scanner (System.in);
            System.out.println();
            System.out.println("Pilih metode dalam menginput matriks :");
            System.out.println("1. Baca File");
            System.out.println("2. Keyboard");
            System.out.print("Masukkan nomor metode menginput matriks : ");
            int file = in.nextInt();
            if (file == 1) {
                System.out.print("Masukkan Nama File : ");
                try {
                    M.BacaFileMatriks();
                    M.TulisMatriks();
                } catch(Exception e) {
                    System.out.println(e);
                }
            } else if (file == 2) {
                System.out.println("keyboard");
                if (t == 1) M.BacaMatriks();
                else if (t == 2) M.BacaMatriksPersegi();
                M.TulisMatriks();
            } else {
                System.out.println("Masukkan harus diantara 1 atau 2");
                continue;
            }
            if (t == 2 && M.baris != M.kolom) {
                System.out.println("Matriks harus merupakan matriks persegi (kolom = baris).");
                continue;
            }
            break;
        }
    }

    public static void inputInterpolasi(matriks M) {
        while (true) {
            System.out.println();
            System.out.println("Pilih metode dalam menginput Interpolasi Polinom :");
            System.out.println("1. Baca File");
            System.out.println("2. Keyboard");
            System.out.print("Masukkan nomor metode menginput Interpolasi Polinom : ");
            Scanner in = new Scanner (System.in);
            int file = in.nextInt();

            if (file == 1) {
                System.out.print("Masukkan Nama File : ");
                try {
                    M.bacaInterpolasiFile();
                    M.TulisMatriks();
                } catch(Exception e) {
                    System.out.println(e);
                }
            } else if (file == 2) {
                M.bacaInterpolasi();
                M.TulisMatriks();
            } else {
                System.out.println("Masukkan harus diantara 1 atau 2");
            }
        }
    }
}
