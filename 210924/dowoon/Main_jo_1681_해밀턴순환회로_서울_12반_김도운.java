package a0923.com.ssafy.hw;

import java.io.*;
import java.util.*;

public class Main_jo_1681_해밀턴순환회로_서울_12반_김도운 {
	
	static int N, answer;
	static int[][] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];								// 1~N 까지의 인덱스를 사용하기 위해 N+1 x N+1 배열로 초기화 
		visited = new boolean[N+1];								// 마찬가지로 N+1 크기의 배열로 초기화 (dfs 시 방문처리 용도) 
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited[1] = true;										// 회사에서 출발하므로 1번 인덱스를 방문처리 
		answer = Integer.MAX_VALUE;								// 최소값을 저장해야하므로 max값으로 초기화 
		
		dfs(1, 1, 0);											// 출발 지점, 현재 방문한 곳의 수, 거리의 합 
				
		System.out.println(answer);
	}

	private static void dfs(int now, int cnt, int sum) {
		if(sum > answer) return;								// 만약 중간지점까지의 합(sum)이 answer값 보다 크다면 종료 
			
		if(cnt == N) {											// N개의 지점을 모두 방문했다면 
			if(arr[now][1] > 0) {
				answer = Math.min(answer, sum+arr[now][1]);		// 현재 sum에는 회사로 돌아오는 비용을 제외한 cost가 누적되어있으므로
																// 마지막 위치에서 집까지 갈 수 있을 경우에만 cost를 누적 
			}
		}
		
		for(int i=2; i<=N; i++) {								// 1은 회사(즉, 이미 방문한 곳이므로 포함 x) 
			if(arr[now][i] != 0 && !visited[i]) {				// 만약 현재 위치에서 i까지 방문할 수 있고, i가 방문되지 않은 곳이라면 
				visited[i] = true;								// i를 방문처리 하고, 
				dfs(i, cnt+1, sum+arr[now][i]);					// i를 방문했다는 가정하에 dfs 진행 
				visited[i] = false;								// i를 방문하지 않았다고 처리하여 다른 루트에서 방문할 수 있도록 처리 
			}
		}
	}

}
