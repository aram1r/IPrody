import java.util.*;
import java.util.stream.Collectors;

public class Camp {
    private ArrayList<BoyScout> boyScouts;

    public Camp() {
        boyScouts = new ArrayList<>();
    }

    public void addScout(BoyScout boyScout) {
        boyScouts.add(boyScout);
    }

    public HashMap<Team, List<BoyScout>> returnBoyScouts() {;
        return boyScouts.stream()
                .sorted(Comparator.comparingInt(BoyScout::getAge))
                .collect(Collectors.groupingBy(
                        BoyScout::getTeam,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
    }
}
