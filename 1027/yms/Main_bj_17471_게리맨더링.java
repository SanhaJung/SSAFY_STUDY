package bj17471;

import java.util.*;
import java.io.*;

public class Main_bj_17471_게리맨더링 {
	
	static int N;
	static boolean[] numbers;
	static int[] population;
	static int[][] adjMatrix;
	static int answer = Integer.MAX_VALUE/2;
	static boolean[] visited;
	
	static void subset(int cnt) {
		if(cnt == N+1) {												//N번 체크 했으면
			if(check()) {
				int a=0, b=0;
				for(int i=1; i<=N; i++) {
					if(numbers[i]) {
						a += population[i];
					}
					else b += population[i];
				}
				answer = Math.min(answer, Math.abs(a-b));
			}
			return;
		}
		
		numbers[cnt]=true;
		subset(cnt+1);
		numbers[cnt]=false;
		subset(cnt+1);
	}
	
	
	static boolean check() {
		int[] a = new int[N+1];
		int[] b = new int[N+1];		
		int a_idx = 0;
		int b_idx = 0;
		
		for(int i=1; i<=N; i++) {
			if(numbers[i]) a[a_idx++]=i;						//numbers에 체크된 정보를 토대로 a,b배열 생성
			else b[b_idx++] = i;
		}
		
		if(a_idx==0 || b_idx ==0) return false;					// 문제 조건에 따라 false
		
		bfs(a,a_idx);									
		for(int i=0; i<a_idx; i++) {
			if(!visited[i]) return false;						// bfs를 돌았을 때 모든 부분이 방문 처리 되있으면 해당 구역이 다 이어져 있다는 뜻
		}
		
		bfs(b,b_idx);
		for(int i=0; i<b_idx; i++) {
			if(!visited[i]) return false;
		}
		
		return true;
	}
	
	static void bfs(int[] n, int size) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		visited = new boolean[size];
		q.offer(0);
		visited[0] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=0; i<size; i++) {
				if(adjMatrix[n[cur]][n[i]] == 1 && !visited[i]) {		// 방문할 수 있고 아직 체크가 안된 곳이면 방문
					visited[i] = true;
					q.offer(i);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		population = new int[N+1];													//지역 인구 저장 배열
		adjMatrix = new int[N+1][N+1];												//인접행렬
		numbers = new boolean[N+1];													//부분집합을 담을 배열
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i=1; i<=N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=N; i++) {													// 인접행렬 생성
			st = new StringTokenizer(in.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int j=0; j<size; j++) {
				int temp = Integer.parseInt(st.nextToken());
				adjMatrix[i][temp] = adjMatrix[temp][i] = 1;
			}
		}
		
		subset(1);
		if(answer == Integer.MAX_VALUE/2) answer = -1;
		System.out.println(answer);
	}
}
