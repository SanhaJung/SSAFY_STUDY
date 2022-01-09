package bj21610;

import java.util.*;
import java.io.*;

public class Main_bj_21610_마법사상어와비바라기 {
	static int N,M;
	static int[][] map;
	static ArrayList<int[]> cloud = new ArrayList<>();
	static int[] dr = {0,0,-1,-1,-1,0,1,1,1}, dc = {0,-1,-1,0,1,1,1,0,-1}; // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static boolean[][] check;
	
	static void move(int d, int s) {
		int size = cloud.size();
		for(int i=0; i<size; i++) {
			int[] cur = cloud.get(0);
			int r=cur[0] , c = cur[1];
			for(int j=0; j<s; j++) {
				r += dr[d]+N;
				c += dc[d]+N;
			}
			r = r%N;
			c = c%N;
			cloud.remove(0);
			cloud.add(new int[] {r,c});
		}
	}
	
	static void rain() {
		check = new boolean[N][N];
		
		for(int[] cur : cloud) {
			map[cur[0]][cur[1]]++;
			check[cur[0]][cur[1]] = true;
		}
	}
	
	static void bug() {
		for(int[] cur : cloud) {
			int r = cur[0];
			int c = cur[1];
			int cnt = 0;
			for(int d=2; d<9; d+=2) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc]>0) {
					cnt++;
				}
			}
			map[r][c] += cnt;
		}
		
		cloud.clear();
	}
	
	static void make() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]>=2 && !check[i][j]) {
					cloud.add(new int[] {i,j});
					map[i][j]-=2;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		check = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cloud.add(new int[] {N-1,0});
		cloud.add(new int[] {N-1,1});
		cloud.add(new int[] {N-2,0});
		cloud.add(new int[] {N-2,1});
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			move(d,s);
			rain();
			bug();
			make();
		}
		
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				answer += map[i][j];
			}
		}
		
		System.out.println(answer);
	}
}
