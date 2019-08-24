package isaacsQuickSortWithoutGenerics;

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
// Imported for random number generator for the array.
import java.util.Random;

public class Driver 
{
	// Where the program begins.
	public static void main(String arg[])
	{
		menu();
	}
	//Generic insertion sort which works the same as a normal insertion sort.
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
	// This is my insertion sort method for the low,median value, and high cases.
	// This method will sort an array of number by inserting values which are < the key by swapping values. 
	public static void insertionSortForLowMedianValHigh(int[] array, int arraySize)
	{
		int i, key, j;
		// I'm cycling through the array where the index starts at 1 element past the very first.
		// This is so the key is assigned to the value to the right of the first.
		for(i = 1; i < arraySize; i++)
		{
			// The key is first assigned to element 1
			key = array[i];
			// j is going to be the element to which the key is compared to
			// as of now j is zero.
			j = i-1;
			
			// while the element j is > the key lets say j is 8 and the key is 6
			// I want to put 8 where 6 is and 6 where 8 is.
			while(j >= 0 && array[j] > key)
			{
				// If I get to hear via the while loop condition then I want to swap the elements, meaning element 0 if it's > the key
				// the key being the next element than I swap those elements to where element [0] become element [1] and element [1] is put
				// in front of element [0]
				// Example: array{5,4,9,6} 
				// j = 5. This is > than 4 which means 5 is put where 4 is. Those elements are swapped.
				array[j+1] = array[j];
				// After that j is reset to array[0], or one element before the key.
				j = j-1;
			}
			// The next element in the array becomes the key.
			array[j+1] = key;
		}
	}
	// This method takes the lower array bound p1 and puts that in a generic variable.
	// THen it swaps the elements p1 and p2 and  p2 becomes the temporary variable.
	public static <E> void swap(E[] A, int p1, int p2) 
	{
	  E temp = A[p1];
	  A[p1] = A[p2];
	  A[p2] = temp;
	}

	// This is the generic default case for quick sorting via the index of the array.
	// Quicksort method.
	// I'm giving the function a generic array and the range i-j of the array.
	static <E extends Comparable<? super E>> void qsortDefault(E[] A, int i, int j) 
	{   
		// this makes my starting pivot point the average spot in the array.
		// If the array contains 100 elements than my pivot point is virtually at the middle.
		int pivotindex = findpivotDefault(A, i, j);
		// swaps the pivot with j putting the pivot at the end.
		swap(A, pivotindex, j);       
		// k will be the first position in the right subarray
		int k = partition(A, i-1, j, A[j]);
		swap(A, k, j);              // Put pivot in place
		if((j - i) <= 10)
		{
			insertionSort(A,j-i);
		}
		else 
		{
			if ((k - i) > 1)
			{
				// This will sort the left partition; the left partition being the values to the left of the pivot point.
				qsortDefault(A, i, k - 1); 
			}
			if ((j - k) > 1)
			{
				// // This will sort the left partition; the left partition being the values to the right of the pivot point.
				qsortDefault(A, k + 1, j); 
			}
		}
	}
	
	
	static <E extends Comparable<? super E>> int findpivotDefault(E[] A, int i, int j)
	{ 
		return (i+j)/2; 
	}
	
	static void qsortLow(int[] A, int i, int j) 
	{      // Quicksort
		int pivotindex = findpivotLow(A, i, j); // Pick a pivot
		swapForLowMedianValHigh(A, pivotindex, j);       // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partitionForLowHighMedianVal(A, i-1, j, A[j]);
		swapForLowMedianValHigh(A, k, j);              // Put pivot in place
		if((j - i) <= 10)
		{
			insertionSortForLowMedianValHigh(A,j-i);
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
	
	static <E extends Comparable<? super E>> int findpivotLow(int[] A, int i, int j)
	{ 
		int lowestValue = A[i];
		int lowestIndx = -1;
		for(int index = i; index < j; index++)
		{
			if (Math.abs(A[index]) < lowestValue )
			{
				lowestValue = A[index];
				lowestIndx = index;
			}
		}
		return lowestIndx;  
	}
	static void qsortHigh(int[] A, int i, int j) 
	{      // Quicksort
		int pivotindex = findpivotHigh(A, i, j); // Pick a pivot
		swapForLowMedianValHigh(A, pivotindex, j);       // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partitionForLowHighMedianVal(A, i-1, j, A[j]);
		swapForLowMedianValHigh(A, k, j);              // Put pivot in place
		if((j - i) <= 10)
		{
			insertionSortForLowMedianValHigh(A,j-i);
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
	
	static  int findpivotHigh(int[] A, int i, int j)
	{ 
		int highest_val = A[i];
		int highest_indx = 10000000;
		for(int index = i; index < j; index++)
		{
			if (Math.abs(A[index]) > (highest_val))
			{
				highest_val = A[index];
				highest_indx = index;
			}
		}
		return highest_indx;  
	}
	static void qsortMedianVal(int[] A, int i, int j) 
	{      // Quicksort
		int pivotindex = findpivotMedianVal(A, i, j); // Pick a pivot
		swapForLowMedianValHigh(A, pivotindex, j);       // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partitionForLowHighMedianVal(A, i-1, j, A[j]);
		swapForLowMedianValHigh(A, k, j);              // Put pivot in place
		if((j - i) <= 10)
		{
			insertionSortForLowMedianValHigh(A,j-i);
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
	
	static int findpivotMedianVal(int[] A, int i, int j)
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
	public static void swapForLowMedianValHigh(int[] A, int p1, int p2) 
	{
	  int temp = A[p1];
	  A[p1] = A[p2];
	  A[p2] = temp;
	}
	static int partitionForLowHighMedianVal(int[] A, int l, int r,int pivot) 
	{
		do 
		{ // Move bounds inward until they meet
			while (A[++l] < (pivot) )
				;
			while ((r != 0) && (A[--r] > (pivot) ))
				;
			swapForLowMedianValHigh(A, l, r); // Swap out-of-place values
		} while (l < r); // Stop when they cross
		
		swapForLowMedianValHigh(A, l, r); // Reverse last, wasted swap
		
		return l; // Return first position in right partition
	}
	// Default generic partition method created by professor.
	static <E extends Comparable<? super E>> int partition(E[] A, int l, int r,E pivot) 
	{
		do 
		{ // Move bounds inward until they meet
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
		System.out.println("\tL - Sort List implementation low");
		System.out.println("\tH - Sort List implementation high");
		System.out.println("\tM - Sort List implementation Median value");
		System.out.println("\t1 - Print List implementation low");
		System.out.println("\t2 - Print List implementation high");
		System.out.println("\t3 - Print List implementation Median value");
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
		Integer arrayForDefault[] = null;
		int arrayForLowHighMedianVal[] = null;
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
				print(arrayForDefault);
				final long endTimeDefaultCase = System.currentTimeMillis();
				System.out.println("Total execution time: " + (endTimeDefaultCase - startTimeDefaultCase) );
				break;
			case 'G':
			case 'g':
				arrayForDefault = new Integer[10000];
				for (int i = 0; i < 10000; i++)
				{
					arrayForDefault[i] = new Integer(rand.nextInt(1000000) );
				}
				arrayForLowHighMedianVal = new int[10000];
				for (int i = 0; i < 10000; i++)
				{
					arrayForLowHighMedianVal[i] = new Integer(rand.nextInt(1000000) );
				}
				break;
			case 'S':
			case 's':
				qsortDefault(arrayForDefault,0,9999);
				break;
			case 'l':
			case 'L':
				qsortLow(arrayForLowHighMedianVal,0,9999);
				break;
			
			case 'h':
			case 'H':
				qsortHigh(arrayForLowHighMedianVal,0,9999);
				break;
			case 'm':
			case 'M':
				qsortMedianVal(arrayForLowHighMedianVal,0,9999);
				break;
			case '1':
				final long startTimeLowCase = System.currentTimeMillis();
				printForMedianVal(arrayForLowHighMedianVal);
				final long endTimeLowCase = System.currentTimeMillis();
				System.out.println("Total execution time: " + (endTimeLowCase - startTimeLowCase) );
				break;
			case '2':
				final long startTimeHighCase = System.currentTimeMillis();
				printForMedianVal(arrayForLowHighMedianVal);
				final long endTimeHighCase = System.currentTimeMillis();
				System.out.println("Total execution time: " + (endTimeHighCase - startTimeHighCase) );
				break;
			case '3':
				final long startTimeMedianValCase = System.currentTimeMillis();
				printForMedianVal(arrayForLowHighMedianVal);
				final long endTimeMedianValCase = System.currentTimeMillis();
				System.out.println("Total execution time: " + (endTimeMedianValCase - startTimeMedianValCase));
				break;
			}
		}
	}
}
