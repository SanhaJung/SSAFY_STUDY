package a0903;

import java.io.*;
import java.util.*;

public class Main_bj_14501_퇴사_서울_12반_허은아 {
	// N+1일째 퇴사
	// N일 동안 최대한 많은 상담
	// 하루에 하나씩 서로 다른 사람의 상담
	// 상담기간 T, 금액 P
	// 최대 수익 구하기

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // N+1일 째에 퇴사
		int[][] consulting = new int[N+1][2];
		
		for (int i = 1; i < N+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			consulting[i][0] = Integer.parseInt(st.nextToken());
			consulting[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < consulting.length; i++) {
			
		}
		
	}

}
