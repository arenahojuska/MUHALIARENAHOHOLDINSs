package hello;

import java.io.IOException;

public class Bmain {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Run classes in groups of three up to B16
        runInGroupsOfThree(args, B1.class, B2.class, B3.class);
        runInGroupsOfThree(args, B4.class, B5.class, B6.class);
        runInGroupsOfThree(args, B7.class, B8.class, B9.class);
        runInGroupsOfThree(args, B10.class, B11.class, B12.class);
        runInGroupsOfThree(args, B13.class, B14.class, B15.class);
        
        // Final group (B16)
        runInGroupsOfThree(args, B16.class);
        // Run classes in groups of three up to B16
        runInGroupsOfThree(args, B1.class, B2.class, B3.class);
        runInGroupsOfThree(args, B4.class, B5.class, B6.class);
        runInGroupsOfThree(args, B7.class, B8.class, B9.class);
        runInGroupsOfThree(args, B10.class, B11.class, B12.class);
        runInGroupsOfThree(args, B13.class, B14.class, B15.class);
        // Run classes in groups of three up to B16
        runInGroupsOfThree(args, B1.class, B2.class, B3.class);
        runInGroupsOfThree(args, B4.class, B5.class, B6.class);
        runInGroupsOfThree(args, B7.class, B8.class, B9.class);
        runInGroupsOfThree(args, B10.class, B11.class, B12.class);
        runInGroupsOfThree(args, B13.class, B14.class, B15.class);
    }

    @SafeVarargs
    private static void runInGroupsOfThree(String[] args, Class<?>... classes) throws InterruptedException {
        Thread[] threads = new Thread[classes.length];

        for (int i = 0; i < classes.length; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                try {
                    // This calls the main method of each class
                    classes[index].getMethod("main", String[].class).invoke(null, (Object) args);
                } catch (Exception e) {
                    System.out.println("Error in " + classes[index].getSimpleName() + ": " + e.getMessage());
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Error while waiting for thread: " + e.getMessage());
            }
        }
    }
}