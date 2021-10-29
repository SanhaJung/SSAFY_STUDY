package hw1022;

import java.io.*;
import java.util.*;
public class Main_bj_1504_특정한최단경로 {
	static class Node implements Comparable<Node>{
		int to;
		int w;
		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w-o.w;
		}
	}
	static List<Node>[]list;
	static int N,E;
	static int MAX=400000000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list=new ArrayList[N+1];
		for(int i=1;i<=N;i++)
			list[i] = new ArrayList<>();
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int sum1=0;
		sum1+=dijkstra(1, v1);
		sum1+=dijkstra(v1,v2);
		sum1+=dijkstra(v2, N);
		
		int sum2=0;
		sum2+=dijkstra(1,v2);
		sum2+=dijkstra(v2,v1);
		sum2+=dijkstra(v1,N);
		if(sum1>=MAX&&sum2>=MAX)System.out.println(-1);
		else
			System.out.println(Math.min(sum1, sum2));
		
	}
	private static int dijkstra(int start,int dest) {
		PriorityQueue<Node>queue = new PriorityQueue<>();
		int []dist = new int[N+1];
		boolean[]visit =new boolean[N+1];
		Arrays.fill(dist, MAX);
		dist[start]=0;
		queue.add(new Node(start,0));
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int to = node.to;
			if(visit[to])continue;
			visit[to]=true;
			for(Node next:list[to]) {
				if(dist[next.to]>=dist[to]+next.w&&!visit[next.to]) {
					dist[next.to] = dist[to]+next.w;
					queue.add(new Node(next.to,dist[next.to]));
				}
			}
		}
		return dist[dest];
	}

}
