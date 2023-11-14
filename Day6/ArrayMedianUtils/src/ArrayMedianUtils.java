import java.util.Arrays;
import java.util.Comparator;
//Student: Ray Ding, Partner: Xiyao Xu
public class ArrayMedianUtils{
    public static <T extends Comparable<T>> T medianComparable(T[] array){
        if(array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        Arrays.sort(array);
        int mid = array.length/2;
        return array[mid]; //element in the middle
    }

    public static <T> T medianComparator(T[] array, Comparator<? super T> comparator){
        if(array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        if(comparator == null){
            throw new IllegalArgumentException("Comparator must not be null");
        }
        Arrays.sort(array, comparator);
        int mid = array.length/2;
        return array[mid];

    }


}