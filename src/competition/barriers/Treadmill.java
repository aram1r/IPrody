package competition.barriers;

public class Treadmill extends BarrierClass{
    private final int distance;


    public Treadmill(int distance) {
        super();
        this.distance = distance;
        barrierType = BarrierType.TREADMILL;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int getStats() {
        return distance;
    }

    public BarrierType getBarrierType() {
        return barrierType;
    }
}
