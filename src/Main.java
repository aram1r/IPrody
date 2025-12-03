import lookUpPackage.BfsVoyager;
import lookUpPackage.DfsVoyager;

public class Main {
    public static void main(String[] args) {
        DfsVoyager dfsVoyager = new DfsVoyager(5, 5);
        dfsVoyager.printMap();

        System.out.println("DFSVoyager нашёл островов: " + dfsVoyager.findIslands());

//        Integer[][] newarray = new Integer[5][5];
//        newarray[0][0] = 1;
//        newarray[0][1] = 2;
//        newarray[0][2] = 2;
//        newarray[0][3] = 1;
//        newarray[0][4] = 1;
//        newarray[1][0] = 1;
//        newarray[1][1] = 1;
//        newarray[1][2] = 1;
//        newarray[1][3] = 1;
//        newarray[1][4] = 2;
//        newarray[2][0] = 2;
//        newarray[2][1] = 1;
//        newarray[2][2] = 1;
//        newarray[2][3] = 2;
//        newarray[2][4] = 2;
//        newarray[3][0] = 1;
//        newarray[3][1] = 2;
//        newarray[3][2] = 2;
//        newarray[3][3] = 1;
//        newarray[3][4] = 1;
//        newarray[4][0] = 2;
//        newarray[4][1] = 2;
//        newarray[4][2] = 1;
//        newarray[4][3] = 2;
//        newarray[4][4] = 2;
//        dfsVoyager = new  DfsVoyager(newarray);
//        BfsVoyager bfsVoyager = new BfsVoyager(newarray);
//        bfsVoyager.printMap();
//        System.out.println(bfsVoyager.lookupIslands());

        BfsVoyager bfsVoyager = new BfsVoyager(5, 5);
        bfsVoyager.printMap();
        System.out.println("BFSVoyager нашёл островов: " + bfsVoyager.findIslands());
    }
}