import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;

class Solution {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int orders = s.nextInt();
		PizzaShop ps = new PizzaShop(orders);
	
		while(orders-- > 0) {
			ps.addOrder(s.nextLong(), s.nextLong());
		}
		System.out.println(ps.calculateMinimumAverageWaitingTime());
	}
}

class PizzaShop {	
	private int orders;
	private Queue<Order> arrivalQueue;	
	private Queue<Order> cookingQueue;
		
	PizzaShop(int orders) {
		this.orders = orders;
		this.arrivalQueue = new PriorityQueue<>(orders, 
				new Comparator<Order>() {
					public int compare(Order o1, Order o2) {
						return (o1.arrivalTime < o2.arrivalTime) ? -1 : (o1.arrivalTime == o2.arrivalTime) ? 0 : 1;
					}
				});
		
		this.cookingQueue = new PriorityQueue<>(orders, 
				new Comparator<Order>() {
					public int compare(Order o1, Order o2) {
						return (o1.cookTime < o2.cookTime) ? -1 : (o1.cookTime == o2.cookTime) ? 0 : 1;
					}
				});
	}
	
	public void addOrder(long arrivalTime, long cookTime) {
		arrivalQueue.add(new Order(arrivalTime, cookTime));
	}
	
	public long calculateMinimumAverageWaitingTime() {
		long time = arrivalQueue.peek().arrivalTime;
		long waitTime = 0;
		Order current = null;
		
		while(!arrivalQueue.isEmpty() || !cookingQueue.isEmpty()) {
			if(!arrivalQueue.isEmpty()) {
				while(arrivalQueue.peek() != null && time >= arrivalQueue.peek().arrivalTime) {
					current = arrivalQueue.remove();
					cookingQueue.add(current);
				}
			}
			
			if(!cookingQueue.isEmpty()) {
				if(time >= cookingQueue.peek().arrivalTime) {
					current = cookingQueue.remove();
					time += current.cookTime;
					current.completionTime  = time;
					waitTime += (current.completionTime - current.arrivalTime);
					continue;
				}
			}
			time++;
		}
		
		return waitTime / orders;
	}
	 
	private static class Order {
		public long arrivalTime;
		public long cookTime;
		public long completionTime;
		
		Order(long arrivalTime, long cookTime) {
			this.arrivalTime = arrivalTime;
			this.cookTime = cookTime;
		}
	}

}