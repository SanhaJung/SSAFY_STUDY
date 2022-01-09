import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        int answer = 0;         
        int cur_weight = 0, idx=0;
       
        Queue<Integer> q = new ArrayDeque<>();           
             
        while(idx < truck_weights.length){
           
            if (q.size() == bridge_length) {                  // 다리가 트럭으로 가득 차있는 경우
                cur_weight -= q.poll();                       // 마지막 트럭을 내보내줌 
            }
            
            else if (cur_weight+truck_weights[idx]>weight) {  // 현재 트럭이 올라가면 하중이 초과되는 경우
                q.offer(0);                                   // 남은 트럭을 진행시키기 위해 임의로 빈 트럭 삽입
                answer++;           
            }
            else {
                q.offer(truck_weights[idx]);
                cur_weight += truck_weights[idx];
                answer++;
                idx++;
            }            
        }        
        
        return answer + bridge_length;
    }
}