package Daily.m3.d07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B13459 {
	static char[][] arr;
	static Temp RED,BLUE,GOAL;
	static int max;
	static boolean[][][][] visited;
	static int N,M;
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/temp.txt")))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			max=0;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new char[N+1][M+1];
			visited= new boolean[N+1][M+1][N+1][M+1];
			for(int i=1;i<=N;i++) {
				st=new StringTokenizer(br.readLine());
				String strTmp = st.nextToken();
				for(int j=1;j<=M;j++) {
					arr[i][j]=strTmp.charAt(j-1);
					if(arr[i][j]=='.' || arr[i][j]=='#') continue;
					if(arr[i][j]=='R') RED=new Temp(i,j);
					else if(arr[i][j]=='O') GOAL=new Temp(i,j);
					else if(arr[i][j]=='B') BLUE=new Temp(i,j);
				}
			}
			bfs();
			sb.append(max).append("\n");
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static int[] dr= {0,-1,0,1,0};
	static int[] dc= {0,0,1,0,-1};
	private static void bfs() {
		Queue<Target> queue = new LinkedList<>();
		queue.add(new Target(RED.x,RED.y,BLUE.x,BLUE.y,0));
		visited[RED.x][RED.y][BLUE.x][BLUE.y]=true;
		//방문체크를 해주어야 하나?
		boolean rSuccess=false;
		boolean bSuccess=false;
		
		while(!queue.isEmpty()) {
			Target t=queue.poll();
			
			if(t.cnt>9) continue;
			boolean rPossible=true;
			boolean bPossible=true;
			for(int i=1;i<=4;i++) {
				boolean dual=false;
				rSuccess=false;
				bSuccess=false;
				rPossible=true;
				bPossible=true;
				int rRow=t.rX;
				int rCol=t.rY;
				int bRow=t.bX;
				int bCol=t.bY;
				while(rPossible) {
					rRow+=dr[i];
					rCol+=dc[i];
					if(rRow==GOAL.x && rCol ==GOAL.y) {
						rSuccess=true;
						break;
					}
					if(rRow >=N || rRow <1 || rCol >=M || rCol <1 || arr[rRow][rCol]=='#' || (rRow ==bRow && rCol == bCol)) rPossible=false;
				}
				if(!rSuccess) {
					if((rRow ==bRow && rCol == bCol)) {
						dual=true;
					}
					rRow-=dr[i];
					rCol-=dc[i];
				}else {
					rRow=0;
					rCol=0;
				}
				if(!dual && !rSuccess) {
					while(bPossible) {
						bRow+=dr[i];
						bCol+=dc[i];
						if(bRow>=N || bRow <1 || bCol >=M || bCol<1 || arr[bRow][bCol]=='#' || (rRow==bRow && rCol == bCol) ) bPossible=false;
						if(bRow==GOAL.x && bCol==GOAL.y) break;
						if(!bPossible) {
							if(visited[rRow][rCol][bRow-dr[i]][bCol-dc[i]]) continue;
							queue.add(new Target(rRow,rCol,bRow-dr[i],bCol-dc[i],t.cnt+1));
							visited[rRow][rCol][bRow-dr[i]][bCol-dc[i]]=true;
						}
					}
				}else if(dual) {
					while(bPossible) {
						bRow+=dr[i];
						bCol+=dc[i];
						if(bRow>=N || bRow <1 || bCol >=M || bCol<1 || arr[bRow][bCol]=='#') bPossible=false;
						if(bRow==GOAL.x && bCol==GOAL.y) break;
						if(!bPossible) {
							if(visited[bRow-2*dr[i]][bCol-2*dc[i]][bRow-dr[i]][bCol-dc[i]]) continue;
							queue.add(new Target(bRow-2*dr[i],bCol-2*dc[i],bRow-dr[i],bCol-dc[i],t.cnt+1));
							visited[bRow-2*dr[i]][bCol-2*dc[i]][bRow-dr[i]][bCol-dc[i]]=true;
						}
					}
				}else if(rSuccess) {
					while(bPossible) {
						bRow+=dr[i];
						bCol+=dc[i];
						if(bRow>=N || bRow <1 || bCol >=M || bCol<1 || arr[bRow][bCol]=='#') bPossible=false;
						if(bRow==GOAL.x && bCol==GOAL.y) {
							bSuccess=true;
							break;
						}
					}
					if(!bSuccess) {
						break;
					}
				}
			}
			if(rSuccess && !bSuccess) {
				max=1;
				break;
			}
		}
	}

	static class Target{
		int rX;
		int rY;
		int bX;
		int bY;
		int cnt;
		static int dir;
		public Target(int rX, int rT, int bX, int bY,int cnt) {
			this.rX = rX;
			this.rY = rT;
			this.bX = bX;
			this.bY = bY;
			this.cnt= cnt;
		}
		@Override
		public String toString() {
			return "[" + rX + "," + rY + "," + bX + "," + bY + ", cnt=" + cnt + "]";
		}
	}
	
	static class Temp{
		int x;
		int y;
		public Temp(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Temp [x=" + x + ", y=" + y + "]";
		}
		
	}
}

