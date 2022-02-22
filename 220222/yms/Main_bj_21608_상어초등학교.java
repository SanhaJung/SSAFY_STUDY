package bj21608;

import java.util.*;
import java.io.*;

public class Main_bj_21608_상어초등학교 {
	
	static int N,answer;
	static int[][] info,board;
	static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
	
	static class Seat implements Comparable<Seat>{ 
        int r, c, likes, empty;										// 행, 열 , 좋아하는 사람 수, 빈칸 수

        public Seat(int r, int c, int likes, int empty) {
            this.r = r;
            this.c = c;
            this.likes = likes;
            this.empty = empty;
        }

		@Override
		public int compareTo(Seat o) {								// 좋아하는 사람 수, 빈칸 수, 작은 행 번호, 작은 열 번호 순으로 정렬
			if(this.likes == o.likes) {
				if(this.empty == o.empty) {
					if(this.r == o.r) return this.c - o.c;
					else return this.r - o.r;
				}
				else return -(this.empty - o.empty);
			}
			else return -(this.likes - o.likes);
		}
		
    }
	
	static Seat getSeat(int number) {
		PriorityQueue<Seat> pq = new PriorityQueue<>();
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(board[i][j]==0) {								// 해당 칸이 빈칸이면
					int likes = getLikes(number,i,j);
					int empty = getEmpty(number,i,j);
					pq.add(new Seat(i,j,likes,empty));				// 행,열,좋아하는사람수,빈칸수를 담아서 우선순위 큐에 추가
				}
			}
		}
		return pq.poll();
	}
	
	static int getLikes(int number, int r, int c) {
		int likes = 0;
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(0<nr && nr<=N && 0<nc && nc<=N) {					// 인덱스 범위를 벗어나지 않고
				for(int i=0; i<4; i++) {
					if(info[number][i]==board[nr][nc]) {			// 좋아하는 사람이 있으면
						likes++;
						break;
					}
				}
			}
		}
		
		return likes;
	}
	
	static int getEmpty(int number, int r, int c) {
		int empty = 0;
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(0<nr && nr<=N && 0<nc && nc<=N) {					// 인덱스 범위를 벗어나지 않고
				if(board[nr][nc]==0) {								// 해당 칸이 비어있다면
					empty++;
				}
			}
		}
		
		return empty;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		info = new int[(N*N)+1][4];
		board = new int[N+1][N+1];
		int[] students = new int[(N*N)+1];
		
		for(int i=1; i<=N*N; i++) {
			st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken());
			students[i] = num;
			for(int j=0; j<4; j++) {
				info[num][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<=N*N; i++) {
			Seat seat = getSeat(students[i]);
			board[seat.r][seat.c] = students[i];
		}
		
		int answer = 0;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				answer += Math.pow(10,getLikes(board[i][j], i, j)-1);
			}
		}
		
		System.out.println(answer);
	}
}