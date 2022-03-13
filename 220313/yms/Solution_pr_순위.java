class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        boolean[][] matrix = new boolean[n+1][n+1];        
        
        for(int i=0; i<results.length; i++) {
            int winner = results[i][0];
            int loser = results[i][1];
            matrix[winner][loser] = true;
        }
        
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    // k 선수를 거쳐 j 선수와의 승부 결과를 알 수 있으면 true로 바꿔줌
                    if(!matrix[i][j]) matrix[i][j] = matrix[i][k] && matrix[k][j];
                }
            }
        }
        
        for(int i=1; i<=n; i++) {
            int cnt = 0;
            for(int j=1; j<=n; j++) {
                if(matrix[i][j] || matrix[j][i]) cnt++;
            }
            // 자신을 제외한 전원과의 승부 결과를 알 수 있으면 순위를 알 수 있음
            if(cnt == n-1) answer++;
        }
        
        return answer;
    }
}