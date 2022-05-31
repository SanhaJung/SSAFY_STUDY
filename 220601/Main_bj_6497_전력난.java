package bj6497;

import java.util.*;
import java.io.*;

public class Main_bj_6497_전력난 {
	
	static int m,n;
	static Edge[] edges;
	
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int weight;
		
		public Edge(int start ,int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}	
	}
	
	static int[] parents;
	
	static void make() {
		parents = new int[m+1];
		for(int i=1; i<=m; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a]==a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(in.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			if(m==0 && n==0) break;
			int sum = 0;
			edges = new Edge[n];
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(x,y,z);
				sum+=z;
			}
			
			Arrays.sort(edges);
			
			make();
			
			int cnt=0, result=0;
			
			for(Edge edge : edges) {
				if(union(edge.start, edge.end)) {
					result += edge.weight;
					if(++cnt==m-1) break;
				}
			}
			
			System.out.println(sum-result);
		}
		
	}
}
