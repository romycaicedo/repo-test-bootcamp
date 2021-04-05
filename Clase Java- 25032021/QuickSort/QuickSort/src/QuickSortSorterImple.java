import java.util.Comparator;

public class QuickSortSorterImple<T> implements Sorter<T> {
    @Override
    public void sort(T[] arr, Comparator<T> c) {
        int i, j, left = 0, right = arr.length - 1, stack_pointer = -1;
        int[] stack = new int[128];
        Object swap, temp;
        while (true) {
            if (right - left <= 7) {
                for (j = left + 1; j <= right; j++) {
                    swap = arr[j];
                    i = j - 1;
                    while (i >= left && c.compare(arr[i], (T) swap) > 0) {
                        arr[i + 1] = arr[i--];
                    }
                    arr[i + 1] = (T) swap;
                }
                if (stack_pointer == -1) {
                    break;
                }
                right = stack[stack_pointer--];
                left = stack[stack_pointer--];
            } else {
                int median = (left + right) >> 1;
                i = left + 1;
                j = right;
                swap = arr[median]; arr[median] = arr[i]; arr[i] = (T) swap;
                if (c.compare(arr[left], arr[right]) > 0) {
                    swap = arr[left]; arr[left] = arr[right]; arr[right] = (T) swap;
                }
                if (c.compare(arr[i], arr[right]) > 0) {
                    swap = arr[i]; arr[i] = arr[right]; arr[right] = (T) swap;
                }
                if (c.compare(arr[left], arr[i]) > 0) {
                    swap = arr[left]; arr[left] = arr[i]; arr[i] = (T) swap;
                }
                temp = arr[i];
                while (true) {
                    //noinspection ControlFlowStatementWithoutBraces,StatementWithEmptyBody
                    while (c.compare(arr[++i], (T) temp) < 0);
                    //noinspection ControlFlowStatementWithoutBraces,StatementWithEmptyBody
                    while (c.compare(arr[--j], (T) temp) > 0);
                    if (j < i) {
                        break;
                    }
                    swap = arr[i]; arr[i] = arr[j]; arr[j] = (T) swap;
                }
                arr[left + 1] = arr[j];
                arr[j] = (T) temp;
                if (right - i + 1 >= j - left) {
                    stack[++stack_pointer] = i;
                    stack[++stack_pointer] = right;
                    right = j - 1;
                } else {
                    stack[++stack_pointer] = left;
                    stack[++stack_pointer] = j - 1;
                    left = i;
                }
            }
        }
    }
}
