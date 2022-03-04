import java.util.*;

class Solution {
    
    static long calculate(long num1, long num2, char op) {
        if(op=='+') return num1+num2;
        else if (op=='-') return num1-num2;
        else return num1*num2;
    }
    
    public long solution(String expression) {
        long answer = 0;
        char[][] priority = {{'+','-','*'},{'+','*','-'},{'-','+','*'},
                             {'-','*','+'},{'*','+','-'},{'*','-','+'}};    // 6가지 경우의 수
        ArrayList<Long> numbers = new ArrayList<>();        // 숫자 저장할 리스트
        ArrayList<Character> ops = new ArrayList<>();       // 연산자 저장할 리스트
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<expression.length(); i++) {
            char cur = expression.charAt(i);
            if('0'<=cur && cur<='9') sb.append(cur);
            else {
                numbers.add(Long.parseLong(sb.toString()));
                ops.add(cur);
                sb.setLength(0);
            }
        }
        
        numbers.add(Long.parseLong(sb.toString()));
        
        for(int i=0; i<6; i++) {
            ArrayList<Long> copy_numbers = new ArrayList<>(numbers);
            ArrayList<Character> copy_ops = new ArrayList<>(ops);
            
            for(int j=0; j<3; j++) {
                for(int idx=0; idx<copy_ops.size(); idx++) {
                    char op = copy_ops.get(idx);
                    if(op==priority[i][j]) {
                        // 연산자 인덱스위치의 수와 연산자 인덱스+1 위치의 수 연산
                        long temp = calculate(copy_numbers.remove(idx), copy_numbers.remove(idx), op);
                        copy_ops.remove(idx);
                        copy_numbers.add(idx, temp);
                        idx--;
                    }
                }
            }
            
            answer = Math.max(answer, Math.abs(copy_numbers.get(0)));
            
        }
        
        return answer;
    }
}