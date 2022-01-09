package a1006.algo;

import java.io.*;
import java.util.*;

public class Solution_모의_4014_활주로건설_서울_12반_정산하 {
	
	static int N, X, map[][];
	static int Cnt;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_모의_4014_활주로건설.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			
			Cnt = 0;
			
			st  = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			// 활주로 초기값입력받기
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			// 행열기준으로 탐색
			for(int i=0;i<N;i++) {
				// 행기준
				checkRow(i);
//				System.out.println(Cnt);
				checkCol(i);
			}
			
			
			sb.append("#").append(test_case).append(" ").append(Cnt).append("\n");
		}
		System.out.println(sb);
		br.close();	
		
	}

	private static void checkCol(int i) {
		int deforCnt = 1;
		int deforH = map[0][i];
		for(int j=1;j<N;j++) {
			if(deforH-map[j][i]==0) {
				deforCnt++;
			}
			else if(Math.abs(deforH-map[j][i])>1) { // 활주로 못놓음
				return;
			}
			// 오르막
			else if(deforH-map[j][i]==-1) {  // 활주로
				// 경사로를 놓을 평지가  X보다 짧다.
				if(deforCnt<X) {
					return;
				}
				
				// 경사로를 놓았다면
				deforCnt=1;
				deforH=map[j][i];
			}
			// 내리막
			else if(deforH-map[j][i]==1) {  // 활주로
				for(int x=1;x<X;x++) {
					// 범위를 넘어가면 경사로를 만들지 못한다.
					if(j+x>=N) return;
					
					// 경사로를 놓을 높이와 현재높이가 다르면
					if(map[j+x][i]!=map[j][i]) {
						return;
					}

				}
				j += X-1;
				deforCnt=0;
				deforH--;
			}
		}
//		System.out.println(i);
		Cnt++;
		
	}

	private static void checkRow(int i) {
		int deforCnt = 1;
		int deforH = map[i][0];
		for(int j=1;j<N;j++) {
			if(deforH-map[i][j]==0) {
				deforCnt++;
			}
			else if(Math.abs(deforH-map[i][j])>1) { // 활주로 못놓음
				return;
			}
			// 오르막
			else if(deforH-map[i][j]==-1) {  // 활주로
				// 경사로를 놓을 평지가  X보다 짧다.
				if(deforCnt<X) {
					return;
				}
				
				// 경사로를 놓았다면
				deforCnt=1;
				deforH=map[i][j];
			}
			// 내리막
			else if(deforH-map[i][j]==1) {  // 활주로
				for(int x=1;x<X;x++) {
					// 범위를 넘어가면 경사로를 만들지 못한다.
					if(j+x>=N) return;
					
					// 경사로를 놓을 높이와 현재높이가 다르면
					if(map[i][j+x]!=map[i][j]) {
						return;
					}

				}
				j += X-1;
				deforCnt=0;
				deforH--;
			}
		}
//		System.out.println(i);
		Cnt++;
	}

}
