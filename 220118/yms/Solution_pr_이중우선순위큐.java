import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
       
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> maxpq = new PriorityQueue<>((o1,o2) -> -Integer.compare(o1,o2));
        StringTokenizer st = null;
        
        for(int i=0; i<operations.length; i++) {
            st = new StringTokenizer(operations[i]);
            if(st.nextToken().equals("I")) {
                int num = Integer.parseInt(st.nextToken());
                pq.offer(num);
                maxpq.offer(num);
            }
            else {
                if(!pq.isEmpty()) {
                    if(st.nextToken().equals("1")) {
                        int max = maxpq.poll();
                        pq.remove(max);
                    }
                    else {
                        int min = pq.poll();
                        maxpq.remove(min);
                    }
                }
            }
        }
        
        if(!pq.isEmpty()) {
            answer[0] = maxpq.poll();
            answer[1] = pq.poll();
        }
        
        return answer;
    }
}