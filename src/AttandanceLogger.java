import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class AttandanceLogger{

    private HashMap<String, ArrayList<LocalTime>> attendanceJournal;

    public AttandanceLogger() {
        attendanceJournal = new HashMap<>();
    }

    public void addVisit(String user, LocalTime localTime) {
        if ( !attendanceJournal.containsKey(user) && user!=null && localTime!=null ) {
            attendanceJournal.put(user, new ArrayList<>());
        }
        attendanceJournal.get(user).add(localTime);
    }

    public HashMap<String, Integer> calculateFrequencyVisits () {
        HashMap<String, Integer> result = new HashMap<>();
        for (String user : attendanceJournal.keySet()) {
                result.put(user, attendanceJournal.get(user).size());
        }
        return result;
    }

    public String calculateMostFrequentHour () {
        HashMap<String, Integer> result = new HashMap<>();
        Integer mostFrequent = 0;
        for (ArrayList<LocalTime> list : attendanceJournal.values()) {
            for (LocalTime time : list) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
                String hour = time.format(formatter);
                if (result.containsKey(hour)) {
                    result.put(hour, result.get(hour) + 1);
                    if (result.get(hour) >= mostFrequent) {
                        mostFrequent = result.get(hour);
                    }
                } else {
                    result.put(hour, 1);
                    if (mostFrequent < 1) {
                        mostFrequent = 1;
                    }
                }
            }
        }
        String resultHour = "";
        for (String hourKey : result.keySet()) {
            if (result.get(hourKey) >= mostFrequent) {
                resultHour = hourKey;
            }
        }
        return "Самый частый час посещения: " + resultHour + ", посещений в этот час " + mostFrequent + " раз";
    }
}
