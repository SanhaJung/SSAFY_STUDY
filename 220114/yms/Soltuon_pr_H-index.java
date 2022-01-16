import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        //[0,1,3,5,6]
        Arrays.sort(citations);
        
        // h-index의 범위 = 0~citations의 길이
        for(int h=0; h<citations.length; h++) { // h-index를 0부터 넣어봐서 확인하는 반복문
            // 현재 인덱스의 의미 :  인덱스 번 미만 인용된 논문의 수
            // citations.length - 현제 인덱스 : 인덱스번 이상 인용된 논문의 수
            if(citations[h]>=citations.length-h) {
                answer = citations.length-h;
            }
        }
        
        return answer;
    }
}