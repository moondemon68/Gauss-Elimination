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
                subMenu(M, 1);
            } else if (menu == 3) { // Matriks balikan
                inputMatriks(M, 2);
                InverseMatriks(M);
            } else if (menu == 4) { // Matriks kofaktor
                inputMatriks(M, 2);
                tulisKofaktor(M);
            } else if (menu == 5) { // Adjoin
                inputMatriks(M, 2);
                tulisAdjoin(M);
            } else if (menu == 6) { // Interpolasi Polinom
                inputInterpolasi(M);
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
        System.out.print("Masukkan pilihan nomor menu : ");
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
            matriks eliminasiGauss = M.copy();
            eliminasiGauss.EchelonForm();
            System.out.println();
            System.out.println("Hasil dari Eliminasi Gauss adalah ");
            eliminasiGauss.TulisMatriks();
            if (x == 1) {
                System.out.println("Determinan dari matriksnya adalah " + M.Determinant());
                simpanHasil(M, M.Determinant(), 2);
            } else {
                eliminasiGauss.BuatMatriksSolusi();
            }
        } else if (type == 2) {
            matriks eliminasiGaussJordan = M.copy();
            eliminasiGaussJordan.ReducedEchelonForm();
            System.out.println();
            System.out.println("Hasil dari Eliminasi Gauss-Jordan adalah");
            eliminasiGaussJordan.TulisMatriks();
            if (x == 1) {
                System.out.println("Determinan dari matriksnya adalah " + M.Determinant());
                simpanHasil(M, M.Determinant(), 2);
            } else {
                eliminasiGaussJordan.BuatMatriksSolusi();
            }
        } else if (type == 3) {
            boolean ok = true;
            if (M.Determinant() == 0) {
                System.out.println("Tidak dapat melakukan metode balikkan karena matriks ini tidak memiliki invers");
                ok = false;
            }
            if (ok) {
                matriks matriksBalikan = M.copy();

                if (M.baris != M.kolom - 1) {
                    System.out.println("Baris dan kolom matriks koefisien tidak sama.");
                } else {
                    matriksBalikan = matriksBalikan.CaraBalikan();
                    if (matriksBalikan.baris != 0) {
                        System.out.println("\nHasil dari Cara Balikan adalah:");
                        matriksBalikan.tulisMatriksSolusi();
                    }
                }
            }
        } else if (type == 4) {
            if (M.baris != M.kolom - 1) {
                System.out.println("Baris dan kolom matriks koefisien tidak sama.");
            } else {
                System.out.println("\nHasil dari Metode Cramer adalah : ");
                M.Cramer();
            }
        }
        while (true) {
            System.out.println();
            System.out.println("Coba metode lain ?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            System.out.print("Pilih nomor jawaban : ");
            Scanner in = new Scanner (System.in);
            int number = in.nextInt();
            if (number == 1) {
                subMenu(M, x);
            } else if (number != 2) {
                System.out.println("Masukkan harus antara 1 - 2");
                continue;
            }
            break;
        }
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
                while (true) {
                    if (t == 1) System.out.println("\n(Matriks yang terbaca adalah Augmented Matriks)");
                    else System.out.println("\n(Matriks yang terbaca adalah Coefficient Matriks)");
                    System.out.print("Masukkan Nama File : ");
                    try {
                        M.BacaFileMatriks();
                        System.out.println("\nMatriks yang terbaca adalah : ");
                        M.TulisMatriks();
                    } catch(Exception e) {
                        System.out.println("Terjadi kesalahan dalam nama atau file matriks");
                        break;
                    }
                    break;
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
                System.out.println("Matriks harus merupakan matriks persegi.");
                continue;
            }
            break;
        }
    }

    public static void InverseMatriks(matriks M) {
        if (M.Determinant() == 0) {
            System.out.println("Matriks tidak memiliki invers karena memiliki determinan = 0");
            return;
        }
        Scanner in = new Scanner (System.in);
        matriks ret = M.copy();
        while (true) {
            System.out.println("\nPilih Metode dalam membalikkan matriks :");
            System.out.println("1. Metode Gauss-Jordan");
            System.out.println("2. Metode Matriks Adjoin");
            System.out.print("Masukkan nomor pilihan metode : ");
            int type = in.nextInt();
            if (type == 1) {
                System.out.println("Hasil matriks balikkan dengan metode gauss-jordan adalah : ");
                ret.Inverse();
                ret.TulisMatriks();
                simpanHasil(ret, -1, 1);
            } else if (type == 2) {
                ret = ret.caraBalikanAdjoin();
                System.out.println("Hasil matriks balikkan dengan metode adjoin adalah : ");
                ret.TulisMatriks();
                simpanHasil(ret, -1, 1);
            } else {
                System.out.println("nomor pilihan harus diantara 1 atau 2");
                continue;
            }
            break;
        }
        while (true) {
            System.out.println();
            System.out.println("Coba metode lain ?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            System.out.print("Pilih nomor jawaban : ");
            int number = in.nextInt();
            if (number == 1) {
                InverseMatriks(M);
            } else if (number != 2) {
                System.out.println("Masukkan harus antara 1 - 2");
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
                while (true) {
                    System.out.print("Masukkan Nama File : ");
                    try {
                        M.bacaInterpolasiFile();
                    } catch(Exception e) {
                        System.out.println("Terjadi kesalahan dalam nama atau file interpolasi");
                        break;
                    }
                    break;
                }
            } else if (file == 2) {
                M.bacaInterpolasi();
            } else {
                System.out.println("Masukkan harus diantara 1 atau 2");
                continue;
            }
            break;
        }

    }

    public static void tulisBalikkan(matriks M) {
        if (M.Determinant() == 0) {
            System.out.println("\nMatriks tidak mempunyai balikan");
            return;
        }
        System.out.println("\nHasil matriks balikannya adalah :");
        matriks hasilInverse = M;
        hasilInverse.Inverse();
        hasilInverse.TulisMatriks();
    }

    public static void tulisKofaktor(matriks M) {
        matriks hasilKofaktor = M.buatKofaktor();
        System.out.println("\nHasil matriks kofaktornya adalah :");
        hasilKofaktor.TulisMatriks();
        simpanHasil(hasilKofaktor, 0, 1);
    }

    public static void tulisAdjoin(matriks M) {
        matriks hasilAdjoin = M.buatAdjoin();
        System.out.println("\nHasil matriks adjoinnya adalah :");
        hasilAdjoin.TulisMatriks();
        simpanHasil(hasilAdjoin, 0, 1);
    }

    public static void simpanHasil(matriks M, double hasil, int type) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Simpan hasil ke dalam suatu file txt ?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            System.out.print("Pilih nomor jawaban : ");
            int number = in.nextInt();
            if (number == 1) {
                M.TulisFile(type, hasil, "#");
            } else if (number != 2) {
                System.out.println("Masukkan harus antara 1 - 2");
                continue;
            }
            break;
        }
    }
}
