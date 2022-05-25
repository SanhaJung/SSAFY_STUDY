package bj1647;

import java.util.*;
import java.io.*;

public class Main_bj_1647_도시분할계획 {
	
	static Edge[] edges;
	static int N,M;
	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int weight;
		
		public Edge(int start, int end, int weight) {
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
		parents = new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
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
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new Edge[M];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(A,B,C);
		}
		
		Arrays.sort(edges);
		make();
		int cnt = 0, result = 0;
		
		for(Edge edge : edges) {
			if(union(edge.start, edge.end)) {
				result += edge.weight;
				if(++cnt == N-2) break;
			}
		}
		
		/*
		 * MST로 마을 모두 연결 후 마지막 가중치가 가장 큰 간선 하나를 없애주면 마을이 2개로 나누어짐
		 */
		
		System.out.println(result);
		
		
	}
}
