package bj17837;

import java.io.*;
import java.util.*;

public class Main_bj_17837_새로운게임2 {
	
	static int N,K;
	static int[][] board;												// 입력으로 주어지는 보드의 색깔 배열
	static int[] dr = {0,0,-1,1}, dc = {1,-1,0,0}; 						// 우 좌 상 하
	static ArrayList<Integer>[][] horse_board;							// 각 칸에 말들의 번호를 저장할 리스트 배열
	static ArrayList<Horse> list = new ArrayList<>();					// 말들의 정보를 담을 리스트
	
	static class Horse {
		int r;
		int c;
		int num;
		int d;
		
		public Horse(int r, int c, int num, int d) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.d = d;
		}		
	}
	
	static void white(int r, int c, int nr, int nc, int num) {				// 흰색 : 해당 말의 위에 있는 말들을 순서대로 다음 칸으로 이동
		int idx = horse_board[r][c].indexOf(num);							// 현재 칸에 찾는 말의 번호가 몇 번째에 있는지 인덱스 검색
		for(int i=idx; i<horse_board[r][c].size(); i++) {					// 그 인덱스 부터 리스트의 끝까지 탐색하면 위에 있는 것들의 정보를 얻을 수 있음
			horse_board[nr][nc].add(horse_board[r][c].get(i));				// 새로운 칸에 옮길 말들을 저장
			list.get(horse_board[r][c].get(i)).r = nr; 						// 옮긴 후 말들의 위치 정보를 업데이트
			list.get(horse_board[r][c].get(i)).c = nc; 
			horse_board[r][c].remove(i--);									// 현재 칸에서 말을 제거 (인덱스 조절)
		}
	}
	
	static void red(int r, int c, int nr, int nc, int num) {				// 빨간색 : 해당 말의 위에 있는 말들을 역순으로 다음 칸으로 이동
		int idx = horse_board[r][c].indexOf(num);							// 현재 칸에 찾는 말의 번호가 몇 번째에 있는지 인덱스 검색
		for(int i=horse_board[r][c].size()-1; i>=idx; i--) {				// 리스트의 끝부터 현재 인덱스까지 탐색하면 역순으로 정보를 얻을 수 있음
			horse_board[nr][nc].add(horse_board[r][c].get(i));				// 새로운 칸에 옮길 말들을 저장
			list.get(horse_board[r][c].get(i)).r = nr; 						// 옮긴 후 말들의 위치 정보를 업데이트
			list.get(horse_board[r][c].get(i)).c = nc; 
			horse_board[r][c].remove(i);									// 현재 칸에서 말을 제거 (인덱스 조절)
		}
	}
		
	static void blue(int r, int c, int nr, int nc, Horse h) {
		if(h.d==0) h.d=1;													// 좌-우 , 상-하 방향 조정
		else if(h.d==1) h.d=0;
		else if(h.d==2) h.d=3;
		else if(h.d==3) h.d=2;
		
		nr = r+dr[h.d];														// nr,nc를 바뀐 방향으로 새로 저장
		nc = c+dc[h.d];
																			// 다음 칸이 파란색이거나 범위를 벗어나면 아무것도 안함
		if(0<=nr && nr<N && 0<=nc && nc<N && board[nr][nc]==0) {			// 다음 칸이 흰칸이면 흰색 연산
			white(r,c,nr,nc,h.num);
		}
		else if(0<=nr && nr<N && 0<=nc && nc<N && board[nr][nc]==1) {		// 다음 칸이 빨간칸이면 빨간색 연산
			red(r,c,nr,nc,h.num);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		horse_board = new ArrayList[N][N];
		int answer = 0;
		
		for(int i=0; i<N; i++) {								// 0 : 흰색 1 : 빨간색 2 : 파란색
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				horse_board[i][j] = new ArrayList<>();
			}
		}
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			horse_board[r][c].add(i);							// 해당 칸 r,c에 말의 번호를 입력
			list.add(new Horse(r,c,i,d));						// 말의 정보를 입력 (행,열,번호,방향)
		}
		
		label:while(answer<1000) {								// 1000번 이상이면 종료
			answer++;
			for(Horse h : list) {
				int nr = h.r+dr[h.d];
				int nc = h.c+dc[h.d];
				
				if(0<=nr && nr<N && 0<=nc && nc<N && board[nr][nc]==0) {	//흰색
					white(h.r,h.c,nr,nc,h.num);
				}
				else if (0<=nr && nr<N && 0<=nc && nc<N && board[nr][nc]==1) {		//빨간색
					red(h.r,h.c,nr,nc,h.num);
				}
				else  {																//파란색
					blue(h.r,h.c,nr,nc,h);
				}
				
				if(horse_board[h.r][h.c].size()>=4) {								// 옮긴 직후 4개 이상 쌓여있으면 바로 종료
					break label;
				}
			}
		}
		
		if(answer>=1000) answer = -1;
		System.out.println(answer);
	}

}
