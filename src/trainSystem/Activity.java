package trainSystem;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

//trainTrack.trainA_MoveOntoTrack(trainName);
//trainTrack.trainA_MoveToJunction(trainName);
//trainTrack.trainA_MoveAcrossJunction(trainName);
//trainTrack.trainA_MoveToNextJunction(trainName);
//trainTrack.trainA_MoveAcrossSecondJunction(trainName);
//trainTrack.trainA_MoveToExit(trainName);
public class Activity {
    private final CopyOnWriteArrayList<String> theActivities;
    private final String[] trainTrack;

    public Activity(String[] trainTrack) {
        theActivities = new CopyOnWriteArrayList<>();
        this.trainTrack = trainTrack;
    }

    public void addMovedTo(int section) {
        String tempString1 = "Train " + trainTrack[section] + " moving to/moved to [" + section + "]";
        theActivities.add(tempString1);
        theActivities.add(trackString());
    }

    public void addMessage(String message) {
        String tempString1 = message;
        theActivities.add(tempString1);
    }

    public void printActivities() {
        System.out.println("TRAIN TRACK ACTIVITY(Tracks[0..19])");
        Iterator<String> iterator = theActivities.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private String trackString() {
        String trackStateAsString = trainTrack[5] + trainTrack[6] + trainTrack[7] + trainTrack[8] + trainTrack[9] + "\n"
                + trainTrack[0] + trainTrack[10] + trainTrack[11] + trainTrack[12] + trainTrack[13] + trainTrack[14] + "\n"
                + trainTrack[15] + trainTrack[16] + trainTrack[17] + trainTrack[18] + trainTrack[0] + "\n"
                + trainTrack[1] + trainTrack[2] + trainTrack[3] + trainTrack[4] + "\n";
        return (trackStateAsString);

    }
}
