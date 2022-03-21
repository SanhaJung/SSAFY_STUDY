package bj9184;

import java.util.*;
import java.io.*;

public class Main_bj_9184_신나는함수실행 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int[][][] dp = new int[21][21][21];
		
		for(int i=0; i<=20; i++) {
			for(int j=0; j<=20; j++) {
				for(int k=0; k<=20; k++) {
					if(i==0 || j==0 || k==0) dp[i][j][k] = 1;
					else if (i<j && j<k) dp[i][j][k] = dp[i][j][k-1]+dp[i][j-1][k-1]-dp[i][j-1][k];
					else dp[i][j][k] = dp[i-1][j][k]+dp[i-1][j-1][k]+dp[i-1][j][k-1]-dp[i-1][j-1][k-1];
				}
			}
		}
		
		while(true) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int result = 0;
			
			if(a==-1 && b==-1 && c==-1) break;
			
			if(a<=0 || b<=0 || c<=0) result = 1;
			else if(a>20 || b>20 || c>20) result = dp[20][20][20];
			else result = dp[a][b][c];
			
			sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
