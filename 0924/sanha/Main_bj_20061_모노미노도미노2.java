package a0923.algo;

import java.util.*;
import java.io.*;

public class Main_bj_20061_모노미노도미노2 {
	// N: 입력받는 블록의 수
	// board: 보드의 상태(false: 빈칸, true: 블록 있음)
	static int N; 
	static boolean[][] board; 
	static Deque<Tile> block_q = new ArrayDeque<>();  // 블록을 저장하는 큐(블록의 순서:FIFO)
	static int totalScore;
	// 블록을 이루는 타일
	static class Tile {
		int x, y;		// 처음 놓여지는 좌표값
		int form;		// 타일이 이루는 블록의 형태
		public Tile(int form, int x, int y) {
			this.form = form;
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_20061_모노미노도미노2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 10 X 10 배열 생성
		board =  new boolean[10][10];
		// 블록개수 저장
		N = Integer.parseInt(br.readLine());
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int f = Integer.parseInt(st.nextToken());     // 블록의 형태
			int x = Integer.parseInt(st.nextToken());	  // 입력되는 x값
			int y = Integer.parseInt(st.nextToken());	  // 입력되는 y값
			block_q.offer(new Tile(f, x, y));			  // 타일 큐에 저장
		} 

		for(int t=0;t<N;t++) {
			Tile temp = block_q.poll();
			
			// 1. 블록 다운
			// 초록
			blockDown(temp, 0);
			// 파랑
			blockDown(temp, 1);
			
			// 2. 점수 확인
			// 초록
			scoreCheck(0);
			// 파랑
			scoreCheck(1);
			
			// 3. 연한색 영역 처리
			// 마지막 블록 없어지고 블록 내리기
			// 초록
			specialCell(0);
			// 파랑
			specialCell(1);
			
		}
		
		
		
		System.out.println(totalScore);
		System.out.println(countTile());
		br.close();
	}
	// 남은 타일 수 세는 함수
	private static int countTile() {
		int cnt = 0;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(board[i][j]) cnt++;
			}
		}
		return cnt;
	}
	// 연한색 칸을 처리하는 함수(1 or 2 줄을 이동)
	private static void specialCell(int c) {
		// 초록
		if(c==0) {
			boolean r8 = false;
			boolean r9 = false;
			// 초록의 0, 1행 검증
			// 인덱스로는 4, 5
			for(int j=0;j<=3;j++) {
				if(board[4][j]) {
					r8 = true;
				}
				if(board[5][j]) {
					r9 = true;
				}
			}
			// 연한색 0, 1행에 있음
			if(r9 && r8) {
				for(int i=7;i>=4;i--) {
					for(int j=0;j<=3;j++) {
						board[i+2][j] = false; 				  // 내릴 행을 초기화 한 후 
						if(board[i][j]) board[i+2][j] = true; // 2행 위가 true이면 2행 아래 true
					}
				}
			
			} else if(r9) {
				for(int i=8;i>=5;i--) {
					for(int j=0;j<=3;j++) {
						board[i+1][j] = false;
						if(board[i][j]) board[i+1][j] = true; // 2행 위가 true이면 2행 아래 true
					}
				}
			}
		// 파랑
		} else if(c==1) {
			boolean c8 = false;
			boolean c9 = false;
			// 초록의 0, 1열 검증
			// 인덱스로는 4, 5
			for(int j=0;j<=3;j++) {
				if(board[j][4]) {
					c8 = true;
				}
				if(board[j][5]) {
					c9 = true;
				}
			}
			// 연한색 0, 1행에 있음
			if(c9 && c8) {
				for(int i=7;i>=4;i--) {
					for(int j=0;j<=3;j++) {
						board[j][i+2] = false;
						if(board[j][i]) board[j][i+2] = true; // 2행 위가 true이면 2행 아래 true
					}
				}
			
			} else if(c9) {
				for(int i=8;i>=5;i--) {
					for(int j=0;j<=3;j++) {
						board[j][i+1] = false;
						if(board[j][i]) board[j][i+1] = true; // 2행 위가 true이면 2행 아래 true
					}
				}
			}
		}
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {

				System.out.print(board[i][j]? "O ":"X ");
			}
			System.out.println();
		}
		System.out.println();
		
	}

	private static void scoreCheck(int c) {
		// 초록
		if(c==0) {
			// 값이 있는 행큐에 저장
			Deque<Deque> rowQ = new ArrayDeque<>();
			// 아래쪽에 있는 행부터 검사
			for(int i=9;i>=4;i--) {
				// 각 행에서 채워져있는 칸의 열의 인덱스 저장큐
				Deque<Integer> q = new ArrayDeque<Integer>();
				// 몇개의 열이 차있는지 확인하는 반복문
				for(int j=0;j<=3;j++) {
					if(board[i][j]) { 		 // 칸이 채워져 있다면
						q.offer(j);			 // 큐에 열의 인덱스 offer
						board[i][j] = false; // 초기화( 없어진행이 있을때 중복체크 되지 않도록)
					}
				}
				if(q.size()==4) totalScore++; // 꽉 찬행 처리
				else if (q.size()!=0) {       // 값이 있는 행 큐에 저장(꽉찬행 제외)
					rowQ.offer(q);
				}
			}
			// 값이 있는 행 아래서부터 저장
			int nr=9;
			while(!rowQ.isEmpty()) {
				Deque<Integer> idxQ = rowQ.poll(); // 각 행에서 채워져있는 칸의 열의 인덱스 저장큐
				while(!idxQ.isEmpty()) {
					board[nr][idxQ.poll()] = true;
				}
				nr--;
			}
			
		}
		//파랑
		else if(c==1) {
			// 값이 있는 열 큐에 저장
			Deque<Deque> colQ = new ArrayDeque<>();
			// 왼쪽에 있는 행부터 검사
			for(int i=9;i>=4;i--) {
				// 각 열에서 채워져있는 칸의 열의 인덱스 저장큐
				Deque<Integer> q = new ArrayDeque<Integer>();
				// 몇개의 열이 차있는지 확인하는 반복문
				for(int j=0;j<=3;j++) {
					if(board[j][i]) { 		 // 칸이 채워져 있다면
						q.offer(j);			 // 큐에 행의 인덱스 offer
						board[j][i] = false; // 초기화( 없어진행이 있을때 중복체크 되지 않도록)
					}
				}
				if(q.size()==4) totalScore++; // 꽉 찬열 처리
				else if (q.size()!=0) {       // 값이 있는 열 큐에 저장(꽉찬열 제외)
					colQ.offer(q);
				}
			}
			// 값이 있는 행 아래서부터 저장
			int nc=9;
			while(!colQ.isEmpty()) {
				Deque<Integer> idxQ = colQ.poll(); // 각 행에서 채워져있는 칸의 열의 인덱스 저장큐
				while(!idxQ.isEmpty()) {
					board[idxQ.poll()][nc] = true;
				}
				nc--;
			}
		}
		
	}

	private static void blockDown(Tile temp, int c) {
		// 초록
		if(c==0) {
			// 1 X 1 블록일때
			if(temp.form==1) {
				int px = temp.x;
				while(true) {
					int nx = px+1;
					// 인덱스가 경계인 9에 닿거나 아래칸이 블록이 있다면
					if(nx==10 || board[nx][temp.y]) {
						board[px][temp.y] = true;
						break;
					}
					px = nx; // while완료조건에 도달하지않았다면 다음 진행
				}
			// 1 X 2블록
			} else if(temp.form==2) {
				int px = temp.x;
				while(true) {
					int nx = px+1;
					// 인덱스가 경계인 9에 닿거나 아래칸이 블록이 있다면
					if(nx==10 || board[nx][temp.y] || board[nx][temp.y+1]) {
						board[px][temp.y] = true;
						board[px][temp.y+1] = true;
						break;
					}
					px = nx;
				}
			// 2 X 1블록
			}else if(temp.form==3) {
				int px = temp.x+1;  // 블록의 아래칸이 기준
				while(true) {
					int nx = px+1; // 1 X 2의 아래칸을 기준으로 잡음
					// 인덱스 밖으로 나가거나
					if(nx==10 || board[nx][temp.y]) {
						board[px-1][temp.y] = true;
						board[px][temp.y] = true;
						break;
					}
					px = nx;
				}
			}
		}
		// 파랑
		else if(c==1) {
			// 1 X 1 블록일때
			if(temp.form==1) {
				int py = temp.y;
				while(true) {
					int ny = py+1;
					// 인덱스가 경계인 9에 닿거나 아래칸이 블록이 있다면
					if(ny==10 || board[temp.x][ny]) {
						board[temp.x][py] = true;
						break;
					}
					py = ny; // while완료조건에 도달하지않았다면 다음 진행
				}
			// 1 X 2블록
			} else if(temp.form==2) {
				int py = temp.y+1;  // 블록의 오른쪽 칸이 기준
				while(true) {
					int ny = py+1; // 1 X 2의 오른쪽 칸을 기준으로 잡음
					// 인덱스 밖으로 나가거나
					if(ny==10 || board[temp.x][ny]) {
						board[temp.x][py] = true;
						board[temp.x][py-1] = true;
						break;
					}
					py = ny; // while완료조건에 도달하지않았다면 다음 진행
				}
			// 2 X 1블록
			}else if(temp.form==3) {
				int py = temp.y;
				while(true) {
					int ny = py+1;
					// 인덱스가 경계인 9에 닿거나 아래칸이 블록이 있다면
					if(ny==10 || board[temp.x][ny] || board[temp.x+1][ny]) {
						board[temp.x][py] = true;
						board[temp.x+1][py] = true;
						break;
					}
					py = ny; // while완료조건에 도달하지않았다면 다음 진행
				}
			}
				}
		
	}

}
