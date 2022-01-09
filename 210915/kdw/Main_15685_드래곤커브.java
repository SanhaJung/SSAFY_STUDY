package s0915;

/*
 * 참고 : https://ju-nam2.tistory.com/93
 */

import java.io.*;
import java.util.*;
import java.util.List;
import java.awt.*;

public class Main_15685_드래곤커브 {

	static int N, x, y, d, g;
	static boolean[][] map;
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new boolean[101][101];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			
			draw(x, y, d, g);
		}
		
		int answer = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) answer++;
			}
		}
		System.out.println(answer);
		br.close();
	}

	private static void draw(int x, int y, int dir, int gen) {
		List<Integer> dirList = new ArrayList<>();
		dirList.add(dir);
		
		for (int i = 1; i <= gen; i++) {				// n 세대 드래곤은 n-1세대 드래곤에서 생기는 커브의 두배(90도 회전해서)가 생기므로 
            for (int j=dirList.size()-1; j>=0; j--) {	// 이전 세대까지 축적한 커브을 90도 회전시켜 dirList에 추가
            	dirList.add((dirList.get(j) + 1) % 4);	// 방향이 전환되므로 +1 처리 
            }
        }

        map[y][x] = true;
        int ny = y;
        int nx = x;
        
        for (Integer d : dirList) {						// dirList에 있는 방향들을 순서대로 순회하면서 
            ny += dy[d];								// 이동할 방향으로 꼭지점 마킹 
            nx += dx[d];
            map[ny][nx] = true;
        }
	}
}
