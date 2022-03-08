import java.util.*;

class Solution {
    
    static boolean[] visited;
    static Map<String, ArrayList<Integer>> map;
    
    static void subset(String[] s, int cnt, int score) {// info 배열에서 얻을 수 있는 모든 경우의 정보를 
                                                        // 부분집합으로 뽑아 map에 입력       
        if(cnt==4) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<4; i++) {
                if(visited[i]) sb.append(s[i]);         // 선택했다면 해당 문자열을
                else sb.append("-");                    // 안했다면 하이픈을 추가
            }
            
            if(!map.containsKey(sb.toString()))         // 해당 키에 대한 값이 없으면 새로 입력
                map.put(sb.toString(),new ArrayList<>(Arrays.asList(score)));
            
            else map.get(sb.toString()).add(score);     // 있으면 기존 값의 리스트에 추가
            
            return;
        }
        
        visited[cnt] = true;
        subset(s,cnt+1,score);
        visited[cnt] = false;
        subset(s,cnt+1,score);
    }
    
    static int getScore(ArrayList<Integer> list, int score) {   // for문으로 list크기만큼 탐색하면 시간초과나서 이분탐색
        int start = 0;
        int end = list.size()-1;
        
        while(start<=end) {
            int mid = (start+end)/2;
            if(list.get(mid)>=score) {
                end = mid-1;
            }
            else {
                start = mid+1;
            }
        }
        
        return list.size()-start;
    }
    
    public int[] solution(String[] info, String[] query) {
        StringTokenizer st = null;
        
        int[] answer = new int[query.length];
        map = new HashMap<>();
        
        for(int i=0; i<info.length; i++) {
            visited = new boolean[4];
            st = new StringTokenizer(info[i]);
            // 점수를 제외한 언어,직군,경력,음식을 배열에 저장
            String[] s = {st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken()};
            // 점수 저장
            int score = Integer.parseInt(st.nextToken());
            subset(s, 0, score);
        }
        
        // 이분탐색을 위한 정렬
        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        
        for(int i=0; i<query.length; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] s = query[i].split(" ");
            
            int score = Integer.parseInt(s[1]);
            
            if(!map.containsKey(s[0])) answer[i] = 0;
            else answer[i] = getScore(map.get(s[0]), score);
        }
        
        return answer;
    }
}