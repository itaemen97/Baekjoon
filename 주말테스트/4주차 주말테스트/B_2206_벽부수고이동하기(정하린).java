package Daily.m2.d29;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2206_repeat {
	static int[][] arr,visit;
	static int N,M,min;
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/temp.txt")))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr=new int[N][M];
			visit=new int[N][M];
			min=Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++) {
				st=new StringTokenizer(br.readLine());
				String strTmp=st.nextToken();
				for(int j=0;j<M;j++) {
					arr[i][j]=strTmp.charAt(j)-'0';
					visit[i][j]=987654321;
				}
			}
			bfs();
			if(min==Integer.MAX_VALUE) sb.append(-1);
			else sb.append(min);
			System.out.println(sb.toString());
		} catch (Exception e) {}
	}
	
	static int[] dr= {0,-1,0,1,0};
	static int[] dc= {0,0,1,0,-1};
	static void bfs() {
		Queue<Target> queue = new LinkedList<>();
		queue.add(new Target(0,0,1,0));
		visit[0][0]=0;
		
		while(!queue.isEmpty()) {
			Target t=queue.poll();
			if(t.x==(N-1) &&t.y==(M-1) ) {
				min=Math.min(min, t.cnt);
				break;
			}
			
			for(int i=1;i<=4;i++) {
				int row=t.x+dr[i];
				int col=t.y+dc[i];
				if(row >=N || row<0 || col >=M || col <0) continue;
				if(visit[row][col]<=t.contact) continue;
				
				//나눠서 처리해야할듯
				if(arr[row][col]==0) {
					visit[row][col]=t.contact;
					queue.add(new Target(row,col,t.cnt+1,t.contact));
				}else {
					if(t.contact==0) {
						visit[row][col]=1;
						queue.add(new Target(row,col,t.cnt+1,t.contact+1));
					}
				}
			}
		}
	}
	
	static class Target{
		int x;
		int y;
		int cnt;
		int contact;
		public Target(int x, int y, int cnt, int contact) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.contact = contact;
		}
		@Override
		public String toString() {
			return "Target [x=" + x + ", y=" + y + ", cnt=" + cnt + ", contact=" + contact + "]";
		}
		
	}
}
