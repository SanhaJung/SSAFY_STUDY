package bj2217;

import java.util.*;
import java.io.*;

public class Main_bj_2217_로프 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		int answer = Integer.MIN_VALUE/2;
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		Arrays.sort(arr);
		
		for(int i=0; i<N; i++) {			
			answer = Math.max(answer, arr[i]*(N-i));
		}
		
		System.out.println(answer);
	}

}
