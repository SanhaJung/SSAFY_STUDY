package a0903.study;

import java.util.*;
import java.io.*;

public class Main_bj_3190_뱀 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_3190_뱀.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int second = 0;
		// 뱀 머리 좌표
		int pi = 0;
		int pj = 0;
		// 뱀 머리 방향(초기: 우)
		int pd = 0;
		// 뱀 꼬리 좌표
		int ti = 0;
		int tj = 0;
		// 방향 바꿔야할 초
		int ds = 0;
		int ki = 1;
		// 바꿀 방향
		int dd = 0;
		// 우0, 하1, 좌2, 상3
		int[][][] D = {//   왼       우    현재방향
					   { {-1, 0}, { 1, 0}, { 0, 1} },
					   { { 0, 1}, { 0,-1}, { 1, 0} },
					   { { 1, 0}, {-1, 0}, { 0,-1} },
					   { { 0,-1}, { 0, 1}, {-1, 0} },
					   };
		ArrayDeque<int[]> q = new ArrayDeque<>();
		ArrayDeque<int[]> snakeQ = new ArrayDeque<>();
		
		int N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		boolean isApple = false;
		// 뱀 시작점
		board[0][0] = 1;
//		q.offer(new int[] {0, 0});
		
		int k = Integer.parseInt(br.readLine());
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(br.readLine());
			// 사과가 있는 곳: 2
			board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 2;
		}
		
		int l = Integer.parseInt(br.readLine());
		// 우0, 하1, 좌2, 상3
		// 왼쪽0, 오른쪽1
		for(int i=0;i<l;i++) {
			st = new StringTokenizer(br.readLine());
			// 왼쪽
			int tempS = Integer.parseInt(st.nextToken());
			String tempD = st.nextToken();
			if(tempD.equals("L")) {
				q.offer(new int[] {tempS, -1});
			// 오른쪽
			} else {
				q.offer(new int[] {tempS, 1});
			}
		}
		if(q.size()!=0) {
		int[] first = q.poll();
		ds = first[0];
		dd = first[1];
		}
		loop : while(true) { // 벽에 닿으면 끝
			// 현재 좌표(pi, pj)
			// 현재 방향(pd)
			// 우0, 하1, 좌2, 상3
			// 왼쪽0, 오른쪽1, 현재방향2
			// board - 뱀: 1, 사과: 2
			// 사과 있는지: isApple
			
			// 머리 처리
			int ni = 0;
			int nj = 0;
			if(ds==second) {
				// 방향바꾸기
				pd = (pd+dd)%4;
				//!!!!!!!!!!인덱스 음수일때 처리해주기!!!!!!!!!!!!!!
				if(pd==-1) {
					pd = 3;
				}
				ni = pi + D[pd][2][0];
				nj = pj + D[pd][2][1];
				// 새로운 방향 저장
				if(q.size()!=0) {
					int[] temp = q.poll();
					ds = temp[0];
					dd = temp[1];
				}
			// 직진
			} else {
				ni = pi + D[pd][2][0];
				nj = pj + D[pd][2][1];
			}
			second++;
			if(0<=ni && ni<N && 0<=nj && nj<N) {
				pi = ni;
				pj = nj;
				if(board[ni][nj]==2) isApple = true;
				// 자기몸 만나면 끝
				else if(board[ni][nj]==1) break loop;
				board[pi][pj] = 1;
				snakeQ.offer(new int[] {pi, pj});
				// 사과 X 꼬리 처리
				if(!isApple) {
					board[ti][tj] = 0;
					int[] tail = snakeQ.poll();
					ti = tail[0];
					tj = tail[1];
				} else isApple = false;
			// 판을 나가면 끝
			}else break loop;
			
		}

		System.out.println(second);
		br.close();
	}

}
