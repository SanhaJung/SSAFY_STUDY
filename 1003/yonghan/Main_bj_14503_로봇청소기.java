package a1003;

import java.io.*;
import java.util.*;

public class Main_bj_14503_로봇청소기 {
	static int N,M, cnt;
	static int []di = {-1, 0, 1, 0}; //북 동 남 서
	static int []dj = {0, 1, 0, -1};
	static int [][] map;
	static boolean [][] check;
	
	static class Cleaner {
		int i,j,d;
		public Cleaner(int i , int j, int d) {
			this.i = i;
			this.j = j;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int [N][M];
		check = new boolean [N][M];
		st= new StringTokenizer(br.readLine());
		
		Cleaner cleaner = new Cleaner(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		for(int i= 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int startI = cleaner.i;
		int startJ = cleaner.j;
		int startD = cleaner.d;
		
		cnt=0;
		clean(startI, startJ, startD);
		System.out.println(cnt);
	}
	
	public static void clean(int row, int col, int direction) {
        // 현재 위치 청소
        if (map[row][col] == 0) {
            map[row][col] = -1; //구분하기 위해 -1로 (0을 바꿔줌)
            cnt++;
        }

        //왼쪽방향부터 차례대로 탐색을 진행한다.
        boolean flag = false;
        int origin = direction;
        
        for (int i = 0; i < 4; i++) { 
            int next_d = (direction + 3) % 4; //0->3 / 1->0 / 2->1 // 3->2
            int next_r = row + di[next_d];
            int next_c = col + dj[next_d];

            if (next_r > 0 && next_c > 0 && next_r < N && next_c < M) {
                if (map[next_r][next_c] == 0) {   // 아직 청소하지 않은 공간이라면
                    clean(next_r, next_c, next_d);
                    flag = true;
                    break;
                }
            }
            direction = (direction + 3) % 4;
        }

        // 네 방향 모두 청소가 되어있거나 벽인 경우
        if (!flag) {
            int next_d = (origin + 2) % 4; //북 0 <-> 남 2 // 동 1 <-> 서 3
            int next_br = row + di[next_d];
            int next_bc = col + dj[next_d];

            if (next_br > 0 && next_bc > 0 && next_br < N && next_bc < M) {
                if (map[next_br][next_bc] != 1) {
                    clean(next_br, next_bc, origin); // 바라보는 방향 유지한 채 후진
                }
            }
        }
    }

}
