package s1005;

import java.io.*;
import java.util.*;

public class Main_17478_재귀함수가뭔가요 {
	private static String pre = "";
	private static String[] msg = {
			"\"재귀함수가 뭔가요?\"",
			"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.",
			"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.",
			"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"",
			"\"재귀함수는 자기 자신을 호출하는 함수라네\"",
			"라고 답변하였지."
	};
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		recursivePrint(n);
	}
	
	
	public static void recursivePrint(int n) {
		// 언더바가 각 재귀함수마다 다르게 설정되어야 하므로, pre를 그대로 사용하면, 
		// 언더바로 몇번쨰 재귀호출인지 구분할 수 없음(다 동일한 길이)
		String tmp = pre;
		// 재귀함수 리턴조건 
		if(n == 0) {
			System.out.println(tmp+msg[0]);
			System.out.println(tmp+msg[4]);
			System.out.println(tmp+msg[5]);
			return;
		}
		System.out.println(tmp+msg[0]);
		System.out.println(tmp+msg[1]);
		System.out.println(tmp+msg[2]);
		System.out.println(tmp+msg[3]);
		pre += "____";
		recursivePrint(n-1);
		// 라고답변하지 부분이 하위에 호출된 재귀함수를 감싸고 있으므로, 마지막에 출력 
		System.out.println(tmp+msg[5]);
	}
}
