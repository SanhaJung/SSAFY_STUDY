package a8458;

import java.util.*;
import java.io.*;

public class Solution_d4_8458_원점으로집합 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/a8458/input_d4_8458.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(in.readLine());
			int[] arr = new int[N];
			int answer = 0;
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				arr[i] = Math.abs(Integer.parseInt(st.nextToken()))+Math.abs(Integer.parseInt(st.nextToken()));
			}
			
			// 1. 원점으로부터의 맨해튼 거리가 모두 홀수이거나 짝수여야됨.
			int max = arr[0];
			int check = arr[0]%2;
			for(int i=1; i<N; i++) {
				max = Math.max(max, arr[i]);
				if((arr[i])%2 != check) {
					answer = -1;
					break;
				}
			}
			
			int sum = 0;			// 총 이동량을 나타낼 변수
			if(answer>=0) {
				while(true) {
					if(sum>=max && (sum-max)%2 == 0)	// 총 이동량이 최대값의 맨해튼 거리보다 많고 그 차이가 짝수면 종료(홀수일경우 다음에 왔다갔다 해야함)
						break;
					sum += ++answer;
				}
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb.toString());
		in.close();
	}

}
