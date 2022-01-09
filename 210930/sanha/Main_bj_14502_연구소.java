package a0930.algo;

import java.io.*;
import java.util.*;

public class Main_bj_14502_연구소 {
	
	static int N, M;
	static int[][] map;
	static int[][] curMap; // 벽세운 경우의 배열(map copy배열)
	
	static int[] di = {-1, 1, 0, 0}; // 상하좌우
	static int[] dj = { 0, 0,-1, 1};
	
	static List<int[]> virus_list = new LinkedList<>(); // 바이러스 리스트
	static List<int[]> blank_list = new LinkedList<>(); // 빈칸리스트
	static int[] numbers;  // 조합의 인덱스(빈칸리스트의 인덱스)
	
	static int minAddVirus = 987654321;  // 바이러스 최소개수 저장 변수(안전지대가 최대이기 위해)

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14502_연구소.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		numbers = new int[3];	// 조합을 저장할 배열 할당
		
		// 입력 받기
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) virus_list.add(new int[] {i, j});	// 바이러스 리스트에 추가
				if(map[i][j]==0) blank_list.add(new int[] {i, j});  // 빈칸리스트에 추가
			}
		}
		
		// 빈칸중에 3개뽑기 - 조합
		comb(0, 0);
		
		// 안전지역 출력
		// 초기 빈칸의 수 - 추가된 바이러스의 수 - 3(새로 새운 벽)
		System.out.println( blank_list.size() - minAddVirus - 3);
	}
	
	// 3개의 벽을 놓을 조합 생성
	private static void comb(int cnt, int start) {
		if(cnt == 3) {
			// 벽 세운 카피 맵
			// 초기 맵 deep copy
			curMap = new int[N][M];
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) curMap[i][j]=map[i][j];
			}
			// 3개 벽세우기
			for(int idx:numbers) {
				curMap[blank_list.get(idx)[0]][blank_list.get(idx)[1]] = 1;
			}
			
			// 바이러스 뿌리기
			int vi_add = 0;
			for(int[] vi: virus_list) {			// 초기에 있던 바이러스로부터 바이러스 퍼트리기
				vi_add += bfs(vi[0], vi[1]);	// 추가된 바이러스 수 누적
			}
			// 추가된 바이러스가 최소값인지아닌지 
			minAddVirus = Math.min(minAddVirus, vi_add);
			return;
			
		}
		// 조합
		for(int i=start;i<blank_list.size();i++) {
			numbers[cnt] = i;
			comb(cnt+1, start+1);
		}
	}
	
	// 바이러스 뿌리기(추가된 바이러스의 수 리턴)
	private static int bfs(int c, int r) { 
		int cnt = 0;	// 추가된 바이러스 수 
		
		boolean[][] v= new boolean[N][M];
		Deque<int[]> q = new ArrayDeque<int[]>();
		// 방문처리 후 
		// 큐에 추가
		v[c][r] = true;
		q.offer(new int[] {c, r});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int pi = cur[0];
			int pj = cur[1];
			
			for(int d=0;d<4;d++) {
				int ni = pi + di[d];
				int nj = pj + dj[d];
				// 범위내에 있고 방문하지 않았으며
				// 빈칸일때
				if(0<=ni && ni<N && 0<=nj && nj<M && !v[ni][nj] && curMap[ni][nj]==0) {
					curMap[ni][nj] = 2;	// 바이러스퍼트리기
					cnt++;				// 추가된 바이러스 수 증가
					v[ni][nj] = true;	// 방문처리 후 큐에 추가
					q.offer(new int[] {ni, nj});
				}
			}
		}
		return cnt;		// 추가된 바이러스 수 리턴
	}

}
