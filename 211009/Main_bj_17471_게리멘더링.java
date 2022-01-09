package a1006.algo;

import java.io.*;
import java.util.*;

public class Main_bj_17471_게리멘더링 {
	
	static int N;
	static int[] pop;
	static boolean[] isSelected;
	static boolean[][] adjArr;
	
	static boolean[] visited;
	
	static int minPop = 987654321;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17471_게리멘더링.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		// 
		isSelected = new boolean[N+1];
		// 인구수 저장
		pop = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<N+1;i++) {
			pop[i] = Integer.parseInt(st.nextToken());
		}
		// 인접행렬 생성
		adjArr = new boolean[N+1][N+1];
		for(int i=1;i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int c=1;c<=n;c++) {
				int j = Integer.parseInt(st.nextToken());
				adjArr[i][j] = true;
				adjArr[j][i] = true;
			}
		}
		// 선거구 나누고  연결되어 있는지
		subset(1);
		
		
		System.out.println(minPop==987654321? "-1":minPop);
	}

	private static void subset(int cnt) {
		int s1 = 0;
		int s2 = 0;
		if(cnt == N) {

			// 뽑은것 연결되어 있는지
			for(int i=1;i<=N;i++) {
				// true인것
				if(isSelected[i]) {
					bfs1(i);
					break;
				}
			}
			for(int i=1;i<=N;i++) {
				if(isSelected[i]!=visited[i]) return;
				if(isSelected[i]) s1 += pop[i];
			}
			// 나머지 연결되어 있는지
			for(int i=1;i<=N;i++) {
				// false인것
				if(!isSelected[i]) {
					bfs2(i);
					break;
				}
			}
			for(int i=1;i<=N;i++) {
				if(isSelected[i]==visited[i]) return;
				if(!isSelected[i]) s2 += pop[i];
			}
			// 인구수 차이 구하기
			minPop = Math.min(minPop, Math.abs(s1-s2));
			return;
		}
		isSelected[cnt] = true;
		subset(cnt+1);
		isSelected[cnt] = false;
		subset(cnt+1);
	}

	private static void bfs1(int n) {
		Deque<Integer> queue = new ArrayDeque<Integer>();
		// static으로 선언해도 됨
		visited = new boolean[N+1];
		// 방문 처리하고
		visited[n] = true;
		// 넣기
		queue.offer(n);
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int i = 0;i<visited.length;i++) {
				if(isSelected[i]	// 부분집합으로 뽑혔고
						&& visited[i]==false  // 미방문 확인
						&& adjArr[current][i]) {  // 인접정점인지 확인
					// 방문처리 하고
					visited[i]=true;
					// 큐에 넣기
					queue.offer(i);
				}
			}
		}
	}
	private static void bfs2(int n) {
		Deque<Integer> queue = new ArrayDeque<Integer>();
		// static으로 선언해도 됨
		visited = new boolean[N+1];
		// 방문 처리하고
		visited[n] = true;
		// 넣기
		queue.offer(n);
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int i = 0;i<visited.length;i++) {
				if(!isSelected[i]	// 부분집합으로 뽑혔고
						&& visited[i]==false  // 미방문 확인
						&& adjArr[current][i]) {  // 인접정점인지 확인
					// 방문처리 하고
					visited[i]=true;
					// 큐에 넣기
					queue.offer(i);
				}
			}
		}
		
	}
}
