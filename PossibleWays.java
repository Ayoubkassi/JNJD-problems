import java.util.ArrayList;
import java.util.List;

class PossibleWays {

    // bottom is 0,0 and top is n-1,m-1

    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static List<Integer> possibleDirections(Pos p, int n, int m) {
        List<Integer> res = new ArrayList<Integer>();
        if (p.x <= n - 1)
            res.add(1);
        if (p.y <= m - 1)
            res.add(2);

        return res;

    }

    // we must pass by at least k-1 point and check for all possible ways
    public static int allPaths(int n, int m, List<Pos> checkpoints, Pos p) {
        // so we will start with a brute force algorithme which is a DFS
        // generate all possible paths , and after we can check
        // generate all paths but at each time choose just the ones that minimize the
        // manhatan distance = (Xb-Xa)+(Yb-Ya)
        // i'm in the left bottom , i must just go to either right or top until reach my
        // goal
        if (p.x == n - 1 && p.y == m - 1)
            return 1;
        List<Integer> possibleDir = possibleDirections(p, n, m);
        for (int dir : possibleDir) {
            if (dir == 1) {
                return 1 + allPaths(n, m, checkpoints, new Pos(p.x + 1, p.y));
            } else if (dir == 2) {
                return 1 + allPaths(n, m, checkpoints, new Pos(p.x, p.y + 1));
            }
        }

        return 1;

    }

    public static void main(String[] args) {
        System.out.println("BIsmi Allah");

        List<Pos> checkpoints = new ArrayList<>(List.of(new Pos(2, 1), new Pos(3, 1), new Pos(5, 1)));

        int res = allPaths(4, 6, checkpoints, new Pos(0, 0));

        System.out.println(res);
    }
}