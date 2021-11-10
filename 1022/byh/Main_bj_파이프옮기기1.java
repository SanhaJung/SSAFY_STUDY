package a1022;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_파이프옮기기1 {
	static int N ,answer;
	static int [][] map;
	static int [] di = {0,1,1}; //우 하 우하
	static int [] dj = {1,0,1}; //
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int [N+1][N+1]; //1,1 시작
		for(int i =1; i<=N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer=0;
		dfs(1, 2 , 0 ); //파이프 끝 위치 1,2 파이프모양 0
		
		System.out.println(answer);
		
	}
	private static void dfs(int i, int j, int shape) {
		if(i == N && j==N ) { //도달
			
			answer++;
			return;
		}
		
		if (shape==0) {	// 파이프가 ㅡ 모양 -> 우, 우하
			if (j + 1 <= N && map[i][j + 1] == 0)
				dfs(i, j + 1, 0);

			if (i + 1 <= N    && j + 1 <= N && map[i][j + 1] == 0 && map[i + 1][j] == 0 && map[i + 1][j + 1] == 0)
				dfs(i + 1, j + 1, 2);
			
		} else if (shape == 1) {
			// 파이프 모양 ㅣ모양 -> 하, 우하
			if (i + 1 <= N && map[i + 1][j] == 0)
				dfs(i + 1, j, 1);

			if (i + 1 <= N && j + 1 <= N && map[i][j + 1] == 0 && map[i + 1][j] == 0 && map[i + 1][j + 1] == 0)
				dfs(i + 1, j + 1, 2);

		} else if (shape == 2) {
			// 파이프모양 대각선(우하) -> 우, 하, 우하 가능

			if (j + 1 <= N && map[i][j + 1] == 0)
				dfs(i, j + 1, 0);

			if (i + 1 <= N && map[i + 1][j] == 0)
				dfs(i + 1, j, 1);

			if (i + 1 <= N && j + 1 <= N && map[i][j + 1] == 0 && map[i + 1][j] == 0 && map[i + 1][j + 1] == 0)
				dfs(i + 1, j + 1, 2);
		}
		
	}
}
