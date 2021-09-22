package bj16234;

import java.util.*;
import java.io.*;

public class Main_bj_16234_인구이동 {
	
	static int[][] arr;
	static boolean[][] visited;
	static boolean[][][] check;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int N,L,R;
	
	static boolean open() {
		boolean status = false;
		check = new boolean[N][N][4];
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				for(int d=1; d<3; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(0<=nr && nr<N && 0<=nc && nc<N) {
						int temp = Math.abs(arr[r][c]-arr[nr][nc]);
						if(L<=temp && temp<=R) {
							check[r][c][d] = true;
							check[nr][nc][(d+2)%4] = true;
							status = true;
						}
					}
				}
			}
		}
		
		return status;
	}
	
	static void bfs(int r, int c) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		int total = arr[r][c];
		int cnt = 1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			r = cur[0];
			c = cur[1];
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(check[r][c][d] && 0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc]) {
					visited[nr][nc] = true;
					total += arr[nr][nc];
					cnt++;
					q.offer(new int[] {nr,nc});
				}
			}
		}
		
		//System.out.println("total = "+total+", cnt = "+cnt);
		
		if(cnt>1) {
			total /= cnt;
			visited = new boolean[N][N];
			q.offer(new int[] {r,c});
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				r = cur[0];
				c = cur[1];
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(check[r][c][d] && 0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc]) {
						visited[nr][nc] = true;
						arr[nr][nc] = total;
						q.offer(new int[] {nr,nc});
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		check = new boolean[N][N][4];
		
		int answer = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(open()) {
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j])
					bfs(i,j);
				}
			}
			answer++;	
			
		}
		System.out.println(answer);
	}
}
