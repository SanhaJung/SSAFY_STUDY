package 계절학기_알고리즘_대회;

import java.util.*;
import java.io.*;

public class Solution_1_광이삼의암벽등반 {
	
	static int M,N,L,answer;
	static int[][] map;
	static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
	
	static class Info {
		int r;
		int c;
		int distance;
		int[][] map;
		int cnt;
		
		public Info(int r, int c, int distance, int[][] map, int cnt) {
			this.r = r;
			this.c = c;
			this.distance = distance;
			this.map = map;
			this.cnt = cnt;
		}
		
	}
	
	static void bfs(int r, int c) {
		Queue<Info> q = new ArrayDeque<>();
		q.offer(new Info(r,c,L,map,0));
		
		while(!q.isEmpty()) {
			Info info = q.poll();
			
			if(info.distance>0) {
				for(int d=0; d<4; d++) {
					int nr = info.r+dr[d];
					int nc = info.c+dc[d];
					
					if(0<=nr && nr<M && 0<=nc && nc<N) {
						if(info.map[nr][nc]==3) {
							answer = Math.min(answer, info.cnt);
						}
						else if(info.map[nr][nc]==1) {
							q.offer(new Info(nr,nc,info.distance-1,info.map,info.cnt));
							info.map[nr][nc]=0;
							q.offer(new Info(nr,nc,L,info.map,info.cnt+1));
						}
						else if(info.map[nr][nc]==0){
							q.offer(new Info(nr,nc,info.distance-1,info.map, info.cnt));
						}
					}
				}
			}
		}
		
		if(answer == 10) answer = -1;
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/계절학기_알고리즘_대회/input_1.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(in.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[M][N];
			int r=0 ,c=0;
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==2) {
						r = i;
						c = j;
					}
						
				}
			}
			answer = 10;
			
			bfs(r,c);
			sb.append('#').append(tc).append(" ").append(answer).append('\n');
		}
		System.out.println(sb.toString());
		in.close();
	}
}
