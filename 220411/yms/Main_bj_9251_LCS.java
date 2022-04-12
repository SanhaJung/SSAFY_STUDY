package bj9251;

import java.util.*;
import java.io.*;

public class Main_bj_9251_LCS {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = in.readLine();
		String s2 = in.readLine();
		
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		
		for(int i=1; i<=s1.length(); i++) {
			for(int j=1; j<=s2.length(); j++) {
				// ex) s1 : {A,C}, s2 : {C,A,P,C} 에서 s1[1]와 s2[3]이 같으므로 기존 최장 부분 수열에 C를 붙일 수 있다.
				// 따라서 {A} {C,A,P}의 최장 부분 수열에서 길이를 1만큼 더해주면됨
				// 만약 s1[1]와 s2[2]처럼 다른 경우에는 이전 최장 부분 수열과 길이가 같으므로
				// s1[1]을 제외한 {A},{C,A,P}의 최장 부분 수열의 길이와 s2[2]를 제외한 {A,C} {C,A}의 최장 부분 수열의 길이 중 큰 값을 넣으면 된다. 
				if(s1.charAt(i-1) == s2.charAt(j-1)) dp[i][j] = dp[i-1][j-1]+1;
				else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		
		System.out.println(dp[s1.length()][s2.length()]);
		
	}
}
