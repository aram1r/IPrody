import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        AttandanceLogger logger = new AttandanceLogger();

        logger.addVisit("user2", LocalTime.of(10, 30));
        logger.addVisit("user3", LocalTime.of(8, 30));
        logger.addVisit("user4", LocalTime.of(5, 30));
        logger.addVisit("user5", LocalTime.of(7, 30));
        logger.addVisit("user6", LocalTime.of(8, 30));
        logger.addVisit("user7", LocalTime.of(1, 30));
        logger.addVisit("user8", LocalTime.of(2, 30));
        logger.addVisit("user9", LocalTime.of(3, 30));
        logger.addVisit("user2", LocalTime.of(4, 30));
        logger.addVisit("user2", LocalTime.of(11, 30));
        logger.addVisit("user2", LocalTime.of(11, 15));
        logger.addVisit("user2", LocalTime.of(11, 25));
        logger.addVisit("user2", LocalTime.of(11, 44));

        System.out.println(logger.calculateMostFrequentHour());

        System.out.println(logger.calculateFrequencyVisits());

    }
}