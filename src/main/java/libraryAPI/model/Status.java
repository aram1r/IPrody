package libraryAPI.model;

public class Status {

    private boolean borrowed;
    private boolean returned;

    public Status() {};

    public Status(boolean borrowed, boolean returned) {
        this.borrowed = borrowed;
        this.returned = returned;
    }

    public Status(String status) {
        if (status.equalsIgnoreCase("borrowed")) {
            this.borrowed = true;
            this.returned = false;
        } else  {
            this.borrowed = false;
            this.returned = true;
        }
    }

    public String getStatus() {
        return borrowed ? "borrowed" : "returned";
    }
}
