package a1027;

import java.io.*;
import java.util.*;

public class Main_bj_3954_Brainfk인터프리터 {
	
	static class Pair{ //괄호 시작, 끝 변수 저장 클래스
		int left, right;
		public Pair(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
	
	static int Sm, Sc, Si;
	static String code;
	static String input;
	
	static String [] answer = {"Terminates", "Loops"};
	static Stack<Integer> stack;
	static Pair [] pairs;
	
	static int [] memory;
	static int inputIdx, memIdx, loopCnt;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			Sm = Integer.parseInt(st.nextToken()); //메모리 (배열의) 크기 (십만 이하)
			Sc = Integer.parseInt(st.nextToken()); // 프로그램 코드의 크기 (5000 이하)
			Si = Integer.parseInt(st.nextToken()); // 입력의 크기 (5000이하)
			
			code = br.readLine();
			input = br.readLine();
			
			//초기화
			inputIdx=0;
			memIdx=0;
			loopCnt=0;
			stack = new Stack<>();
			pairs = new Pair[4096];
			memory=new int [100001];
			Arrays.fill(memory, 0);
			////////////////////
			
			findBracket();
			
			solve();
			
			
			
		}
	}

	private static void solve() {
		int idx = 0;
		int max_index = 0;
		
		while(true) {
			char command = code.charAt(idx);
			
			switch (command) {
			case '-':
				memory[memIdx] = (memory[memIdx] -1 ) % 256;
				break;
			case '+':
				memory[memIdx] = (memory[memIdx] +1 ) % 256;
				break;
			case '<':
				if(memIdx == 0) 
					memIdx = Sm -1;
				else 
					memIdx--;
				break;
			case '>':
				if(memIdx == Sm-1) 
					memIdx = 0;
				else 
					memIdx++;
				break;
			
			case '[':
				if(memory[memIdx] == 0) {
					idx = pairs[idx--].right;
					loopCnt++;
				}
				break;
			case ']':
				if(memory[memIdx] != 0) {
					idx = pairs[idx--].left;
					loopCnt++;
				}
				break;
			case '.':
				break;
			case ',':
				if(inputIdx == Si) memory[memIdx] = 255;
				else memory[memIdx] = (int)input.charAt(inputIdx++);
				break;
			}
			idx++;
			loopCnt++;
			
			if(idx > max_index) max_index=idx;
			
			if(idx==Sc) {
				//종료
				System.out.println(answer[0]);
				
				break;
			}
			if(loopCnt>50000000) {
				// 무한루프
				System.out.println(answer[1]+" "+pairs[max_index].left + " " +pairs[max_index].right);
				break;
			}
		}
		
	}

	private static void findBracket() {
		/*
		 * [와 ]는 루프를 의미하며, 중첩될 수 있다. 
		 * 입력으로 주어진 프로그램은 잘 짜여 있음이 보장된다. 
		 * 즉 프로그램을 왼쪽에서 오른쪽으로 훑으면서 [의 개수에서 ]의 개수를 빼면
		 * 항상 0보다 크거나 같고, 맨 끝까지 훑으면 그 값은 0이 된다.
		 */
		for(int i=0; i<Sc; i++ ) {
			if(code.charAt(i) == '[') {
				stack.push(i);
				pairs[i] = new Pair(i,0);
			}
			else if(code.charAt(i) == ']') {
				int temp = stack.pop();
				pairs[i] = new Pair(temp, i);
				pairs[temp].right = i;
				
			}
		}
	}
}
