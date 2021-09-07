package a0903;

import java.io.*;
import java.util.*;

public class Main_bj_3190_뱀 {
	static class Direction {
		int second;
		char dir;

		public Direction(int second, char dir) {
			this.second = second;
			this.dir = dir;
		}
	}

	static int[] di = { -1, 0, 1, 0 }; // 북 동 남 서
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/snake.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		int[][] map = new int[N + 1][N + 1];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			map[row][col] = 1; // 사과

		}

		int L = Integer.parseInt(br.readLine());
		Direction[] input = new Direction[L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			input[i] = new Direction(sec, c);
		}

		List<int[]> list = new ArrayList<int[]>(); // 뱀 위치를 담을 큐 //리스트로 바꾸기 remove0으로 진행
	
		list.add(new int[] { 1, 1 });
	
		int idx = 0, sec = 0;

		int d = 1; // 방향변수 (북, 동, 남, 서 ) 처음 동쪽이므로 1
		int i = 1, j = 1; // 시작 좌표 (1,1)

		while (true) {
			// (1,1)에서 뱀 시작 , 맵 크기 벗어나면 break
			// 사과만나면 몸통 줄어들지 않음(뱀의 머리이동후 꼬리 변경x)
			// input 배열의 dir에 따라 우회전, 좌회전
			sec++;
			
			if (idx < L && sec == input[idx].second) {
				if (input[idx].dir == 'D') {
					// 우회전
					d = (d + 1) % 4; // 북 동 남 서 기준으로 가는방향 +1이 우회전
				} else if (input[idx].dir == 'L') {
					// 좌회전
					d = (d + 3) % 4; // 북 동 남 서 기준으로 가는방향 +3이 우회전
				}
				idx++;
			}

			int ni = i + di[d];
			int nj = j + dj[d];

			list.add(new int[] { ni, nj }); // 뱀 머리칸

			if (ni <= 0 || N < ni || nj <= 0 || N < nj) { // 범위밖
				sec++;
				break;
			}

			if (map[ni][nj] == 1) {
				map[ni][nj] = 0;
				list.add(new int[] {ni, nj});
			}
				

			else if (map[ni][nj] == 0) {
				list.add(new int[] {ni, nj});
				list.remove(0);
				
			} else {
				// System.out.println("뱀머리 좌표 : " +ni +" , " +nj +"값 : "+map[ni][nj]);
				sec++;
				break;

			}

			sec++;
			i = ni;
			j = nj;

		}

		System.out.println(sec);

		br.close();

	}
}
