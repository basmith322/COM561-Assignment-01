package trainSystem;

public class TrainTrack {
    private final String[] slots = {"[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]",
            "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]", "[..]"};

    //declare array to hold the binary semaphores for access to track slots (sections)
    private final MageeSemaphore slotSem[] = new MageeSemaphore[19];

    Activity theTrainActivity;

    MageeSemaphore trackALimit;

    //Constructor for TrainTrack
    public TrainTrack() {
        //record the train activity
        theTrainActivity = new Activity(slots);
        //create array of slotSems and set them all to value 1
        for (int i = 0; i <= 19; i++) {
            slotSem[i] = new MageeSemaphore(1);
        }//end for
        //create semaphores for limiting number of trains on top of track
        trackALimit = new MageeSemaphore(7);
        //end constructor

        //move A trains
        public void trainA_MoveOntoTrack (String trainName){
            CDS.idleQuietly((int) (Math.random() * 100));
            //record the train activity
            theTrainActivity.addMessage("Train " + trainName + " is joining/joined the A loop at section 5");
            slotSem[4].P();
            slotSem[5].P(); //wait for slots 4 and 5 to be empty
            slots[5] = "[" + trainName + "]"; //move train into slot 5
        }//end function

        //move trains to junction
        public void trainA_MoveToJunction (String trainName){
            CDS.idleQuietly((int) (Math.random() * 100));
            int currentPosition = 5;
            do {
                slotSem[currentPosition + 1].P();
                slots[currentPosition + 1] = slots[currentPosition];
                slots[currentPosition] = "[..]";
                theTrainActivity.addMovedTo(currentPosition + 1);
                slotSem[currentPosition].V();
                currentPosition++;
            } while (currentPosition < 9);
            CDS.idleQuietly((int) (Math.random() * 100));
        }//end move train to junction

        public void trainA_MoveAcrossJunction (String trainName){
            CDS.idleQuietly((int) Math.random() * 100);
            trackALimit.P(); //wait for space on the track
            slotSem[0].P(); //wait for the junction to be free
            slotSem[18].P(); //ensure 18 is empty
            slotSem[10].P(); //ensure slot after the junction is free
            slots[0] = slots[9]; //move train onto junction
            slots[9] = "[..]";
            theTrainActivity.addMovedTo(0);
            slotSem[9].V(); //signal slot that is being left
            //move off junction
            slots[10] = slots[0]; //move train off the junction
            slots[0] = "[..]";
            theTrainActivity.addMovesTo(10);
            slotSem[0].V();
            CDS.idleQuietly((int)Math.random()*100);
        }// end move across junction
    }

}
