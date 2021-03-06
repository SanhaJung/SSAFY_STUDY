//문제망함

class Solution {
    public int solution(String name) {
        int len = name.length();
      int min_move = len-1; 
      // 처음부터 끝까지 오른쪽으로 갈 때의 이동횟수

      int answer = 0 ;
      
      for(int i=0; i<len; ++i) {
         // 1. 상하: A로 가는 가까운 길
         // a ... l  'm'  n ... z
         if(name.charAt(i)<='M') {
            answer += name.charAt(i)-'A';
         }
         else {
            answer +='Z'-name.charAt(i)+1;
         }
         
         // 2. 좌우: 연속된 A의 등장에 따라 달라지는 최소 움직임
         int next = i+1;
         while(next<len && name.charAt(next)=='A') {
            ++next;
         }
         min_move = Math.min(min_move,i+len-next+Math.min(i,len-next));
         // len - next : 
         // 총 길이에서 현재 바로 다음에 연속된 A가 지나고 남은 문자 갯수
         // i : 오른쪽으로의 현재까지의 이동횟수
         // i + len - next + i : 현재까지 왔다가 다시 돌아가서 남은 거 까지 가는 이동 횟수
         // min(i,len-next)에서,
         // i 보다 len-next 값이 작은 경우에 len-next를 선택하는데
         // 이는, 마지막 문자가 A인 경우를 제외 하면
         // 무조건 len-1 보다 크게 된다 (len-next >=1)
         // 따라서,i가 len-next(남은 문자)보다 큰 경우는
         // 굳이 왼쪽으로 다시 돌아갈 필요가 없다.
         // 대신 끝이 A인 경우는, len-next가 0이 되므로,
         // 무조건 len-1 보다 작은 i 가 최소 이동횟수가 된다.
         // 따라서 Math.min(i,len-next) 이 부분은 식에서 필요하게 된다.
         
         
      }
      answer += min_move;
      
      return answer;
    }
    
}