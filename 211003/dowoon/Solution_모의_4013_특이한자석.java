package s1003;

import java.io.*;
import java.util.*;

public class Solution_모의_4013_특이한자석 {

		
	static int T, K;
	static ArrayList<Integer>[] magnetList;
	static int[][] magnet;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			K = Integer.parseInt(br.readLine());
			magnetList = new ArrayList[5];
			for(int i=1; i<=4; i++) {								// 리스트 배열 초기화 및 값 저장 
				st = new StringTokenizer(br.readLine());
				magnetList[i] = new ArrayList<>();
				for(int j=0; j<8; j++) {
					magnetList[i].add(Integer.parseInt(st.nextToken()));
				}
			}

			for(int i=0; i<K; i++) {								// K번의 명령어 입력 
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				boolean[] ck = checkSpinAvailable(n);
				rotate(n, d, ck);
			}
			sb.append("#").append(tc).append(" ").append(checkMagnet()).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static boolean[] checkSpinAvailable(int n) {
		boolean[] ck = new boolean[5];
		if(n==1) {
			ck[1] = true;
			if(magnetList[1].get(2)!=magnetList[2].get(6)) {
				ck[2] = true;
				if(magnetList[2].get(2)!=magnetList[3].get(6)) {
					ck[3] = true;
					if(magnetList[3].get(2)!=magnetList[4].get(6)) {
						ck[4] = true;
					}
				}
			}
		}else if(n==2) {
			ck[2] = true;
			if(magnetList[2].get(2)!=magnetList[3].get(6)) {
				ck[3] = true;
				if(magnetList[3].get(2)!=magnetList[4].get(6)) {
					ck[4] = true;
				}
			}
			if(magnetList[2].get(6)!=magnetList[1].get(2)) {
				ck[1] = true;
			}
		}else if(n==3) {
			ck[3] = true;
			if(magnetList[3].get(2)!=magnetList[4].get(6)) {
				ck[4] = true;
			}
			if(magnetList[3].get(6)!=magnetList[2].get(2)) {
				ck[2] = true;
				if(magnetList[2].get(6)!=magnetList[1].get(2)) {
					ck[1] = true;
				}
			}
		}else if(n==4) {
			ck[4] = true;
			if(magnetList[4].get(6)!=magnetList[3].get(2)) {
				ck[3] = true;
				if(magnetList[3].get(6)!=magnetList[2].get(2)) {
					ck[2] = true;
					if(magnetList[2].get(6)!=magnetList[1].get(2)) {
						ck[1] = true;
					}
				}
			}
		}
		return ck;
	}

	private static int checkMagnet() {
		int sum = 0;
		for(int i=1; i<=4; i++) {
			if(magnetList[i].get(0) == 1) sum+= 1<<(i-1);
		}
		return sum;
	}

	private static void rotate(int n, int d, boolean[] ck) {
		// 2번 인덱스 -> 오른쪽 자석과 연결
		// 6번 인덱스 -> 왼쪽 자석과 연결
		
		for(int i=1; i<=4; i++) {
			if(ck[i]) {
				int dir = d;
				if(i%2 != n%2) dir *= -1;
				
				if(dir==1) {			// 시계방향(오른쪽으로 이동)
					int last = magnetList[i].get(magnetList[i].size()-1);
					magnetList[i].remove(magnetList[i].size()-1);
					magnetList[i].add(0, last);
				} else if(dir==-1) {	// 반시계 방향(왼쪽으로 이동) 
					int first = magnetList[i].get(0);
					magnetList[i].remove(0);
					magnetList[i].add(first);
				}
			}
		}
	}
}
