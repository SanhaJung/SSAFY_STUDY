package a1009;

import java.io.*;
import java.util.*;

public class Main_bj_14888_연산자끼워넣기 {
	static int N,min, max;
	static int [] oper = new int [4]; // 덧셈의 갯수, 뺄샘의 갯수, 곱셈의 갯수, 나눗셈의 갯수 저장 배열
	static int [] input;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		input = new int [N];
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		//N개 수 연산할수 있는 방법 모두 해보기
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		cal(input[0] , 1);
		
		System.out.println(max);
		System.out.println(min);
	}
	private static void cal(int val, int idx) {
		if(idx == N ) {
			max = Math.max(max,  val);
			min = Math.min(min,  val);
			return; 
		}
	
		for(int i =0; i<4; i++) {
			if(oper[i] > 0) {
				oper[i]--;
				if(i == 0) {
					cal(val + input[idx], idx+1);
				}
				else if(i == 1) {
					cal(val - input[idx], idx+1);
				}
				else if(i == 2) {
					cal(val * input[idx], idx+1);
				}
				else  {
					//음수를 양수로 나눌 때는 음수를 양수로 바꾼 뒤 몫을 취하고 그 몫을 음수로 바꾼 것과 같다
					if(val<0 && input[idx] > 0) {
						val *=-1;
						int result = val / input[idx];
						result *=-1;
						cal(result, idx+1);
					}
					else {
						cal ( val / input[idx], idx+1);
					}
				}
				oper[i]++; //한 번의 경우 본 뒤에 다시 연산자 갯수 증가시켜 완탐 하도록
				
			}
		}
	}
}
