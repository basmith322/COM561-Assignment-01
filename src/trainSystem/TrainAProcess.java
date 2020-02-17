package trainSystem;

public class TrainAProcess extends Thread {
    String trainName;
    TrainTrack theTrack;

    public TrainAProcess(String trainName, TrainTrack theTrack) {
        this.trainName = trainName;
        this.theTrack = theTrack;
    }

    @Override
    public void run() {
        theTrack.trainA_MoveOntoTrack(trainName);
        theTrack.trainA_MoveToJunction(trainName);
        theTrack.trainA_MoveAcrossJunction(trainName);
        theTrack.trainA_MoveToNextJunction(trainName);
        theTrack.trainA_MoveAcrossSecondJunction(trainName);
        theTrack.trainA_MoveToExit(trainName);
    }
}
