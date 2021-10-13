package hw1013;
import java.io.*;
import java.util.*;
class BC{
	int r;
	int c;
	int range;
	int power;
	public BC(int r, int c, int range, int power) {
		this.r = r;
		this.c = c;
		this.range = range;
		this.power = power;
	}
}
public class Solution_모의_무선충전 {
	static int M,N,answer;
	static int[]A,B;
	static boolean[][]visit;
	static ArrayList<Integer>map[][];
	static ArrayList<BC>list;			//BC만 담기는 list
	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());	//A,B 총 이동 시간
			N = Integer.parseInt(st.nextToken());	//BC개수
			
			A = new int[M];						
			B = new int[M];
			answer = 0;
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++)
				A[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++)
				B[i] = Integer.parseInt(st.nextToken());
			map = new ArrayList[10][10];			//x,y에 따라 그 좌표에 어떤 BC가 있을 수있는지 파악하는 리스트 배열
			
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					map[i][j] = new ArrayList<>();
				}
			}
			
			list = new ArrayList<>();
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());	
				int c = Integer.parseInt(st.nextToken())-1;		//BC의 정보
				int r = Integer.parseInt(st.nextToken())-1;
				int range = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				BC bc = new BC(r,c,range,power);
				list.add(bc);									//BC의 범위 체크
				visit = new boolean[10][10];
				bfs(bc,i);
			}
			int r1=0;
			int c1=0;
			int r2=9;
			int c2=9;
			
			//처음 자리 체크
			check(r1,c1,r2,c2);
			for(int i=0;i<M;i++) {	//방향을 옮기면서 check
				r1+=dr[A[i]];
				c1+=dc[A[i]];
				r2+=dr[B[i]];
				c2+=dc[B[i]];
				check(r1,c1,r2,c2);
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}	
	private static void check(int r1, int c1, int r2, int c2) {
		//map: 어느 좌표의 어떤 BC가 있는지 
		ArrayList<Integer>list1 = map[r1][c1];	//A가 있는 장소에 포함되는 BC	
		ArrayList<Integer>list2 = map[r2][c2];	//B가 있는 장소에 포함되는 BC
		int max = 0;
		// 두 사람이 모두 충전 가능한 경우
		if(list1.size()>0 && list2.size()>0) {
			//충전할 수 있는 모든 경우의 수 중 가장 높은 파워를 충전할수있는 값 더함
			//T=11B,A둘다 4,5에 있다고 가정하면
			//A-파(BC3),B-빨 A-빨,B-파, A,B-둘다 같은색
			for(int i=0;i<list1.size();i++) {
				for(int j=0;j<list2.size();j++) {
					max = Math.max(max, go(list1.get(i),list2.get(j)));
				}
			}
			answer+=max;
		}
		// A만 가능하다면
		else if(list1.size()>0) {
			for(int i=0;i<list1.size();i++) {
				int bc = list1.get(i);
				max = Math.max(max, list.get(bc).power); //가지고 있는 BC중 최대power
			}
			answer+=max;
		}
		//B만 가능하다면
		else if(list2.size()>0) {
			for(int i=0;i<list2.size();i++) {
				int bc = list2.get(i);
				max = Math.max(max, list.get(bc).power);//가지고 있는 BC중 최대 power
			}
			answer+=max;
		}
	}
	private static int go(int bc1, int bc2) {
		if(bc1 == bc2)	//BC가 겹친다면
			return list.get(bc1).power;//각각 절반씩 power를 나눠 합치니까 1개만 생각
		return list.get(bc1).power+list.get(bc2).power;	//bc1+ bc2 
	}
	//BC 충전 범위 체크
	private static void bfs(BC bc,int num) {
		Queue<BC>queue = new LinkedList<BC>();
		queue.offer(bc);
		int level=0;
		visit[bc.r][bc.c]=true;
		map[bc.r][bc.c].add(num);//그 위치에 num번의 BC가 존재
		while(!queue.isEmpty()) {
			int qsize = queue.size();
			for(int k=0;k<qsize;k++) {
				BC tbc = queue.poll();
				int r = tbc.r;
				int c = tbc.c;
				int tr,tc;
				for(int j=1;j<=4;j++) {	//4방향 으로 퍼지기
					tr = r+dr[j];
					tc = c+dc[j];
					if(0<=tr&&tr<10&&0<=tc&&tc<10&&!visit[tr][tc]) {	//방문하지 않았고 경계안에 있다면
						visit[tr][tc] = true;							//방문처리
						map[tr][tc].add(num);							//해당 위치에 num번의 BC존재
						queue.add(new BC(tr,tc,bc.range,bc.power));		//큐에 또다시 저장
					}
				}
			}
			level++;							
			if(level == bc.range)break;			
		}
	}

}
