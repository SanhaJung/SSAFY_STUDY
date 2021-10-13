package s1010;

import java.util.*;
import java.io.*;

public class Solution_1767_프로세서연결하기 {
	static int T, N, size, min;
	static int[][] arr;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Pair core[];
	static boolean visited[];
	static class Pair{
		int y;
		int x;
		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static void combination(int idx, int cnt, int R) {
		if(cnt == R) {							// 크기가 R을 만족하는 조합이 생성되었으면,
			dfs(0, 0);							// dfs 수행 후 리턴 
			return;
		}
		for(int i = idx; i < size; i++) {		// 조합 코드 
			visited[i] = true;
			combination(i+1, cnt+1, R);
			visited[i] = false;
		}
	}
	
	public static void dfs(int idx, int cnt) {
		if(idx == size) {						// 만약 size만큼 dfs가 진행되었으면 
			min = Math.min(min, cnt); 			// 최솟값 갱신
			return;
		}
		if(!visited[idx]) {						// 부분 집합에 포함되는 애들만 다음 단계 진행 
			dfs(idx+1, cnt);
			return;
		}
		for(int i = 0; i < 4; i++) {
			int y = core[idx].y;
			int x = core[idx].x;
			int tmp = 0;
			boolean success = false;
			
			while(true) {
				y += dy[i];
				x += dx[i]; 
				if(y<0||y>=N ||x<0||x>=N) {		// 배열의 끝까지 갔으면 성공(연결됨) 
					success = true;
					break;
				}
				if(arr[y][x]!=0) break;			// 전선이나 코어를 만나면 실패
				arr[y][x]=2; 					// 전선 표시
				tmp++; 							// 전선 길이 합
			}
			if(success) dfs(idx+1, cnt+tmp);
			while(true) { 						// 원 상태로 돌려놓기
				y -= dy[i];
				x -= dx[i]; 
				if( y==core[idx].y && x==core[idx].x) break;
				arr[y][x] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N]; 
			core = new Pair[12]; 
			visited = new boolean[12];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			size = 0; 
			for(int i = 1; i < N - 1; i++) {
				for(int j = 1; j < N - 1; j++) {
					if(arr[i][j]==1) core[size++] = new Pair(i, j); // 가장자리에 위치한 코어는 고려할 필요가 없으므로 1,1 ~ N-2,N-2 안에 있는 코어만 추가 
				}
			}
			
			min = Integer.MAX_VALUE;
			for(int i = size; i >= 0; i--) {
				combination(0, 0, i);								// 원소의 개수가 i인 조합 생성 
				if(min < Integer.MAX_VALUE) break; 					// 최솟값이 갱신되어 있으면 결과가 나왔다는 뜻임
			}
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
