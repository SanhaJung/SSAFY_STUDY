package bj2887;

import java.util.*;
import java.io.*;

public class Main_bj_2887_행성터널 {
	
	static int N;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	static class Planet{
		int num;
		int x;
		int y;
		int z;
		
		public Planet(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	static class Edge implements Comparable<Edge> {
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
		parents = new int[N];
		for(int i=0; i<N; i++) {
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
		if(aRoot==bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		Planet[] planetList = new Planet[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			planetList[i] = new Planet(i,x,y,z);
		}
		
		// x좌표 기준으로 정렬
		Arrays.sort(planetList, (p1, p2) -> Integer.compare(p1.x, p2.x));
		for(int i=1; i<N; i++) {
			int weight = planetList[i].x - planetList[i-1].x;
			pq.offer(new Edge(planetList[i].num,planetList[i-1].num, weight));
		}
		
		// y좌표 기준으로 정렬
		Arrays.sort(planetList, (p1, p2) -> Integer.compare(p1.y, p2.y));
		for(int i=1; i<N; i++) {
			int weight = planetList[i].y - planetList[i-1].y;
			pq.offer(new Edge(planetList[i].num,planetList[i-1].num, weight));
		}
		
		// z좌표 기준으로 정렬
		Arrays.sort(planetList, (p1, p2) -> Integer.compare(p1.z, p2.z));
		for(int i=1; i<N; i++) {
			int weight = planetList[i].z - planetList[i-1].z;
			pq.offer(new Edge(planetList[i].num,planetList[i-1].num, weight));
		}
		
		make();
		
		int cnt=0, result = 0;
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(union(edge.start, edge.end)) {
				result += edge.weight;
				if(++cnt==N-1) break;
			}
		}
		
		System.out.println(result);
		
	}
}
