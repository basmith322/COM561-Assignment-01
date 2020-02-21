package trainSystem;

public class TrainTrackDriver {
    static final int NUM_OF_A_TRAINS = 10; //Declare the number of trains to be created
    static final int NUM_OF_B_TRAINS = 10;
    static TrainTrack theTrainTrack;

    public static void main(String[] args) {
        int totalTrains = NUM_OF_A_TRAINS + NUM_OF_A_TRAINS;
        //create train track
        theTrainTrack = new TrainTrack();
        System.out.println("Started");

        //create arrays to hold the trains
        TrainAProcess[] trainAProcess = new TrainAProcess[NUM_OF_A_TRAINS];
        TrainBProcess[] trainBProcess = new TrainBProcess[NUM_OF_B_TRAINS];

        //create trains to enter the track
        for (int i = 0; i < NUM_OF_A_TRAINS; i++) {
            CDS.idleQuietly((int) (Math.random() * 1000));
            trainAProcess[i] = new TrainAProcess("A" + i, theTrainTrack);
        }//end for

        for (int i=0; i< NUM_OF_B_TRAINS; i++) {
            CDS.idleQuietly((int) (Math.random() * 1000));
            trainBProcess[i] = new TrainBProcess("B" + i, theTrainTrack);
        }

        //start the train processes
        for (int i = 0; i < NUM_OF_A_TRAINS; i++) {
            trainAProcess[i].start();
        }//end for

        for (int i = 0; i < NUM_OF_B_TRAINS; i++) {
            trainBProcess[i].start();
        }//end for

        //Trains now on track
        //wait for all train threads to finish before printing final message
        for (int i = 0; i < NUM_OF_A_TRAINS; i++) {
            try {
                trainAProcess[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }//end try/catch
        }
        for (int i = 0; i < NUM_OF_B_TRAINS; i++) {
            try {
                trainBProcess[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }//end try/catch
        }
            //Display all train activity that took place
            theTrainTrack.theTrainActivity.printActivities();

            //final message
            System.out.println(totalTrains + " trains have successfully travelled the track");
        }//end main
    }//end TrainTrackDriver