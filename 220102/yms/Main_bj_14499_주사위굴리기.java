package bj14499;

import java.util.*;
import java.io.*;

public class Main_bj_14499_주사위굴리기 {
	static int[][] map;
	static int[] dice = {0,0,0,0,0,0,0};	// 위쪽,뒤쪽,오른쪽,왼쪽,앞쪽,아래쪽
	static int[] dr = {0,0,0,-1,1}, dc = {0,1,-1,0,0};	// 오른쪽, 왼쪽, 위쪽, 아래쪽
	static int N,M,x,y;
	
	static void move(int d) {
		int nr = x+dr[d];
		int nc = y+dc[d];
		
		if(0<=nr && nr<N && 0<=nc && nc<M) {
			x = nr;
			y = nc;
			rotate(d);
			if(map[nr][nc]==0) {
				map[nr][nc] = dice[6];
			}
			else {
				dice[6] = map[nr][nc];
				map[nr][nc] = 0;
			}
			System.out.println(dice[1]);
		}		
		return;
	}
	
	static void rotate(int d) {
		int temp = 0;
		if(d==1) {
			temp = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = temp;
		}
		else if (d==2) {
			temp = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = temp;
		}
		else if (d==3) {
			temp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = temp;
		}
		else {
			temp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = temp;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(in.readLine());
		
		for(int i=0; i<K; i++) {
			int d = Integer.parseInt(st.nextToken());
			move(d);
		}
	}
}
