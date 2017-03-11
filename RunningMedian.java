import java.io.*;
import java.util.*;

public class Solution {

		public static void main(String[] args) {
				Scanner s = new Scanner(System.in);
				int n = s.nextInt();
				
			 RunningMedian rm = new RunningMedian();
				
				while(n-- > 0) {
						rm.insert(s.nextInt());
						rm.rebalance();
						System.out.println(rm.getMedian());
				}
		}
	 
}


class RunningMedian {
	PriorityQueue<Integer> lower = new PriorityQueue<Integer>(10,
					new Comparator<Integer>() {
							public int compare(Integer arg0, Integer arg1) {
									return (arg0 < arg1) ? 1 : arg0 == arg1 ? 0 : -1;
							}
					}), higher = new PriorityQueue<Integer>();

	public void insert(Integer n) {
			if (lower.isEmpty() && higher.isEmpty())
					lower.add(n);
			else {
					if (n <= lower.peek())
							lower.add(n);
					else
							higher.add(n);
					rebalance();
			}
	}

	void rebalance() {
			if (lower.size() < higher.size() - 1)
					lower.add(higher.remove());
			else if (higher.size() < lower.size() - 1)
					higher.add(lower.remove());
	}

	public Double getMedian() {
			if (lower.isEmpty() && higher.isEmpty())
					return null;
			else if (lower.size() == higher.size())
					return (lower.peek() + higher.peek()) / 2.0;
			else
					return (lower.size() < higher.size()) ? (double) higher.peek() : (double) lower.peek();
	}
}