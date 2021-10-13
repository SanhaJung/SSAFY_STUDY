package a4013;

import java.util.*;
import java.io.*;

public class Solution_모의_4013_특이한자석 {			// 백준 14891 톱니바퀴
	
	static int[][] gear = new int[4][8];				
	static int[] idx = new int[4];
	static boolean[] check = new boolean[4];
	
	static void work(int n, int d) {
		
		if(check[n]) return;
		
		check[n] = true;
		
		if(n+1<4 && !check[n+1] && gear[n][(idx[n]+2)%8] != gear[n+1][(idx[n+1]+6)%8]) {
			work(n+1,-d);
		}
		if(n-1>=0 && !check[n-1] && gear[n][(idx[n]+6)%8] != gear[n-1][(idx[n-1]+2)%8]) {
			work(n-1,-d);
		}
		
		idx[n] = (8+idx[n]-d)%8;
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/a4013/input_모의_4013.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			int answer = 0;
			int K = Integer.parseInt(in.readLine());
			
			for(int i=0; i<4; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<8; j++) {
					gear[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			idx = new int[4];
			check = new boolean[4];
			
			for(int i=0; i<K; i++) { // N극은 0 S극은 1, 처음 12시 = 0, 3시 = +2, 9시 = +6
				st = new StringTokenizer(in.readLine());
				int n = Integer.parseInt(st.nextToken())-1;
				int d = Integer.parseInt(st.nextToken());
				check = new boolean[4];			
				work(n,d);
			}
			
			for(int j=0; j<4; j++) {
				if(gear[j][(8+idx[j])%8] ==1)
					answer += (int) Math.pow(2, j);
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
			
		}
		System.out.println(sb.toString());
		in.close();
	}
}
