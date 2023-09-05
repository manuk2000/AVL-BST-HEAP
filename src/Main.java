import java.util.Arrays;

//
public class Main {
    class CompareReverse<T extends Comparable> {
        public int compare(T o1, T o2) {
            return o2.compareTo(o1);
        }
    }

    public static void main(String[] args) {
        int size = 8;
//        double[] arr = initDoubleArr(size);
//        int[] arr = initArr(size);
        Integer[] arr = {16, 19, 28, 15, 17, 22, 13, 30, 41, 62};
        System.out.println(Arrays.toString(arr));
        HeapWithArray<Integer> arr1 = new HeapWithArray<>(arr );

        arr1.insert(-63);
//        arr1.heapSort();

        System.out.println(Arrays.toString(arr1.getArray()));
        arr1.delete(0);
        System.out.println(Arrays.toString(arr1.getArray()));
    }

    static int[] initArr(int sizeArray) {
        int[] arr = new int[sizeArray];
        for (int i = 0; i < sizeArray; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }

    static double[] initDoubleArr(int sizeArray) {
        double[] arr = new double[sizeArray];
        for (int i = 0; i < sizeArray; i++) {
            arr[i] = (double) (int) (Math.random() * 100) / 100;
        }
        return arr;
    }

    static void swap(int[] arr, int i, int j) {
//        System.out.println( i +"  "+ j);
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

    }
}