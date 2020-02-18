package trainSystem;

public class TrainTrack {
    private final String[] slots = {"[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]",
            "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]"};

    //declare array to hold the binary semaphores for access to track slots (sections)
    private final MageeSemaphore[] slotSem = new MageeSemaphore[19];

    final Activity theTrainActivity;

    final MageeSemaphore trackLimit;

    //Constructor for TrainTrack
    public TrainTrack() {
        //record the train activity
        theTrainActivity = new Activity(slots);
        //create array of slotSems and set them all to value 1
        for (int i = 0; i <= 18; i++) {
            slotSem[i] = new MageeSemaphore(1);
        }//end for
        //create semaphores for limiting number of trains on track
        trackLimit = new MageeSemaphore(5);
        //end constructor
    }

    //region Train A code
    public void trainA_MoveOntoTrack(String trainName) {
        CDS.idleQuietly((int) (Math.random() * 100));
        //record the train activity
        theTrainActivity.addMessage("Train " + trainName + " is joining/joined the A loop at section 5");
        slotSem[4].P(); //wait for slot 4 to be empty
        slotSem[5].P(); //wait for slot 5 to be empty
        slotSem[4].V(); //release slot 4
        slots[5] = "[" + trainName + "]"; //move train into slot 5
    }//end function

    //move trains to junction
    public void trainA_MoveToJunction() {
        CDS.idleQuietly((int) (Math.random() * 100));
        int currentPosition = 5;
        do {
            slotSem[currentPosition + 1].P(); //wait for the slot ahead to be free
            slots[currentPosition + 1] = slots[currentPosition]; //move train forward one position
            slots[currentPosition] = "[..]"; //clear the slot the train vacated
            theTrainActivity.addMovedTo(currentPosition + 1); //record the train activity
            slotSem[currentPosition].V(); //signal the slot you are leaving
            currentPosition++;
        } while (currentPosition < 9);
        CDS.idleQuietly((int) (Math.random() * 100));
    }//end move train to junction

    public void trainA_MoveAcrossJunction() {
        CDS.idleQuietly((int) (Math.random() * 100));
        trackLimit.P(); //wait for space on the track
        slotSem[0].P(); //wait for the junction to be free
        slotSem[10].P(); //releases slot 10
        slotSem[18].P(); //ensure 18 is free
        slots[0] = slots[9]; //move train onto junction
        slots[9] = "[..]";
        theTrainActivity.addMovedTo(0);
        slotSem[9].V(); //releases slot 9
        slotSem[18].V(); //releases 18
        slots[10] = slots[0]; //move train off the junction
        slots[0] = "[..]";
        theTrainActivity.addMovedTo(10);
        slotSem[0].V(); //releases slot 0
        CDS.idleQuietly((int) (Math.random() * 100));
    }// end move across junction

    public void trainA_MoveToNextJunction() {
        CDS.idleQuietly((int) (Math.random() * 100));
        int currentPosition = 10;
        do {
            slotSem[currentPosition + 1].P();
            slots[currentPosition + 1] = slots[currentPosition];
            slots[currentPosition] = "[..]";
            theTrainActivity.addMovedTo(currentPosition + 1);
            slotSem[currentPosition].V();
            currentPosition++;
        } while (currentPosition < 18);
        CDS.idleQuietly((int) (Math.random() * 100));
    }//end MoveToNextJunction

    public void trainA_MoveAcrossSecondJunction() {
        CDS.idleQuietly((int) (Math.random() * 100));
        trackLimit.P(); //wait for space on the track
        slotSem[0].P(); //wait for the junction to be free
        slotSem[1].P(); //ensure slot after the junction is free
        slots[0] = slots[18]; //move train onto junction
        slots[18] = "[..]";
        theTrainActivity.addMovedTo(0);
        slotSem[18].V(); //signal slot that is being left
        //move off junction
        slots[1] = slots[0]; //move train off the junction
        slots[0] = "[..]";
        theTrainActivity.addMovedTo(18);
        slotSem[0].V();
        CDS.idleQuietly((int) (Math.random() * 100));
    }// end move across junction

    public void trainA_MoveToExit(String trainName) {
        CDS.idleQuietly((int) (Math.random() * 100));
        int currentPosition = 1;
        do {
            slotSem[currentPosition + 1].P();
            slots[currentPosition + 1] = slots[currentPosition];
            slots[currentPosition] = "[..]";
            theTrainActivity.addMovedTo(currentPosition + 1);
            slotSem[currentPosition].V();
            currentPosition++;
        } while (currentPosition < 5);
        CDS.idleQuietly((int) (Math.random() * 100));
        slots[5] = "[..]";
        slotSem[5].V();
        theTrainActivity.addMessage("Train " + trainName + " is leaving/left the track at section 5");
        CDS.idleQuietly((int) (Math.random() * 100));
    }//end trainA_MoveToExit
    //endregion

    //region TrainB Code
    //************ Code for moving train B ***************
    public void trainB_MoveOntoTrack(String trainName) {
        CDS.idleQuietly((int) (Math.random() * 100));
        //record the train activity
        theTrainActivity.addMessage("Train " + trainName + " is joining/joined the B loop at section 14");
        slotSem[13].P();
        slotSem[14].P(); //wait for slots 4 and 5 to be empty
        slotSem[13].V();
        slots[13] = "[..]";
        slots[14] = "[" + trainName + "]"; //move train into slot 5
    }//end function

    //move trains to junction
    public void trainB_MoveToJunction() {
        CDS.idleQuietly((int) (Math.random() * 100));
        int currentPosition = 14;
        do {
            slotSem[currentPosition + 1].P();
            slots[currentPosition + 1] = slots[currentPosition];
            slots[currentPosition] = "[..]";
            theTrainActivity.addMovedTo(currentPosition + 1);
            slotSem[currentPosition].V();
            currentPosition++;
        } while (currentPosition < 18);
        CDS.idleQuietly((int) (Math.random() * 100));
    }//end move train to junction

    public void trainB_MoveAcrossJunction() {
        CDS.idleQuietly((int) (Math.random() * 100));
        trackLimit.P(); //wait for space on the track
        slotSem[0].P();
        slotSem[1].P(); //ensure slot after the junction is free
        slots[0] = slots[18]; //move train onto junction
        slots[18] = "[..]";
        theTrainActivity.addMovedTo(0);
        slotSem[18].V(); //signal slot that is being left
        slots[1] = slots[0]; //move train off the junction
        slots[0] = "[..]";
        theTrainActivity.addMovedTo(18);
        slotSem[0].V();
        slotSem[1].V();
        CDS.idleQuietly((int) (Math.random() * 100));
    }// end move across junction

    public void trainB_MoveToNextJunction() {
        CDS.idleQuietly((int) (Math.random() * 100));
        int currentPosition = 1;
        do {
            slotSem[currentPosition + 1].P();
            slots[currentPosition + 1] = slots[currentPosition];
            slots[currentPosition] = "[..]";
            theTrainActivity.addMovedTo(currentPosition + 1);
            slotSem[currentPosition].V();
            currentPosition++;
        } while (currentPosition < 9);
        CDS.idleQuietly((int) (Math.random() * 100));
    }//end MoveToNextJunction

    public void trainB_MoveAcrossSecondJunction() {
        CDS.idleQuietly((int) (Math.random() * 100));
        trackLimit.P(); //wait for space on the track
        slotSem[0].P(); //wait for the junction to be free
        slotSem[10].P(); //releases slot 10
        slotSem[18].P(); //ensure 18 is free
        slots[0] = slots[9]; //move train onto junction
        slots[9] = "[..]";
        theTrainActivity.addMovedTo(0);
        slotSem[9].V(); //releases slot 9
        slotSem[18].V(); //releases 18
        slots[10] = slots[0]; //move train off the junction
        slots[0] = "[..]";
        theTrainActivity.addMovedTo(10);
        slotSem[0].V(); //releases slot 0
        CDS.idleQuietly((int) (Math.random() * 100));
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
        CDS.idleQuietly((int) (Math.random() * 100));
        slots[14] = "[..]";
        slotSem[14].V();
        theTrainActivity.addMessage("Train " + trainName + " is leaving/left the track at section 14");
        CDS.idleQuietly((int) (Math.random() * 100));
    }//end trainA_MoveToExit
    //endregion
}
