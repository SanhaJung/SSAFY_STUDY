package a1013;

import java.io.*;
import java.util.*;

public class Solution_2105_디저트카페 {
	static int N, cafe;
	static int [][] map;
	static boolean [] cafeNum;
	static int [] di = {1, 1, -1, -1 }; //시계 방향 회전 - 우하, 좌하, 좌상, 우상 
	static int [] dj = {1,-1, -1, 1 };
	static int startI, startJ;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T ; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int [N][N];
			cafeNum = new boolean[101];
			for(int i =0; i<N; i++) {
				st =new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			cafe = -1; //아예 방문 못하면 -1 출력
			
			for(int i=0; i<N-2; i++) {
				for(int j=1; j<N-1; j++) {
					startI = i;
					startJ = j;
					//i, j에서 시작해서 사각형 그리는 dfs
					cafeNum[map[i][j]] = true;
					rotate (i, j, 0);
					cafeNum[map[i][j]] = false;
				}
			}
			System.out.println("#" + tc+" "+cafe);
		}//tc 끝
		br.close();
	}

	private static void rotate(int i, int j, int dir) {
		for(int d =dir ; d < dir+2 && d<4; d++) {
			int ni = i+di[d];
			int nj = j+dj[d];
			
			if(ni>=0 && ni<N && nj>=0 && nj<N) {
				if(ni == startI && nj == startJ) { //첫 방문지로 돌아왔을 때
					int sum =0;
					for(int idx =1; idx<=100; idx++) {
						if(cafeNum[idx]) sum++;
					}
					cafe = Math.max(cafe, sum);
					return;
				}
				
				if(!cafeNum[ map[ni][nj] ]) { //방문 할수 있으면
					cafeNum[map[ni][nj]] = true;
					rotate (ni, nj, d);
					cafeNum[map[ni][nj]] = false;
				}
				
			}
		}
		
	}
}
