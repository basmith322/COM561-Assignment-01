package trainSystem;

class CDS {

    public static void idleQuietly(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
} // end CDS

