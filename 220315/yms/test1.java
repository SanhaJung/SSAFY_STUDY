package 모의역량테스트;

import java.util.*;
import java.io.*;

public class test1 {
	
	static boolean[] map; 
	static int[][] info;
	static int[] numbers;
	static boolean[] visited;
	static int N, answer, temp;
	
	static void permutation(int cnt) {
		if(cnt==3) { 
			int sum = 0;
			map = new boolean[N+1];
			for(int i=0; i<3; i++) {
				temp = Integer.MAX_VALUE/2;
				move(numbers[i], 1, 0);
				sum += temp;
			}
			answer = Math.min(answer, sum);
			return;
		}
		
		for(int i=0; i<3; i++) {
			if(!visited[i]) {
				visited[i] = true;
				numbers[cnt] = i+1;
				permutation(cnt+1);
				visited[i] = false;
			}
		}
	}
	
	static void move(int gate, int person, int cnt) {
		
		int location = info[gate][0];
		int people = info[gate][1];
		int idx = 0;
		
		if(person>people) {
			temp = Math.min(temp, cnt);
			return;
		}
		
		while(true) {
			int left = location-idx;
			int right = location+idx;
			if(left<=0 && right>N) break;
			
			if(left>0 && !map[left]) {
				map[left] = true;
				move(gate, person+1, cnt+idx+1);
				if(person==people && right<=N && !map[right]) {
					map[left] = false;
					map[right] = true;
					move(gate, person+1, cnt+idx+1);
				}
				break;
			}
			else if (right<=N && !map[right]) {
				map[right] = true;
				move(gate, person+1, cnt+idx+1);
				break;
			}
			idx++;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("src/모의역량테스트/sample_input.txt")));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(in.readLine());
			answer = Integer.MAX_VALUE/2;
			temp = Integer.MAX_VALUE/2;
			map = new boolean[N+1];
			info = new int[4][2];
			numbers = new int[3];
			visited = new boolean[3];
			
			for(int i=1; i<=3; i++) {
				st = new StringTokenizer(in.readLine());
				info[i][0] = Integer.parseInt(st.nextToken());
				info[i][1] = Integer.parseInt(st.nextToken());
			}
			
			permutation(0);
			
			sb.append("#").append(tc).append(" ").append(answer).append('\n');
			
		}
		System.out.println(sb.toString());
	}
}
