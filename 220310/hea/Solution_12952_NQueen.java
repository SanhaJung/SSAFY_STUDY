package programmers;

public class Solution_12952_NQueen {
	static int result;

	public static void main(String[] args) {
		System.out.println(solution(4));
	}

	static int solution(int n) {
        result = 0;
        
        // 인덱스 : 행, 값 : 열
        int[] col = new int[n + 1];
        go(n, 1, col);
        
        return result;
    }
    
	// 퀸 놓기
    static void go(int n, int cnt, int[] col){
        if(cnt == n + 1){
            result++;
            return;
        }
        
        for (int i = 1; i <= n; i++){
            col[cnt] = i; // 퀸 놓아보기
            
            // 놓아도 되는 곳인지 확인
            if(isAvailable(cnt, col)) go(n, cnt + 1, col);
        }
    }
    
    static boolean isAvailable(int cnt, int[] col){
        for (int k = 1; k < cnt; k++){
        	// 같은 열에 놓거나 대각선에 놓여있으면 안 된다.
            if(col[k] == col[cnt] || (cnt - k) == Math.abs(col[cnt] - col[k])) return false;
        }
        return true;
    }
}