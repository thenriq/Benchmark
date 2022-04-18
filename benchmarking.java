// Stack Overflow (https://stackoverflow.com/questions/29662026/when-i-do-division-in-java-i-lose-my-decimal-how-to-show-it)
// Coding with John (https://www.youtube.com/channel/UC42pOSNg804f1wCcj7qL0mA)
// https://stackoverflow.com/questions/31771003/how-to-make-a-progress-bar-in-java-console-application

import java.util.Random;

public class benchmarking {
  public static void main(String[] args) throws InterruptedException {

    //Creating array containing length values
    //each looping iteration will create a new array based on content from "arr_size"
    int arr_size[]={100,250,500,750,1000,1250,2500,3750,5000,6250,7500,8750,10000};
    
    //Creating arrays which will contain benchmark results average
    double[] array_to_print_mergeSort = new double[arr_size.length];
    double[] array_to_print_bubbleSort = new double[arr_size.length];
    double[] array_to_print_insertionSort = new double[arr_size.length];
    double[] array_to_print_selectionSort = new double[arr_size.length];
    double[] array_to_print_countingSort = new double[arr_size.length];

    Random rand = new Random();

    int total_tests = 10;
    
    double average_mergeSort = 0;
    double average_bubbleSort = 0;
    double average_insertionSort = 0;
    double average_selectionSort = 0;
    double average_countingSort = 0;

    //variables for progress bar
    final StringBuilder sb  =  new StringBuilder(); 
    String format = "Calculating...[%-13s]%d%%\r";

    //Main looping
    for (int i = 0; i < arr_size.length; i++){ //looping through arr_size to catch length for array "numbers"
      int x = arr_size[i];
      int[] scrambled_Array = new int[x]; //Created array "scrambled_Array". Its length came from "arr_size"

      for (int j = 0; j < scrambled_Array.length; j++) {
        scrambled_Array[j] = rand.nextInt(1000000); //populating array "scrambled_Array" with random numbers
      }

      for (int k = 0; k < total_tests; k++){ //loop to perform benchmark "total_tests" times
          int[] numbers_mergeSort     = scrambled_Array.clone(); // Creating/reseting array to original values before sorted
          long startTime = System . nanoTime ();
          mergeSort(numbers_mergeSort); // running method mergeSort on array "numbers_mergeSort"
          long endTime = System . nanoTime ();
          long timeElapsed = endTime - startTime ;
          double elapsedMillis = (double)timeElapsed /1000000;
          average_mergeSort += elapsedMillis; //cumulative result being passed to variable "average_mergeSort"
      }

      for (int k = 0; k < total_tests; k++){ //loop to perform benchmark "total_tests" times
        int[] numbers_bubbleSort    = scrambled_Array.clone(); // Creating/reseting array to original values before sorted
        long startTime = System . nanoTime ();
        bubbleSort(numbers_bubbleSort); // running method mergeSort on array "numbers_bubbleSort"
        long endTime = System . nanoTime ();
        long timeElapsed = endTime - startTime ;
        double elapsedMillis = (double)timeElapsed /1000000;
        average_bubbleSort += elapsedMillis; //cumulative result being passed to variable "average_bubbleSort"
      }

      for (int k = 0; k < total_tests; k++){ //loop to perform benchmark "total_tests" times
        int[] numbers_insertionSort = scrambled_Array.clone(); // Creating/reseting array to original values before sorted
        long startTime = System . nanoTime ();
        insertionSort(numbers_insertionSort); // running method mergeSort on array "numbers_insertionSort"
        long endTime = System . nanoTime ();
        long timeElapsed = endTime - startTime ;
        double elapsedMillis = (double)timeElapsed /1000000;
        average_insertionSort += elapsedMillis; //cumulative result being passed to variable "average_insertionSort"
      }

      for (int k = 0; k < total_tests; k++){ //loop to perform benchmark "total_tests" times
        int[] numbers_selectionSort = scrambled_Array.clone(); // Creating/reseting array to original values before sorted
        long startTime = System . nanoTime ();
        selectionSort(numbers_selectionSort); // running method mergeSort on array "numbers_selectionSort"
        long endTime = System . nanoTime ();
        long timeElapsed = endTime - startTime ;
        double elapsedMillis = (double)timeElapsed /1000000;
        average_selectionSort += elapsedMillis; //cumulative result being passed to variable "average_selectionSort"
      } 

      for (int k = 0; k < total_tests; k++){ //loop to perform benchmark "total_tests" times
        int[] numbers_countingSort  = scrambled_Array.clone();
        long startTime = System . nanoTime ();
        countingSort(numbers_countingSort); // running method mergeSort on array "numbers_countingSort"
        long endTime = System . nanoTime ();
        long timeElapsed = endTime - startTime ;
        double elapsedMillis = (double)timeElapsed /1000000;
        average_countingSort += elapsedMillis; //cumulative result being passed to variable "average_countingSort"
      }

      array_to_print_mergeSort[i] = average_mergeSort / total_tests; //populating array "array_to_print_mergeSort" with average results
      array_to_print_bubbleSort[i] = average_bubbleSort / total_tests;  //populating array "array_to_print_bubbleSort" with average results
      array_to_print_insertionSort[i] = average_insertionSort / total_tests; //populating array "array_to_print_insertionSort" with average results
      array_to_print_selectionSort[i] = average_selectionSort / total_tests; //populating array "array_to_print_selectionSort" with average results
      array_to_print_countingSort[i] = average_countingSort / total_tests; //populating array "array_to_print_countingSort" with average results

      //Printing progress bar
      sb.append("#");
      double arr_length = arr_size.length;
      double size = ((i+1)/arr_length)*100;
      int size2 = (int)size;
      System.out.print(String.format(format, sb, (size2)));
      Thread.sleep(10);
    }
    
    System.out.print("\n");

    //Printing table headers
    System.out.print ("Size:      ");
    for (int i = 0; i < arr_size.length; i++){
      System.out.print("\t"+arr_size[i]); 
    }

    //Outputing benchmark results to a table
    //Bubble sort
    System.out.print("\n");
    System.out.print("Bubble Sort");
    for (int i = 0; i < array_to_print_bubbleSort.length; i++){
      System.out.print("\t"+(String.format("%-3.3f",array_to_print_bubbleSort[i])));
    }

    //selection sort
    System.out.print("\n");
    System.out.print("Selection Sort");
    for (int i = 0; i < array_to_print_selectionSort.length; i++){
      System.out.print("\t"+(String.format("%-3.3f",array_to_print_selectionSort[i])));
    }

    //insertion sort
    System.out.print("\n");
    System.out.print("Insertion Sort");
    for (int i = 0; i < array_to_print_insertionSort.length; i++){
      System.out.print("\t"+(String.format("%-3.3f",array_to_print_insertionSort[i])));
    }

    //counting sort
    System.out.print("\n");
    System.out.print("Counting Sort");
    for (int i = 0; i < array_to_print_countingSort.length; i++){
      System.out.print("\t"+(String.format("%-3.3f",array_to_print_countingSort[i])));
    }
    
    //merge sort
    System.out.print("\n");
    System.out.print("Merge Sort");
    for (int i = 0; i < array_to_print_mergeSort.length; i++){
      System.out.print("\t"+(String.format("%-3.3f",array_to_print_mergeSort[i])));
    }
  }

  //Method mergeSort
  private static void mergeSort(int[] inputArray) {
    int inputLength = inputArray.length;
    
    //If list is empty, nothing will happen
    if (inputLength < 2) {
      return;
    }
    
    // Splitting array in 2 parts
    int midIndex = inputLength / 2;
    int[] leftHalf = new int[midIndex];
    int[] rightHalf = new int[inputLength - midIndex];
    
    for (int i = 0; i < midIndex; i++) {
      leftHalf[i] = inputArray[i];
    }
    for (int i = midIndex; i < inputLength; i++) {
      rightHalf[i - midIndex] = inputArray[i];
    }
    
    // Sorting each part using recursion
    mergeSort(leftHalf);
    mergeSort(rightHalf);
    
    // both parts being merged together and overwriting to original
    merge(inputArray, leftHalf, rightHalf);
  }

  private static void merge (int[] inputArray, int[] leftHalf, int[] rightHalf) {
    int leftSize = leftHalf.length;
    int rightSize = rightHalf.length;
    
    int i = 0, j = 0, k = 0;
    
    //Compare elements at i and j, and moving smaller element at k
    while (i < leftSize && j < rightSize) {
      if (leftHalf[i] <= rightHalf[j]) {
        inputArray[k] = leftHalf[i];
        i++;
      }
      else {
        inputArray[k] = rightHalf[j];
        j++;
      }
      k++;
    }
    
    while (i < leftSize) {
      inputArray[k] = leftHalf[i];
      i++;
      k++;
    }
    
    while (j < rightSize) {
      inputArray[k] = rightHalf[j];
      j++;
      k++;
    }
  }

  //method bubbleSort
  public static void bubbleSort(int[] inputArray) {
    int n = inputArray.length;
    // iterating over the array comparing elements
    for (int i = 0; i < n-1; i++)
        for (int j = 0; j < n-i-1; j++)
            // Swapping elements if not in order
            if (inputArray[j] > inputArray[j+1]){
              // swap arr[j+1] and arr[j]
              int temp = inputArray[j];
              inputArray[j] = inputArray[j+1];
              inputArray[j+1] = temp;
            }
  }

  // Method InsertionSort
  public static void insertionSort(int[] inputArray) {
		for (int i = 1; i < inputArray.length; i++) {
      int currentValue = inputArray[i];
			int j = i - 1;

      // Compare currentValue with each element on the left of it until an element smaller than it is found.
			while (j >= 0 && inputArray[j] > currentValue) {
				inputArray[j + 1] = inputArray[j];
				j--;
			}
      // Place currentValue at after the element smaller than it.
			inputArray[j + 1] = currentValue;
		}
	}

  // Method SelectionSort
  public static void selectionSort(int[] inputArray){
        int n = inputArray.length;
  
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (inputArray[j] < inputArray[min_idx])
                    min_idx = j;
  
            // Swap the found minimum element with the first
            // element
            int temp = inputArray[min_idx];
            inputArray[min_idx] = inputArray[i];
            inputArray[i] = temp;
        }
    }

    // Method CountingSort
    public static void countingSort(int[] inputArray) {
      int arrayLength = inputArray.length;
      if (arrayLength == 0)
          return;
      // find maximum and minimum values
      int max = inputArray[0], min = inputArray[0];
      for (int i = 1; i < arrayLength; i++) {
          if (inputArray[i] > max)
              max = inputArray[i];
          if (inputArray[i] < min)
              min = inputArray[i];
      }
      int range = max - min + 1;

      int[] count = new int[range];
      /** initialize the occurrence of each element in the count array **/
      for (int i = 0; i < arrayLength; i++)
          count[inputArray[i] - min]++;
      /** calculate sum of indexes **/
      for (int i = 1; i < range; i++)
          count[i] += count[i - 1];
      /** modify original array according to the sum count **/
      int j = 0;
      for (int i = 0; i < range; i++)
          while (j < count[i])
          inputArray[j++] = i + min;
  }
}
