package a0924.algo;

import java.util.*;
import java.io.*;

public class Main_bj_7576_토마토 {
	static int M, N;
	static int[][] storage;
	static int[] di = {-1, 0, 1, 0}; // 상우하좌
	static int[] dj = { 0, 1, 0, -1};
	static int day=-1; // 무조건 한번은 토마토를 찾느라 돌기때문에 -1로 초기화
	static int tomatoCnt;
	static Deque<int[]> q = new ArrayDeque<>();
	
	static void bfs(int i,int j) {
		int c = 0;
		int tomato_c = 0;

		// 처음 풀이
//		while(!q.isEmpty()) {
//			int[] temp = q.poll();
//			int pi = temp[0];
//			int pj = temp[1];
//			c++; // 고려한 토마토 개수
//			
//			for(int d=0;d<4;d++) {
//				int ni = pi+di[d];
//				int nj = pj+dj[d];
//				if(0<=ni && ni<M && 0<=nj && nj<N && storage[ni][nj]==0) {
//					tomato_c++;
//					storage[ni][nj] = 1;
//					q.offer(new int[] {ni, nj});
//				}
//			}
//			
//			if(c==tomatoCnt) {  		// 고려한 토마토 수가 하루에 추가된 토마토수랑 같을때
//				day++;					// 날짜 하루 증가
//				tomatoCnt = tomato_c;   // 다음 고려할 토마토 수 업데이트
//				tomato_c=0;				// 다음번 고려할 토마토 수를 세기 위해 0으로 초기화
//				c=0; 					// 고려한 토마토수 0으로 되돌려줌
//			}
//		}
		
		// 풀이 참고
		while(!q.isEmpty()) {
			int size = q.size();		// 하루를 시작할대 q에 저장되어 있던 토마토수
			
			for(int k=0;k<size;k++) {	// 현재 토마토 수 만큼 for문 돌기
				int[] temp = q.poll();	// 토마토 poll()
				int pi = temp[0];		// 토마토 x좌표
				int pj = temp[1];		// 토마토 y좌표

				for(int d=0;d<4;d++) {
					int ni = pi+di[d];	// 다음 x좌표
					int nj = pj+dj[d];	// 다음 y좌표
					if(0<=ni && ni<M && 0<=nj && nj<N && storage[ni][nj]==0) { // 범위 내에 있고 안익은 토마토라면
						storage[ni][nj] = 1;		 	// 익은 토마토로 변경
						q.offer(new int[] {ni, nj});	// 큐에 offer
					}
				}
			}
			day++;	// 하루 시작할때 저장되어 있던 토마토를 모두 고려해주었기때문에 날짜 증가
			

		}

		
		
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_7576_토마토.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		storage = new int[M][N];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				storage[i][j] = Integer.parseInt(st.nextToken());
				if(storage[i][j]==1) {
					tomatoCnt++; // 처음 토마토 수
					q.offer(new int[] {i, j});
				}
			}
		}
		
		bfs(0, 0);
		

		out:for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(storage[i][j]==0) {
					day = -1;
					break out;
				}
			}
		}
		System.out.println(day);
		br.close();
	}

}
