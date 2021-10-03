package s1003;

import java.io.*;
import java.util.*;

public class Main_17143_낚시왕 {

	static int R, C, M, answer;
	static int r, c, s, d, z;
	static Shark[][] map; 
	static int[] dy = {0, -1, 1, 0, 0};
	static int[] dx = {0, 0, 0, 1, -1};
	static class Shark {
		int r; 
		int c;
		int s;
		int d;
		int z;
		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	static List<Shark> sharkList;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Shark[R+1][C+1];
		sharkList = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());	// 상어가 위치한 행 인덱스 
			c = Integer.parseInt(st.nextToken());	// 상어가 위치한 열 인덱스 
			s = Integer.parseInt(st.nextToken());	// 상어의 속력 
			d = Integer.parseInt(st.nextToken());	// 상어가 이동할 방향(1: 상, 2: 하, 3: 우, 4: 좌)  
			z = Integer.parseInt(st.nextToken());	// 상어의 크기 
			map[r][c] = new Shark(r, c, s, d, z);
			sharkList.add(map[r][c]);
		}
		answer = 0;
		for(int i=1; i<=C; i++) {					// 낚시왕이 한칸 이동하면서 (상어는 1열부터 C열까지 존재) 
			catchShark(i);							// 해당 열에서 상어를 한마리 잡고 (없으면 answer값 변화 x)
			move();									// sharkList에 있는 상어
		}
		System.out.println(answer);
		br.close();
	}

	private static void move() {
		if(sharkList.size() > 0) {					// 잡을 상어가 한마리 라도 있을 경우에만 move 실행 
			for(int i=0; i<sharkList.size(); i++) {	// 상어 리스트에 있는 상어마다 이동 수행 
				Shark shark = sharkList.get(i);		// 이동시킬 상어
				int r = shark.r;
				int c = shark.c;
				int d = shark.d;
				int s = shark.s;
				
				while(s > 0) {						// 속도가 0이 될때까지( 즉, 설정된 방향으로 s번 움직일 때까지 ) 
					r += dy[d];						// 먼저 현재 위치에서 이동할 방향으로 이동시켜보고, 
					c += dx[d];

					if(r<1||r>R || c<1||c>C) {		// 만약 이동한 위치가 범위 밖이라면, 
						r -= dy[d];					// 다시 원래 자리로 돌아와서 
						c -= dx[d];
						
						if(d==1) d=2;				// 방향을 반대로 설정 ( 상<->하 , 우<->좌 ) 
						else if(d==2) d=1;
						else if(d==3) d=4;
						else if(d==4) d=3;
						
						continue;					// 방향이 바뀌었다면, 이동횟수로 치지 않기때문에, 아래의 s-- 코드를 실행시키지 않고, continue 
					}
					
					s--;							// 이동한 위치가 범위 내라면, 이동횟수를 감소시킴 
				}
				shark.r = r;						// 이동한 위치의 정보로 상어 정보 업데이트 
				shark.c = c;
				shark.d = d;
			}
			
			map = new Shark[R+1][C+1];				// 설정된 map을 초기화 시키고, 
			for(int i=0; i<sharkList.size(); i++) {	// 이동을 완료한 상어들을 재배치 
				int r = sharkList.get(i).r;
				int c = sharkList.get(i).c;
				
				if(map[r][c] == null) map[r][c] = sharkList.get(i);									// 만약 배치할 위치에 상어가 없다면 그냥 배치하고, 
				else map[r][c] = map[r][c].z > sharkList.get(i).z ? map[r][c] : sharkList.get(i);	// 이미 다른 상어가 존재한다면, 크기가 큰 상어를 배치 
			}
			
			sharkList.clear();						// sharklist를 초기화 (map에 배치된 상어들만 다시 리스트에 넣어주기 위해 clear 시킴)
			for(int r=1; r<=R; r++) {				// map에 존재하는 상어들만 sharklist에 추가 (이미 크기가 큰 상어에게 잡아먹힌 상어는 sharklist에서 제거되어야 하므로 )
				for(int c=1; c<=C; c++) {
					if(map[r][c] != null) sharkList.add(map[r][c]);
				}
			}
			
		}
	}

	private static void catchShark(int col) {
		for(int row = 1; row<=R; row++) {			// 1행부터 돌면서(사람과 가장 가까운 곳부터 돌면서)
			if(map[row][col]!=null) {				// 잡을 상어가 있다면(map[r][c]가 null이 아니면 상어가 있음) 
				answer += map[row][col].z;			// 해당 상어의 크기를 더하고 바로 탈출(가장 가까운 한마리만 잡기 때문에)
				sharkList.remove(map[row][col]);	// 잡은 상어는 sharkList에서 제거해줌 (크기는 모두 다르다고 하였기에 고유한 값이므로 고유한 값으로 비교)
				map[row][col] = null;				// 잡은 상어가 위차하던 곳을 null로 변경해줌 
				break;								// 한번에 한마리의 상어만 잡기때문에 break로 탈출 
			}
		}
	}
}
