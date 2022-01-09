package a0903;

import java.io.*;
import java.util.*;

public class Main_bj_2217_로프_서울_12반_허은아 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Integer[] rope = new Integer[N];

		for (int i = 0; i < N; i++) {
			rope[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(rope, Collections.reverseOrder());

		int max = rope[0];
		for (int i = 1; i < rope.length; i++) {
			max = Math.max(max, rope[i] * (i + 1));
		}
		System.out.println(max);
	}

}
