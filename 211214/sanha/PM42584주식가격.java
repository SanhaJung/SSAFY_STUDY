package a12algo;
import java.util.*;

public class PM42584주식가격 {

	public static void main(String[] args) {

		int[] prices = {1, 2, 3, 2, 3};
		int[] answer = new int[prices.length];
		

		// stack : 주식 가격이 떨어지지 않은 인덱스를 push
		// stack에 index값 저장
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		stack.push(0);
		
		for(int i=1;i<prices.length;i++) {
			// stack에 top값이 prices의 i번째 값보다 크면: 주식가격이 떨어지면
			while(prices[stack.peek()]>prices[i]) {
				int index = stack.pop();
				answer[index] = i - index;		// 주식가격 떨어지지 않은 시간(top인 인텍스 - 현재인덱스) 저장
			}
			// 비교한 인덱스 i push
			stack.push(i);
		}

		// stack에 남은 인덱스: 끝까지 주식가격 떨어지지 않은 인덱스
		while(!stack.isEmpty()) {
			int index = stack.pop();
			// 주식가격 떨어지지 않은 시간(prices의 길이 - top인 인덱스)저장
			answer[index] = prices.length-index-1;
		}
		System.out.println(Arrays.toString(answer));
	}
}
