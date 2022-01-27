import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        ArrayList<String> cache = new ArrayList<>();
        
        if(cacheSize==0) return cities.length*5;
        
        label:for(int i=0; i<cities.length; i++) {
            String s = cities[i].toUpperCase();
            for(int j=0; j<cache.size(); j++) {
                if(cache.get(j).equals(s)) {
                    cache.remove(j);
                    cache.add(s);
                    answer+=1;
                    continue label;
                }
            }
            
            if(cache.size()>=cacheSize) cache.remove(0);
            cache.add(s);
            answer+=5;
        }
        
        return answer;
    }
}