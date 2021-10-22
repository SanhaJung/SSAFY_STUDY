package s1022;
import java.io.*;
import java.util.*;

public class Main_16637_괄호추가하기 {

	static int N, answer;
	static String exp;
	static List<Integer> nums;
	static List<Character> ops;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); 	// 수식 길이
		exp = br.readLine();					// 수식 입력 
		nums = new ArrayList<>();				// 수식에서 숫자들을 저장할 리스트 
		ops = new ArrayList<>();				// 수식에서 연산자들을 저장할 리스트
		
		for(int i=0; i<N; i++) {
			char c = exp.charAt(i);
			if(c=='+' || c=='-' || c=='*') ops.add(c);	// 수식에서 연산자들을 ops 리스트에 넣어줌 
			else nums.add(c-'0');						// 연산자가 아닌 숫자들을 nums 리스트에 넣어줌 
		}
		
		answer = Integer.MIN_VALUE;				// 연산의 최대값을 구하기 때문에 answer변수를 최솟값으로 초기화 
		dfs(nums.get(0), 0);					// 첫번째 수부터 백트래킹을 시도 
		System.out.println(answer);
		
		br.close();
	}

	private static void dfs(int result, int op) {
		if(op == ops.size()) {					// 모든 연산자를 사용했다면, 
			answer = Math.max(answer, result);	// 최대 결과값 업데이트 
			return;
		}
		
		// 괄호를 넣지 않고 쭉 계산할 경우 
		int tmp = calc(ops.get(op), result, nums.get(op+1));	// 현재까지 연산결과( 연산자 )  + 연산자 다음 수 = tmp
		dfs(tmp, op+1);							// 현재까지의 결과값을 tmp로 설정하고, 다음 연산자로 이동 
		
		// 
		if(op+1 < ops.size()) {					// 만약 다음 연산자가 마지막 연산자가 아니라면 
			int tmp2 = calc(ops.get(op+1), nums.get(op+1),  nums.get(op+2));	// 괄호를 이후(현재 연산자가 아닌, 다음 연산자와 그 연산자의 양 옆 수로 계산)에 추가했을 때의 결과값을 tmp2에 저장 
			dfs(calc(ops.get(op), result, tmp2), op+2);			// tmp2( 다음에 나오는 연산자로 계산한 결과)와 현재 계산해야하는 result를 현재 연산자로 계산하여 dfs 
		}
	}

	private static int calc(char op, int n1, int n2) {
		switch (op) {
			case '+': return n1+n2;
			case '-': return n1-n2;
			case '*': return n1*n2;
		}		
		return -1;
	}
}
