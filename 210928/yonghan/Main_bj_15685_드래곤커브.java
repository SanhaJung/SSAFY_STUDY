package a0928;

import java.io.*;
import java.util.*;

public class Main_bj_15685_드래곤커브 {
	static boolean[][] map = new boolean[101][101];
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//  0(->) 1(^) 2(<-) 3(v)
		// 0세대  ->
		// 1세대  -> ^
		// 2세대  -> ^ <- ^
		// 3세대  -> ^ <- ^ <- v <- ^
		// 4세대  -> ^ <- ^ <- v <- ^ ...
		// 규칙 : 총 크기는 전세대 *2 , 추가되는 방향은 전 세대 역으로 +1씩 더해줌 
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			dragonCurve(x, y, d, g);
		}
		
		//i , j 기준으로 오른쪽, 오른쪽위, 위 4꼭지점 모두 그려진 꼭지점이면 카운트 
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
					answer++;
				}
			}
		}

		System.out.println(answer);
		br.close();
	}

	public static void dragonCurve(int x, int y, int d, int g) {
		List<Integer> list = new ArrayList<>();
		list.add(d);
		//g 세대 까지
		for (int i = 1; i <= g; i++) {
			// 이전 세대의 마지막 인덱스부터 거꾸로 방향 +1 해주면서 추가
			for (int j = list.size() - 1; j >= 0; j--) {
				list.add((list.get(j) + 1) % 4);
			}
		}
		//꼭지점 추가
		map[y][x] = true; 
		for (Integer direction : list) {
			x += dx[direction];
			y += dy[direction];
			map[y][x] = true; //새로 추가된 꼭지점 true
		}
	}
}
