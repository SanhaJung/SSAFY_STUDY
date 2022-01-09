package hw0930;

import java.io.*;
import java.util.*;
class Tree implements Comparable<Tree>{
	int x;
	int y;
	int age;
	public Tree(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
	}
	@Override
	public int compareTo(Tree o1) {
		return this.age-o1.age;
	}
}
public class Main_bj_16235_나무재테크 {
	static int N,M,K;
	static int [][]arr;
	static int [][]yang;
	static boolean [][]visit;
	static int[] dy = {-1,-1,-1,0,0,1,1,1};
    static int[] dx = {-1,0,1,-1,1,-1,0,1};
	static Queue<Tree>queue = new PriorityQueue<Tree>();	//나무가 있는 큐
	static Queue<Tree>die = new LinkedList<Tree>();			//죽은 나무 큐
	static Queue<Tree>breed = new LinkedList<Tree>();		//번식하는 나무 큐
	static Queue<Tree>newTree = new PriorityQueue<Tree>();	//새로운 나무 큐
	private static void spring() {	//봄
		while(!queue.isEmpty()) {		
			Tree t = queue.poll();							//나무가 존재한다면				
			if(yang[t.x][t.y]<t.age) {						//나이만큼 양분이 없다면
				die.offer(new Tree(t.x,t.y,t.age));			//죽은 나무로 등록
			}else {
				yang[t.x][t.y]-=t.age;							//나이만큼 양분을 먹음
				newTree.offer(new Tree(t.x,t.y,t.age+1));		//살아남은 나무 새로등록
			}
		}
	}
	private static void summer() {	//죽은 나무 양분으로 변함
		while(!die.isEmpty()) {
			Tree d = die.poll();
			yang[d.x][d.y]+=d.age/2;	//죽은 나무의 나이를 2로 나눈값을 나무가 있던 칸에 양분으로 추가
		}
	}
	private static void fall() {	//나무 번식
		for (Iterator iterator = newTree.iterator(); iterator.hasNext();) {	//나무들이 있다면
			Tree tree = (Tree) iterator.next();
			if(tree.age%5==0)			//나무나이가 5의 배수인것을 골라
				breed.offer(new Tree(tree.x,tree.y,tree.age));//번식큐에 저장
		}
		while(!breed.isEmpty()) {	//번식하는 나무가 있다면
			Tree b = breed.poll();
			for(int i=0;i<8;i++) {	//인접한 8개의 칸을 돌면서
				int cx = b.x+dx[i];	
				int cy = b.y+dy[i];
				if(1<=cx&&cx<=N&&1<=cy&&cy<=N) {
					newTree.offer(new Tree(cx,cy,1));	//그 칸에 나이가 1인 나무가 생김
				}
			}
		}
	}
	private static void winter() {//모든 땅에 양분 추가
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				yang[i][j]+=arr[i][j];	//각 칸에 추가되는 양분의 양은 arr[i][j]
			}
		}
	}
	private static void year() {
		for(int i=0;i<K;i++) {
			newTree = new PriorityQueue<Tree>(); //새로운 Tree
			spring();	//원래나무에서 나무 죽기도하고 살아남기도함
			summer();	
			fall();
			winter();
			queue = new PriorityQueue<>(newTree);	//새로운 Tree업데이트
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		yang = new int[N+1][N+1];
		visit = new boolean[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				yang[i][j]= 5;
			}
		}
		queue = new PriorityQueue<Tree>();
		for(int i=0;i<M;i++) {		//나무에 대한 정보
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			queue.offer(new Tree(x,y,z));
		}
		year();
		System.out.println(queue.size());	//살아남은 나무들 갯수
	}

}
