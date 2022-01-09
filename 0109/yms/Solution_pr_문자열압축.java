class Solution {    
    static int compression(String s, int length) {
        int idx = length, cnt = 1;
        String word = s.substring(0,length);
        StringBuilder result = new StringBuilder();
        
        while(idx<=s.length()-length) { // length만큼 자를수있는 인덱스까지만 (끝에 자투리 남음)
            if(word.equals(s.substring(idx,idx+length))) {  // 단어가 같으면 카운트만 증가
                cnt++;
            }
            else {
                if(cnt>1) result.append(cnt);   // 1개면 앞에 숫자가 안붙게 조건문
                result.append(word.toString()); // 단어 입력
                cnt = 1;    // 카운트 초기화
                word = s.substring(idx,idx+length); // 단어 갱신
            }            
            idx+=length;
        }
        
        // while문을 빠져나왔을때 아직 연산중이던 cnt와 word입력 처리가 안되어있으므로 여기서 처리
        if(cnt>1) result.append(cnt);
        result.append(word.toString());
        
        return result.length()+s.length()%length;   // 끝에 length길이보다 짧게 문자가 남아있으면 그 갯수 추가
    }
    
    public int solution(String s) {
        int answer = Integer.MAX_VALUE/2;
        
        if(s.length()==1) return 1;
        
        for(int i=1; i<=s.length()/2; i++) {
            answer = Math.min(answer,compression(s,i));
        }
        
        
        return answer;
    }
}