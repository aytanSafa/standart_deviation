import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("Please enter array size");
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();
        System.out.println("Array Size = " + size);


        if (size == 0){
            throw new Exception("Size must be greater than 0");
        }

        short[] shorts = new short[size];

        System.out.println("Please add element to array");
        for (int i = 0 ; i < size ; i++){
            shorts[i] = sc.nextShort();
        }
        System.out.println(Arrays.toString(shorts));

        System.out.println();
        System.out.println("Please enter thread count");
        short numberOfThread = sc.nextShort();
        System.out.println("Thread count -> " + numberOfThread);

        if (numberOfThread < 1 || numberOfThread > 20){
            System.out.println("The number of threads cannot be less than 1 or greater than 20");
        }

        Operation operation = new Operation();
        System.out.printf("Result is %.2f ",operation.calculateStandardDeviation(shorts,numberOfThread));

   }
}