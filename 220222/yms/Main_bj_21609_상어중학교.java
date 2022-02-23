package bj21609;

import java.util.*;
import java.io.*;

public class Main_bj_21609_상어중학교 {
	
	static int N,M,score;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
	static PriorityQueue<Group> pq = new PriorityQueue<>();
	
	static class Group implements Comparable<Group>{
		int r;												// 기준블럭 행
		int c;												// 기준블럭 열
		int size;											// 그룹의 사이즈
		int rainbow;										// 그룹내의 무지개 블럭 수
		ArrayList<int[]> list;								// 그룹에 속한 블럭들의 좌표 리스트
		
		public Group(int r, int c, int size, int rainbow, ArrayList<int[]> list) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.rainbow = rainbow;
			this.list = list;
		}

		@Override
		public int compareTo(Group o) {								// 사이즈 > 무지개블럭 수 > 행이 큰 거 > 열이 큰거 순으로 정렬
			if(this.size == o.size) {
				if(this.rainbow == o.rainbow) {
					if(this.r == o.r) return -(this.c - o.c);
					else return -(this.r - o.r);
				}
				else return -(this.rainbow - o.rainbow);
			}
			else return -(this.size - o.size);
		}
	}
	
	static void bfs(int x, int y) {									// 그룹을 찾는 bfs
		ArrayList<int[]> list=  new ArrayList<>();					// 그룹에 속한 블럭들을 담을 리스트
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x,y});
		list.add(new int[] {x,y});
		visited[x][y] = true;
		int size = 1;
		int rainbow = 0;											
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(0<=nr && nr<N && 0<=nc && nc<N && board[nr][nc]>=0 && !visited[nr][nc]) {	// 검은 블럭이나 빈칸이 아니고 아직 그룹에 속해있지 않은 블럭이면
					if(board[nr][nc]==0 || board[nr][nc] == board[x][y]) {	// 무지개 블럭이거나 그룹의 블럭 색과같으면
						visited[nr][nc] = true;
						q.offer(new int[] {nr,nc});
						if(board[nr][nc]==0) rainbow++;						// 무지개 블럭이라면 무지개 블럭의 수 증가
						size++;
						list.add(new int[] {nr,nc});
					}
				}
			}
		}
		
		for(int[] cur : list) {												// 무지개 블럭은 다른 그룹에도 속할 수 있으므로 visited 배열을 false로 변환
			int r = cur[0];
			int c = cur[1];
			if(board[r][c]==0) visited[r][c] = false;
		}
		
		if(size>1) pq.offer(new Group(x,y,size,rainbow,list));				// 전체 그룹의 크기가 2 이상이라면 우선순위 큐에 추가
	}
	
	static void gravity() {													// 중력
		for(int c=0; c<N; c++) {
			label:for(int r=N-1; r>0; r--) {
				if(board[r][c]==-2) {										// 빈칸이라면
					int nr = r;
					while(nr>=0) {											// 현재 열에서 위로 탐색하는 반복문
						if(board[nr][c]==-1) continue label;				// 검은 블럭은 움직이지 않으므로 중지
						if(board[nr][c]==-2) nr--;							// 빈칸이라면 계속 위로 탐색
						else break;											// 다른 블럭이라면 중지
					}
					if(nr<0) nr = 0;										// 맨 위칸까지 탐색하고 나올경우 nr이 -1이 되므로 0으로 수정
					board[r][c] = board[nr][c];								// 제일아래에 있는 빈칸과 블럭의 위치를 바꿔줌
					board[nr][c] = -2;										
				}
			}
		}
	}
	
	static void rotate() {													// 반시계 방향 회전
		int[][] copy = new int[N][N];
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				copy[r][c] = board[r][c];
			}
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				board[N-1-c][r] = copy[r][c];
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && board[i][j]>0) bfs(i,j);				// 아직 그룹이 없고 일반 블럭이라면 bfs 탐색
			}
		}
		
		while(!pq.isEmpty()) {
			Group group = pq.poll();										// 가장 우선순위가 높은 그룹을 선택
			for(int[] cur : group.list) {									// 그룹을 모두 빈칸 처리하고 점수 증가
				int r = cur[0];
				int c = cur[1];
				board[r][c] = -2;
			}			
			score += Math.pow(group.size, 2);
			
			gravity();
			rotate();
			gravity();
			
			pq.clear();														// 보드의 모양이 바뀌었으므로 새로 그룹 탐색
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && board[i][j]>0) bfs(i,j);
				}
			}
		}
		
		System.out.println(score);
	}
}
