package a1009.study;

import java.util.*;
import java.io.*;

public class Main_bj_14503_로봇청소기 {
	
	static int N, M;		// 행, 열
	static int[][] map;		// 청소구역의 상태(빈칸: 0, 벽: 1)
	
	// 북서남동
	// 상좌하우(인덱스를 1씩 더하면 왼쪽방향으로 방향전환 가능)
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = { 0,-1, 0, 1};
	
	static int clean=1;		// 청소한 구역의 개수(초기값 1: 로봇청소기가 처음있던칸은 빈칸이므로 청소하고시작)
	

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14503_로봇청소기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		if(d==1) d= 3;			// 동쪽input: 1 -> 방향 인덱스: 3
		else if(d==3) d= 1;		// 서쪽input: 3 -> 방향 인덱스: 4
		
//		if(d==1) d= 3;  // 이렇게 해서 틀렸었음..
//		if(d==3) d= 1;
		
		
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 로봇청소기 시작지점과 방향을 매개변수로 넘김
		bfs(r, c, d);

		System.out.println(clean);
		br.close();

	}


	private static void bfs(int r, int c, int d) {
		Deque<int[]> q = new ArrayDeque<int[]>();
		
		map[r][c] = 3;				// 방문처리: 3
		q.offer(new int[] {r, c});	// 큐에 추가
		
		qLoop: while(!q.isEmpty()) {
			int[] cur = q.poll();
			int pi = cur[0];
			int pj = cur[1];
			
			for(int dir=1;dir<5;dir++) {		// [4방탐색] 1부터 시작: 자신의 왼쪽부터 탐색하기때문
				int ni = pi + di[(d+dir)%4];
				int nj = pj + dj[(d+dir)%4];
				// 인접한 칸에 청소할 공간이 있을때
				if(0<=ni && ni<N && 0<=nj && nj<M  &&map[ni][nj]==0) { 
					clean++;						// 청소한 칸 수 증가
					d = (d+dir)%4;					// 방향 설정
					map[ni][nj]=3;					// 방문처리
					q.offer(new int[] {ni, nj});	// q에 추가
					continue qLoop;					// 현 위치에서 다른 인접 칸 탐색할 필요없음
				}
			}
			// continue되지 않고 이부분이 실행: 인접한 4칸에 청소할 곳 없거나 벽인것  -> 후진하기
			int ni = pi - di[d];
			int nj = pj - dj[d];
			if(0<=ni && ni<N && 0<=nj && nj<M ) {
				 //후진도 벽일때 return
				if(map[ni][nj]==1) {
					return;
				// 벽이 아닐때 후진한 칸기준으로 탐색
				} else {
					q.offer(new int[] {ni, nj});	// q에 추가
				}
			}	
		}
	}
}
