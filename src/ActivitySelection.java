import java.util.ArrayList;

class Act{
	int hall = 0;
	int start;
	int finish;
	Act(int start, int finish){
		this.start = start;
		this.finish = finish;
	}
	
	public String toString() {
		return "Start at "+start+ ", finish at "+ finish+" in hall No."+hall;
	}
	public void setHall(int hall) {
		this.hall=hall;	
	}
}

public class ActivitySelection {

	public static void main(String[] args) {
		
		ArrayList<Act> acts = new ArrayList<Act>();
		
		acts.add(new Act(4,7));
		acts.add(new Act(6,9));
		acts.add(new Act(7,8));
		acts.add(new Act(1,3));
		acts.add(new Act(1,4));
		acts.add(new Act(2,5));
		acts.add(new Act(3,7));
		
		
		//sort the activities first
		ArrayList<Act> sorted = new ArrayList<Act>();		
		while(acts.size()>0) {
			Act min = acts.get(0);
			int mindex=0;
			for(int i=1;i<acts.size();i++) {
				if(acts.get(i).finish > min.finish) {
					//System.out.println(acts.get(i).finish+ " > "+min.finish);
					min = acts.get(i);
					mindex=i;
				}				
			}
			sorted.add(0,min);
			acts.remove(mindex);
		}
		
		/*for(int i=0;i<sorted.size();i++) {
			System.out.println(sorted.get(i).toString());
		}*/
		
		int hallNum=0;
		ArrayList<Act> labed = new ArrayList<Act>();
		boolean[] count = new boolean[sorted.size()];
		for(int i=0;i<count.length;i++) {
			count[i]=false;;
		}
		
		while(!checkCount(count)) {
			int i=0;
			for(int x=0;x<count.length;x++) {
				if(!count[x]) {
					i=x;
					//System.out.println("first i = "+i);
					break;
				}
			}
			//the first activities in each loop are always selected
			labed.add(sorted.get(i));
			labed.get(labed.size()-1).setHall(i);
			count[i]=true;
			//System.out.println("add: "+labed.get(labed.size()-1).toString());
			
			
			for(int j=1;j<sorted.size();j++) {				
				if(!count[j] && sorted.get(j).start>=sorted.get(i).finish){
					labed.add(sorted.get(j));
					labed.get(labed.size()-1).setHall(hallNum);;
					count[j]=true;
					i=j;
					//System.out.println("add: "+labed.get(labed.size()-1).toString());
				}
			}
			hallNum++;
		}		
		System.out.println("The minimum number of lecture halls required is "+(hallNum));		
		for(int i=0;i<labed.size();i++) {
			System.out.println(labed.get(i).toString());
		}
		
	}
	
	public static boolean checkCount(boolean[] count){
		for(int i=0;i<count.length;i++) {
			if(!count[i]) {
				//System.out.println(i+" uncheck");
				return false;
			}
		}
		return true;
	}

}
