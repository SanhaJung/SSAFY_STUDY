package bj17825;

import java.util.*;
import java.io.*;

public class Main_bj_17825_주사위윷놀이 {
	
	static Node[] board = new Node[33];				// 윷놀이 판 배열	
	static boolean[] visited = new boolean[33];		// 말이 겹치는지 확인할 배열
	static int[] numbers = new int[10];				// 중복순열을 넣을 배열
	static int[] dice = new int[10];				// 주사위 순서 정보 배열
	static int[] horses = new int[4];				// 4가지 말의 정보 배열
	static int answer = Integer.MIN_VALUE/2;		
	
	static class Node {								//score : 문제에 적혀있는 각 칸의 점수 link : 다음 칸 jump : 지름길로 이어지는 칸 (10,20,30)
		int score;
		int link;
		int jump;
		
		public Node(int score, int link, int jump) {
			super();
			this.score = score;
			this.link = link;
			this.jump = jump;
		}
		
	}
	
	static void permutation(int cnt) {
//		if(cnt==9) {									// 10일때 106260KB/308ms 9일때 38660KB/208ms
		if(cnt==10) {									// 10개를 뽑으면 돌리게 했는데 9개를뽑아서 돌려도 맞음.. 왜인진모르겟음..
			int sum = 0;
			visited = new boolean[33];
			horses = new int[4];
			for(int i=0; i<numbers.length; i++) {
				int end = move(numbers[i], dice[i]);  //순열로 뽑은 말을 해당 주사위만큼 이동시킨다
				if(end == -1) return;				  //말이 겹치면 -1 리턴이 되므로 중지
				horses[numbers[i]] = end;			  //현재 말의 위치로 갱신
				sum += board[end].score;			  //해당 말판의 값을 더해줌
			}
			answer = Math.max(answer, sum);
			return;
		}
		
		for(int i=0; i<4; i++) { // 중복순열 말 뽑기  (0 1 2 3)
			numbers[cnt] = i;
			permutation(cnt+1);
		}
	}
	
	static int move(int idx, int dice) {	
		int temp = horses[idx];				//현재 움직일 말의 위치
		for(int i=0; i<dice; i++) {
			if(i==0 && board[temp].jump>0) { // 처음 시작하고 시작하는 부분이 교차점이면
				temp = board[temp].jump;	 // 교차길로 들어감
			}
			else {
				temp = board[temp].link;	// 가던길로 감
			}
		}
		
		if(temp<32 && visited[temp])		// 현재 위치가 도착점(32)이 아닌데 말이 겹쳐있다면
			return -1;						// 진행할 수 없으므로 -1 리턴
		else {
			visited[horses[idx]] = false;	// 주사위를 굴리기 전 말의 위치 false
			visited[temp] = true;			// 주사위를 굴린 후의 위치 true
			return temp;					// 움직인 후의 말의 위치 return
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		for(int i=0; i<10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<21; i++) {
			board[i] = new Node(i*2,i+1,0);
		}
		board[5].jump = 21;
		board[10].jump = 25;
		board[15].jump = 27;
		
		board[21] = new Node(13,22,0); board[22] = new Node(16,23,0); board[23] = new Node(19,24,0); // 10번 길
		board[24] = new Node(25,30,0); board[30] = new Node(30,31,0); board[31] = new Node(35,20,0); // 25번길
		board[25] = new Node(22,26,0); board[26] = new Node(24,24,0); // 20번길
		board[27] = new Node(28,28,0); board[28] = new Node(27,29,0); board[29] = new Node(26,24,0); //30번길
		
		board[20].link = 32; // 도착점
		board[32] = new Node(0,32,0);
		
//		for(int i=0; i<board.length; i++) {
//			System.out.println(i+" "+board[i].score);
//		}
		
		permutation(0);
		
		System.out.println(answer);
	}
}
