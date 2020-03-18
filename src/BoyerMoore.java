import java.util.ArrayList;
import java.util.Scanner;

class BTable{
	
	char ind;
	int value;
	BTable(char ind, int value){
		this.ind = ind;
		this.value = value;
	}	
}

class GTable{
	
	int k;
	int d;
	GTable(int k, int d){
		this.k = k;
		this.d = d;
	}	
}

public class BoyerMoore {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//get text
		System.out.println("Enter the text: ");
		String text = in.nextLine();
		
		//get pattern
		System.out.println("Enter the pattern: ");
		String patt = in.nextLine();
		in.close();
		
		//create table to store bad match
		ArrayList<BTable> tb = new ArrayList<BTable>();
		//add all characters in pattern to table, except the last one
		boolean set = false;
		for(int i=0;i<patt.length()-1;i++) {
			for(int j=0;j<tb.size();j++) {
				if(patt.charAt(i) == tb.get(j).ind) {
					int value = patt.length()-i-1;
					tb.set(j, new BTable(patt.charAt(i), value) );
					set=true;
					break;
				}
			}
			
			if(!set) {
				int value = patt.length()-i-1;
				tb.add(new BTable(patt.charAt(i), value) );
			}
		}
		//add the bad match for every other character
		tb.add(new BTable('*', patt.length()));
		
		//debug print for the bad match table
		for(int j=0;j<tb.size();j++) {
			System.out.println(tb.get(j).ind+" "+tb.get(j).value);
		}		
		
		ArrayList<GTable> gtb = new ArrayList<GTable>();
		//start create the good suffix table
		for(int i=patt.length()-1; i>0; i--) {
			int k = patt.length()-i;
			
			//get suffix string
			String suffix = "";
			
			for(int j=k;j>0;j--) {
				suffix+=patt.charAt(patt.length()-j);
			}
			System.out.println("suffix: "+suffix);
			
			int d = patt.length();			
			//found match with suffix in the pattern
			for(int j=i-1;j>=0;j--) {
				int match = Match(patt, suffix, j);
				//System.out.println("end: "+j);
				
				/*if(suffix.length()==5) {
					System.out.println("come to the place");					
				}*/
				
				//System.out.println("match at: "+match+" with patt ind at: "+j);
				//if found match with prev differ, set d
				if(match>0) {
					if(patt.charAt(match-1)!=patt.charAt(i-1)) {
						//System.out.println("match at: "+match+" with differ prev "+patt.charAt(match-1));
						d=i-match;
						break;
					}
				}
				
				//if no match found check if its cut by length
				//set a turner so when the turner is on, d value is already found
				boolean turner = false;				
				for(int x=0;x<suffix.length();x++) {
					//start check cut length suffix, start at index 0					
					if(suffix.charAt(x)==patt.charAt(0)) {
						//System.out.println("Match head at: "+ x+" at "+suffix.charAt(x));
						for(int y=0, temp=x; temp<suffix.length(); y++,temp++) {
							if(suffix.charAt(temp)!=patt.charAt(y)){
								//System.out.println("Match stopped at y="+y );
								break;
							}
							if(temp==suffix.length()-1) {
								d = patt.length() - k + x;
								//System.out.println("Match found and d value modified by y="	+ patt.charAt(y)+" temp="+temp+" x="+x +" y= "+y+" to new d="+d);
								turner = true;
								break;
							}
						}
					}
					if(turner) break;
				}
				
			}
			
			System.out.println("k = "+k+" d2 = "+d);
			gtb.add(new GTable(k, d));
		}
		
		/*for(int j=0;j<gtb.size();j++) {
			System.out.println(gtb.get(j).k+" "+gtb.get(j).d);
		}*/
	
		
		//gttatagctgatcgcggcgtagcggcgaa
		
		//put two tables together, starts match pattern into the text
		for(int i=patt.length()-1;i<text.length();i++) {
			int k = 0;
			
			//bad character rule, when the ending character doesn't match
			if(text.charAt(i)!=patt.charAt(patt.length()-1)) {
				//find mismatch character from bad match table
				for(int j=0;j<tb.size();j++) {
					if(text.charAt(i)==tb.get(j).ind) {
						i+=tb.get(j).value;
						/*System.out.println("Shift "+tb.get(j).value
								+" by bad table from char "+text.charAt(i)
								+" with new i="+i);*/
						if(i>text.length()) {
							System.out.println("No match found in text.");
							System.exit(0);
						}
					}
				}
			}
			
			//start check good suffix
			else{
				for(int j=patt.length()-1, toText=i; j>=0 && toText>=0;j--,toText--, k++) {
					if(text.charAt(toText)!=patt.charAt(j)) break;
					//get k for good suffix table
					
					//if match to the end of the pattern, pattern match in the text
					if(j==0) {
						System.out.println("Pattern found in the text, at position: "+(i+1-patt.length()));
						System.exit(0);
					}
				}
				
				//get good suffix value from gtb
				i+=gtb.get(k).d;
				if(i>text.length()) {
					System.out.println("No match found in text.");
					System.exit(0);
				}
			}
		}
	}
	
	//method to check k
	//patt as pattern, k as suffix, use end to cut the pattern
	static int Match(String patt, String k, int end) {
		int result = -1;
		
		for(int i=end; i>0;i--) {
			if(patt.charAt(i)==k.charAt(0)) {
				for(int j =0; j<patt.length();j++) {
					//no index out of range
					if(i+j>patt.length()-1) break;
					
					//if meet any not match character
					if(patt.charAt(i+j)!=k.charAt(j)) break;
					//if loop to the end which is all character matched
					else if(j==k.length()-1) {
						result = i;
						break;
					}
				}
			}
			//skip the rest if there is already a solution
			if(result!=-1) break;
		}
		
		return result;		
	}
}
