import java.util.Arrays;
import java.util.Scanner;

class BirthdayCandles {

    public static void print(int[] arr) {
        for (int val : arr) {
            System.out.println(val);
        }
    }

    // she will display numbers from 0 to n
    public static int[] getCandels(int n) {
        int[] res = new int[10];
        Arrays.fill(res, 0);
        res[0] = 1;
        for (int i = 0; i <= n; i++) {
            int[] currentTab = new int[10];
            Arrays.fill(currentTab, 0);
            // res[j]++;
            // here i must have something wile the number is less than a value
            // i must reterive all that single numbers from that j
            int current = i;
            while (current > 0) {
                currentTab[current % 10]++;
                current /= 10;
            }

            // here do comparaison and assign
            for (int k = 0; k <= 9; k++) {
                res[k] = Math.max(res[k], currentTab[k]);
            }

        }

        // res[0] = 1;
        return res;
    }

    public static void main(String[] args) {
        // System.out.println("Bismi allah");
        Scanner myInput = new Scanner(System.in);
        int n = myInput.nextInt();
        int[] res = getCandels(n);
        print(res);

    }
}