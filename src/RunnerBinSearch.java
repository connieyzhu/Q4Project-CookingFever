package notes2022;

public class RunnerBinSearch {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = 0;
		
		int[] data = new int[44000000];
		
		for(int i = 0; i < data.length; i++) {
			data[i] = i;
		}
		
		//setup a loop to do something 1000x
		
		for(int i = 0; i < 1000; i++) {
			//generate a random number [0, 44000000)
			
			int num = (int)(Math.random()*data.length);
			
			//look for element, but capture time!
			long start = System.currentTimeMillis(); //curr time
			sequentialSearch(data, num); //search
			long total = System.currentTimeMillis()-start;
			System.out.println(total);
			sum += total;
			
		}
		System.out.println(sum/1000);
		
	}
 
	public static int sequentialSearch(int[] arr, int target) {
		for(int i = 0; i < arr.length; i++) {
			if(target==arr[i]) { return i; }
		}
 
		return -1;
	}
 
	public static int binarySearch(int[] elements, int target) {
		int left = 0;
		int right = elements.length - 1;
		while (left <= right) {
			int middle = (left + right) / 2;
			if (target < elements[middle]) {
				right = middle - 1;
			} else if (target > elements[middle]) {
				left = middle + 1;
			} else {
				return middle;
			}
		}
		return -1;
	}
 
	public static int recursiveBinarySearch(int[] array, int start, int end, int target) {
		int middle = (start + end) / 2;
		// base case: check middle element
		if (target == array[middle]) {
			return middle;
		}
		// base case: check if we've run out of elements
		if (end < start) {
			return -1; // not found
		}
		// recursive call: search start to middle
		if (target < array[middle]) {
			return recursiveBinarySearch(array, start, middle - 1, target);
		}
		// recursive call: search middle to end
		if (target > array[middle]) {
			return recursiveBinarySearch(array, middle + 1, end, target);
		}
		return -1;
	}
 
}
