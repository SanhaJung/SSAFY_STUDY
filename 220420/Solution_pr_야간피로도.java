import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<works.length; i++) {
            pq.offer(works[i]);
        }
        
        for(int i=0; i<n; i++) {
            int num = pq.poll()-1;
            pq.offer(num);
        }
        
        while(!pq.isEmpty()) {
            int num = pq.poll();
            if(num>0)
                answer += Math.pow(num,2);
        }
        
        return answer;
    }
}