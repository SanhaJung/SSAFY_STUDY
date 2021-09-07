package as0907;

import java.io.*;
import java.util.*;

//  + - * /
public class Main_bj_14888_연산자끼워넣기_서울_12반_허은아 {
	static int min, max, N;
	static char[] oper;
	static int[] num;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < num.length; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		char[] o = new char[] { '+', '-', '*', '/' };
		oper = new char[N - 1];

		st = new StringTokenizer(br.readLine(), " ");
		int k = 0;
		for (int i = 0; i < 4; i++) {
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				oper[k++] = o[i];
			}
		}

		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		char[] temp = new char[N - 1];
		boolean[] v = new boolean[N - 1];
		perm(0, temp, v);
		System.out.print(max + "\n" + min);

	}

	static void perm(int cnt, char[] temp, boolean[] v) {
		if (cnt == N - 1) {
			int result = cal(num, temp);
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}
		for (int i = 0; i < N - 1; i++) {
			if (v[i])
				continue;

			temp[cnt] = oper[i];
			v[i] = true;
			perm(cnt + 1, temp, v);
			v[i] = false;
		}
	}

	static int cal(int[] num, char[] temp) {
		int sum = num[0];

		for (int i = 0; i < temp.length; i++) {
			char c = temp[i];
			switch (c) {
			case '+': {
				sum += num[i + 1];
				break;
			}
			case '-': {
				sum -= num[i + 1];
				break;
			}
			case '*': {
				sum *= num[i + 1];
				break;
			}
			case '/': {
				sum /= num[i + 1];
				break;
			}
			}
		}

		return sum;
	}
}
