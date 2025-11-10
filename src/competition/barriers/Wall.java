package competition.barriers;

public class Wall extends BarrierClass {
    private final int height;

    public Wall(int height) {
        super();
        this.height = height;
        barrierType = BarrierType.WALL;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public int getStats() {
        return height;
    }
}
