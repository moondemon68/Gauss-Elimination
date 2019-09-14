import java.util.*;

public class matriks {

    int baris;
    int kolom;
    double [][] Mat = new double[105][105];

    //Definisi Tipe Data matriks
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
            for(int j=1; j<=this.kolom; j++ ){
                this.Mat[i][j] = in.nextDouble();
            }
        }
    }

    //Method untuk tulis matriks
    public void TulisMatriks(){

        for(int i=1; i<=this.baris; i++){
            for(int j=1; j<=this.kolom; j++){
                System.out.print(this.Mat[i][j] + " ");
            }
            System.out.println();
        }

    }


    
}
