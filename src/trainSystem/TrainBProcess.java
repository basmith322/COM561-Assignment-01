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
        theTrack.trainB_MoveToJunction(trainName);
        theTrack.trainB_MoveAcrossJunction(trainName);
        theTrack.trainB_MoveToNextJunction(trainName);
//        theTrack.trainB_MoveAcrossSecondJunction(trainName);
//        theTrack.trainB_MoveToExit(trainName);
    }
}
