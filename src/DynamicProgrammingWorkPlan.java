import java.util.ArrayList;

class WorkInfo{	
	int HSR;	//high stress revenue
	int LSR;	//low stress revenue
	WorkInfo(int HSR, int LSR){
		this.HSR = HSR;
		this.LSR = LSR;
	}	
}

public class DynamicProgrammingWorkPlan {

	public static void main(String[] args) {

		ArrayList<WorkInfo> works = new ArrayList<WorkInfo>();
		
		works.add(new WorkInfo(10,5));
		works.add(new WorkInfo(1,50));
		works.add(new WorkInfo(10,5));
		works.add(new WorkInfo(10,1));
		
		//start checking
		System.out.println("Value of the optimal plan: "
		+ maxValue(works,true));
	}

	@SuppressWarnings("unchecked")
	public static int maxValue(ArrayList<WorkInfo> works, boolean workable) {
		if(works.size()==1) {
			//if the yesterday of the last day doesn't over stressed and high stress work benefit more
			if(workable == true) {
				if(works.get(0).HSR>=works.get(0).LSR) {
					return works.get(0).HSR;
				}
				else return works.get(0).LSR;
			}
			else return 0;
		}
		
		//make a copy of the list to use for the recursion
		ArrayList<WorkInfo> copy = (ArrayList<WorkInfo>) works.clone();
		copy.remove(copy.size()-1);
		
		//take a high stress work
		int hBene = 0;
		int lBene = 0;
		
		//if workable because the day later will take high stressed work
		if(workable) {
			//take a high stress work
			hBene = works.get(works.size()-1).HSR + maxValue(copy, false);
			//take a low stress work
			lBene = works.get(works.size()-1).LSR + maxValue(copy, true);
			
			return Math.max(hBene, lBene);
		}
		
		//else pass to next day
		else {
			return maxValue(copy, true);
		}
	}
}
