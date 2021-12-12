import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<int[]> q = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<priorities.length; i++) {
            q.offer(new int[] {i,priorities[i]});
            pq.offer(priorities[i]);
        }
         
        while(true) {
            int[] cur = q.poll();
            
            if(cur[1]<pq.peek())
                q.offer(cur);
            
            else {
                pq.poll();
                answer++;
                if(cur[0]==location)
                    break;
            }
        }
        
        return answer;
    }
}