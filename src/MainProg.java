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
                inputMatriks(M, 1);
                subMenu(M, 0);
            } else if (menu == 2) { // Determinan
                inputMatriks(M, 2);
                subMenu(M, 0);
            } else if (menu == 3) { // Matriks balikan
                inputMatriks(M, 2);
                subMenu(M, 1);
            } else if (menu == 4) { // Matriks kofaktor
                inputMatriks(M, 2);
                subMenu(M, 1);
            } else if (menu == 5) { // Adjoin
                inputMatriks(M, 2);
                subMenu(M, 1);
            } else if (menu == 6) { // Interpolasi Polinom
                inputInterpolasi(M);
                System.out.printf("Matriks interpolasi:\n");
                M.TulisMatriks();

            } else if (menu == 7) { // Keluar
                System.out.println();
                System.out.println("Terima kasih sudah menggunakan program ini :)");
                System.exit(0);
            } else {
                System.out.println("Masukan harus di antara 1 - 7");
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

    public static void subMenu(matriks M, int x) {
        // x = 1 untuk matriks koefisien yang berbentuk persegi
        int type;
        while (true) {
            System.out.println();
            System.out.println("---- Silahkan pilih metode penyelesaian ----");
            System.out.println("1. Metode Eliminasi Gauss");
            System.out.println("2. Metode Eliminasi Gauss-Jordan");
            if (x != 1) {
                System.out.println("3. Metode Matriks Balikan");
                System.out.println("4. Kaidah Cramer");
            }
            System.out.print("Pilih nomor metode penyelesaian : ");
            Scanner in = new Scanner (System.in);
            type = in.nextInt();
            if ((type > 2 || type < 1) && x == 1) {
                System.out.println("Masukkan harus di antara 1 - 2");
                continue;
            } else if (type > 4 || type < 1) {
                System.out.println("Masukkan harus di antara 1 - 4");
                continue;
            }
            break;
        }
        if (type == 1) {
            matriks eliminasiGauss = M;
            eliminasiGauss.EchelonForm();
            System.out.println();
            System.out.println("Hasil dari Eliminasi Gauss adalah ");
            eliminasiGauss.TulisMatriks();
        } else if (type == 2) {
            matriks eliminasiGaussJordan = M;
            eliminasiGaussJordan.ReducedEchelonForm();
            System.out.println();
            System.out.println("Hasil dari Eliminasi Gauss-Jordan adalah");
            eliminasiGaussJordan.TulisMatriks();
        } else if (type == 3) {
            if (M.Determinant() == 0) {
                System.out.println("Tidak dapat melakukan metode balikkan karena matriks ini tidak memiliki invers");
                return;
            }
            matriks matriksBalikan = M;
            if (M.baris != M.kolom-1) {
                System.out.println("Baris dan kolom matriks koefisien tidak sama.");
            } else {
                matriksBalikan = matriksBalikan.CaraBalikan();
                if (matriksBalikan.baris != 0) {
                    System.out.println("Hasil dari Cara Balikan adalah:");
                    matriksBalikan.TulisMatriks();
                }
            }
        } else if (type == 4) {
            System.out.println("Hasil dari Metode Cramer adalah");
            M.Cramer();
        }
    }

    public static void inputMatriks(matriks M, int t) {
        // type 1 artinya persegi panjang
        // type 2 artinya persegi
        while (true) {
            Scanner in = new Scanner (System.in);
            System.out.println();
            System.out.println("Pilih metode dalam menginput matriks persegi :");
            System.out.println("1. Baca File");
            System.out.println("2. Keyboard");
            System.out.print("Masukkan nomor metode menginput matriks persegi : ");
            int file = in.nextInt();
            if (file == 1) {
                System.out.println("(Matriks yang terbaca adalah Augmented Matriks)");
                System.out.print("Masukkan Nama File : ");
                try {
                    M.BacaFileMatriks();
                    System.out.println("Matriks yang terbaca adalah : ");
                    M.TulisMatriks();
                } catch(Exception e) {
                    System.out.println(e);
                }
            } else if (file == 2) {
                if (t == 1) {
                    System.out.println();
                    System.out.println("(Masukkan matriks harus berbentuk Augmented)");
                    M.BacaMatriks();
                }
                else if (t == 2) M.BacaMatriksPersegi();
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
                continue;
            }
            break;
        }
    }
}
