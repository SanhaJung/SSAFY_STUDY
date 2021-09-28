package a0926.study;

import java.io.*;
import java.util.*;

public class Main_bj_20057_마법사상어와토네이도_구글링 {
	static int N, map[][], result;
	static int dx[]= {0,1,0,-1};
	static int dy[]= {-1,0,1,0};
	// 이걸 규칙으로 하려고했어서 너무 꼬였다 -> 상수로 경우마다 두는 것이 좋은 듯
	static int percent[]= {1,1,2,2,5,7,7,10,10};
	static int movex[][]= {
			{-1,1,-2,2,0,-1,1,-1,1,0}, //좌
			{0,0,1,1,3,1,1,2,2,2}, //하
			{-1,1,-2,2,0,-1,1,-1,1,0}, //우
			{0,0,-1,-1,-3,-1,-1,-2,-2,-2} //상
	
	};
	static int movey[][]= {
			{0,0,-1,-1,-3,-1,-1,-2,-2,-2}, //좌
			{-1,1,-2,2,0,-1,1,-1,1,0}, //하
			{0,0,1,1,3,1,1,2,2,2}, //우
			{-1,1,-2,2,0,-1,1,-1,1,0} //상
	};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_20057_마법사상어와토네이도.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve(1,0,0,0, N/2, N/2);
		System.out.println(result);

	}
	//nCnt : 현재 방향으로 몇 칸가야하는지 (N-1번만큼 가야됨)
	//cnt  : nCnt값으로 몇 번 갔는지 (2번 갔는지 확인, N-1일떄는 3번 가야됨)
	// d   : 방향 
	// dCnt: 현재 d방향으로 몇 번 갔는지
	// x, y: 토네이도 시작 좌표
	private static void solve(int nCnt, int cnt, int d, int dCnt, int x, int y) {
		// 완료 조건
		if(x==0 && y==0) return;
		
		//방향 바꿔주기 
		if(dCnt==nCnt) {  	// 현재 방향을 가야하는 횟수를 채우면
			d = (d+1)%4;	// 방향 바꾸고
			dCnt=0;			// 현재 d방향으로 간 횟수 초기화
			cnt++;			// 현재 방향으로 가야할 칸 수 조절 cnt update
		}
		if(cnt==2 && nCnt!=N-1) { // 현재 방향으로 가야 할 칸수를 두 번 반복 했고 N-1이 아니면
			cnt=0;				  // 반복 횟수 초기화
			nCnt++;				  // nCnt늘려주기 
		}
		
		int nx=x+dx[d]; 		//토네이도 보낼 위치 
		int ny=y+dy[d]; 
		
		int send=map[nx][ny];	// 이동시킬 모래의 양 저장
		int outSend=0;			// 격자 밖으로 나가는 모래의 양
		
		for(int i=0; i<9; i++) {
			//모래가 옮겨질 좌표
			int sx=x+movex[d][i];
			int sy=y+movey[d][i];
			
//			int plusSend=(int)(send*((double)percent[i]/100));
			int plusSend=(send*percent[i])/100;
			outSend+=plusSend;
			if(sx<0|| sy<0|| sx>=N || sy>=N) {
				result+=plusSend; //밖으로 나가는거
				continue;
			}
			map[sx][sy]+=plusSend; //밖으로 안나가면 그 좌표에 더해줌 
		}
		if(x+movex[d][9]<0 || y+movey[d][9]<0||x+movex[d][9]>=N || y+movey[d][9]>=N) result+=send-outSend;
		else map[x+movex[d][9]][y+movey[d][9]]+=send-outSend; //남은거 a자리에 넣어주기 
		map[nx][ny]=0;
		
		solve(nCnt,cnt,d,dCnt+1,nx,ny);
	}
}