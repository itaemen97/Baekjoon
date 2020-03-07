package algo0229;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b_2206 {
    static int[][] map, check;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        map = new int[N][M];
        check = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
                check[i][j] = 2;
            }
        }

        Queue<Position> q = new LinkedList<>();
        q.add(new Position(0, 0, 1, 0));
        int ans = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Position tmp = q.poll();
            if (tmp.r == N - 1 && tmp.c == M - 1) {
                ans = Math.min(ans, tmp.cnt);
                break;
            }

            for (int k = 0; k < 4; k++) {
                int rr = tmp.r + dr[k];
                int cc = tmp.c + dc[k];

                if (rr >= N || cc >= M || rr < 0 || cc < 0 || check[rr][cc] <= tmp.wgt)
                    continue;


                if (map[rr][cc] == 0) {
                    check[rr][cc] = tmp.wgt;
                    q.add(new Position(rr, cc, tmp.cnt + 1, tmp.wgt + map[rr][cc]));
                } else if (map[rr][cc] == 1 && tmp.wgt == 0) {    //벽인데 아직 벽뚫은적없으면
                    check[rr][cc] = tmp.wgt + map[rr][cc];
                    q.add(new Position(rr, cc, tmp.cnt + 1, tmp.wgt + map[rr][cc]));
                }
            }

        }
        System.out.println(check[N-1][M-1] == 2 ? -1 : ans);
    }

    static class Position {
        int r, c, cnt, wgt;

        Position(int r, int c, int cnt, int wgt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.wgt = wgt;
        }
    }
}
