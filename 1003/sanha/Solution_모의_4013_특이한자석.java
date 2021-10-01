package a1001.algo;

import java.io.*;
import java.util.*;

public class Solution_모의_4013_특이한자석 {

	static int K;
	static int total;
	static List[] map;
	

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_모의_4013_특이한자석.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			total = 0;
			K = Integer.parseInt(br.readLine());
			
			map = new List[4];
			for(int i=0;i<4;i++) {
				// 0: N극
				// 1: S극
				map[i] = new ArrayList<Integer>();
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<8;j++) {
					map[i].add(Integer.parseInt(st.nextToken()));
				}
			}

			for(int k=0;k<K;k++) {
				st = new StringTokenizer(br.readLine(), " ");
				int n = Integer.parseInt(st.nextToken())-1;
				int d = Integer.parseInt(st.nextToken());
				
				// 현재 날의 상태
				// 날의 자성이 달라서 돌아가면: true
				boolean[] state = new boolean[3];
				for(int i=0;i<3;i++) {
					if(map[i].get(2)!=map[i+1].get(6)) state[i] = true;
				}

				
				if(n==0) {
					rotate(n, d);
					for(int i=0;i<3;i++) {
						if(state[i]) {
							rotate(i+1, d*=(-1));
						} else break;
					}

				} 
				if(n==3) {
					rotate(n, d);
					for(int i=2;i>=0;i--) {
						if(state[i]) {
							rotate(i, d*=(-1));
						} else break;
					}

				} 
				if(n==1) {
					rotate(n, d);
					if(state[0]) rotate(0, d*(-1));
					for(int i=1;i<3;i++) {
						if(state[i]) {
							rotate(i+1, d*=(-1));
						} else break;
					}

				} 
				if(n==2) {
					rotate(n, d);
					if(state[2]) rotate(3, d*(-1));
					for(int i=1;i>=0;i--) {
						if(state[i]) {
							rotate(i, d*=(-1));
						} else break;
					}
				} 

			}
			
			for(int i=0;i<4;i++) {
				if((int)map[i].get(0)!=0) total += Math.pow(2, i);
			}
			
			sb.append("#").append(test_case).append(" ").append(total).append("\n");
		}
		System.out.println(sb);
		br.close();
	}


	private static void rotate(int n, int d) {
		if(d==1) { // 시계
			map[n].add(0, map[n].remove(7));
		}
		else {
			map[n].add(7, map[n].remove(0));
		}
		
	}

}
