package bj2239;

import java.util.*;
import java.io.*;

public class Main_bj_2239_스도쿠 {
	static int[][] board = new int[9][9];
//	static boolean flag;
	
	static void dfs(int idx, int num) {
		int r = idx/9;
		int c = idx%9;
//		if(flag) return;
		
		if(idx == 81) {
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
//			flag = true;
//			return;
		}
		if(board[r][c]!=0) {
			dfs(idx+1, num);
		}
		else {
			for(int n=1; n<=9; n++) {
				if(box_check(r,c,n) && line_check(r,c,n)) {
					board[r][c] = n;
					dfs(idx+1,n);
					board[r][c] = 0;
				}
			}
		}
	}
	
	static boolean box_check(int r, int c, int num) {
//		if(r<3) r=0;
//		else if (r<6) r=3;
//		else r = 6;
//		if(c<3) c=0;
//		else if (c<6) c=3;
//		else c = 6;
		r = r/3 * 3;
		c = c/3 * 3;
		for(int i=r; i<r+3; i++) {
			for(int j=c; j<c+3; j++) {
				if(board[i][j] == num) return false;
			}
		}
		return true;
	}
	
	static boolean line_check(int r, int c, int num) {
		for(int i=0; i<9; i++) {
			if(board[i][c] == num) return false;
			if(board[r][i] == num) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<9; i++) {
			String s = in.readLine();
			for(int j=0; j<9; j++) {
				board[i][j] = s.charAt(j)-'0';
			}
		}
		
		dfs(0,1);
		
	}
}
