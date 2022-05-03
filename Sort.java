//Tomasz Koczar
//02.05.2022AD
//Rozne metody sortowania
//Przyjmujemy porzadany porzadek za niemalejacy
//* |O| - theta z greki


//Interface 
interface Sort{
    void sort(int[] t);
}


/************************Sortowania proste******************************/

//Sortowanie bąbelkowe 02.05.2022AD
/*
Żłożoność:
Operacjami dominującymi są porównania wykonywane w pętli wewnętrznej.
Pesymistycznie jest ich:
 (n-1) + (n-2) + . . . + 1 = (1+n-1)*(n-1) / 2 = n*(n-1) / 2
Pesymistyczna złożoność czasowa wynosi
 T(n) = 1/2 * n2 – 1/2 * n
 T(n) = 1/2 * n2+ O(1)
 T(n) = |O|(n2) 

*/
class BubbleSort implements Sort{
    
    @Override
    public void sort(int[] t){
        int i,j,temp;                            //Do iteracji oraz swapu
        for(i= 0; i<t.length ; i++){             //Petla zewnetrzna
            for(j = i+1; j< t.length; j++){      //Petla wewntrzna
                if(t[j]<t[i]){                   //Operacja dominujaca
                    temp = t[j];
                    t[j] = t[i];
                    t[i] = temp;
                }
            }

        }

    }
}

//Sortowanie metodą prostego wyboru 02.05.2022AD
/*
Złożoność:
Operacjami dominującymi są porównania wykonywane w pętli wewnętrznej.
Pesymistycznie jest ich:
 (n-1) + (n-2) + . . . + 1 = (1+n-1)*(n-1) / 2 = n*(n-1) / 2
Pesymistyczna złożoność czasowa wynosi
 T(n) = 1/2 * n2 – 1/2 * n
 T(n) = 1/2 * n2+ O(1)
 T(n) = |O|(n2) 
*/
class SelectionSort implements Sort{

    @Override
    public void sort(int[] t){
        int min,i,j,temp;                        //Element najmniejszy, zmienne do iteracjii,swapu
        for(i=0; i < t.length-1; i++){           //Petla zewnetrzna
            min = i;                             //Znajdz najmniejszy
            for(j = i+1; j<t.length; j++){       //Petla wewnetrzna
                if(t[j] < t[min])
                    min = j;
            }
            temp = t[min];                       //Swap
            t[min] = t[i];
            t[i] = temp;
        }
    }
}


//Sortowanie przez wstawianie 02.05.2022AD
/*
Zołożoność:
Operacjami dominującymi są porównania wykowywane w petli while.
Pesymistycznie jest ich:
 1 + 2 + 3 + 4 + ... + n = (n+1)n/2 = (1/2)n2 + (1/2)n
Pesymistyczna zlozonosc wynosi:
 T(n) = (1/2)n2 + (1/2)n
 T(n) = (1/2)n2 + O(1)
 T(n) = |O|(n2)
 */

class InsertionSort implements Sort{

    @Override
    public void sort(int[] t){
        int i,j,temp;                            //Do iterowania
        for(i = 1; i< t.length; i++){
            temp = t[i];                         //"Karta" do wstwienia
            j = i-1;
            while( j>-1 && t[j] > temp){         //Szukamy dla niej miejsca   
                t[j+1] = t[j];                   //Przesuwamy posortowane karty
                j--;
            }   
            t[j+1] = temp;                       //Wstaw karte za pierwsza mniejsza
        }
    }
}

/*****************************Sortowania złożone*****************************/

//Sortowanie przez Scalanie 02.05.2022AD
//Algorytm typu "Dziel i zwycieżaj"
//Wersja standardowa
//Nie dziala w miejscu
//Wersja stabilna

/*
Analiza złożoności:
 Procedura "merge" działa w czasie linowym.
 W trakcie działania algorytmu dzielmy problem na dwa,
 dwa razy mniejsze problemy.
 Tak więc czas działania wynosi:
 T(n) = 2T(n/2) + |O|(n).
 Stosując twierdzenie o rekurencji uniwersalnej:
 a = 2, b = 2, n^(log a z b) = n^(log 2 z 2) = n^1.
 Zatem f(n) = |O|(n) = |O|(n ^ (log a z b)), więc:
 T(n) = |O|(n * lg n)
*/

class MergeSort implements Sort{

    /*Scala t[p...q] oraz t[q+1...r] 
     t - tablica
     p - poczatkowy indeks
     q - graniczny indeks
     r - konicowy indeks
    */

    private void merge(int t[],int p, int q, int r){
        int[] t2 = t.clone();                              //Kopjuje tablice
        int i = p, j = q+1, k = p;                         //Indeksy poczatkowe, k- indeks tablicy poczatkowej 

        while( i <= q && j <= r){                          //Scal
            if(t2[i] <= t2[j])  t[k++] = t2[i++];
            else                t[k++] = t2[j++];
        }

        while(i <= q) t[k++] = t2[i++];                    //Przepisz to co zostalo
        while(j <= r) t[k++] = t2[j++];
    }

    private void mergeSort(int[] t, int p, int r){
        if(p>=r) return;                                   //Zakoncz
        int q = (p+r)/2;                                   //Podziel
        mergeSort(t, p, q);                                //Zwyciężaj
        mergeSort(t, q+1, r);
        merge(t, p, q, r);

    }

    @Override
    public void sort(int[] t){
        mergeSort(t, 0, t.length - 1);
    }
}

//Sortowanie szybkie 03.05.2022AD
/*
Analiza złożoności:
 Pesymistycznie mamy:
 T(n) = T(n-1) + |O|(n) = n * |O|(n) = |O|(n^2).
 Optymalnie pattriton dzieli na połowy więc:
 T(n) = 2T(n/2) + |O|(n)
 Stosując twierdzenie o rekurencji uniwersalnej:
 a = 2, b = 2, n^(log a z b) = n^(log 2 z 2) = n^1.
 Zatem f(n) = |O|(n) = |O|(n ^ (log a z b)), więc:
 T(n) = |O|(n * lg n)
*/
class QuickSort implements Sort{


    //Dzielenie werjsa z "Wprowadzenia do algorytmów"
    /*
     t - tablica
     p - początkowy indeks
     r - koniowy indeks
     q - graniczny indeks 
     */
    private int pattriton(int[] t, int p, int r){
        int pivot = t[r],i = p-1, j = p,temp;

        while(j<p){
            if(t[j] > pivot){
                i++;
                temp = t[j];
                t[j] = t[i];
                t[i] = temp;
            }
            j++;
        }
        t[r] = t[i+1];
        t[i+1] = pivot;
        return (i+1);
    }


    private void quickSort(int[] t, int p, int r){
        int q = pattriton(t, p, r);
        quickSort(t, p, q);
        quickSort(t, q+1, r);
    }

    public void sort(int t[]){
        quickSort(t, 0, t.length);
    }
}


//Program
class SortApp{
    public static void display(int[] t){
        for (int i = 0; i<t.length; i++) {
            System.out.print(t[i] + ",");
        }
        System.out.print("\n");
    }
    public static void main(String[] args){
        int[] t1 = {6,4,3,7,8,9,15,11,2,1,5};
        int[] t2 = {6,4,3,7,8,9,15,11,2,1,5};
        int[] t3 = {6,4,3,7,8,9,15,11,2,1,5};
        int[] t4 = {6,4,3,7,8,9,15,11,2,1,5};
        int[] t5 = {6,4,3,7,8,9,15,11,2,1,5};
        /////////////////////////////////////

        Sort s1 = new BubbleSort();
        s1.sort(t1);
        display(t1);

        //////////////////////////////////////

        Sort s2 = new SelectionSort();
        s2.sort(t2);
        display(t2);

        //////////////////////////////////////

        Sort s3 = new InsertionSort();
        s3.sort(t3);
        display(t3);

        //////////////////////////////////////

        Sort s4 = new MergeSort();
        s4.sort(t4);
        display(t4);

        //////////////////////////////////////

        Sort s5 = new QuickSort();
        s5.sort(t5);
        display(t5);

    }
}

