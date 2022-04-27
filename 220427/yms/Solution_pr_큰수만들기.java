import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        /*
        시간초과
        for(int i=0; i<number.length(); i++) {
            sb.append(number.charAt(i));
        }
        
        int cnt = 0;
        
        while(cnt<k) {
            for(int i=1; i<number.length()-(k-cnt); i++) {
                if(sb.charAt(i-1)<sb.charAt(i)) {
                    sb.deleteCharAt(i-1);
                    cnt++;
                    break;
                }
            }
        }
        */

        int idx = 0;
        for(int i=0; i<number.length()-k; i++) {
            int max = -1;
            for(int j = idx; j<= k+i; j++) {    // 끝에 한자리만 남기고 최대값 뽑기
                if(max < number.charAt(j)-'0') {
                    max = number.charAt(j)-'0';
                    idx = j+1;
                }
            }
            sb.append(max);
        }

        answer = sb.toString();
        
        return answer;
    }
}