package bj15683;

import java.util.*;
import java.io.*;

public class Main_bj_15683_감시 {	//DFS 사용시 시간 초과, BFS 사용시 메모리 322896KB 실행시간 2232ms
	
	static int[] dr = {-1,0,1,0}; // 상 우 하 좌
	static int[] dc = {0,1,0,-1};
	static int N,M;
	static int[][] arr;
	static int[][] copyarr;
	static int answer = Integer.MAX_VALUE/2;
	static ArrayList<CCTV> list = new ArrayList<>();
	
	static class CCTV {
		int r;
		int c;
		int num;
		
		public CCTV(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}
		
	}
	
//	static void dfs(int r, int c, int d) { //그리기
//		for(int i=0; i<list.size(); i++) {
//			int nr = r+dr[d];
//			int nc = c+dc[d];
//			
//			if(0<=nr && nr<N && 0<=nc && nc<M ) {
//				if(copyarr[nr][nc]==6) return;
//				else if (copyarr[nr][nc]==0) copyarr[nr][nc] = -1;
//				dfs(nr,nc,d);
//			}
//		}		
//	}
	
	static void bfs(int r, int c, int d) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int nr = cur[0]+dr[d];
			int nc = cur[1]+dc[d];
			
			if(0<=nr && nr<N && 0<=nc && nc<M ) {
				if(copyarr[nr][nc]==6) break;
				else if (copyarr[nr][nc]==0) copyarr[nr][nc] = -1;
				q.offer(new int[] {nr,nc});				
			}
		}
	}
	
	static int[] numbers;	// 리스트에 들어있는 CCTV들의 순서에 맞춰 각각의 CCTV들이 볼 방향을 중복순열로 저장
	
	static void permutation(int cnt) {
		
		if(cnt==list.size()) {
			make();
			for(int i =0; i<numbers.length; i++) {
				int r = list.get(i).r;
				int c = list.get(i).c;
				int num = list.get(i).num;
				int d = numbers[i];
				setting(r,c,num,d);
			}
			
			calculate();
			return;
		}
		
		for(int i=0; i<4; i++) {
			numbers[cnt] = i;
			permutation(cnt+1);
		}
		
	}
	
	static void setting(int r, int c, int num, int d) {
		if(num == 1) {
			bfs(r,c,d);
		}
		else if (num == 2) {
			if(d==0 || d== 1) {
				bfs(r,c,1);
				bfs(r,c,3);
			}
			else {
				bfs(r,c,0);
				bfs(r,c,2);
			}
		}
		else if (num == 3) {
			if(d==0) {
				bfs(r,c,0);
				bfs(r,c,1);
			}
			else if(d==1) {
				bfs(r,c,1);
				bfs(r,c,2);
			}
			else if(d==2) {
				bfs(r,c,2);
				bfs(r,c,3);
			}
			else if(d==3) {
				bfs(r,c,3);
				bfs(r,c,0);
			}
		}
		else if (num == 4) {
			if(d==0) {
				bfs(r,c,3);
				bfs(r,c,0);
				bfs(r,c,1);
			}
			else if(d==1) {
				bfs(r,c,0);
				bfs(r,c,1);
				bfs(r,c,2);
			}
			else if(d==2) {
				bfs(r,c,1);
				bfs(r,c,2);
				bfs(r,c,3);
			}
			else if(d==3) {
				bfs(r,c,2);
				bfs(r,c,3);
				bfs(r,c,0);
			}
		}
		else if (num == 5) {
			bfs(r,c,0);
			bfs(r,c,1);
			bfs(r,c,2);
			bfs(r,c,3);
		}
		
	}
	
	static void make() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copyarr[i][j] = arr[i][j];
			}
		}
	}
	
	static void calculate() {
		int temp = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copyarr[i][j] == 0) 
					temp++;
				
			}
		}
		answer = Math.min(answer, temp);
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];		
		copyarr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]>0 && arr[i][j]<6)
					list.add(new CCTV(i,j,arr[i][j]));
			}
		}
		
		numbers = new int[list.size()];
		
		permutation(0);
		
		System.out.println(answer);
	}
}
