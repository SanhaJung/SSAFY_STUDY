package programmers;

import java.util.*;

public class Solution_77486_2021DevMatching_다단계칫솔판매 {

	public static void main(String[] args) {
		System.out.println(Arrays
				.toString(solution(new String[] { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" },
						new String[] { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" },
						new String[] { "young", "john", "tod", "emily", "mary" }, new int[] { 12, 4, 2, 5, 10 })));
	}

	static int[] solution(String[] enroll, String[] referral, String[] sellers, int[] amount) {
		int[] answer = new int[enroll.length];

		HashMap<String, String> recommender = new HashMap<>();	// key:판매자, value:판매자를 추천한 추천인 
		HashMap<String, Integer> profit = new HashMap<>();		// key:판매자, vaule:판매자의 이익

		for (int n = 0; n < enroll.length; n++) {
			recommender.put(enroll[n], referral[n].equals("-") ? "center" : referral[n]);
			profit.put(enroll[n], 0);
		}

		for (int k = 0; k < sellers.length; k++) {
			String seller = sellers[k]; // 판매자
			int money = amount[k] * 100; // 수익

			profit.replace(seller, (int) (profit.get(seller) + money * 0.9)); // 판매자 90% 가져감

			String next = recommender.get(seller);
			money *= 0.1;
			
			while (true) {
				if(next.equals("center")) break;
				
				// 10% 추천인에게 주기
				if (money * 0.1 < 1) {								
					// 원 단위로 잘라서 1원보다 적으면 본인이 다 가진다.
					profit.replace(next, (int) (profit.get(next) + money));
					break;
				}else {
					// 본인 90% 가지기
					// 만약 10%가 n.xxx이면 소수점 버림
					profit.replace(next, (int) (profit.get(next) + (int) (money - (int)(money * 0.1))));
				}
				
				money *= 0.1;					// 다음 사람에게 줄 10%
				next = recommender.get(next);	// 다음 사람
			}
		}

		for (int k = 0; k < enroll.length; k++) {
			answer[k] = profit.get(enroll[k]);
		}

		return answer;
	}
}