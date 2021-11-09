package a1104;

import java.io.*;
import java.util.*;

public class Main_주사위굴리기_14499 {
	static int N, M, K;
	static int x,y;
	static int [][] map;
	static int [] dice = new int [7]; // 
	 //동 서 북 남
	static int []dx = {0, 0, -1, 1 };
	static int []dy = {1, -1, 0, 0 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int [N][M];
		for(int i =0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int k = 0; k<K; k++) {
			int dir = Integer.parseInt(st.nextToken());
			int nx = x+dx[dir-1];
			int ny = y+dy[dir-1];
			
			if(0<=nx && nx<N && 0<=ny && ny<M ) {
				changeDice(dir);
				
				if (map[nx][ny] == 0) {
                    map[nx][ny] = dice[6];
                } else {
                    dice[6] = map[nx][ny];
                    map[nx][ny] = 0;
                }
     
                x = nx;
                y = ny;
                System.out.println(dice[1]);
			}
		}
		
	}
	private static void changeDice(int dir) {
		int [] tempDice = dice.clone();
        // 6 밑면, 1 윗면
		
        // 동쪽 1, 서쪽 2, 북쪽 3, 남쪽 4
        if (dir == 1) {
            dice[1] = tempDice[4];
            dice[3] = tempDice[1];
            dice[4] = tempDice[6];
            dice[6] = tempDice[3];
        } else if (dir == 2) {
            dice[1] = tempDice[3];
            dice[3] = tempDice[6];
            dice[4] = tempDice[1];
            dice[6] = tempDice[4];
        } else if (dir == 3) {
            dice[1] = tempDice[5];
            dice[2] = tempDice[1];
            dice[5] = tempDice[6];
            dice[6] = tempDice[2];
        } else {
            dice[1] = tempDice[2];
            dice[2] = tempDice[6];
            dice[5] = tempDice[1];
            dice[6] = tempDice[5];
        }
		
	}
}
