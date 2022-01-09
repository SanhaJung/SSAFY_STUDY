package swTestSample;

import java.io.*;
import java.util.*;

public class Solution_2382_미생물격리 {
	static class Microbe implements Comparable<Microbe> {
		int r, c, num, d; 				// 세로위치, 가로위치, 미생물 수, 이동방향

		public Microbe(int r, int c, int num, int d) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
			this.d = d;
		}

		@Override
		public int compareTo(Microbe o) {
			return o.num - this.num; 	// 미생물의 수가 큰 순서로
		}
	}

	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());						 // N*N Map
			int M = Integer.parseInt(st.nextToken());				     	 // 격리 시간
			int K = Integer.parseInt(st.nextToken()); 					     // 미생물 군집의 개수

			Microbe map[][] = new Microbe[N][N];							 // 군집 배치할 맵
			List<Microbe> microbes = new ArrayList<>();						 // 살아있는 군집들 관리
			// 초기 미생물 군집들
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());					 // 미생물 수
				int d = Integer.parseInt(st.nextToken()) - 1; 				 // 방향
				Microbe micro = new Microbe(r, c, n, d);
				microbes.add(micro);
			}

			// 격리 시간 M만큼
			for (int m = 0; m < M; m++) {
				for (int i = 0; i < microbes.size(); i++) {					 // 군집 이동
					Microbe micro = microbes.get(i);
					int d = micro.d;
					int num = micro.num;
					int nr = micro.r + dr[d];								 // 바라본 곳으로 이동
					int nc = micro.c + dc[d];
					if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {  // 가장자리라면
						if (d <= 1)	d = 1 - d;								 // 방향 반대로
						else d = 5 - d;
						num /= 2;											 // 미생물 수 반으로
					}
					micro.r = nr;											 // 군집 정보 업데이트
					micro.c = nc;
					micro.d = d;
					micro.num = num;
				}
				
				Collections.sort(microbes);									 // 미생물의 수가 많은 군집부터
				map = new Microbe[N][N];
				// 군집이동이 끝난 후 map 위에 배치하기
				for (int i = 0; i < microbes.size(); i++) {
					Microbe micro = microbes.get(i);
					int num = micro.num;
					int nr = micro.r;
					int nc = micro.c;
					if (num == 0) continue;									 // 미생물의 수가 0이면 군집 사라짐
					if (map[nr][nc] == null) { 								 // 맵의 해당 위치가 null이면 그냥 놓기
						map[nr][nc] = micro;
					} else { 												 // null이 아니면
						map[nr][nc].num += micro.num;						 // 미생물의 수가 많은 군집부터 놓았으므로
					}
				}
				
				microbes.clear();							
				for (int i = 0; i < N; i++) {	
					for (int j = 0; j < N; j++) {
						if (map[i][j] != null) {							 // 맵 위에 군집이 있으면
							microbes.add(map[i][j]);						 // 군집 관리 리스트에 추가
						}
					}
				}
			}
			
			long result = 0;
			for (int i = 0; i < microbes.size(); i++) {
				result += microbes.get(i).num;
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
