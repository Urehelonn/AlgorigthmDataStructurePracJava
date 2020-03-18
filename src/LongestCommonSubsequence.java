import java.util.Scanner;

public class LongestCommonSubsequence {
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("The first String is: ");
		String x = in.next();
		System.out.println("The first String is: ");
		String y = in.next();
		in.close();				
		
		//start checking with recursive process
		String res = LCS("", x, y);
		
		System.out.println("The longest common subsequence in those two strings is: "
				+ res);
	}
	
	//i as the left over of X, j as the left over of y
	public static String LCS(String result, String x, String y) {
		
		//checking
//		System.out.println("x: "+x);
//		System.out.println("y: "+y);
//		System.out.println("res: "+result);
		
		if(x.length()==0 || y.length()==0) {
			return result;
		}
		
		if(x.charAt( x.length()-1 ) == y.charAt( y.length()-1) ) {
			return LCS(result=x.charAt( x.length()-1 )+result,
					x.substring(0, x.length()-1),y.substring(0, y.length()-1));
		}
		
		else {
			String res1 = LCS(result, x.substring(0, x.length()-1), y);
			String res2 = LCS(result, x, y.substring(0, y.length()-1));
			if(res1.length()>=res2.length()) return res1;
			else return res2;
		}
	}
}
