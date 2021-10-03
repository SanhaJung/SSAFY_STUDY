package hw0923;

import java.io.*;
import java.util.*;
public class Main_jo_1681_해밀턴순환회로_서울_12반_오지영 {
	static int N,g[][],min=987654321;
	static boolean[]v;
	
	private static void permutation(int y,int cnt,int sum) {
		if(sum>=min) return;	//시간초과 막기위한 가지치기
		
		if(cnt==N-1) {	//회사 말고는 다 갔다면
			if(g[y][0]!=0) {	//회사로 갈 수 있다면
				min = Math.min(min,sum+g[y][0]);	//min값 구하기
			}
			return;
		}
		for(int i=1;i<N;i++) {	//열
			if(!v[i]&&g[y][i]!=0) {	//그 행을 방문하지 않았고 갈 곳이 있다면
					v[i] = true; // 그 행 방문처리
					permutation(i,cnt+1,sum+g[y][i]);	//행자리에 열값을 넣어주고, sum에 해당 값을 넣어준다음
					v[i] = false;//다시 풀어줌
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		g = new int[N][N];
		StringTokenizer st =null;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N;j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		v = new boolean[N];
//		v[0] = true;	//첫행 방문
		permutation(0,0,0);	
		System.out.println(min);
		br.close();
	}
}
