package s1005;

import java.io.*;
import java.util.*;

public class Main_20055_컨베이어벨트위의로봇 {
	
	static int N, K, stage, zero;
	static int[] belt;
	static boolean[] robot;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new int[2*N+1];
		robot = new boolean[2*N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=2*N; i++){
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		
		zero = 0;
		stage = 0;
		while(true) {
			++stage;							// 각 루프마다 stage 증가 
			
			rotate();							// 컨베이어벨트와 로봇을 둘다 회전시켜 준다.

			moveRobot();						// 로봇을 회전된 벨트에서 이동시킨다. 
			
			if(zero >= K) break;				// 만약 내구도가 0인 곳이 k개 이상이라면 탈출한다. 
			
		}
		System.out.println(stage);
		br.close();
	}

	private static void moveRobot() {
		if(robot[N]) robot[N] = false;			// 내리는 위치에 로봇이 존재한다면 로봇을 그 즉시 내린다. 
												// 내린다는 의미는 N+1로 가는게 아니라 벨트 위에서 아예 제거한다는 뜻

		for(int i=N-1; i>0; i--) {				// 내리는 위치의 이전 인덱스부터 올리는 위치까지 순회하면서 
			if(!robot[i]) continue;				// 만약 현재 인덱스에 로봇이 없다면 다음 위치로 이동 
			
			if(belt[i+1]>0 && !robot[i+1]) {	// 현재 인덱스에 로봇이 있고, 이동시킬 인덱스에 로봇이 없고, 벨트의 내구도가 0 이상이면,  
				robot[i] = false;				// 현재 인덱스의 로봇을 제거하고 
				robot[i+1] = true;				// 다음 인덱스로 이동시킴 
				if(--belt[i+1] == 0) zero++;	// 이동한 곳의 벨트의 내구도를 1 감소시키고, 만약 내구도가 0이 되었다면 zero의 개수를 증가킨다
			}
		}
		
		if(!robot[1] && belt[1]>0) {			// 로봇을 이동시킨 다음, 올리는 위치에 로봇이 없고 벨트의 내구도가 0이 아니면 
			robot[1] = true;					// 올리는 위치에 로봇을 올리고, 
			if(--belt[1] == 0) zero++;			// 올리는 위치의 내구도를 1 감소시키고, 내구도가 0이되었다면 zero 갯수 증가 
		}
		
		if(robot[N]) robot[N] = false;			// 내리는 위치에 로봇이 존재한다면 로봇을 그 즉시 내린다. 
	}

	private static void rotate() {				// 1차원 배열 돌리기
		belt[0] = belt[2*N];					// 마지막 인덱스의 값을 쓰지 않는 0번 인덱스에 저장해두고,  
		for(int i=2*N; i>1; i--) {				// 오른쪽으로 값 슬라이딩 
			belt[i] = belt[i-1];
		}
		belt[1] = belt[0];						// 1번 인덱스에 저장해뒀던 마지막 인덱스의 값을 넣어줌 

		robot[0] = robot[2*N];					// 위와 동일 
		for(int i=2*N; i>1; i--) {
			robot[i] = robot[i-1];
		}
		robot[1] = robot[0];
	}
}
