import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Operation {
    double totalCountByPower = 0;

    // This method provide to find total powers in each element of subArrays
    public double findPowerByAritmeticMean(short[] array , double aritmeticMean){
        double subTotal = 0;

        for (short count : array) {
            double result = count - aritmeticMean;
            subTotal = subTotal + Math.pow(result,2);
        }
        return subTotal;
    }
    private synchronized void increaseTotalCount(double subTotal){
        totalCountByPower  =  totalCountByPower + subTotal;
    }

    // this method provide to calculate standard deviation
    public double calculateStandardDeviation(short[] counts, short numberOfThreads){

        double aritmeticMean = findAritmeticMean(counts);
        List<short []> subCountList =  splitArray(counts,numberOfThreads);

        ExecutorService executorService = Executors.newFixedThreadPool(subCountList.size());

        for (int i = 0 ; i <= subCountList.size(); i++ ){
            int taskNumber = i;
            executorService.submit(() -> {
                double subTotal = findPowerByAritmeticMean(subCountList.get(taskNumber),aritmeticMean);
                increaseTotalCount(subTotal);
            });
        }
        waitTasks(executorService);
        double dividedTotalPower = dividePowerTotal(totalCountByPower,counts.length);

        return Math.sqrt(dividedTotalPower);
    }
    //  This method provide to wait all task done.
    private static void waitTasks(ExecutorService executorService) {
        executorService.shutdown();

        try {
            // Wait for all tasks to complete or timeout after a certain duration
            boolean terminated = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            if (terminated) {
                System.out.println("All tasks have completed.");
            } else {
                System.out.println("Timeout occurred before all tasks could complete.");
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted while waiting for tasks to complete.");
            Thread.currentThread().interrupt();
        }
    }

    // This function divides the sum of squares by n-1.
    private double dividePowerTotal(double totalCountByPower, int size) {
        return totalCountByPower / (size - 1);
    }


    // This method is the function that returns the arithmetic mean.
    public double findAritmeticMean(short[] array){

        double total = 0;

        for (short i : array) {
            total = total + i;
        }
        return total / array.length;
    }

    // This function splits the array according to the number of threads.
    public  List<short[]> splitArray(short[] array, short numThreads) {
        List<short[]> subArrays = new ArrayList<>();

        int arrayLength = array.length;
        int subArraySize = arrayLength / numThreads;
        int remainingElements = arrayLength % numThreads;
        int currentIndex = 0;

        for (int i = 0; i < numThreads; i++) {
            int currentSubArraySize = subArraySize + (i < remainingElements ? 1 : 0);
            short[] subArray = new short[currentSubArraySize];
            System.arraycopy(array, currentIndex, subArray, 0, currentSubArraySize);
            subArrays.add(subArray);
            currentIndex += currentSubArraySize;
        }
        return subArrays;
    }
}
