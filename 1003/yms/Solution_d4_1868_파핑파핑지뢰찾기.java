package a1868;

import java.util.*;
import java.io.*;

public class Solution_d4_1868_파핑파핑지뢰찾기 {
	
	static char[][] board;
	static int N;
	static int[] dr = {-1,-1,0,1,1,1,0,-1,-1}, dc = {0,1,1,1,0,-1,-1,-1};
	
	static void bfs(int r, int c) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r,c});
		board[r][c] = '#';
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			r = cur[0];
			c = cur[1];		
						
			if(check(r,c)) {
				for(int d=0; d<8; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(0<=nr && nr<N && 0<=nc && nc<N  && board[nr][nc]=='.') {
						board[nr][nc] = '#';
						q.offer(new int[] {nr,nc});
					}
				}
			}
		}
	}
	
	static boolean check(int r, int c) {
		for(int d=0; d<8; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(0<=nr && nr<N && 0<=nc && nc<N) {
				if(board[nr][nc]=='*')
					return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/a1868/input_d4_1868.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(in.readLine());
			board = new char[N][N];
			int answer = 0;
			
			for(int i=0; i<N; i++) {
				String s = in.readLine();
				for(int j=0; j<N; j++) {
					board[i][j] = s.charAt(j);
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(check(i,j) && board[i][j]=='.') {
						bfs(i,j);
						answer++;
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(board[i][j]=='.') {
						answer++;
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb.toString());
		in.close();
	}
}
