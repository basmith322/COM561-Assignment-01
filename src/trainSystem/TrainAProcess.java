package trainSystem;

public class TrainAProcess extends Thread {
    final String trainName;
    final TrainTrack theTrack;

    public TrainAProcess(String trainName, TrainTrack theTrack) {
        this.trainName = trainName;
        this.theTrack = theTrack;
    }

    @Override
    public void run() {
        theTrack.trainA_MoveOntoTrack(trainName);
        theTrack.trainA_MoveTowardFirstJunction();
        theTrack.trainA_MoveAcrossFirstJunction();
        theTrack.trainA_MoveTowardSecondJunction();
        theTrack.trainA_MoveAcrossSecondJunction();
        theTrack.trainA_MoveToExit(trainName);
    }
}
