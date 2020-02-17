package trainSystem;
import

public class TrainAProcess extends Thread{
    String trainName;
    TrainTrack theTrainTrack;

    public TrainAProcess(String trainName, TrainTrack theTrainTrack) {
        this.trainName = trainName;
        this.theTrainTrack = theTrainTrack;
    }

    @Override
    public void run() {
        theTrainTrack.trainA_MoveOntoTrack(trainName);
    }

    public void start() {
    }
}
