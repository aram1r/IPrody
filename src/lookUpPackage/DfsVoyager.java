package lookUpPackage;

public class DfsVoyager extends VoyagerClass {
    public DfsVoyager(int m, int n) {
        super(m, n);
    }

    public DfsVoyager(Integer[][] array) {
        super(array);
    }


    @Override
    boolean lookupIslands(int i, int j, Boolean[][] checkedCells, Boolean flag) {
        if (checkedCells[i][j] == null) {
            checkedCells[i][j] = Boolean.TRUE;
//            printCheckedCells(checkedCells);
            if ((j+1)<array[i].length && checkedCells[i][j+1]==null && array[i][j+1] == earth) {
                int newCell = j+1;
                flag = true;
                lookupIslands(i, newCell, checkedCells, flag);
            }
            if ((i-1)>=0 && checkedCells[i-1][j]==null && array[i-1][j] == earth) {
                int newCell = i-1;
                flag = true;
                lookupIslands(newCell, j, checkedCells, flag);
            }
            if ((j-1)>=0 && checkedCells[i][j-1]==null && array[i][j-1] == earth) {
                int newCell = j-1;
                flag = true;
                lookupIslands(i, newCell, checkedCells, flag);
            }
            if ((i+1)<array.length && checkedCells[i+1][j]==null && array[i+1][j] == earth) {;
                int newCell = i+1;
                flag = true;
                lookupIslands(newCell, j, checkedCells, flag);
            }
        }
        return flag;
    }
}
