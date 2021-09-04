package a0903.study;

import java.util.*;
import java.io.*;

public class Main_bj_2217_로프 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] rope = new int[N];
		int maxW = 0;
		
		for(int i=0;i<N;i++) {
			rope[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(rope);
		
		for(int i=0;i<N;i++) {
			maxW = Math.max(maxW, rope[i]*(N-i));
		}
		
		System.out.println(maxW);
		br.close();
	}

}
