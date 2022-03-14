class Solution {
    
    static int answer;
    
    static void dfs(int N, int number, int cnt, int result) {
        
        if(result == number) {                  // 원하는 숫자를 만들었으면
            answer = Math.min(answer, cnt);     // answer 갱신
            return;
        }
        
        int temp = 0;   // N이 연속으로 나오는 숫자 (2, 22, 222 ..)를 고려해주기 위한 변수
        
        for(int i=0; i<8; i++) {    // 최대 8번
            if(cnt+i<8) {   // N을 i번 만큼 더 썼을 때 8번을 넘지 않는다면 계산
                temp = 10*temp + N;
                dfs(N, number, cnt+i+1, result+temp);
                dfs(N, number, cnt+i+1, result-temp);
                dfs(N, number, cnt+i+1, result*temp);
                dfs(N, number, cnt+i+1, result/temp);
            }
        }
    }
    
    public int solution(int N, int number) {
        answer = Integer.MAX_VALUE/2;
        
        dfs(N, number, 0, 0);
        
        if(answer == Integer.MAX_VALUE/2) answer = -1;  // 최솟값이 갱신이 안된 경우 -1 리턴
        return answer;
    }
}