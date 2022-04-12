package bj1003;

import java.util.*;
import java.io.*;

public class Main_bj_1003_피보나치함수 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		int[] zero = new int[41];
		zero[0] = 1;
		zero[1] = 0;
		int[] one = new int[41];
		one[0] = 0;
		one[1] = 1;
		
		for(int i=2; i<=40; i++) {
			zero[i] = zero[i-1]+zero[i-2];
			one[i] = one[i-1]+one[i-2];
		}
		
		for(int i=0; i<T; i++) {
			int num = Integer.parseInt(in.readLine());
			System.out.println(zero[num]+" "+one[num]);
		}
		
		
	}
}
