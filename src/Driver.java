// Isaac Hartzell
// CIS 2207 N02
// 3-19-18
// Chapter 7 & 8
/* Description:
  	Problem 7.5)

	Starting with the C++ code (or Java) for Quicksort given in this chapter, write a series
	of Quicksort implementations to test the following optimizations on a wide
	range of input data sizes. Try these optimizations in various combinations to
	try and develop the fastest possible Quicksort implementation that you can.

	(a) Look at more values when selecting a pivot.
	(b) Do not make a recursive call to qsort when the list size falls below a
	given threshold (code it to 10 for the moment), and use Insertion Sort to complete the sorting process.
	Test various values for the threshold size.

 */

import java.util.Scanner;
import java.util.Random;
import java.lang.*;

public class Driver 
{
	  public static void insertionSort(int[] array, int n)
	  {
		  int i, key, j;
		  for(i = 1; i < n; i++)
		  {
			  key = array[i];
			  j = i-1;
			  
			  while(j >= 0 && array[j] > key)
			  {
				  array[j+1] = array[j];
				  j = j-1;
			  }
			  array[j+1] = key;
		  }
	  }
	  public static void swap(int[] a, int p1, int p2) 
	  {
	    int temp = a[p1];
		a[p1] = a[p2];
		a[p2] = temp;
	  }
	  
	  //static <E extends Comparable<? super E>>
	  void qsort(int[] A, int i, int j, int threshold) 
	  {      // Quicksort
			int pivotindex = findpivot(A, i, j); // Pick a pivot
			swap(A, pivotindex, j);       // Stick pivot at end
			// k will be the first position in the right subarray
			int k = partition(A, i-1, j, A[j]);
			swap(A, k, j);              // Put pivot in place
			
			if ((j - i) > threshold){
				insertionSort(A, j-i);
			}else{
				//if ((k-i) > 1) 
					//qsort(A, i, k-1);   // Sort left partition
				//if ((j-k) > 1) 
					//qsort(A, k+1, j);   // Sort right partition
			}
	  }

	  /*
	  static void qsort(int[] A, int i, int j) 
	{      // Quicksort
		int pivotindex = findpivot(A, i, j); // Pick a pivot
		swap(A, pivotindex, j);       // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partition(A, i-1, j, A[j]);
		swap(A, k, j);              // Put pivot in place
		
		if ((k-i) > 1)
		{
			if((j-k) < 10)
			{
				qsort(A, i, k-1); // Sort left partition
			}  
		}
		else
		{
			insertionSort(A,j-i);
		}
		if ((j-k) > 1)
		{
			if((j-k) < 10)
			{
				qsort(A, k+1, j);  // Sort right partition
			}
		}
		else
		{
			insertionSort(A,j-i);
		}		
	}
	  */
	
	static int findpivot(int[] a, int i, int j)
	{ 
		return (i+j)/2; 
	}
	// Quick sort implemntation for the low value.
	
	static void qsortImplementationLow(int[] A, int i, int j) 
	{      // Quicksort
		int pivotindex = findpivot(A, i, j); // Pick a pivot
		swap(A, pivotindex, j);       // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partition(A, i-1, j, A[j]);
		swap(A, k, j);              // Put pivot in place
		
		if ((k-i) > 1)
		{
			if((j-k) < 10)
			{
				qsortImplementationLow(A, i, k-1); // Sort left partition
			}  
		}
		else
		{
			insertionSort(A,j-i);
		}
		if ((j-k) > 1)
		{
			if((j-k) < 10)
			{
				qsortImplementationLow(A, k+1, j);  // Sort right partition
			}
		}
		else
		{
			insertionSort(A,j-i);
		}		
	}
	// find pivot implementation for the low value.
	
	static int findpivotImplementatioLow(int[] a, int i, int j)
	{ 
		int lowestValue = a[i];
		int lowestIndx = -1;
		for(int ii = i; ii < j; ii++)
		{
			if (a[ii] < lowestValue)
			{
				lowestValue = a[ii];
				lowestIndx = ii;
			}
		}
		return lowestIndx; 
	}
	//Quick sort implementation for the high value
	 
	static void qsortImplementationHigh(int[] A, int i, int j) 
	{      // Quicksort
		int pivotindex = findpivot(A, i, j); // Pick a pivot
		swap(A, pivotindex, j);       // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partition(A, i-1, j, A[j]);
		swap(A, k, j);              // Put pivot in place
		
		if ((k-i) > 1)
		{
			if((j-k) < 10)
			{
				qsortImplementationHigh(A, i, k-1); // Sort left partition
			}  
		}
		else
		{
			insertionSort(A,j-i);
		}
		if ((j-k) > 1)
		{
			if((j-k) < 10)
			{
				qsortImplementationHigh(A, k+1, j);  // Sort right partition
			}
		}
		else
		{
			insertionSort(A,j-i);
		}	
	}
	//find pivot  implementation for the high value.
	static int findpivotImplementatioHigh(int[] A, int i, int j)
	{ 
		return (j); 
	}
	// This is the quick sort implementation for the median value not by the index.
	 
	static void qsortImplementationMidByVal(int[] A, int i, int j) 
	{      // Quicksort
		int pivotindex = findpivotImplementatioMidByVal(A, i, j); // Pick a pivot
		swap(A, pivotindex, j);       // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partition(A, i-1, j, A[j]);
		swap(A, k, j);              // Put pivot in place
		
		if ((k-i) > 1) 
			qsortImplementationMidByVal(A, i, k-1);   // Sort left partition
		if ((j-k) > 1) 
			qsortImplementationMidByVal(A, k+1, j);   // Sort right partition
	}
	 // This finds the pivot in regards to a mid value not the index of the array.
	static int findpivotImplementatioMidByVal(int[] A, int i, int j)
	{ 
		// The sum will hold the sum of all elements added together.
		// The average will have the average number of those elements.
		int sum = 0;
		int avg = 0;
	
		// Cycling the range of i and j from the array.
		for(int index = i; index < j; index++)
		{
			// This will add all elements in the array A.
			sum += A[index];
		}
		// The average is the sum of the elements in the array devided by the difference of the range
		avg = sum / (j - i);
		
		// I'm cycling through the array with the range being between i and j.
		int cloestIndx = -1;
		float smallestError = avg - A[i];
		for(int index = i; index < j; index++)
		{
			// if the element is equivalent to the average then I return the avg.
			float err = avg - A[index];
			if (err < smallestError){
				smallestError = err;
				cloestIndx = index;
			}
		}
		return cloestIndx;
	}
	
	static int partition(int[] a, int l, int r, int a2) 
	{
		do { // Move bounds inward until they meet
			while (a[++l] < (a2) )
				;
			while ((r != 0) && (a[--r] > (a2) ))
				;
			swap(a, l, r); // Swap out-of-place values
		} while (l < r); // Stop when they cross
		
		swap(a, l, r); // Reverse last, wasted swap
		
		return l; // Return first position in right partition
	}

	public static void main(String arg[])
	{
		//menu();
		/*array = new int[1000];
		for (int i = 0; i < 1000; i++)
		{
			array[i] = new Integer(rand.nextInt(1000000) );
		}
		int thresholds = [10, 5, 20, 1000, 10000];
		for(int t = 0;t < thresholds.length, t++){
			int threshold = thresholds[t];
			
			final long startTimeLowCase = System.currentTimeMillis();
			qsortImplementationHigh(array, 0,999);
			final long endTimeLowCase = System.currentTimeMillis();
			System.out.println("Total execution time: " + (endTimeLowCase - startTimeLowCase) );
			
			final long startTimeLowCase = System.currentTimeMillis();
			qsortImplementationLow(array, 0,999);
			final long endTimeLowCase = System.currentTimeMillis();
			System.out.println("Total execution time: " + (endTimeLowCase - startTimeLowCase) );
		}*/
	}
	
	public static char getChoice()
	{
		String line;
		
		Scanner user_Input = new Scanner(System.in);		
		
		System.out.println("Enter Choice:");
		System.out.println();
		System.out.println("\tG - Generate List");
		System.out.println("\tS - Sort List implementation index/default");
		System.out.println("\tP - Print List implementation index/default");
		System.out.println("\t1 - Sort List implementation low");
		System.out.println("\t2 - Sort List implementation high");
		System.out.println("\t3 - Print List implementation low");
		System.out.println("\t4 - Print List implementation high");
		System.out.println("\tQ - Quit");
		System.out.println();
		System.out.print("Choice: ");
		
		line = user_Input.nextLine();
		
		return line.charAt(0);
	}
	
	public static void print(int array[])
	{
		if (array == null)
		{
			System.out.println("Nothing to print");
		}
		else
		{
			for (int i = 0; i < array.length; i++)
			{
				System.out.println(array[i]);
			}
		}
	}
	
	public static void menu()
	{
		char choice = ' ';
		boolean isDone = false;
		int[] array = null;
		Random rand = new Random();
		
		while (! isDone)
		{
			choice = getChoice();
			
			switch (choice)
			{
			case 'Q':
			case 'q': 
				isDone = true;
				System.out.println("Good Bye");
				break;
			case 'P':
			case 'p':
				final long startTimeDefaultCase = System.currentTimeMillis();
				print(array);
				final long endTimeDefaultCase = System.currentTimeMillis();
				System.out.println("Total execution time: " + (endTimeDefaultCase - startTimeDefaultCase) );
				break;
			case 'G':
			case 'g':
				array = new int[1000];
				for (int i = 0; i < 1000; i++)
				{
					array[i] = new Integer(rand.nextInt(1000000) );
				}
				break;
			case 'S':
			case 's':
				// This is the default qsort done by index.
				//qsort(array,0,999);
				break;
				
			case '1':
				qsortImplementationLow(array,0,999);
				break;
			
			case '2': 
				qsortImplementationHigh(array,0,999);
				break;
			case '3':
				final long startTimeLowCase = System.currentTimeMillis();
				print(array);
				final long endTimeLowCase = System.currentTimeMillis();
				System.out.println("Total execution time: " + (endTimeLowCase - startTimeLowCase) );
				break;
			case '4':
				final long startTimeHighCase = System.currentTimeMillis();
				print(array);
				final long endTimeHighCase = System.currentTimeMillis();
				System.out.println("Total execution time: " + (endTimeHighCase - startTimeHighCase) );
				break;
			
			}
		}
	}
}
