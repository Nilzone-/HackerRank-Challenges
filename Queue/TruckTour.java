import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		
		Queue<PetrolAndDistance> q = new LinkedList<>();
		
		while(n-- > 0) {
			PetrolAndDistance pd = new PetrolAndDistance(s.nextLong(), s.nextLong());
			q.add(pd);
		}
		
		System.out.println(findBestPetrolPump(q, 0, 0, 0));
	}
	
	public static int findBestPetrolPump(Queue<PetrolAndDistance> q, int index, int station, int amount){
			
			PetrolAndDistance pd = q.remove();
			q.add(pd);
			if(pd.petrolIsLessThanDistance()) return findBestPetrolPump(q, 0, station + 1, 0);
			
			Iterator iterator = q.iterator();
			while(iterator.hasNext() && amount >= 0) {
				index++;
				amount += (pd.petrol - pd.distance);
				pd = (PetrolAndDistance) iterator.next();
			}
			if(index == q.size()) return station;
			return findBestPetrolPump(q, 0, station + 1, 0);            
		}
	
	
	static class PetrolAndDistance {
		private long petrol;
		private long distance;
		
		PetrolAndDistance(long petrol, long distance) {
			this.petrol = petrol;
			this.distance = distance;
		}
		
		public boolean petrolIsLessThanDistance() {
			return petrol < distance;
		}
		
	}
}