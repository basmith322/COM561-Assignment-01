package trainSystem;

class CDS {

    public static void idleQuietly(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
} // end CDS

