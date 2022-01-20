import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<clothes.length; i++) {
            String s = clothes[i][1];
            if(!map.containsKey(s))  map.put(s,1);
            else map.put(s,map.get(s)+1);
        }
        
        for(String s : map.keySet()) { 
            answer *= (map.get(s)+1); // (map.get(s) + 아무것도 안고르는 경우의 수 1)
        }

        return answer-1; // 아무것도 안고르는 경우 1개를 빼줌
    }
}