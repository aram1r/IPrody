import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        int[] target = new int[getRandom(5, 3)];
        int[] source = new int[getRandom(5, 3)];
        fillArray(target, 10);
        fillArray(source, 10);
        System.out.println(target.length + " " + source.length);
        int[] result = arrayCopy(source, target);

        for (int i : result) {
            System.out.print(i + " ");
        }

        System.out.println(" ");
        int[] arrayToSort = new int[25];
        fillArray(arrayToSort, 100);
        for (int i : arrayToSort) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
        int[] sortedArray = cocktailSort(arrayToSort);
        for (int i : sortedArray) {
            System.out.print(i + " ");
        }
    }

    //min добавлен для избежания массивов нулевой длины
    public static int getRandom(int limit, int min) {
        return ((int) (Math.random() * limit) + min);
    }

    public static void fillArray(int[] array, int limit) {
        for (int i = 0; i < array.length; i++) {
            array[i] = getRandom(limit, 0);
        }
    }

    public static int[] arrayCopy(int[] source,  int[] target) {
        int[] result = new int[source.length + target.length];
        for (int i = 0; i < result.length; i++) {
            if (i < source.length) {
                result[i] = source[i];
            } else {
                result[i] = target[i-source.length];
            }
        }
        return result;
    }

    public static int[] cocktailSort(int[] array) {
        int operationsMade = 0;
        int rightIndex = array.length-1, leftIndex = 0;
        int direction = 0;
        for (int i = 0; i < array.length; i++) {
            for (int k = leftIndex; k < rightIndex; k++) {
                if (direction == 0) {
                    if (k<=rightIndex-1 && array[k] > array[k+1]) {;;
                        int temp = array[k];
                        array[k] = array[k+1];
                        array[k+1] = temp;
                        operationsMade++;
                    } else if (k+1==rightIndex){
                        rightIndex--;
                    }
                } else {
                    if ((rightIndex-k-1>=leftIndex) && array[rightIndex-k]<array[rightIndex-k-1]) {;
                        int temp = array[rightIndex-k];
                        array[rightIndex-k] = array[rightIndex-1-k];
                        array[rightIndex-1-k] = temp;
                        operationsMade++;
                    } else if (k==array.length-1-leftIndex) {
                        leftIndex++;
                    }
                }
            }
            direction = direction == 0 ? 1 : -1;
        }

        System.out.println("Operations made: " + operationsMade);
        return array;
    }
}
