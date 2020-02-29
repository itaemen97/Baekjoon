import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj_2206 {
	static class Pos {
		int x, y, cnt, b;

		public Pos(int x, int y, int cnt, int b) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.b = b;
		}
	}

	static int N, M;
	static int[][] map;
	static boolean[][][] visit;
	static boolean flag;
	static int ans = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visit = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < str.length(); j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		bfs(0,0);
		if(flag)
			System.out.println(ans);
		else
			System.out.println("-1");

	}

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static void bfs(int x, int y) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(x, y, 1, 0));

		while (!q.isEmpty()) {
			Pos p = q.poll();

			if (p.x == N - 1 && p.y == M - 1) {
				ans = p.cnt;
				flag = true;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				// 이미 벽을 뿌셨다.
				if (p.b == 1) {
					// 다음을 벽이 뿌셔졌을때 방문하지 않았고 벽이아니면
					if (!visit[nx][ny][p.b] && map[nx][ny] == 0) {
						visit[nx][ny][p.b] = true;
						q.add(new Pos(nx, ny, p.cnt + 1, p.b));
					}
				} else {
					// 벽을 안뿌셨는데 벽이 나왔다. 그럼 뿌시고 가자
					if (map[nx][ny] == 1) {
						if (!visit[nx][ny][p.b + 1]) {
							visit[nx][ny][p.b + 1] = true;
							q.add(new Pos(nx, ny, p.cnt + 1, p.b + 1));
						}
					} else if (map[nx][ny] == 0) {
						if (!visit[nx][ny][p.b]) {
							visit[nx][ny][p.b] = true;
							q.add(new Pos(nx, ny, p.cnt + 1, p.b));
						}
					}
				}
			}
		}
	}
}
