package s1104;

import java.io.*;
import java.util.*;

public class Main_2468_안전영역 {

	static int N, answer;
	static int[][] arr, copy;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		copy = new int[N][N];
		
		int maxHeight = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, arr[i][j]);
			}
		}
		
		answer = 1;
		for(int i=1; i<=maxHeight; i++) {
			copyMap(i);
			bfs(copy);
		}
		
		System.out.println(answer);
		
	}

	private static void bfs(int[][] arr) {
		boolean[][] v = new boolean[N][N];
		Queue<int[]> q = new ArrayDeque<>();
		int count = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] > 0 && !v[i][j]) {
					v[i][j] = true;
					q.offer(new int[] {i, j});
					count++;
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						
						for(int d=0; d<4; d++) {
							int nr = cur[0] + dr[d];
							int nc = cur[1] + dc[d];
							
							if(nr>=0&&nr<N && nc>=0&&nc<N && arr[nr][nc]>0 &&!v[nr][nc]) {
								v[nr][nc] = true;
								q.offer(new int[] {nr, nc});
							}
						}
					}
					q.clear();
				}
			}
		}
		answer = Math.max(answer, count);
	}

	private static void copyMap(int limit) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j]<=limit) copy[i][j] = 0;
				else copy[i][j] = arr[i][j];
			}
		}
	}

}
