package a0924.com.ssafy.ws;
import java.io.*;
import java.util.*;

public class Main_bj_7576_토마토_서울_12반_김도운 {
	
	static int N, M, answer;
	static int[][] arr;
	static Queue<Tomato> q;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static class Tomato {
		int y;
		int x;
		public Tomato(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		q = new ArrayDeque<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) q.offer(new Tomato(i, j));			// 익은 토마토의 좌표를 큐에 넣음 
			}
		}
		
		while(!q.isEmpty()) {											// 큐에서 익은 토마토를 하나씩 빼면서 익지 않은 토마토를 익히기 시작 
			Tomato cur = q.poll();										// 토마토를 하나 빼고,
			int y = cur.y;												// 토마토의 행 인덱스 
			int x = cur.x;												// 토마토의 열 인덱스 
			
			for(int d=0; d<4; d++) {
				int ny = y+dy[d];
				int nx = x+dx[d];
				
				if(ny<0||ny>=N || nx<0||nx>=M) continue;				// 익은 토마토를 기준으로 사방탐색한 뒤, 이동한 위치가 배열의 범위 밖이면 스킵 
				
				if(arr[ny][nx]!=0) continue;							// 만약 익지 않은 토마토가 아닌경우( 이미 익은 토마토이거나, 토마토가 없는 경우 ) 스킵  
				
				arr[ny][nx] = arr[y][x] + 1;							// 익지 않은 토마토를, 현재 익은 토마토 값보다 1 증가시킴 ( 나중에 몇일 걸렸는지 확인하기 위함 )
				q.offer(new Tomato(ny, nx));							// 새롭게 익은 토마토의 좌표를 큐에 추가 
			}	
		}
		
		answer = -987654321;
		
		loop: for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++){
				if(arr[i][j] == 0) {									// 만약 배열에 익지않은 토마토가 하나라도 있다면 정답을 -1로 초기화하고 루프 탈출 
					answer = -1;
					break loop;
				}
				answer = Math.max(answer, arr[i][j]);					// 0이 아니라면 매번 max값 업데이트 
			}
		}
		System.out.println(answer == -1 ? -1 : answer-1);				// 만약 answer이 -1이 아니라면, 시작이 2일차부터 시작되므로, 마지막에 1만큼 빼줌 
		br.close();
	}
}