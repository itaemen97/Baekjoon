package 백준_0307;

import java.util.Scanner;

public class A_1799_비숍 {
	static int N;
	static int[][] map;
	static int ans;
	static int max;
	static int[] leftcheck = new int[21];
	static int[] rightcheck = new int[21];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		bisup(1,1,false,0);
		ans = max;
		max = 0;
		bisup(1,2,true,0);
		ans += max;
		System.out.println(ans);
	}
	static void bisup(int r, int c, boolean check, int cnt) {
		if(cnt > max) {
			max = cnt;
		}
		if(c > N) {
			r++;
			if(!check) {
				if(r % 2 == 0) {
					c = 2;
				} else {
					c = 1;
				}				
			} else {
				if(r % 2 == 0) {
					c = 1;
				} else {
					c = 2;
				}
			}
		}
		if(r > N) {
			return;
		}
		if(leftcheck[r+c] == 0 && rightcheck[10+r-c] == 0 && map[r][c] == 1) {
			leftcheck[r+c] = 1;
			rightcheck[10+r-c] = 1;
			bisup(r,c+2,check,cnt+1);
			leftcheck[r+c] = 0;
			rightcheck[10+r-c] = 0;			
		}
		bisup(r,c+2,check,cnt);
		return;
	}
}
