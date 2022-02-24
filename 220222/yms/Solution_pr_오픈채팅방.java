import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        StringTokenizer st = null;
        Map<String, String> map = new HashMap<>();      // 회원 정보를 관리할 맵
        Queue<String[]> q = new ArrayDeque<>();         // 로그를 저장할 큐
        
        for(int i=0; i<record.length; i++) {
            st = new StringTokenizer(record[i]);
            String order = st.nextToken();
            String uid = st.nextToken();
        
            if(order.equals("Enter")) {
                String nickName = st.nextToken();
                if(map.get(uid)==null) {                // 처음 들어온 uid정보면 맵에 추가
                    map.put(uid, nickName);
                }
                else map.replace(uid, nickName);        // 아니라면 닉네임 수정
                q.offer(new String[]{order, uid});      // 큐에 저장
            }
            else if (order.equals("Leave")) {
                q.offer(new String[]{order, uid});      // 큐에 저장
            }
            else {
                String nickName = st.nextToken();
                map.replace(uid, nickName);             // 닉네임 수정
            }
        }
        
        answer = new String[q.size()];
        int idx = 0;
        while(!q.isEmpty()) {
            String[] cur = q.poll();
            String order = cur[0];
            String uid = cur[1];
            if(order.equals("Enter")) answer[idx++] = map.get(uid) + "님이 들어왔습니다.";
            else answer[idx++] = map.get(uid) + "님이 나갔습니다.";
        }
        
        return answer;
    }
}