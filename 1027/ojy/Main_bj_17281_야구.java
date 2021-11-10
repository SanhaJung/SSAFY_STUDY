package hw1027;
import java.io.*;
import java.util.*;

//타자마다 각 이닝에서 어떠한 결과를 얻는데, 이때 가장 많은 득점을 하는 타순
public class Main_bj_17281_야구 {
	static int N,max;
	static int[][]players;
	static int[]line;
	static boolean[]visit;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		players = new int[N+1][10];
		visit = new boolean[10];
		line = new int[10];
		for(int i=1;i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1;j<=9;j++) {
				players[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		line[4]=1;					//1번선수 4번타자
		visit[4]=true;
		permutation(2);
		System.out.println(max);
		br.close();
	}
	//순열을 사용해서 몇번선수를 몇번 타자로 정할지 결정
	private static void permutation(int cnt) {
		if(cnt==10) {
			game();
			return;
		}
		for(int i=1;i<=9;i++) {
			if(visit[i])continue;
			visit[i]=true;
			line[i]=cnt;			
			permutation(cnt+1);
			visit[i] = false;
			
		}
	}
	private static void game() {
		int score=0;									//Score
		int starter = 1;//타순 1번타자						//처음시작하는 타자
		int inning=1;									//이닝
		int out=0;										//out
		int []base = new int[4];//홈,1루,2루,3루
		while(inning<=N) {								//N번째 이닝까지 실행
			
			if(out==3) {								//세번 out되면
				out=0;									//out초기화			
				base[0]=base[1]=base[2]=base[3]=0;		//base초기화
				inning++;								//이닝+1
				continue;
			}	
			int player = line[starter];					//현재 이닝의 처음 시작하는 타자(몇번선수)
			int result = players[inning][player];		//현재 타자의 행동(그 선수의 행동)
			
			base[0]= 1;									//일단 홈에 한명이 서있음
			if(result==1) {								//안타
				score+=base[3];							//모든 주자가 한루씩 진루
				base[3]=base[2];
				base[2]=base[1];
				base[1]=base[0];
				base[0]=0;
			}else if(result==2) {						//2루타
				score+=base[3]+base[2];					//타자와 모든 주자가 두루씩 진루
				base[3]=base[1];
				base[2]=base[0];
				base[0]=0;
				base[1]=0;
			}else if(result==3) {						//3루타
				score+=base[1]+base[2]+base[3];			//타자와 모든 주자가 세루씩 진루
				base[3]=base[0];
				base[0]=base[1]=base[2]=0;
			}else if(result==4) {						//4루타
				score+=base[0]+base[1]+base[2]+base[3];	//타자와 모든 주자가 홈까지 진루
				base[0]=base[1]=base[2]=base[3]=0;
			}else {										//out
				out++;									//모든 주자 진루x
			}
			max=Math.max(max, score);					//최대 득점
			starter=starter%9+1;						//starter(시작하는 타자가 계속변함)
		}
	}

}
