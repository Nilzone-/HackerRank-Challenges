import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		DisjointSet ds = new DisjointSet(N*2+1);
		
		for(int i = 1; i <= N*2; i++) {
			ds.makeSet(i);
		}
		
		for(int i = 0; i < N; i++) {
			ds.union(s.nextInt(), s.nextInt());
		}
		
		//ds.print();
		int[] minMax = ds.findMinMax();
		System.out.println(minMax[0] + " " + minMax[1]);
		
	}
}

class DisjointSet {

	private int[] parents;
	private int[] sizes;

	DisjointSet(int N) {
		parents = new int[N];
		sizes = new int[N];
	}

	public void makeSet(int i) {
		parents[i] = i;
		sizes[i] = 1;
	}

	public void union(int a, int b) {
		int first = find(a);
		int second = find(b);

		if (first == second) {
			return;
		}

		if(sizes[first] < sizes[second]) {
			parents[first] = second;
			sizes[second] += sizes[first];
		} else {
			parents[second] = first;
			sizes[first] += sizes[second];
		}
	}

	public int find(int i) {
		if (parents[i] != i) {
			parents[i] = find(parents[i]);
		}

		return parents[i];
	}
	
	public boolean isConnected(int a, int b) {
		return find(a) == find(b);
	}
	
	public int[] findMinMax() {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(int i = 1; i < sizes.length; i++) {
			if(sizes[i] < min && sizes[i] > 1 && isConnected(sizes[find(parents[i])], sizes[i])) 
				min = sizes[i];
			if(sizes[i] > max && sizes[i] > 1 && isConnected(sizes[find(parents[i])], sizes[i])) 
				max = sizes[i];
		}
		return new int[]{min, max};
	}

	public void print() {
		for(int i = 1; i < parents.length; i++) {
			System.out.println("parents[" + i + "] = " + parents[i] + "\t\t\t" + 
							   "sizes[" + i + "] = " + sizes[i] + "\t\t\t" + 
							   "isConnected: " + isConnected(sizes[find(parents[i])], sizes[i]));
		}
	}
}