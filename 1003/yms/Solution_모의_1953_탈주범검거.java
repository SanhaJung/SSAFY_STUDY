package a1953;

import java.util.*;
import java.io.*;
public class Solution_모의_1953_탈주범검거 {
	
	static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
	static int N,M,R,C,L;
	static int[][] map;

	static int bfs() {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		q.offer(new int[] {R,C});
		visited[R][C] = true;
		int cnt = 1;
		int hour = 1;
		
		while(!q.isEmpty()) {
			int size = q.size();
			if(L==hour++) {
				return cnt;
			}
			for(int i=0; i<size; i++) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(0<=nr && nr<N && 0<=nc && nc<M && !visited[nr][nc] && map[nr][nc]>0) {
						if(d==0 && (!(map[r][c]==1 || map[r][c]==2 || map[r][c]==4 || map[r][c]==7) || !(map[nr][nc]==1 || map[nr][nc]==2 || map[nr][nc]==5 || map[nr][nc]==6))) {
							continue;
						}
						else if(d==1 && (!(map[r][c]==1 || map[r][c]==3 || map[r][c]==4 || map[r][c]==5) || !(map[nr][nc]==1 || map[nr][nc]==3 || map[nr][nc]==6 || map[nr][nc]==7))) {
							continue;
						}
						else if(d==2 && (!(map[r][c]==1 || map[r][c]==2 || map[r][c]==5 || map[r][c]==6) || !(map[nr][nc]==1 || map[nr][nc]==2 || map[nr][nc]==4 || map[nr][nc]==7))) {
							continue;
						}
						else if(d==3 && (!(map[r][c]==1 || map[r][c]==3 || map[r][c]==6 || map[r][c]==7)|| !(map[nr][nc]==1 || map[nr][nc]==3 || map[nr][nc]==4 || map[nr][nc]==5))) {
							continue;
						}
						visited[nr][nc] = true;
						q.offer(new int[] {nr,nc});
						cnt++;
					}
				}
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/a1953/input_모의_1953.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T =Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());	// 배열 세로 크기
			M = Integer.parseInt(st.nextToken());	// 배열 가로 크기
			R = Integer.parseInt(st.nextToken());	// 맨홀 세로 위치
			C = Integer.parseInt(st.nextToken());	// 맨홀 가로 위치
			L = Integer.parseInt(st.nextToken());	// 소요 시간
			map = new int[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append('#').append(tc).append(' ').append(bfs()).append('\n');
		}
		
		System.out.println(sb.toString());
		in.close();
	}
}
