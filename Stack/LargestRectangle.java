import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		final int N = s.nextInt();
		long[] arr = new long[N];
				
		for(int i = 0; i < N; i++) {
			arr[i] = s.nextLong();
		}
		
		System.out.println(getLargestRectangle(arr, 0, 0));
	}
	
	public static long getLargestRectangle(long[] arr, int index, long max) {
		int count = 1;
		int i = index - 1;
		int j = index + 1;
		
		if(index == arr.length-1) return max;
		
		while(i > -1 && arr[index] <= arr[i]) {
			count++;
			i--;
		}
		while(j <= arr.length-1 && arr[index] <= arr[j]) {
			count++;
			j++;
		}
		
		max = (max < (arr[index] * count) ? (arr[index] * count) : max);
		
		return getLargestRectangle(arr, index + 1, max);
	}
}