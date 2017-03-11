import java.util.*;

class Solution {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int cookies = s.nextInt();
		long minimumSweetness = s.nextLong();
		Cookie c = new Cookie(cookies, minimumSweetness);

		for(int i = 0; i < cookies; i++) {
			c.addCookie(s.nextInt());
		}
		System.out.println(c.calculateNumberOfOperations());
	}
}


class Cookie {
	private Queue<Integer> cookieQueue;
	private long minimumSweetness;
	
	Cookie(int cookies, long minimumSweetness) {
		this.cookieQueue = new PriorityQueue<>(cookies);
		this.minimumSweetness = minimumSweetness;
	}
	
	public void addCookie(int cookie) {
		cookieQueue.add(cookie);
	}
	
	public int calculateNumberOfOperations() {
		int numOfOperations = 0;
		if(cookieQueue.peek() >= minimumSweetness) {
			return 0;
		}
		
		while (cookieQueue.peek() < minimumSweetness && cookieQueue.size() > 1) {
			cookieQueue.add(1 * cookieQueue.remove() + 2 * cookieQueue.remove());
			numOfOperations++;
		}
		
		if(cookieQueue.peek() < minimumSweetness) {
			return -1;
		}
		return numOfOperations;
	}
}