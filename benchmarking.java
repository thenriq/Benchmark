
// Stack Overflow (https://stackoverflow.com/questions/29662026/when-i-do-division-in-java-i-lose-my-decimal-how-to-show-it)
// Coding with John (https://www.youtube.com/channel/UC42pOSNg804f1wCcj7qL0mA)
// Stack Overflow (https://stackoverflow.com/questions/31771003/how-to-make-a-progress-bar-in-java-console-application)
// GeeksforGeeks (https://www.geeksforgeeks.org/)

import java.util.Random;

public class benchmarking {
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

  //Method Shell sort
  public static void shellSort(int[] inputArray){
    int n = inputArray.length;
 
    // Start with a big gap, then reduce the gap
    for (int gap = n/2; gap > 0; gap /= 2){

      // Perform a gapped insertion sort for this gap size..
      // The first gap items a[0..gap-1] are already in gapped order
      // keep adding one more element until the full array is gap sorted.
      
      for (int i = gap; i < n; i += 1){
        // a[i] should be added to the components that have been gapped.
        // Sort a[i] into temp and drill a hole in position i
        int temp = inputArray[i];
        // Previous gap-sorted items should be shifted up 
        // until the right place for a[i] is discovered.
        int j;
        
        for (j = i; j >= gap && inputArray[j - gap] > temp; j -= gap)
        inputArray[j] = inputArray[j - gap];
        // place temp (the original a[i]) in its proper place
        inputArray[j] = temp;
      }
    }
  }

  public static void cocktailSort(int[] inputArray){
    boolean swapped = true;
    int start = 0;
    int end = inputArray.length;

    while (swapped == true){
      // Because the switched flag can be true from a previous iteration,
      // reset it when you join the loop.
      swapped = false;

      // As with the bubble sort, loop from bottom to top.
      for (int i = start; i < end - 1; ++i){
        if (inputArray[i] > inputArray[i + 1]){
          int temp = inputArray[i];
          inputArray[i] = inputArray[i + 1];
          inputArray[i + 1] = temp;
          swapped = true;
        }
      }

      // If nothing has changed, the array will be sorted.
      if (swapped == false)
        break;

      // Reset the swapped flag if necessary
      // so that it may be utilized in the following stage.
      swapped = false;

      // since the item at the end is in its proper place,
      // shift the end point back one
      end = end - 1;

        // conducting the same comparison as in the previous stage from top to bottom
      for (int i = end - 1; i >= start; i--){
        if (inputArray[i] > inputArray[i + 1]){
          int temp = inputArray[i];
          inputArray[i] = inputArray[i + 1];
          inputArray[i + 1] = temp;
          swapped = true;
        }
      }
      // Because the previous stage would have shifted the next 
      // smallest number to its proper place,
      // raise the beginning point.
      start = start + 1;
    }
  }

  public static void main(String[] args) throws InterruptedException {

    //Creating array containing length values
    //each looping iteration will create a new array based on content from "arr_size"
    //By editing the array below, we can choose which array sizes we are going to create to be tested
    int arr_size[]={100,250,500,750,1000,1250,2500,3750,5000,6250,7500,8750,10000};
    
    
    //Creating arrays which will contain benchmark average results
    double[] array_to_print_mergeSort = new double[arr_size.length];
    double[] array_to_print_bubbleSort = new double[arr_size.length];
    double[] array_to_print_countingSort = new double[arr_size.length];
    double[] array_to_print_shellSort = new double[arr_size.length];
    double[] array_to_print_cocktailSort = new double[arr_size.length];

    Random rand = new Random();

    int total_tests = 10;
    
    double total_mergesort = 0;
    double total_bubbleSort = 0;
    double total_countingSort = 0;
    double total_shellSort = 0;
    double total_cocktailSort = 0;

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
        total_mergesort += elapsedMillis; //cumulative result being passed to variable "average_mergeSort"
      }

      for (int k = 0; k < total_tests; k++){ //loop to perform benchmark "total_tests" times
        int[] numbers_bubbleSort    = scrambled_Array.clone(); // Creating/reseting array to original values before sorted
        long startTime = System . nanoTime ();
        bubbleSort(numbers_bubbleSort); // running method mergeSort on array "numbers_bubbleSort"
        long endTime = System . nanoTime ();
        long timeElapsed = endTime - startTime ;
        double elapsedMillis = (double)timeElapsed /1000000;
        total_bubbleSort += elapsedMillis; //cumulative result being passed to variable "average_bubbleSort"
      }

      for (int k = 0; k < total_tests; k++){ //loop to perform benchmark "total_tests" times
        int[] numbers_countingSort  = scrambled_Array.clone();
        long startTime = System . nanoTime ();
        countingSort(numbers_countingSort); // running method countingSort on array "numbers_countingSort"
        long endTime = System . nanoTime ();
        long timeElapsed = endTime - startTime ;
        double elapsedMillis = (double)timeElapsed /1000000;
        total_countingSort += elapsedMillis; //cumulative result being passed to variable "average_countingSort"
      }

      for (int k = 0; k < total_tests; k++){ //loop to perform benchmark "total_tests" times
        int[] numbers_shellSort  = scrambled_Array.clone();
        long startTime = System . nanoTime ();
        shellSort(numbers_shellSort); // running method shellSort on array "numbers_shellSort"
        long endTime = System . nanoTime ();
        long timeElapsed = endTime - startTime ;
        double elapsedMillis = (double)timeElapsed /1000000;
        total_shellSort += elapsedMillis; //cumulative result being passed to variable "average_shellSort"
      }

      for (int k = 0; k < total_tests; k++){ //loop to perform benchmark "total_tests" times
        int[] numbers_cocktailSort  = scrambled_Array.clone();
        long startTime = System . nanoTime ();
        cocktailSort(numbers_cocktailSort); // running method cocktailSort on array "numbers_cocktailSort"
        long endTime = System . nanoTime ();
        long timeElapsed = endTime - startTime ;
        double elapsedMillis = (double)timeElapsed /1000000;
        total_cocktailSort += elapsedMillis; //cumulative result being passed to variable "average_cocktailSort"
      }

      array_to_print_mergeSort[i] = total_mergesort / total_tests; //populating array "array_to_print_mergeSort" with average results
      array_to_print_bubbleSort[i] = total_bubbleSort / total_tests;  //populating array "array_to_print_bubbleSort" with average results
      array_to_print_countingSort[i] = total_countingSort / total_tests; //populating array "array_to_print_countingSort" with average results
      array_to_print_shellSort[i] = total_shellSort / total_tests; //populating array "array_to_print_shellSort" with average results
      array_to_print_cocktailSort[i] = total_cocktailSort / total_tests; //populating array "array_to_print_cocktailSort" with average results
      
      // setting all total variable below to zero,
      // so as it can be used to the next length size array test
      total_mergesort = 0;
      total_bubbleSort = 0;
      total_countingSort = 0;
      total_shellSort = 0;
      total_cocktailSort = 0;

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

    //cocktail sort
    System.out.print("\n");
    System.out.print("Cocktail Sort");
    for (int i = 0; i < array_to_print_cocktailSort.length; i++){
      System.out.print("\t"+(String.format("%-3.3f",array_to_print_cocktailSort[i])));
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

    //shell sort
    System.out.print("\n");
    System.out.print("Shell Sort");
    for (int i = 0; i < array_to_print_shellSort.length; i++){
      System.out.print("\t"+(String.format("%-3.3f",array_to_print_shellSort[i])));
    }
  }
}
