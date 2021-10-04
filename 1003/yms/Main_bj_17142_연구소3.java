package bj17142;

import java.util.*;
import java.io.*;

public class Main_bj_17142_연구소3 {	
	static int N,M,blank,time;
	static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
	static int[][] map;
	static ArrayDeque<int[]> q = new ArrayDeque<>();
	static ArrayList<int[]> virus = new ArrayList<>();
	static int answer = Integer.MAX_VALUE/2;
	static int[] numbers;
	static boolean[][] visited = new boolean[N][N];
	
	static void combination(int start, int cnt) {
		if(cnt==M) {
			q.clear();
			visited =new boolean[N][N];
			for(int i=0; i<numbers.length; i++) {
				int[] cur = virus.get(numbers[i]);
				visited[cur[0]][cur[1]] = true;
				q.offer(new int[] {cur[0], cur[1]});
			}
			if(bfs()) {
				answer = Math.min(answer, time);
			}
			return;
		}
		
		for(int i=start; i<virus.size(); i++) {
			numbers[cnt] = i;
			combination(i+1,cnt+1);
		}
	}
	
	static boolean bfs() {		
		int cnt = 0;
		time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc] && map[nr][nc]!=1) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr,nc});
						if(map[nr][nc]==0) cnt++;
					}
				}
				if(cnt == blank) {
					time++;
					return true;
				}
			}
			time++;
		}		
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		numbers = new int[M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					blank++;
				}
				else if (map[i][j] == 2) {
					virus.add(new int[] {i,j});
				}
			}
		}
		
		if(blank == 0)
			answer = 0;
		else {
			combination(0,0);
			if(answer == Integer.MAX_VALUE/2)
				answer = -1;
		}
		System.out.println(answer);
		
	}
}
