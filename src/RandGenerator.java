import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandGenerator {

	public static void main(String[] args) {
		//a simple random number generator
		//will remove 7-10 talents randomly from the list
		int randSize = getRand(7,10);
		
		System.out.println("randSize = "+randSize);
		System.out.println("Get random personality size between 1-4: "+ (11-randSize));
		
		List<Integer> pers = new ArrayList<Integer>();
		
		for(int i=0;i<13;i++) {
			pers.add(i+1);
		}
		
		System.out.println("Get random personality list: ");
		
		//remove random personalities between 0-12
		//first remove 1 from 6 or 7
		int temp = getRand(6,7);
		System.out.println("Remove "+temp);
		pers.remove(temp);
		
		//remove 1 from 2 or 3
		temp = getRand(2,3);
		pers.remove(temp);
		System.out.println("Remove "+temp);
		
		//remove 10 if not evil(3);
		if(!pers.contains(3)) {
			pers.remove(10);
			System.out.println("Remove 10");
			randSize++;
		}
		
		for(int i=0;i<randSize;i++) {
			if(pers.size()==1) break;
			pers.remove(getRand(0,pers.size()-1));
		}
		

		for(int i=0;i<pers.size();i++) {
			System.out.print(pers.get(i)+" ");
		}
		
		System.out.println();
		int talentSize = getRand(5,6);
		System.out.println("Get random talent size between 1-2: " + (7-talentSize));
		
		List<Integer> tals = new ArrayList<Integer>();
		
		System.out.println("Get random talent list: ");
		//between 0-6
		for(int i=0;i<7;i++) {
			tals.add(i);
		}

		//remove random talent from the list
		for(int i=0;i<talentSize;i++) {
			tals.remove(getRand(0,tals.size()-1));
		}
		
		for(int i=0;i<tals.size();i++) {
			System.out.print(tals.get(i)+" ");
		}
		
		System.out.println();
	}
	
	
	public static int getRand(int l, int h) {
		Random random = new Random();
		int randInt = random.nextInt((h-l)+1)+l;
		return randInt;
	}

}
