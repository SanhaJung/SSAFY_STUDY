package hw1006;
import java.io.*;
import java.util.*;
//현재 위치를 청소한 후, 왼쪽으로 돌면서 한곳이라도 청소하지 않은 곳이 있으면 그방향으로 진행,
//한 바퀴를 다 돈후에도 더 청소할 곳을 못찾으면 그 상태에서 뒤로 후진
public class Main_bj_14503_로봇청소기 {
	static int[][]arr;
	static int N,M;
	static int[]dx = {-1,0,1,0};
	static int[]dy = {0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());					//행
		M = Integer.parseInt(st.nextToken());					//열
		arr =new int[N][M];										//배열 할당
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());				//start 행
		int c = Integer.parseInt(st.nextToken());				//start 열
		int d = Integer.parseInt(st.nextToken());				//초기방향
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++)
				arr[i][j] = Integer.parseInt(st.nextToken());	
		}
		boolean flag = false;
		int cnt=0;
		//r,c: 현재 좌표
		//cx,cy: 왼쪽으로 돌렸을때 좌표
		while(true) {
			arr[r][c] = -1;							//현재 있는 곳 방문 -1
			int cd = (d+3)%4;						//왼쪽방향으로 돌림
			int cx = r+dx[cd];						//왼쪽으로 돌렸을때 좌표
			int cy = c+dy[cd];
			if(cx>=0&&cx<N&&cy>=0&&cy<M) {			
				if(arr[cx][cy]==0) {				//벽이 아니고 청소할공간이 있을때
					d = cd;							//현재방향을 왼쪽으로 업데이트
					r = cx;							//한칸이동
					c = cy;
					cnt=0;							//새로운 곳 cnt초기화
					continue;
				}else {								//벽이거나 청소가 되있다면
					cnt++;							//cnt+1
					d = cd;							//방향만 업데이트
				}
			}
			if(cnt==4) {							//네방향 막힘
				cnt = 0;							//cnt 초기화
//				int rd = (d+2)%4;					//반대 방향으로
//				r = r+dx[rd];						//후퇴
//				c = c+dy[rd];						//후퇴
				r = r-dx[d];
				c = c-dy[d];
				if(arr[r][c] ==1)break;				//후퇴했는데 벽이면 끝
			}
		}
		
		int result=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(arr[i][j]==-1)					//-1로 된것들은 방문한 곳이니까
					result++;						//방문한곳 +1
			}
		}
		System.out.println(result);
	}

}
