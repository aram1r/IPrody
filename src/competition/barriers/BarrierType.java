package competition.barriers;

public enum BarrierType {
    TREADMILL ("беговая дорожка"), WALL ("стена");

    private String type;

    BarrierType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
