package s0915;

import java.io.*;
import java.util.*;
import java.awt.*;

public class Main_16234_인구이동 {

	static int N, L, R, answer;
	static int[][] map;
	static boolean[][] v;
	static Queue<Country> unions;
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static class Country{
		int y;
		int x;

		public Country(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
					
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());		// 입력 부분 
			}
		}
		
		/*
		 * 1. 만약 이미 방문된 국가면 스킵
		 * 2. 연합을 생성해본다. 
		 * 3. 연합이 생성된다면, 인구이동 계산 후, move를 true 처리해줌.
		 */
		
		answer = 0;
		
		while(true) {			
			v = new boolean[N][N];
			boolean move = false;
			
			for(int i=0; i<N; i++) {					// map을 순회하면서, 모든 국가에서 연합을 생성 시도 
				for(int j=0; j<N; j++) {
					if(v[i][j]) continue;				// 이미 방문된 국가라면(이미 연합에 포함되있다면) 스킵
					if(isUnion(i, j)) move = true;		// 만약 아직 방문되지 않았다면, map[i][j]를 시작으로 연합을 생성해보기 시 
				}										// 만약 해당 국가에서 isUnion이 true를 리턴한다면 연합이 생성되었고, 인구 이동이 발생한것이므로, move를 true로 초기화 
			}
			
			if(move) answer++;							// 인구 이동이 있었다면 answer값 1 증가 
			else break;									// 더 이상 생성될 수 있는 연합이 없다면 인구 이동이 없으므로 break
		}
		
		System.out.println(answer);
	}

	private static boolean isUnion(int y, int x) {		// map[y][x]가 연합인지 판별 
		int result = 0;									// 연할이 될 경우, 평균 인구 수로 변경시키기 위한 값 
		Queue<Country> tmp = new ArrayDeque<>();		// 인접하면서 L <= 인구수 <= R 인 국가들을 구하려면 좌표를 이동하면서 국가를 넣었다 뺴야하므로, poll을 수행할 용도의 큐 
		unions = new ArrayDeque<>();					// 연합에 속한 국가들을 저장할 리스트(poll이 없고 add만 할 용도) 
		
		tmp.offer(new Country(y, x));					// tmp와 unions에 모두 시작 국가를 add 
		unions.offer(new Country(y, x));
		
		result = map[y][x];								// 초기 값을 시작 국가의 인구수로 초기화
		while(!tmp.isEmpty()) {

			Country c = tmp.poll();						// 큐에서 국가를 빼고 
			v[y][x] = true;								// 방문 처리(이 후, 이동한 국가에서 연합 국가를 탐색할 때 제외시키기 위함)
			
			for(int d=0; d<4; d++) {
				int ny = c.y+dy[d];
				int nx = c.x+dx[d];
				
				if(ny<0||ny>=N || nx<0||nx>=N || v[ny][nx]) continue;	// 만약 이동 위치가 범위 밖이거나, 이미 연합에 속해있다면 스킵 
				
				int dist = Math.abs(map[c.y][c.x]-map[ny][nx]);			// 그게 아니라면, 우선 인구 수의 차이가 범위 내인지 판별 
				
				if(dist>=L && dist<=R) {
					v[ny][nx] = true;									// 만약 범위 내라면, 방문 처리 후,
					result += map[ny][nx];								// 총 인원 수에 이동한 국가의 인원 수 더
					
					unions.offer(new Country(ny, nx));					// tmp, unions에 이동한 국가 추가 
					tmp.offer(new Country(ny, nx));						
				}
			}
		}
		
		int unionCnt = unions.size();
		
		if(unionCnt > 1) {								// 만약 unions 리스트의 크기가 1보다 크다면, 연합이 생성된 것이므로,
			int avg = result/unionCnt;					// unions에 속한 국가들의 인구 수를 평균 값으로 업데이트 
			for(Country c : unions) {
				map[c.y][c.x] = avg;
			}
			return true;								// 인구 이동이 발생했으므로 true 리턴 
		}
		return false;									// 위에서 리턴되지 못했다면, 연합이 생성되지 못한 것이므로 false 리턴 
	}
}
