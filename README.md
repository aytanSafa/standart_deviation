# Standard Deviation

This algorithm allows to find the standard deviation. The array length, array elements and thread count are requested by the application as parameters with the Scanner.The arithmetic mean is found with the elements in the array.Afterwards, the array elements are divided into sub-arrays as many as the given number of threads.The split arrays are sent to the threads and the operations are done in parallel.It is expected that the parallel operations are finished and then the standard deviation is found by applying the necessary formula.

## Constraints

1 >= numberOfThreads <= 20







