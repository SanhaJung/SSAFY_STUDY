package a12algo;

import java.io.*;
import java.util.*;

public class swea우수문제3물고기와바닷새2 {
	
	static class Bird{
		int col = 0;
		int depth = 0;
		
		Bird() {
			super();
		}
	}
	
	static int second = 0;
	static int[][] sea;
	static int R, C;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = { 0, 0, 1,-1};
	static int[] dir = {-1, 1};
	static int fishCnt = 0;
	static int fi, fj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			second = 0;
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			sea = new int[R][C];
			
			for(int i=0;i<R;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<C;j++) {
					sea[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 고래가 있으면 그 열은 모두 고래로 바꾸기
			for(int j=0;j<C;j++) {
				for(int i=0;i<R;i++) {
					if(sea[i][j] == 2) {
						for(int k=i; k<R;k++) {
							sea[k][j] = 2;
						}
						break;
					}
				}
			}
			
			Bird seaBrid = new Bird();
			int row=0;
			
			// 첫줄 물고기 위치
			for(int j=0;j<C;j++) {
				if(sea[0][j]==1) {
					fi = 0;
					fj = j;
					break;
				}
			}
			
			// 물고기 먹기
			eatFish(fi, fj, seaBrid);
			
			
			if(second!=0) second++;
			sb.append("#").append(t).append(" ").append(second).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void eatFish(int fi, int fj, Bird seaBird) {
		boolean[][] v = new boolean[R][C];
		int cnt = 0;
		int pi = fi;
		int pj = fj;
		ArrayDeque<int[]> q = new ArrayDeque<int[]>();
		v[pi][pj] = true;
		q.offer(new int[] {pi, pj});
		
		while(!q.isEmpty() ) {
			int[] cur = q.poll();
			pi = cur[0];
			pj = cur[1];
			
			if(sea[pi][pj]==1 && seaBird.depth>=pi) {
				// 물고기 먹으면 방문배열 초기화
				v = new boolean[R][C];
				// 물고기 먹기
				seaBird.depth++;	// 새가 들어갈 수 있는 깊이 + 1
				sea[pi][pj] = 0;	// 물고기 먹기
				second++;			// 1초 증가
				// 바닷새 위치 이동
				second += Math.abs(seaBird.col - pj);	// 이동시간 증가
				seaBird.col = pj;						// 새의 위치이동
			}
			// 4방탐색
			for(int d = 0;d<4;d++) {
				int ni = pi + di[d];
				int nj = pj + dj[d];
				if(0<=ni && ni<R && 0<=nj && nj<C && !v[ni][nj]) {
					v[ni][nj] = true;
					q.offer(new int[] {ni, nj});
				}
			}
		}
	}

}
