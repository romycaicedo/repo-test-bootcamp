public class SortUtil {
    public SortUtil() {
    }

    public static <T> void ordenar(Precedable<T> arr[]){
        for(int i = 0; i<arr.length;i++){
            for (int j = 0; j<arr.length-1;j++){
                if(arr[j].precedeA((T)arr[j+1])>0){
                    Precedable<T> aux = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1]=aux;
                }
            }
        }

        }



    }

