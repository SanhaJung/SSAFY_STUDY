import java.util.*;

class Solution {
    
    static boolean[] visited;
    static String[] select;
    static Set<String> set;
    static int answer;
    
    static void combination(String[] user_id, String[] banned_id, int start, int cnt) {
        
        if(cnt==banned_id.length) {
            String[] temp = new String[select.length];
            for(int i=0; i<select.length; i++) {
                temp[i] = select[i];
            }
            Arrays.sort(temp);      // 중복되는 경우의 수를 체크하기위해 정렬 후 key로 생성
            String key = "";
            for(int i=0; i<temp.length; i++) {
                key+=temp[i];
            }
            set.add(key);
            return;
        }
        
        for(int i=start; i<banned_id.length; i++) {
            for(int j=0; j<user_id.length; j++) {
                if(!visited[j] && check(user_id[j],banned_id[i])) {   // 선택하지 않은 유저이고 아이디 형식이 맞으면
                    visited[j] = true;
                    select[cnt] = user_id[j];
                    combination(user_id, banned_id, i+1, cnt+1);
                    visited[j] = false;
                }
            }
        }
    }
    
    static boolean check(String userId, String bannedId) {
        if(userId.length()!= bannedId.length()) return false;
        
        for(int i=0; i<userId.length(); i++) {
            if(bannedId.charAt(i)=='*') continue;
            else if(userId.charAt(i)!=bannedId.charAt(i)) return false;
        }
        
        return true;
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        visited = new boolean[user_id.length];
        select = new String[banned_id.length];
        set = new HashSet<>();
        
        combination(user_id,banned_id,0,0);
        
        answer = set.size();
        
        return answer;
    }
}