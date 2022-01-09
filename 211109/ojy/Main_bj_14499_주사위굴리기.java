package hw1109;
import java.io.*;
import java.util.*;
/* 2
 4 1 3
   5
   6*/
//1이 위 6이 밑
public class Main_bj_14499_주사위굴리기 {
	static int N,M,x,y,k;
	static int [][]arr;
	static int []dice;
	static int dx[]= {0,0,0,-1,1};
	static int dy[]= {0,1,-1,0,0};
	private static void play(int dir) {
		int []temp = Arrays.copyOf(dice, dice.length);
		if(dir==1) {			//동쪽으로
			dice[6] = temp[3];	
			dice[3] = temp[1];
			dice[1] = temp[4];
			dice[4] = temp[6];
		}else if(dir==2) {		//서쪽으로
			dice[6] = temp[4];
			dice[4] = temp[1];
			dice[1] = temp[3];
			dice[3] = temp[6];
		}else if(dir==4) {		//남쪽으로
			dice[6] = temp[5];
			dice[5] = temp[1];
			dice[1] = temp[2];
			dice[2] = temp[6];
		}else if(dir==3) {		//북쪽으로
			dice[5] = temp[6];
			dice[2] = temp[1];
			dice[1] = temp[5];
			dice[6] = temp[2];
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr= new int[N][M];
		dice = new int[7];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<k;i++) {
			int dir = Integer.parseInt(st.nextToken());
			int cx = x+dx[dir];				//주사위가 그방향으로 이동했을때 좌표
			int cy = y+dy[dir];
			if(0<=cx&&cx<N&&0<=cy&&cy<M) {	//지도안에 있으면
				play(dir);
				if(arr[cx][cy]==0)			//이동한 칸에 쓰여있는 수가 0이면, 
					arr[cx][cy] = dice[6];	//주사위 바닥면이 배열에 복사됨
				else {
					dice[6] =arr[cx][cy];	//주사위 바닥면에 배열값을 복사
					arr[cx][cy]=0;			//배열값은 0
				}
				x=cx;						//이동좌표(현재자리) 업데이트
				y=cy;
				System.out.println(dice[1]); //주사위 윗면 수 출력
			}
		}
	}

}
