package baekjoon;

import java.util.*;
import java.io.*;

public class 벽부수고이동하기 {
	static class P {
		int r, c, cnt;
		boolean used;

		P(int r, int c, int cnt, boolean used) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.used = used;
		}
	}

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/b/벽부수고이동하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		boolean[][][] v = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		Queue<P> q = new LinkedList<>();
		q.offer(new P(0, 0, 1, false));
		v[0][0][0] = true;

		while (!q.isEmpty()) {
			P p = q.poll();
			if (p.r == N - 1 && p.c == M - 1) {
				System.out.println(p.cnt);
				return;
			}

			for (int k = 0; k < 4; k++) {
				int a = p.r + dr[k];
				int b = p.c + dc[k];

				if (a >= 0 && b >= 0 && a < N && b < M) {
					if (map[a][b] == 0) {
						if (!p.used && !v[a][b][0]) {
							v[a][b][0] = true;
							q.offer(new P(a, b, p.cnt + 1, p.used));
						} else if (p.used && !v[a][b][1]) {
							v[a][b][1] = true;
							q.offer(new P(a, b, p.cnt + 1, p.used));
						}
					} else if (map[a][b] == 1 && !v[a][b][1] && !p.used) {
						v[a][b][1] = true;
						q.offer(new P(a, b, p.cnt + 1, true));
					}
				}
			}
		}

		System.out.println(-1);
	}
}
