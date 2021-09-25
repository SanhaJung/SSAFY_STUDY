package s0917;

import java.io.*;
import java.util.*;

public class Main_bj_17144_미세먼지안녕_서울_12반_김도 {

	static int R, C, T, cleaner;
	static int[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Queue<Dust> dusts;
	static class Dust {
		int y;
		int x;
		int w;
		public Dust(int y, int x, int w) {
			super();
			this.y = y;
			this.x = x;
			this.w = w;
		}
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R =  Integer.parseInt(st.nextToken());
		C =  Integer.parseInt(st.nextToken());
		T =  Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) cleaner = i;
			}
		}

		while(T-- > 0) {
			spreadDust();								// 먼지 확산(bfs)
			clearAir();									// 공기 청정(배열 이동) 
		}
		
		int answer = 0;						
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] > 0) answer += map[i][j];	// 먼지의 총량 계산 
			}
		}
		System.out.println(answer);
	}

	private static void clearAir() {
		int first = cleaner-1;
		int second = cleaner;
 
        // 위쪽 공기청정기 - 반시계방향
        for (int i=first-1; i>0; i--) map[i][0] = map[i-1][0];			// v 
        for (int i=0; i<C-1; i++) map[0][i] = map[0][i+1];				// <
        for (int i=0; i<first; i++) map[i][C-1] = map[i+1][C-1];		// ^
        for (int i=C-1; i>1; i--) map[first][i] = map[first][i-1];		// >
        map[first][1]=0;												// 공기청정기에서 바람이 나오는 곳은 0처리(미세먼지가 있을 수 없음)
        
        // 아래쪽 공기청정기 - 시계방향
        for (int i=second+1; i<R-1; i++) map[i][0] = map[i+1][0];		// ^
        for (int i=0; i<C-1; i++) map[R-1][i] = map[R-1][i+1]; 			// <
        for (int i=R-1; i>second; i--) map[i][C-1] = map[i-1][C-1];		// v
        for (int i=C-1; i>1; i--) map[second][i] = map[second][i-1];	// > 
        map[second][1] = 0;												// 공기청정기에서 바람이 나오는 곳은 0처리(미세먼지가 있을 수 없음)
	
	}

	private static void spreadDust() {
		dusts = new ArrayDeque<>();										// 먼지들을 넣을 큐 
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] > 0) dusts.offer(new Dust(i, j, map[i][j]));	// 먼지인 경우에만 큐에 추가 
			}
		}																
		while(!dusts.isEmpty()) {
			Dust cur = dusts.poll();									// 큐에서 먼지 정보를 하나 빼고, 
			int y = cur.y;
			int x = cur.x;
			
			if(cur.w < 5) continue;										// 만약 해당 좌표의 미세먼지가 5 미만이면(5로 나눴을 때 0이므로 퍼지지 못함) 다음 미세먼지로 이동 
			
			int addAmount = cur.w/5;									// 옆에 퍼트릴 먼지량 계산 
			int spreadCnt = 0;											// 먼지가 퍼진 방향을 셀 변수 
			
			for(int d=0; d<4; d++) {
				int ny = y+dy[d];
				int nx = x+dx[d];
				
				if(ny<0||ny>=R || nx<0||nx>=C || map[ny][nx]==-1) continue;	// 범위 밖이거나, 공기청정기의 위치면 스킵 
				
				map[ny][nx] += addAmount;								// 그게 아니면, 이동할 위치에 퍼트릴 먼지량을 더함 (이미 미세먼지가 있는 경우에도 더함)
				++spreadCnt;											// 퍼트린 방향 수 증가 
			}
			
			map[y][x] -= addAmount*spreadCnt;							// 미세먼지의 중심지(미세먼지를 퍼뜨린 곳)의 미세먼지량 업데이트 
		}
	}
}
