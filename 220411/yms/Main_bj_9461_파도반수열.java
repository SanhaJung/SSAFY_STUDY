package bj9461;

import java.util.*;
import java.io.*;

public class Main_bj_9461_파도반수열 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		long[] dp = new long[101];
		dp[1]=1;
		dp[2]=1;
		dp[3]=1;
		for(int i=4; i<=100; i++) {
			dp[i] = dp[i-2]+dp[i-3];
			System.out.println(dp[i]);
		}
		
		for(int i=0; i<T; i++) {
			System.out.println(dp[Integer.parseInt(in.readLine())]);
		}
	}
}
