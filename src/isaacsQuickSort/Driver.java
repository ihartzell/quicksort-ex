package isaacsQuickSort;

//Isaac Hartzell
//CIS 2207 N02
//3-19-2018
//Chapter 7 & 8
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

public class Driver 
{
	public static void main(String arg[])
	{
		menu();
	}
	
	public static <E extends Comparable<? super E>> void insertionSort(E[] array, int arraySize)
	{
		int i, j;
		E key;
		for(i = 1; i < arraySize; i++)
		{
			key = array[i];
			j = i-1;
			  
			while(j >= 0 && key.compareTo(array[j]) < 1)
			{
				array[j+1] = array[j];
				j = j-1;
			}
			array[j+1] = key;
		}
	}
	public static <E extends Comparable<? super E>> void insertionSortForMedianVal(int[] array, int arraySize)
	{
		 int i, key, j;
		  for(i = 1; i < arraySize; i++)
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
	public static <E> void swap(E[] A, int p1, int p2) 
	{
	  E temp = A[p1];
	  A[p1] = A[p2];
	  A[p2] = temp;
	}

	
	
	static <E extends Comparable<? super E>> void qsortDefault(E[] A, int i, int j) 
	{      // Quicksort
		int pivotindex = findpivotDefault(A, i, j); // Pick a pivot
		swap(A, pivotindex, j);       // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partition(A, i-1, j, A[j]);
		swap(A, k, j);              // Put pivot in place
		if((j - i) > 10)
		{
			insertionSort(A,j-i);
		}
		else 
		{
			if ((k - i) > 1)
			{
				qsortDefault(A, i, k - 1); // Sort left partition
			}
			if ((j - k) > 1)
			{
				qsortDefault(A, k + 1, j); // Sort right partition
			}
		}
	}
	
	
	static <E extends Comparable<? super E>> int findpivotDefault(E[] A, int i, int j)
	{ 
		return (i+j)/2; 
	}
	
	static <E extends Comparable<? super E>> void qsortLow(E[] A, int i, int j) 
	{      // Quicksort
		int pivotindex = findpivotLow(A, i, j); // Pick a pivot
		swap(A, pivotindex, j);       // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partition(A, i-1, j, A[j]);
		swap(A, k, j);              // Put pivot in place
		if((j - i) > 10)
		{
			insertionSort(A,j-i);
		}
		else 
		{
			if ((k - i) > 1)
			{
				qsortLow(A, i, k - 1); // Sort left partition
			}
			if ((j - k) > 1)
			{
				qsortLow(A, k + 1, j); // Sort right partition
			}
		}
	}
	
	static <E extends Comparable<? super E>> int findpivotLow(E[] A, int i, int j)
	{ 
		E lowestValue = A[i];
		int lowestIndx = -1;
		for(int index = i; index < j; index++)
		{
			if (A[index].compareTo(lowestValue) > 0)
			{
				lowestValue = A[index];
				lowestIndx = index;
			}
		}
		return lowestIndx;  
	}
	static <E extends Comparable<? super E>> void qsortHigh(E[] A, int i, int j) 
	{      // Quicksort
		int pivotindex = findpivotHigh(A, i, j); // Pick a pivot
		swap(A, pivotindex, j);       // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partition(A, i-1, j, A[j]);
		swap(A, k, j);              // Put pivot in place
		if((j - i) > 10)
		{
			insertionSort(A,j-i);
		}
		else 
		{
			if ((k - i) > 1)
			{
				qsortHigh(A, i, k - 1); // Sort left partition
			}
			if ((j - k) > 1)
			{
				qsortHigh(A, k + 1, j); // Sort right partition
			}
		}
	}
	
	static <E extends Comparable<? super E>> int findpivotHigh(E[] A, int i, int j)
	{ 
		E highest_val = A[i];
		int highest_indx = 10000000;
		for(int index = i; index < j; index++)
		{
			if (A[index].compareTo(highest_val) > 0)
			{
				highest_val = A[index];
				highest_indx = index;
			}
		}
		return highest_indx;  
	}
	static <E extends Comparable<? super E>> void qsortMedianVal(int[] A, int i, int j) 
	{      // Quicksort
		int pivotindex = findpivotMedianVal(A, i, j); // Pick a pivot
		swapForMedianVal(A, pivotindex, j);       // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partitionForMedianVal(A, i-1, j, A[j]);
		swapForMedianVal(A, k, j);              // Put pivot in place
		if((j - i) > 10)
		{
			insertionSortForMedianVal(A,j-i);
		}
		else 
		{
			if ((k - i) > 1)
			{
				qsortMedianVal(A, i, k - 1); // Sort left partition
			}
			if ((j - k) > 1)
			{
				qsortMedianVal(A, k + 1, j); // Sort right partition
			}
		}
	}
	
	static <E extends Comparable<? super E>> int findpivotMedianVal(int[] A, int i, int j)
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
				int smallestError = avg - A[i];
				for(int index = i; index < j; index++)
				{
					// if the element is equivalent to the average then I return the avg.
					int err = avg - A[index];
					if (err < smallestError){
						smallestError = err;
						cloestIndx = index;
					}
				}
				return cloestIndx;
	}
	public static <E> void swapForMedianVal(int[] A, int p1, int p2) 
	{
	  int temp = A[p1];
	  A[p1] = A[p2];
	  A[p2] = temp;
	}
	static <E extends Comparable<? super E>> int partitionForMedianVal(int[] A, int l, int r,int pivot) 
	{
		do { // Move bounds inward until they meet
			while (A[++l] < (pivot) )
				;
			while ((r != 0) && (A[--r] > (pivot) ))
				;
			swapForMedianVal(A, l, r); // Swap out-of-place values
		} while (l < r); // Stop when they cross
		
		swapForMedianVal(A, l, r); // Reverse last, wasted swap
		
		return l; // Return first position in right partition
	}
	static <E extends Comparable<? super E>> int partition(E[] A, int l, int r,
			E pivot) 
	{
		do { // Move bounds inward until they meet
			while (A[++l].compareTo(pivot) < 0)
				;
			while ((r != 0) && (A[--r].compareTo(pivot) > 0))
				;
			swap(A, l, r); // Swap out-of-place values
		} while (l < r); // Stop when they cross
		
		swap(A, l, r); // Reverse last, wasted swap
		
		return l; // Return first position in right partition
	}
	
	public static char getChoice()
	{
		String line;
		
		Scanner user_Input = new Scanner(System.in);		
		
		System.out.println("Enter Choice:");
		System.out.println();
		System.out.println("\tG - Generate List");
		System.out.println("\tS - Sort List");
		System.out.println("\tP - Print List");
		System.out.println("\t1 - Sort List implementation low");
		System.out.println("\t2 - Sort List implementation high");
		System.out.println("\t3 - Sort List implementation Median value");
		System.out.println("\t4 - Print List implementation low");
		System.out.println("\t5 - Print List implementation high");
		System.out.println("\t6 - Print List implementation Median value");
		System.out.println("\tQ - Quit");
		System.out.println();
		System.out.print("Choice: ");
		
		line = user_Input.nextLine();
		
		return line.charAt(0);
	}
	
	public static void print(Integer array[])
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
	public static void printForMedianVal(int array[])
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
		Integer array[] = null;
		int arrayForMedianVal[] = null;
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
				array = new Integer[100];
				for (int i = 0; i < 100; i++)
				{
					array[i] = new Integer(rand.nextInt(10000) );
				}
				/*arrayForMedianVal = new int[100];
				for (int i = 0; i < 100; i++)
				{
					arrayForMedianVal[i] = new Integer(rand.nextInt(10000) );
				}*/
				break;
			case 'S':
			case 's':
				qsortDefault(array,0,99);
				break;
			case '1':
				qsortLow(array,0,999);
				break;
			
			case '2': 
				qsortHigh(array,0,999);
				break;
			case '3':
				qsortMedianVal(arrayForMedianVal,0,999);
				break;
			case '4':
				final long startTimeLowCase = System.currentTimeMillis();
				print(array);
				final long endTimeLowCase = System.currentTimeMillis();
				System.out.println("Total execution time: " + (endTimeLowCase - startTimeLowCase) );
				break;
			case '5':
				final long startTimeHighCase = System.currentTimeMillis();
				print(array);
				final long endTimeHighCase = System.currentTimeMillis();
				System.out.println("Total execution time: " + (endTimeHighCase - startTimeHighCase) );
				break;
			case '6':
				final long startTimeMedianValCase = System.currentTimeMillis();
				printForMedianVal(arrayForMedianVal);
				final long endTimeMedianValCase = System.currentTimeMillis();
				System.out.println("Total execution time: " + (endTimeMedianValCase - startTimeMedianValCase));
				break;
			}
		}
	}
}

