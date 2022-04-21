class Solution {
    public int solution(int[][] board, int[][] skill) {
        
        // 참고
        // 누적합 dp 점화식
        // dp[i][j] = arr[i-1][j-1]+dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1]
        int answer = 0;
        int[][] prefixSum = new int[board.length+1][board[0].length+1];
        
        for(int i=0; i<skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            if(type==1) degree = -degree;
            
            prefixSum[r1][c1] += degree;
            prefixSum[r1][c2+1] -= degree;
            prefixSum[r2+1][c1] -= degree;
            prefixSum[r2+1][c2+1] += degree;
        }
        
        for(int i=0; i<prefixSum.length; i++) {
            for(int j=1; j<prefixSum[i].length; j++) {
                prefixSum[i][j] += prefixSum[i][j-1];
            }
        }
        
        for(int i=1; i<prefixSum.length; i++) {
            for(int j=0; j<prefixSum[i].length; j++) {
                prefixSum[i][j] += prefixSum[i-1][j];
            }
        }
        
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j]+prefixSum[i][j]>0) answer++;
            }
        }
        
        return answer;
    }
}