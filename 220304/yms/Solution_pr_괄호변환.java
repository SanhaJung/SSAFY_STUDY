class Solution {
    
    static String seperate(String w) {
        StringBuilder sb = new StringBuilder();
        String u = "";
        String v = "";
        int cnt = 0;
        
        if(w=="") return "";                            // 빈 문자열이면 빈 문자열 반환
        
        for(int i=0; i<w.length(); i++) {               // u와 v로 분리하기
            if(w.charAt(i)=='(') cnt++;
            else cnt --;
            sb.append(w.charAt(i));
            
            if(cnt==0) {                                // 균형잡힌 괄호 문자열이 되는 순간
                u = sb.toString();                      // u 문자열 저장
                v = w.substring(i+1,w.length());        // 전체 문자열에서 u 문자열을 제외한 나머지를 v 문자열에 저장
                break;
            }
        }
        // u가 올바른 괄호 문자열이면 수행한 결과 문자열을 u에 이어 붙인 후 1단계부터 재귀적 수행
        if(check(u)) return u+seperate(v);
        // 아니라면 문제 조건대로 재귀적 수행
        else return "("+seperate(v)+")"+reverse(u);
    }

    // 올바른 괄호 문자열인지 체크하는 메서드
    static boolean check(String s) {
        int cnt = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)=='(') cnt++;
            else cnt--;
            if(cnt<0) return false;
        }
        if(cnt==0) return true;
        else return false;
    }
    
    // 4-4 조건 메서드
    static String reverse(String u) {
        StringBuilder result = new StringBuilder();
        for(int i=1; i<u.length()-1; i++) {
            if(u.charAt(i)=='(') result.append(')');
            else result.append('(');
        }
        return result.toString();
    }
    
    public String solution(String p) {
        String answer = "";
        answer = seperate(p);
        
        return answer;
    }
}