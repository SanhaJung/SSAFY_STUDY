package bj17406;

import java.util.*;
import java.io.*;

public class Main_bj_17406_배열돌리기4 {
	static int N,M,K;
	static int[][] map,temp;
	static int[] numbers;
	static boolean[] visited;
	static ArrayList<int[]> list = new ArrayList<>();
	static int answer = Integer.MAX_VALUE/2;
	
	static void permutation(int cnt) {
		if(cnt==K) {
			temp = new int[N+1][M+1];	
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			for(int k=0; k<K; k++) {
				int r = list.get(numbers[k])[0];
				int c = list.get(numbers[k])[1];
				int s = list.get(numbers[k])[2];
				rotate(r,c,s);
			}
			
			for(int i=1; i<=N; i++) {
				int sum = 0;
				for(int j=1; j<=M; j++) {
					sum += temp[i][j];
				}
				answer = Math.min(answer, sum);
			}
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(!visited[i]) {
				visited[i]=true;
				numbers[cnt] = i;
				permutation(cnt+1);
				visited[i]=false;
			}
		}
	}
	
	static void rotate(int r, int c, int s) {
		
		for(int k=1; k<=s; k++) {
			int before = temp[r-k][c-k];
			int cur = 0;
			
			for(int i=c-k+1; i<=c+k; i++) {
				cur = temp[r-k][i];
				temp[r-k][i] = before;
				before = cur;
			}
			
			for(int i=r-k+1; i<=r+k; i++) {
				cur = temp[i][c+k];
				temp[i][c+k] = before;
				before = cur;
			}
			
			for(int i=c+k-1; i>=c-k; i--) {
				cur = temp[r+k][i];
				temp[r+k][i] = before;
				before = cur;
			}
			
			for(int i=r+k-1; i>=r-k; i--) {
				cur = temp[i][c-k];
				temp[i][c-k] = before;
				before = cur;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		numbers = new int[K];
		visited = new boolean[K];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			list.add(new int[] {r,c,s});
		}
		
		permutation(0);
		
		System.out.println(answer);
		
	}
}
