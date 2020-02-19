package trainSystem;

public class TrainTrackDriver {
    static final int NUM_OF_TRAINS = 10; //Declare the number of trains to be created
    static TrainTrack theTrainTrack;

    public static void main(String[] args) {
        //create train track
        theTrainTrack = new TrainTrack();
        System.out.println("Started");

        //create arrays to hold the trains
        TrainAProcess[] trainAProcess = new TrainAProcess[NUM_OF_TRAINS];
        TrainBProcess[] trainBProcess = new TrainBProcess[NUM_OF_TRAINS];

        //create trains to enter the track
        for (int i = 0; i < NUM_OF_TRAINS; i++) {
            CDS.idleQuietly((int) (Math.random() * 1000));
            trainAProcess[i] = new TrainAProcess("A" + i, theTrainTrack);
            trainBProcess[i] = new TrainBProcess("B" + i, theTrainTrack);
        }//end for

        //start the train processes
        for (int i = 0; i < NUM_OF_TRAINS; i++) {
            trainAProcess[i].start();
            trainBProcess[i].start();
        }//end for

        //Trains now on track
        //wait for all train threads to finish before printing final message
        for (int i = 0; i < NUM_OF_TRAINS; i++) {
            try {
                trainAProcess[i].join();
                trainBProcess[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }//end try/catch
        }
            //Display all train activity that took place
            theTrainTrack.theTrainActivity.printActivities();

            //final message
            System.out.println("All Trains have successfully travelled the track");
        }//end main
    }//end TrainTrackDriver