package Algorizm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_2206_벽부수고이동하기 {
	static class Node {
		int x;
		int y;
		int v;
		int chance;

		Node(int x, int y, int v, int chance) {
			this.x = x;
			this.y = y;
			this.v = v;
			this.chance = chance;
		}
	}

	static int N, M;
	static int[][] Map;
	static boolean[][][] Visit;
	static int[] dx = new int[] { -1, 0, 1, 0 };
	static int[] dy = new int[] { 0, 1, 0, -1 };
	static Queue<Node> queue = new LinkedList<>();
	static int Min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Map = new int[N][M];
		Visit = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < M; j++) {
				Map[i][j] = Character.getNumericValue(str.charAt(j));
			}
		}

		for (int i = 0; i < 2; i++) {
			Visit[0][0][i] = true;
		}
		if (N == 1 && M == 1) {
			System.out.println(1);
		} else {
			BFS();
			System.out.println(Min == Integer.MAX_VALUE ? "-1" : Min);
		}

	}

	public static void BFS() {
		queue.add(new Node(0, 0, 1, 0));

		while (!queue.isEmpty()) {
			Node t = queue.poll();
			if (t.x == N - 1 && t.y == M - 1) {
				Min = Math.min(Min, t.v);
				// break;
			}

			for (int i = 0; i < 4; i++) {
				int x = t.x + dx[i];
				int y = t.y + dy[i];

				if (x < 0 || y < 0 || x >= N || y >= M) {
					continue;
				}

				if (!Visit[x][y][t.chance] && Map[x][y]==0) { //1인애들이 휘젓고 다녀도 1인애들이 계속 지나갈수있기때문에 가능
					Visit[x][y][t.chance] = true;
					queue.add(new Node(x, y, t.v + 1, t.chance));
				}

				int temp = t.chance + 1;
				if (temp <= 1 && !Visit[x][y][temp]) {
					Visit[x][y][temp] = true;
					queue.add(new Node(x, y, t.v + 1, temp));
				}

			}

		}

	}
}
