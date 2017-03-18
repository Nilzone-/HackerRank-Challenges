import java.util.*;

class Solution {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int Q = s.nextInt();
		DisjointSet ds = new DisjointSet(N+1);
		
		for(int i = 1; i <= N; i++) {
			ds.makeSet(i);
		}
		
		for(int i = 0; i < Q; i++) {
			int command = s.next().charAt(0);
			if(command == 'Q') {
				System.out.println(ds.getSetSize(s.nextInt()));
			} else {
				ds.union(s.nextInt(), s.nextInt());
			}
		}
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

		parents[first] = second;
		sizes[second] += sizes[first];
	}

	public int find(int i) {
		if (parents[i] != i) {
			parents[i] = find(parents[i]);
		}

		return parents[i];
	}

	public int getSetSize(int i) {
		return sizes[find(parents[i])];
	}

}