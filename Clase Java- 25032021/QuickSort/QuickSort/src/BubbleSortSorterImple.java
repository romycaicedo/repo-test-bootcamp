import java.util.Comparator;

public class BubbleSortSorterImple<T> implements Sorter<T>{
    @Override
    public void sort(T[] arr, Comparator<T> c) {
        int left = 0;
        int right = arr.length-1;
        // the outer loop, runs from right to left
        for(int i=right;i>1;i--){
            // the inner loops, runs from left to the right, limited by the outer loop
            for(int j=left;j<i;j++){
                // if the left item is greater than the right one, swaps
                if(c.compare(arr[j], arr[j+1]) > 0){
                    swap(arr,j, j+1);
                }
            }
        }
    }
    private void swap(T[] a, int left, int right){
        T temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }
}
