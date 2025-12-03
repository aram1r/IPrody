package lookUpPackage;

public abstract class VoyagerClass {
    Integer[][] array;
    static int earth = 1;
    static int water = 2;

    public VoyagerClass(int m, int n) {
        array = new Integer[m][n];
        fillArray();
    }

    public VoyagerClass(Integer[][] array) {
        this.array = array;
    }

    private void fillArray() {
        for (int i = 0; i<array.length; i++) {
            for (int j = 0; j<array[i].length; j++) {
                array[i][j] = (int)(Math.random()*2+1);
            }
        }
    }

    public void printMap() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
    }

    protected void printCheckedCells(Boolean[][] checkedCells) {
        System.out.println(" ");
        for (int i = 0; i < checkedCells.length; i++) {
            for (int j = 0; j < checkedCells[i].length; j++) {
                if (array[i][j] == water) {
                    System.out.print("2");
                    System.out.print(" ");
                } else {
                    if (checkedCells[i][j] != null) {
                        System.out.print("■");
                        System.out.print(" ");
                    } else {
                        System.out.print(array[i][j]);
                        System.out.print(" ");

                    }
                }
            }
            System.out.println(" ");
        }
    }


    public int findIslands() {
        int islandsCount = 0;
        Boolean[][] checkedCells = new Boolean[array.length][array[0].length];
        for (int i = 0; i<array.length; i++) {
            for (int j = 0; j<array[i].length; j++) {
                if (checkedCells[i][j] == null) {
                    if (array[i][j] == earth) {
//                        System.out.println("Начата обработка острова стартующей с клетки " + i + " " + j);
                        Boolean flag = false;
                        if (lookupIslands(i, j, checkedCells, flag)) {
                            islandsCount++;
                        }
//                        System.out.println("Обработка острова стартующего с клетки " + i + " " + j + " Закончена");
                    } else {
                        checkedCells[i][j] = Boolean.TRUE;
                    }
                }
            }
        }
        return islandsCount;
    }

    abstract boolean lookupIslands(int i, int j, Boolean[][] visited, Boolean flag);
}
