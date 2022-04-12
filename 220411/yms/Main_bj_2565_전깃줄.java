package bj2565;

import java.util.*;
import java.io.*;

public class Main_bj_2565_전깃줄 {
	
	static class Wire implements Comparable<Wire> {
		int start;
		int end;
		
		public Wire(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Wire o) {
			return Integer.compare(this.start, o.start);
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(in.readLine());
		ArrayList<Wire> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			list.add(new Wire(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(list);
		
		int[] dp = new int[N];
		
		for(int i=0; i<N; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(list.get(i).end>list.get(j).end) dp[i] = Math.max(dp[i], dp[j]+1);
			}
		}
		
		int max = 0;
		for(int i=0; i<N; i++) max = Math.max(max, dp[i]);
		System.out.println(N-max);
	}
}
