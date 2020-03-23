import java.util.Scanner;

public class CoinChangeProblem {

    public static void main(String[] args) {
        // assume we have infinite supply of each of the denominations in currency (sorted)
        double[] coinSup = new double[]{0.25, 1, 2, 5, 10, 20, 50, 100};
        // what will be our min number of coins we need to pay for certain amount?
        System.out.println("What value of coins do you need?");
        Scanner in = new Scanner(System.in);
        double value = in.nextDouble();
        int num = coinChange(coinSup, value);
        System.out.println("To get value of "+value+", we need at least "+num+" coin(s).");
    }

    public static int coinChange(double[] coinSup, double value) {
        int res = 0;
        for (int i = coinSup.length - 1; i >= 0; i--) {
//            System.out.println("i: "+i);
//            find the max value coin for the stock
            if (value - coinSup[i] >= 0) {
//                add the coin to the stock
                res += Math.floor(value / coinSup[i]);
//                remove corresponding value from out current value
                value %= coinSup[i];
//                System.out.println("new value: "+value);
            }
            if (value % coinSup[i] == 0) {
                break;
            }
        }
//        in case of any leftovers
        if(value>0){
            res+= Math.ceil(value / coinSup[0]);
        }
//        return stock number
        return res;
    }
}
