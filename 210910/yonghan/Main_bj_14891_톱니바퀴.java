package a0907;

import java.io.*;
import java.util.StringTokenizer;

public class Main_bj_14891_톱니바퀴 {
	static int [][] gear = new int [4][8];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i =0; i<4; i++) {
			//st = new StringTokenizer(br.readLine()); 입력 공백없음
			String input = br.readLine();
			for(int j=0; j<8; j++) {
				gear[i][j] = input.charAt(j) -'0';
			}
		}
		int K = Integer.parseInt(br.readLine());
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()); //몇번째
			int dir = Integer.parseInt(st.nextToken()); // 오른쪽 왼쪽
			
			rotation(idx-1, dir);
		}
		
		int sum=0;
		int [] score = {1,2,4,8};
		for(int i =0; i<4; i++) {
			if(gear[i][0] == 1) sum+=score[i];
		}
		
		System.out.println(sum);
	}
	private static void rotation(int idx, int dir) {
		//현재 idx 톱니 기준으로 왼쪽 오른쪽 톱니 회전 
		//(만약 회전한다면 방향은 반대)
		left(idx-1, -dir);
		right(idx+1, -dir);
		self(idx, dir);
	}
	static void self(int idx, int dir) {
		if(dir==1) { //시계 방향 
			//0 1 2 3 ... -> 7 0 1 2 ...
			int temp = gear[idx][7];
			for(int i = 7; i>0; i--) {
				gear[idx][i] = gear[idx][i-1];
			}
			gear[idx][0] = temp;
			
		}else { //반시계 방향
			int temp = gear[idx][0];
			for(int i =0; i<7; i++) {
				gear[idx][i] = gear[idx][i+1];
			}
			gear[idx][7] = temp;
		}
	}
	static void right(int idx, int dir) {
		if(idx>3) return;
		
		if(gear[idx][6] !=gear[idx-1][2]) { // 현재 톱니 9시방향과 이전톱니 3시방향 극이 다르면
			right(idx+1, -dir); //현재 기준 오른쪽 톱니 회전
			self(idx, dir); //자신 회전
		}
	}
	static void left(int idx, int dir) {
		if(idx<0) return;
		
		if(gear[idx][2] != gear[idx+1][6]) { //현재 톱니 3시 방향과 다음톱니 9시방향 극이 다르면
			left(idx-1, -dir); //현재 기준 왼쪽 톱니 회전
			self(idx, dir); //자신 회전
		}
	}
}	
