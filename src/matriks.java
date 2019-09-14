import java.util.*;

public class matriks {

    double [][] Mat = new double[105][105];

    //Definisi Tipe Data matriks
    matriks() {
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                this.Mat[i][j] = 0.0;
            }
        }
    }

    //Method untuk baca matriks
    public void BacaMatriks(matriks M, int kolom, int baris){
        Scanner s = new Scanner (System.in);
        for(int i=1; i<=baris; i++){
            for(int j=1; j<=kolom; j++ ){
                M.Mat[i][j] = s.nextDouble();
            }
        }
    }


    
}
