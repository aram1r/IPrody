package competition.barriers;

public abstract class BarrierClass implements BarrierInterface {

    BarrierType barrierType;

    protected BarrierClass() {

    }

    public int getStats() {
        return 0;
    }

    public BarrierType getBarrierType() {
        return barrierType;
    }
}
