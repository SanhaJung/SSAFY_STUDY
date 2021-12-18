package a12algo;

import java.util.*;
import java.io.*;

public class bj1018체스판다시칠하기 {
	static int M, N;
	static int min = 987654321;
	static int[][] board;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		board = new int[M][N];
		
		for(int i=0;i<M;i++) {
			int j = 0;
			for(char c: br.readLine().toCharArray()) {
				if(c=='W') board[i][j] = 0;
				else board[i][j] = 1;
				j++;
			}
		}

		for(int i=0;i<M-8+1;i++) {
			for(int j=0;j<N-8+1;j++) {
				check(i, j);
			}
		}
		System.out.println(min);
	}
	
	public static void check(int sr, int sc) {
		// 흰색 시작
		int change = 0;
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(board[sr+i][sc+j]!=(i+j)%2) change++;
				}
			}
		min = Math.min(min, change);
		// 검정색 시작
		change = 0;
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(board[sr+i][sc+j]!=(i+j+1)%2) change++;
				}
			}
		min = Math.min(min, change);
	}
}
