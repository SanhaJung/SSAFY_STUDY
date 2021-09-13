package a0908.study;

import java.io.*;
import java.util.*;

public class Main_bj_14891_톱니바퀴 {
	static char[][] wheel = new char[4][];
	static int K;
	static int[][] n;
	static int[] da = new int[] {1, -1};
	static int result;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14891_톱니바퀴.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for(int i=0;i<4;i++) {
			wheel[i] = br.readLine().toCharArray();
		}
		
		K = Integer.parseInt(br.readLine());
		
		n = new int[K][2];
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			n[i][0] = Integer.parseInt(st.nextToken());
			n[i][1] = Integer.parseInt(st.nextToken());
		}
		for(int[] e: n) {
			// 톱니 상태
			int[] r = state(wheel);
			// 처음 회전 방향
			int didx = 0;
			if(e[1]==-1) didx = 1;
			// 톱니 비교
			if(e[0]==1) {
				// 회전 시작 톱니 돌리기
				rotation(0, e[1]);
				for(int i=0;i<3;i++) {
					if(r[i]==0) break;
					else {
						rotation(i+1, da[(++didx)%2]);
					}
				}
			} else if(e[0]==2){
				rotation(1, da[didx]);
				if(r[0]==1) rotation(0, da[(didx+1)%2]);
				if(r[1]==1) {
					// 전위, 후위 연산 조심!!!
					rotation(2, da[(++didx)%2]);
					if(r[2]==1) rotation(3, da[(++didx)%2]);
				}
			}else if(e[0]==3) {
				rotation(2, da[didx]);
				if(r[2]==1) rotation(3, da[(didx+1)%2]);
				if(r[1]==1) {
					rotation(1, da[(++didx)%2]);
					if(r[0]==1) rotation(0, da[(++didx)%2]);
				}
			}else if(e[0]==4) {
				rotation(3, e[1]);
				for(int i=2;i>=0;i--) {
					if(r[i]==0) break;
					else {
						rotation(i, da[(++didx)%2]);
					}
				}
			}
			
		
		}

		
		for(int i=0;i<4;i++) {
			if(wheel[i][0]=='1') result += Math.pow(2, i);
		}
		System.out.println(result);
	}
	
	static void rotation(int num, int d) {
		// 시계방향
		if(d==1) {
			char t = wheel[num][7];
			char temp =wheel[num][0];
			for(int i=1;i<=7;i++) {
				char temp2  = wheel[num][i];
				wheel[num][i]=temp;
				temp = temp2;
			}
			wheel[num][0] = t;
		// 반시계방향
		}else if(d==-1) {
			char t = wheel[num][0];
			char temp =wheel[num][7];
			for(int i=6;i>=0;i--) {
				char temp2  = wheel[num][i];
				wheel[num][i]=temp;
				temp = temp2;
			}
			wheel[num][7] = t;
		}
	}
	static int[] state(char[][] arr) {
		int[] r = {0, 0, 0};
		for(int i=0;i<3;i++) {
			// 자석 다를때 1
			if(arr[i][2]!=arr[i+1][6]) r[i] = 1;
		}
		return r;
	}
}
