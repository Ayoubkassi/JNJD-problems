import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MehdiAzzuzMeeting {

    static class Pair {
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void print(List<Integer> arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static List<Integer> convertToMinutes(List<String> list) {
        List<Integer> res = new ArrayList<>();
        for (String val : list) {
            String first = val.split("-")[0];
            String second = val.split("-")[1];

            String hours = first.split(":")[0];
            String mins = first.split(":")[1];
            int hrs = Integer.parseInt(hours);
            int mns = Integer.parseInt(mins);
            res.add(hrs * 60 + mns);

            // second

            hours = second.split(":")[0];
            mins = second.split(":")[1];
            hrs = Integer.parseInt(hours);
            mns = Integer.parseInt(mins);
            res.add(hrs * 60 + mns);

        }

        return res;
    }

    public static List<Pair> getAvailableScheduler(List<String> mehdi, List<String> azzuz) {
        List<Pair> res = new ArrayList<>();
        // start working here
        // start by converting everything into minutes
        List<Integer> mehdiMns = convertToMinutes(mehdi);
        List<Integer> azzuzMns = convertToMinutes(azzuz);

        // here i must have some constant time in access
        Map<Integer, Integer> mapMehdi = new HashMap<>();
        Map<Integer, Integer> mapAzzuz = new HashMap<>();

        // sort our arraylist : special sort
        // so elements that are even elements

        List<Integer> mehdiStartMns = new ArrayList<>();
        List<Integer> azzuzStartMns = new ArrayList<>();

        int i = 0;
        for (int val : mehdiMns) {
            mapMehdi.put(val, i);
            if (i % 2 == 0)
                mehdiStartMns.add(val);
            i++;
        }

        i = 0;
        for (int val : azzuzMns) {
            mapAzzuz.put(val, i);
            if (i % 2 == 0)
                azzuzStartMns.add(val);
            i++;
        }

        Collections.sort(mehdiStartMns);
        Collections.sort(azzuzStartMns);

        List<Integer> sortedMnsMehdi = new ArrayList<>();
        List<Integer> sortedMnsAzzuz = new ArrayList<>();

        // have the perfect sorted array for mehdi
        for (int val : mehdiStartMns) {
            sortedMnsMehdi.add(val);
            sortedMnsMehdi.add(mehdiMns.get(mapMehdi.get(val) + 1));
        }

        // same for azzuz
        for (int val : azzuzStartMns) {
            sortedMnsAzzuz.add(val);
            sortedMnsAzzuz.add(azzuzMns.get(mapAzzuz.get(val) + 1));
        }

        // take gaps
        List<Pair> mehdiGap = new ArrayList<Pair>();
        for (i = 1; i < sortedMnsMehdi.size() - 1; i += 2) {
            mehdiGap.add(new Pair(sortedMnsMehdi.get(i), sortedMnsMehdi.get(i + 1)));
        }

        List<Pair> azzuzGap = new ArrayList<Pair>();
        for (i = 1; i < sortedMnsAzzuz.size() - 1; i += 2) {
            azzuzGap.add(new Pair(sortedMnsAzzuz.get(i), sortedMnsAzzuz.get(i + 1)));
        }

        // start looping pair by pair and comparaing with others

        for (Pair me : mehdiGap) {
            inner: for (Pair az : azzuzGap) {
                // verify that the gap length more thatn 30
                if (me.b < az.a || me.a > az.b || (me.b - me.a) < 30)
                    continue inner;
                //
                else {
                    // take the minimum intersection interval
                    // choose a point : a is the max
                    int aInter = Math.max(me.a, az.a);

                    // choose b point : b is the min between those 2
                    int bInter = Math.min(me.b, az.b);

                    if (bInter - aInter >= 30)
                        res.add(new Pair(aInter, bInter));
                }

            }
        }

        // print(mehdiMns);
        // print(azzuzMns);

        return res;
    }

    public static void main(String[] args) {
        System.out.println("Bismi Allah");

        List<String> mehdi = new ArrayList<>(List.of("10:30-11:30", "9:00-9:45", "14:15-14:45"));
        List<String> azzuz = new ArrayList<>(List.of("9:15-9:45", "11:15-11:45", "14:00-15:00"));

        List<Pair> res = getAvailableScheduler(mehdi, azzuz);
        System.out.println(res.size());

        for (Pair p : res) {
            System.out.println("begin: " + p.a + "-> end: " + p.b);
        }

        // 630 690 540 585 855 885
        // 555 585 675 705 840 900

        // available : 585-630 , 690-855
        // available : 585-675 , 705-840

        // sort
        // filter by difference last than 30
        //

    }
}