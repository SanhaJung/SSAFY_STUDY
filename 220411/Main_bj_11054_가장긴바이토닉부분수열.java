package bj11054;

import java.util.*;
import java.io.*;

public class Main_bj_11054_가장긴바이토닉부분수열 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int[] arr = new int[N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] LIS = new int[N+1];	// Longest Increasing Sequence
		int[] LDS = new int[N+1];	// Longest Decreasing Sequence
		
		for(int i=1 ;i<=N; i++) {
			LIS[i] = 1;
			LDS[i] = 1;
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<i; j++) {
				// LIS[i] : i번째까지의 최장 증가 수열 길이
				if(arr[i]>arr[j]) LIS[i] = Math.max(LIS[i], LIS[j]+1);
			}
			for(int j=N; j>N-i; j--) {
				// LDS[i] : i부터 N까지의 최장 감소 수열 길이
				if(arr[N-i]>arr[j]) LDS[N-i] = Math.max(LDS[N-i], LDS[j]+1);
			}
		}
		
		int answer = 0;
		for(int i=1; i<=N; i++) {
			// 1~i번째까지의 최장 증가 수열 길이 + i번째~N번째까지의 최장 감소 수열 길이 - 1 (i번째가 중복되므로)
			answer = Math.max(answer, LDS[i]+LIS[i]-1);
		}
		
		System.out.println(answer);
		
	}
}
