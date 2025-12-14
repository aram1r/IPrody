public class BoyScout {
    private String name;
    private int age;
    private Team team;

    public BoyScout(String name, int age, Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "BoyScout{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", team = " + team + '}';
    }
}
