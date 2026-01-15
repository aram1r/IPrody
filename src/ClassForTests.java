public class ClassForTests{

    public int[] extractNewArray(int[] array) {
        int[] newArray;
        for (int i = 0; i<array.length; i++) {
            if (array[i] == 4) {
                newArray = new int[array.length - i];
                System.arraycopy(array, i, newArray, 0, newArray.length);
                return newArray;
            }
        }
        throw new RuntimeException("В массиве ни одной 4");
    }

    public boolean checkForNumbers(int[] array) {
        boolean flag = false;
        boolean flag1 = false;
        for (int i = 0; i<array.length; i++) {
            if (array[i] == 4 || array[i] == 1) {
                if (array[i] == 4) {
                    flag = true;
                }
                if (array[i] == 1) {
                    flag1 = true;
                }
            } else {
                return false;
            }
        }
        return flag && flag1;
    }
}
