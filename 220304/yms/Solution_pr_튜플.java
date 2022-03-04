import java.util.*;

class Solution {
    
    public int[] solution(String s) {
        int[] answer = {};
        PriorityQueue<String> pq = new PriorityQueue<>(
            (String s1, String s2) -> Integer.compare(s1.length(),s2.length()));
        // 문자열의 길이가 적은 순으로 정렬
        ArrayList<Integer> list = new ArrayList<>();    // 답을 저장할 리스트
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<s.length(); i++) {
            char cur = s.charAt(i);
            if(cur=='{') {                              // 괄호안의 숫자와 콤마를 우선순위 큐에 저장
                while(cur!='}') {
                    if(('0'<=cur && cur<='9') || cur==',') sb.append(cur);
                    cur = s.charAt(++i);
                }
                pq.offer(sb.toString());
                sb.setLength(0);
            }
        }
        
        while(!pq.isEmpty()) {
            st = new StringTokenizer(pq.poll(), ",");
            while(st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if(!list.contains(num)) {
                    list.add(num);
                }
            }
        };
        
        answer = new int[list.size()];
        
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}