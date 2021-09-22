package bj14891;

import java.util.*;
import java.io.*;

public class Main_bj_14891_톱니바퀴 {
	
	static int[][] arr = new int[4][8];				
	static int[] idx = new int[4];
	static boolean[] check = new boolean[4];
	
	static void work(int n, int d) {
		
		if(n<0 || n>4 || check[n]) return;
		check[n] = true;
		
		if(n+1<4 && !check[n+1] && arr[n][(idx[n]+2)%8] != arr[n+1][(idx[n+1]+6)%8]) {
			work(n+1,-d);
		}
		if(n-1>=0 && !check[n-1] && arr[n][(idx[n]+6)%8] != arr[n-1][(idx[n-1]+2)%8]) {
			work(n-1,-d);
		}
		
		idx[n] = (8+idx[n]-d)%8;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int answer = 0;
		
		for(int i=0; i<4; i++) {
			String s = in.readLine();
			for(int j=0; j<8; j++) {
				arr[i][j] = (s.charAt(j)-'0');
			}
		}
		
		int K = Integer.parseInt(in.readLine());
		
		for(int i=0; i<K; i++) { // N극은 0 S극은 1, 처음 12시 = 0, 3시 = +2, 9시 = +6
			st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			check = new boolean[4];			
			work(n,d);
		}
		
		for(int j=0; j<4; j++) {
			if(arr[j][(8+idx[j])%8] ==1)
				answer += (int) Math.pow(2, j);
		}
		
		System.out.println(answer);
	}
}
