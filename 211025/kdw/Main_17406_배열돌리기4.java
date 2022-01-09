package s1025;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17406_배열돌리기4 {

	static int N, M, K, r, c, s, answer;
	static int[][] map;
	static Cmd[] commands, numbers;
	static boolean[] v;
	static class Cmd{
		int r;
		int c;
		int s;
		public Cmd(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		commands = new Cmd[K];
		numbers = new Cmd[K];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			commands[k] = new Cmd(r, c, s);
		}
		v = new boolean[K];
		answer = Integer.MAX_VALUE;
		permu(0);
		System.out.println(answer);
//		printMap();
	}
	
	private static void permu(int cnt) {
		if(cnt == K) {
			int[][] copy = new int[N+1][M+1];
			for(int i=0; i<=N; i++) {
				for(int j=0; j<=M; j++) {
					copy[i][j] = map[i][j];
				}
			}
			for(int i=0; i<K; i++) {
				rotate(numbers[i].r, numbers[i].c, numbers[i].s, copy);
			}
			for(int i=1; i<=N; i++) {
				int sum = 0;
				for(int j=1; j<=M ;j++) {
					sum += copy[i][j];
				}
				answer = Math.min(answer, sum);
			}
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(v[i]) continue;
			numbers[cnt] = commands[i];
			v[i] = true;
			permu(cnt+1);
			v[i] = false;
		}
	}

	private static void printMap() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private static void rotate(int r, int c, int s, int[][] copy) {
		int sr = r-s;
		int sc = c-s;
		
		int dr = r+s;
		int dc = c+s;
		
		while(s-->0) {
			
			copy[sr][0] = copy[sr][dc];
			for(int j=dc; j>sc; j--) {
				copy[sr][j] = copy[sr][j-1];
			}
			for(int i=sr; i<dr; i++) {
				copy[i][sc] = copy[i+1][sc]; 
			}
			for(int j=sc; j<dc; j++) {
				copy[dr][j] = copy[dr][j+1];
			}
			for(int i=dr; i>sr; i--) {
				copy[i][dc] = copy[i-1][dc];
			}
			copy[sr+1][dc] = copy[sr][0];
			
			sr++;
			sc++;
			dr--;
			dc--;
			if(sr==dr || sc==dc) break;
		}
	}
	
}
