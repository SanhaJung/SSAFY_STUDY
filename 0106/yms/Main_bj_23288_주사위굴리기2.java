package bj23288;

import java.util.*;
import java.io.*;

public class Main_bj_23288_주사위굴리기2 {
	static int[][] map;
	static int[][] score_board;
	static int[] dice = {0,1,2,3,4,5,6};	// 위쪽,뒤쪽,오른쪽,왼쪽,앞쪽,아래쪽
	static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};	// 위쪽, 오른쪽, 아래쪽, 왼쪽
	static int N,M,K,cnt,answer;
	static int x=1, y=1;
	static boolean[][] visited;
	
	static void dfs(int r, int c, int num) {
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(0<nr && nr<=N && 0<nc && nc<=M && !visited[nr][nc] && num == map[nr][nc]) {
				visited[nr][nc] = true;
				cnt++;
				dfs(nr,nc,num);
			}
		}
	}
	
	static int move(int d) {
		if(0>= x+dr[d] || x+dr[d]>N || 0>=y+dc[d] || y+dc[d]>M) {
			if(d==0) d=2;
			else if(d==1) d=3;
			else if(d==2) d=0;
			else d=1;
		}		
		int nr = x+dr[d];
		int nc = y+dc[d];
		
		answer += score_board[nr][nc];
		x = nr; y = nc;
		
		rotate(d);
		
		if(dice[6]>map[x][y])	return (d+1)%4;
		else if (dice[6]<map[x][y]) return (4+d-1)%4;
		else return d;
	}
	
	static void rotate(int d) {
		int temp = 0;
		if(d==0) {
			temp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = temp;
		}
		else if (d==1) {
			temp = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = temp;
		}
		else if (d==2) {
			temp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = temp;
		}
		else {			
			temp = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = temp;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		score_board = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				visited = new boolean[N+1][M+1];
				cnt = 1;
				visited[i][j] = true;
				dfs(i,j,map[i][j]);
				
				score_board[i][j] = cnt*map[i][j];
			}
		}
		
		int d = 1;
		for(int i=0; i<K; i++) {
			d = move(d);
		}
		
		System.out.println(answer);
	}
}
