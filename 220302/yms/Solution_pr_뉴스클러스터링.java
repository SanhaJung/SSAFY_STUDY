import java.util.*;

class Solution {    
    public int solution(String str1, String str2) {
        int answer = 0;
        ArrayList<String> str1_arr = new ArrayList<>();
        ArrayList<String> str2_arr = new ArrayList<>();
        
        str1 = str1.toUpperCase();      // 문제 조건에 맞게 대문자로 변환
        str2 = str2.toUpperCase();
        
        for(int i=0; i<str1.length()-1; i++) {
            // 문제 조건에 맞게 알파벳만 집합에 추가
            if('A'<=str1.charAt(i) && str1.charAt(i)<='Z' && 'A'<=str1.charAt(i+1) && str1.charAt(i+1)<='Z')
                str1_arr.add(Character.toString(str1.charAt(i)) + str1.charAt(i+1));
        }
        for(int i=0; i<str2.length()-1; i++) {
            if('A'<=str2.charAt(i) && str2.charAt(i)<='Z' && 'A'<=str2.charAt(i+1) && str2.charAt(i+1)<='Z')
                str2_arr.add(Character.toString(str2.charAt(i)) + str2.charAt(i+1));
        }        
        
        double denominator = str1_arr.size()+str2_arr.size();       // 합집합의 크기 = A+B-AB의 교집합
        double numerator = 0;
        
        for(String s1 : str1_arr) {
            for(String s2 : str2_arr) {
                if(s1.equals(s2)) {
                    numerator++;
                    str2_arr.remove(s2);        // 다중 집합의 교집합엔 동일 원소가 더 적은 집합에서의 개수를 가지는 성질 구현
                    break;
                }
            }
        }
        
        denominator -= numerator;
        if(numerator==0 && denominator==0) answer = 65536;
        else answer = (int) (numerator/denominator*65536);
        
        return answer;
    }
}