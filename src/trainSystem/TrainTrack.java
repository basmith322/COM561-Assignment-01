package trainSystem;

public class TrainTrack {
    private final String[] slots = {"[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]",
            "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]"};

    private final MageeSemaphore[] slotSem = new MageeSemaphore[19]; //declare array to hold the binary semaphores for access to track slots (sections)
    final Activity theTrainActivity; //reference the train activity
    final MageeSemaphore trackLimit; //declare a semaphore to hold the track limit value

    public TrainTrack() {
        /*record the train activity*/
        theTrainActivity = new Activity(slots);

        /*create array of slotSems and set them all to value 1*/
        for (int i = 0; i <= 18; i++) {
            slotSem[i] = new MageeSemaphore(1);
        }//end for

        trackLimit = new MageeSemaphore(8); //create semaphore for limiting number of trains on track
        //end constructor
    }

    //region Train A code
    public void trainA_MoveOntoTrack(String trainName) {
        CDS.idleQuietly((int) (Math.random() * 100));
        trackLimit.P(); // wait for space on the track
        /*record the train activity*/
        theTrainActivity.addMessage("Train " + trainName + " is joining/joined the A loop at section 5");
        slotSem[4].P(); //wait for slot 4 to be empty
        slotSem[5].P(); //wait for slot 5 to be empty
        slotSem[4].V(); //release slot 4
        slots[5] = "[" + trainName + "]"; //move train into slot 5
    }//end function

    /*move trains toward junction*/
    public void trainA_MoveTowardFirstJunction() {
        int currentPosition = 5; //set current position to 5
        moveTowardsTrackAJunction(currentPosition); //run moveTowardsTrackAJunction function
    }//end move train to junction

    public void trainA_MoveAcrossFirstJunction() {
        crossTrackAJunction();
    } //move train across first junction

    /*moves trains toward second junction*/
    public void trainA_MoveTowardSecondJunction() {
        int currentPosition = 10;
        moveTowardTrackBJunction(currentPosition);
    }//end MoveToNextJunction

    public void trainA_MoveAcrossSecondJunction() {
        crossTrackBJunction();
    } //move train across second junction

    /*move trains to the exit and leave the junction*/
    public void trainA_MoveToExit(String trainName) {
        CDS.idleQuietly((int) (Math.random() * 100));
        int currentPosition = 1; //set current position to 1
        do {
            slotSem[currentPosition + 1].P(); //check the space ahead is empty
            slots[currentPosition + 1] = slots[currentPosition]; //move train to the space ahead
            slots[currentPosition] = "[..]"; //set the space the train just left to empty
            theTrainActivity.addMovedTo(currentPosition + 1);
            slotSem[currentPosition].V(); //release the current position
            currentPosition++; //iterate the current position
        } while (currentPosition < 5); //continue until current position is 5
        slots[5] = "[..]"; // empty slot 5
        slotSem[5].V(); //release slot 5
        theTrainActivity.addMessage("Train " + trainName + " is leaving/left the track at section 5");
        trackLimit.V(); //clear space on track
        CDS.idleQuietly((int) (Math.random() * 100));
    }//end trainA_MoveToExit
    //endregion

    //region TrainB Code
    //************ Code for moving train B ***************
    public void trainB_MoveOntoTrack(String trainName) {
        CDS.idleQuietly((int) (Math.random() * 100));
        trackLimit.P(); // wait for space on the track
        //record the train activity
        theTrainActivity.addMessage("Train " + trainName + " is joining/joined the B loop at section 14");
        slotSem[13].P();
        slotSem[14].P(); //wait for slots 4 and 5 to be empty
        slotSem[13].V();
        slots[13] = "[..]";
        slots[14] = "[" + trainName + "]"; //move train into slot 5
    }//end function

    //move trains to junction
    public void trainB_MoveTowardFirstJunction() {
        int currentPosition = 14;
        moveTowardTrackBJunction(currentPosition);
    }//end move train to junction

    public void trainB_MoveAcrossFirstJunction() {
        crossTrackBJunction();
    }// end move across junction

    public void trainB_MoveTowardSecondJunction() {
        int currentPosition = 1;
        moveTowardsTrackAJunction(currentPosition);
    }//end MoveToNextJunction

    public void trainB_MoveAcrossSecondJunction() {
        crossTrackAJunction();
    }// end move across junction

    public void trainB_MoveToExit(String trainName) {
        CDS.idleQuietly((int) (Math.random() * 100));
        int currentPosition = 10;
        do {
            slotSem[currentPosition + 1].P();
            slots[currentPosition + 1] = slots[currentPosition];
            slots[currentPosition] = "[..]";
            theTrainActivity.addMovedTo(currentPosition + 1);
            slotSem[currentPosition].V();
            currentPosition++;
        } while (currentPosition < 14);
//        CDS.idleQuietly((int) (Math.random() * 100));
        slots[14] = "[..]";
        slotSem[14].V();
        theTrainActivity.addMessage("Train " + trainName + " is leaving/left the track at section 14");
        trackLimit.V(); //clear space on track
        CDS.idleQuietly((int) (Math.random() * 100));
    }//end trainA_MoveToExit
    //endregion

    //region Functions for moving train to junctions and across them
    private void moveTowardsTrackAJunction(int currentPosition) {
        CDS.idleQuietly((int) (Math.random() * 100));
        do {
            slotSem[currentPosition + 1].P(); //wait for the slot ahead to be free
            slots[currentPosition + 1] = slots[currentPosition]; //move train forward one position
            slots[currentPosition] = "[..]"; //clear the slot the train vacated
            theTrainActivity.addMovedTo(currentPosition + 1); //record the train activity
            slotSem[currentPosition].V(); //signal the slot you are leaving
            currentPosition++;
        } while (currentPosition < 8);
        CDS.idleQuietly((int) (Math.random() * 100));
    }

    private void crossTrackAJunction() {
        CDS.idleQuietly((int) (Math.random() * 100));
        slotSem[0].P(); //wait for the junction to be free
        slotSem[9].P(); //wait for 9 to be free
        slots[9] = slots[8]; //move from 8 into 9
        slots[8] = "[..]"; //empty 8
        theTrainActivity.addMovedTo(9);
        slotSem[8].V(); //release 8
        slotSem[10].P(); //wait for 10 to be free
        slots[0] = slots[9]; //move from 9 into 0
        slots[9] = "[..]"; //empty 9
        theTrainActivity.addMovedTo(0);
        slotSem[9].V(); //release 9
        slots[10] = slots[0]; //move from 0 into 10
        slots[0] = "[..]"; //empty 0
        theTrainActivity.addMovedTo(10);
        slotSem[0].V(); //release 0
        CDS.idleQuietly((int) (Math.random() * 100));
    }

    private void moveTowardTrackBJunction(int currentPosition) {
        CDS.idleQuietly((int) (Math.random() * 100));
        do {
            slotSem[currentPosition + 1].P();
            slots[currentPosition + 1] = slots[currentPosition];
            slots[currentPosition] = "[..]";
            theTrainActivity.addMovedTo(currentPosition + 1);
            slotSem[currentPosition].V();
            currentPosition++;
        } while (currentPosition < 17);
        CDS.idleQuietly((int) (Math.random() * 100));
    }

    private void crossTrackBJunction() {
        CDS.idleQuietly((int) (Math.random() * 100));
        slotSem[0].P(); //wait for the junction to be free
        slotSem[18].P(); //wait for 18 to be free
        slots[18] = slots[17]; //move from 17 into 18
        slots[17] = "[..]"; //empty 17
        theTrainActivity.addMovedTo(18);
        slotSem[17].V(); //release 17
        slotSem[1].P(); //wait for 1 to be free
        slots[0] = slots[18]; //move from 18 into 0
        slots[18] = "[..]"; //empty 9
        theTrainActivity.addMovedTo(0);
        slotSem[18].V(); //release 18
        slots[1] = slots[0]; //move from 0 into 1
        slots[0] = "[..]"; //empty 0
        theTrainActivity.addMovedTo(1);
        slotSem[0].V(); //release 0
        CDS.idleQuietly((int) (Math.random() * 100));
    }
    //endregion
}
