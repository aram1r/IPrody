package lookUpPackage;

import java.util.ArrayList;

public class BfsVoyager extends VoyagerClass {


    public BfsVoyager(int m, int n) {
        super(m, n);
    }

    public BfsVoyager(Integer[][] array) {
        super(array);
    }

    @Override
    boolean lookupIslands (int i, int j, Boolean[][] visited, Boolean flag) {
        ArrayList<Coordinates> coordinates = new ArrayList<>();
        coordinates.add(new Coordinates(i, j));
        flag = false;
        while (!coordinates.isEmpty()) {
            Coordinates c = coordinates.remove(0);
            if (visited[c.getY()][c.getX()] == null) {
                int x = c.getX();
                int y = c.getY();
                if ((x+1)<array[0].length && array[y][x+1] == 1) {
                    coordinates.add(new Coordinates(y, x+1));
                    flag = true;
                }
                if ((y-1)>=0 && array[y-1][x] == 1) {
                    coordinates.add(new Coordinates(y-1, x));
                    flag = true;
                }
                if ((x-1)>=0 && array[y][x-1] == 1) {
                    coordinates.add(new Coordinates(y, x-1));
                    flag = true;
                }
                if ((y+1)<array.length && array[y+1][x] == 1) {
                    coordinates.add(new Coordinates(y+1, x));
                    flag = true;
                }
                visited[y][x] = true;
//                printCheckedCells(visited);
            }

        }
        return flag;
    }
}

class Coordinates {
    int i;
    int j;
    public Coordinates(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getX() {
        return j;
    }

    public void setX(int j) {
        this.j = j;
    }

    public int getY() {
        return i;
    }

    public void setY(int i) {
        this.i = i;
    }
}
