package s0926;

import java.io.*;
import java.util.*;

public class Main_20058_마법사상어와파이어스톰 {

	static int N, Q, L, SIZE, cnt, totalSum;
	static int[][] A;
	static int dy[] = {-1, 1, 0, 0};
	static int dx[] = {0, 0, -1, 1};
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		SIZE = 1<<N;
		A = new int[SIZE][SIZE];
		
		for(int i=0; i<SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<SIZE; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<Q; i++) {								// Q번 동안 
			L = Integer.parseInt(st.nextToken());
			rotate(L);											// 1<<L x 1<<L 크기의 배열로 나누어 회전시키고, 
			melt();												// 인접한 곳 중에 3면 이상이 얼음이 아닌 곳의 값을 1씩 감소시킴 
		}
		
		v = new boolean[SIZE][SIZE];							
		cnt = 0;												// 연결된 최대 얼음군의 크기 저장할 변수 
		totalSum = 0;											// 최대 얼음군에 있는 얼음들의 값을 더할 변수 
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				if(!v[i][j] && A[i][j]>0) {						// 아직 방문되지 않고, 얼음인 부분이면 
					v[i][j] = true;								// 얼음군에 포함시켜주고, 
					cnt = Math.max(cnt, count(i, j));			// 해당 좌표를 시작으로 인접한 얼음들을 포함시켜가면 cnt, totalSum 계산 
				}
			}
		}

		System.out.println(totalSum);
		System.out.println(cnt);
	}
	private static int count(int y, int x) {
		int result = 1;											// 현재 시작 위치를 포함한 상태에서 얼음군에 포함된 얼음을 세므로 1로 초기화 
		totalSum += A[y][x];
		
		for(int d=0; d<4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
            if(ny<0||ny>=SIZE || nx<0||nx>=SIZE) continue;

            if(A[ny][nx] > 0 && !v[ny][nx]) {					// 이동할 인접한 위치가 얼음이고, 아직 얼음군에 포함되지 않았다면,
            	v[ny][nx] = true;								// 방문처리 시켜주고, 
            	result += count(ny, nx);						// 다음 위치를 기준으로 다시 count 진행 
            }
		}
		return result;
	}
	
	private static void melt() {
		Queue<int[]> q = new ArrayDeque<>();					// 3면이 얼음이 아닌 좌표를 저장할 큐 
        
        for(int y=0; y<SIZE; y++) {						
            for(int x=0; x<SIZE; x++) {
                int count = 0;									// 매 인덱스마다 3면 이상이 얼음인지 체크할 카운트 변수 
                for(int d=0; d<4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    
                    if(ny<0||ny>=SIZE || nx<0||nx>=SIZE) continue; // 확인할 다음 좌표가 범위밖이면 스킵 	

                    if(A[ny][nx]<=0) continue;					// 만약 확인할 곳의 값이 0이하라면(얼음이 없다면) 스킵 
                    count++;									// 그게 아니라면 이동한 위치에는 얼음이 있으므로 카운트값 증가 
                }
                if(count < 3) q.offer(new int[] {y, x});		// 인접하면서 얼음인 곳이 3개 미만이면 큐에 해당 좌표 추가 
            }
        }
        
        while(!q.isEmpty()) {									// 큐에서 3면 이상이 얼음이 아닌 좌표들을 하나씩 빼면서 
        	int[] cur = q.poll();
        	int y = cur[0];
        	int x = cur[1];
            A[y][x]--;											// 해당 위치의 얼음값을 1씩 감소 
        }		
	}
	private static void rotate(int layer) {
		
		int[][] copy = new int[SIZE][SIZE];						// 배열을 회전시키기 위해 사용할 copy 배열 
		layer = 1<<layer;										// Math.pow(2, layer)와 동일 
		int loop = SIZE / layer;								// 2^N x 2^N 사이즈의 배열에서 돌릴 배열의 개수 = (2^N / 2^L) ^ 2

		for(int r=0; r<SIZE; r+=layer) {						// 돌릴 각각의 배열의 시작 행 인덱스 
			for(int c=0; c<SIZE; c+=layer) {					// 돌릴 각각의 배열의 시작 열 인덱스  

				for(int y=0; y<layer; y++) {					// 각각의 작은 배열마다 회전 시작 
					for(int x=0; x<layer; x++) {
						copy[c+y][r+x] = A[c+layer-1-x][r+y];	// | -> ㅡ && ㅡ -> |  즉, 배열을 90도 회전하므로 열이 행으로 행이 열로 바뀌는 작업  
					}
				}
			}
		}

        A = copy;												// 원래 배열을 회전된 배열로 업데이트 
	}

	private static void printMap() {
		for(int[] row : A) {
			System.out.println(Arrays.toString(row));
		}
	}
}
