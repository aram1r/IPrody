package competition.participants;

import competition.barriers.BarrierClass;

public abstract class ParticipantClass implements Participant, Runable, Jumpable {

    protected final String name;
    protected int maxDistance;
    protected final int maxHeight;
    protected int overcomedDistance;

    public ParticipantClass(String name, int  maxDistance, int maxHeight) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }

    public String getName() {
        return name;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    @Override
    public boolean jump(int height) {
        return maxHeight > height;
    }

    @Override
    public boolean run(int speed) {
        return maxDistance > speed;
    }

    @Override
    public boolean overcome(BarrierClass barrierClass) {
        boolean result = false;
        switch (barrierClass.getBarrierType()) {
            case WALL -> result = jump(barrierClass.getStats());
            case TREADMILL -> result = run(barrierClass.getStats());
        }
        return result;
    }

    public int getOvercomedDistance() {
        return overcomedDistance;
    }

    public void setOvercomedDistance(int overcomedDistance) {
        this.overcomedDistance = overcomedDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }
}
