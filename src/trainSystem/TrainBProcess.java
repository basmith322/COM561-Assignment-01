package trainSystem;

public class TrainBProcess extends Thread {
    final String trainName;
    final TrainTrack theTrack;

    public TrainBProcess(String trainName, TrainTrack theTrack) {
        this.trainName = trainName;
        this.theTrack = theTrack;
    }

    @Override
    public void run() {
        theTrack.trainB_MoveOntoTrack(trainName);
        theTrack.trainB_MoveToJunction();
        theTrack.trainB_MoveAcrossJunction();
        theTrack.trainB_MoveToNextJunction();
        theTrack.trainB_MoveAcrossSecondJunction();
        theTrack.trainB_MoveToExit(trainName);
    }
}
