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
        theTrack.trainA_MoveToJunction();
        theTrack.trainA_MoveAcrossJunction();
        theTrack.trainA_MoveToNextJunction();
        theTrack.trainA_MoveAcrossSecondJunction();
        theTrack.trainA_MoveToExit(trainName);
    }
}
