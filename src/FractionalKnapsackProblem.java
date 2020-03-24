import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsackProblem {

    public static void main(String[] args) {
        // thief entered the room with max capability to take x weight
        System.out.println("What is the capability of the thief?");
        Scanner in = new Scanner(System.in);
        double cap = in.nextDouble();
        // there's goods states, with weight and worth
        // 2, 6/7, 5, 6.1
        double[] goodsWeight = new double[]{10, 60, 20, 5};
        double[] goodsValue = new double[]{5, 70, 100, 60};
        double thiefIncome = fractionKnapsack(cap, goodsWeight, goodsValue);
        // how much can the thief takes?
        DecimalFormat dec = new DecimalFormat("#0.00");
        System.out.println("The thief can get upto $" + dec.format(thiefIncome) + " goods with capability of " + cap + " lb");
    }

    public static double fractionKnapsack(double cap, double[] weights, double[] values) {
        double res = 0;
        Fragment[] frac = new Fragment[weights.length];
        for (int i = 0; i < frac.length; i++) {
            frac[i] = new Fragment(values[i] / weights[i], i);
        }
        Arrays.sort(frac);
        // after sort based on the value/weight ratio while keep the index in record
        // take the most valuable goods, and fill the rest of the capability using the other goods
        for (int i = 0; i < frac.length; i++) {
            int index = frac[i].index;
            // make sure there's enough capability for the goods
            if (cap >= weights[index]) {
                cap -= weights[index];
                res += values[index];
            } else {
                res+=frac[i].valueRatio*cap;
                cap = 0;
            }
            if (cap <= 0) break;
        }
        return res;
    }

    static class Fragment implements Comparable<Fragment> {
        double valueRatio;
        int index;

        Fragment(double v, int o) {
            this.valueRatio =v;
            this.index = o;
        }

        @Override
        public int compareTo(Fragment fragment) {
            return this.valueRatio > fragment.valueRatio ? -1 : 1;
        }
    }

}
