package competition;

import competition.barriers.BarrierClass;
import competition.barriers.BarrierType;
import competition.barriers.Treadmill;
import competition.barriers.Wall;
import competition.participants.Cat;
import competition.participants.Human;
import competition.participants.ParticipantClass;
import competition.participants.Robot;

public class Competition{




    public Competition() {

    }

    public static void start() {
        ParticipantClass[] participantsArray = new ParticipantClass[3];
        participantsArray[0] = new Cat("Dusya", 25, 2);
        participantsArray[1] = new Human("Alex", 12, 3);
        participantsArray[2] = new Robot("C3PO", 7, 0);

        BarrierClass[] barrierArray = new BarrierClass[3];
        barrierArray[0] = new Treadmill(6);
        barrierArray[1] = new Treadmill( 9);
        barrierArray[2] = new Wall( 1);

        for (ParticipantClass participantClass : participantsArray) {
            for (BarrierClass barrierClass : barrierArray) {
                if (!participantClass.overcome(barrierClass)) {
                    if (barrierClass.getBarrierType().equals(BarrierType.WALL)) {
                        System.out.println("Участник " + participantClass.getName() + " не перепрыгнул " + barrierClass.getBarrierType().getType() + " высотой " + barrierClass.getStats() + ". Пройдено " + participantClass.getOvercomedDistance());
                    } else {
                        System.out.println("Участник " + participantClass.getName() + " не прошёл препятствие " + barrierClass.getBarrierType().getType() + " на дистанции " + barrierClass.getStats() + ". Пройдено " + participantClass.getOvercomedDistance());
                    }
                    break;
                } else {
                    if (barrierClass.getBarrierType().equals(BarrierType.TREADMILL)) { //Если беговая дорожка добавляем дистанцию к пройденной, и вычитаем из максимальной
                        participantClass.setOvercomedDistance(barrierClass.getStats() + participantClass.getOvercomedDistance());
                        participantClass.setMaxDistance(participantClass.getMaxDistance() - barrierClass.getStats());
                        System.out.println("Участник " + participantClass.getName() + " прошёл препятствие " + barrierClass.getBarrierType().getType() + " на дистанции " + barrierClass.getStats() + ".");
                    } else {
                        System.out.println("Участник " + participantClass.getName() + " перепрыгнул " + barrierClass.getBarrierType().getType() + " высотой " + barrierClass.getStats() + ".");
                    }
                }
            }
        }
    }
}
