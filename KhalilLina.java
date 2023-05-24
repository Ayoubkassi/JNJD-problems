
class KhlalilLina {

    // khalil start at pos 0
    // lina start at pos 1
    // and they move by two steps at each time

    public static String firstOccurence(int k, int num) {
        int current;
        int itter = -1;
        while (num > 0) {
            current = num % 10;
            num /= 10;
            itter++;
            if (current == k)
                break;
        }

        return itter % 2 != 0 ? "Khalil" : "Lina";
    }

    public static void main(String[] args) {
        // System.out.println("Bismi Allah");

        String res = firstOccurence(1, 2019);
        System.out.println(res);
    }
}