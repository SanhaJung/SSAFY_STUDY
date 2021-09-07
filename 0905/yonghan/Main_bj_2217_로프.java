package a0903;

import java.io.*;
import java.util.*;

public class Main_bj_2217_로프 {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/rope.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int [] rope = new int [N];
		
		int max = 0;
		
		for(int i =0; i<N; i++) {
			rope[i] = sc.nextInt();
			
		}
		
		Arrays.sort(rope);
		max = rope[N-1];
		
		for(int i =0; i<N; i++) {
			int temp = rope[N-1-i]*(i+1);
			if(temp>=max) max = temp;
		}
		
		System.out.println(max);
		sc.close();
	}
}
