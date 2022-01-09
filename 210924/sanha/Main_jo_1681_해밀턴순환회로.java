package a0923.algo;

import java.io.*;
import java.util.*;

// 처음엔 MST로 접근했다 <- 입력이 인접행렬로 들어왔기때문
// 하지만 문제를 풀다보니 MST를 구하는 문제가 아니었고 
// 문제에 조건에 맞추고자 조건을 줘봤지만 실패
// -> 이렇게 형태만보고 판단하지말자

// 문제분석
// 정해진 지점에서 정해진 지점의 경로를 구하고싶다.
// -> 그래프 dfs 알고리즘 사용 -> 성공

public class Main_jo_1681_해밀턴순환회로_서울_12반_정산하 {
	static int N;			 	// 배달 장소 수(1번에 회사 포함)
	static int[][] weights;	 	// 각 장소를 이동하는 가중치 배열
	static boolean[] v;      	// 각 장소 방문여부 판단 배열
	static int min = 987654321; // 선택 경로 최소 가중치 저장
	
	// cur: 현재 방문 정점
	// n  : 방문한 정점의 수
	// res: 현재까지 배달경로 가중치 합
	static void dfs(int cur,int n, int res) {
		// 기저조건: n개의 정점을 방문했을때
		if(n== N) {
			if(weights[cur][0]==0) return;  		// 회사로 돌아갈길 없을때 return
			
			res += weights[cur][0];					// 회사로 돌아가는 가중치 누적
			min = Math.min(res, min);       		// 배달경로 가중치 합이 최소보다 작을때 저장
			return;
		}
		if(res>min) return;  			    		// 최솟값보다 비용이 클때 백트래킹
		
		// 다음 정점 선택
		for(int i=0;i<N;i++) {
			if(!v[i] && weights[cur][i]!=0) {		// 방문하지 않았고 가중치 0이 아니면(길이 있으면)
				v[i] = true;						// 방문처리후 
				dfs(i, n+1, res+weights[cur][i]); 	// 다음 정점에서 dfs
				v[i] = false;						// 재귀함수 끝난 후에 방문처리 false로 되돌려줌
			}

		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_jo_1681_해밀턴순환회로.txt"));
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine().trim()); // 정점의 개수 입력 받기
		weights = new int[N][N];					// 입력받을 가중치의 배열
		v = new boolean[N];							// 각 정점 방문 여부 확인 배열
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<N;j++) {
				weights[i][j] = Integer.parseInt(st.nextToken()); // 가중치 입력 저장
			}
		}
		
		v[0] = true;   // 1번(인덱스 0번)인 회사 먼저 방문
		dfs(0, 1, 0);  // 회사에서  출발하기 때문에 방문한 정점수 1로시작
		
		// 최소 배달경로 가중치 누적합 출력
		System.out.println(min);
		
		
		br.close();
	}
	
	

}
