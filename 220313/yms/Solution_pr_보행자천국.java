class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        
        // cityMap==2인 경우 기존 진행방향이 오른쪽인지 아래쪽인지 알아야함
        // (i,j)로 올 수 있는 경우의 수 = 왼쪽(i,j-1)에서 오는 경우 + 위(i-1, j)에서 오는 경우
        // 왼쪽에서 오는 경우와 위에서 오는 경우 배열을 따로 선언해줌                      
        
        // 배열 크기를 m*n 으로 하면 이전 칸을 불러올때 i-1>=0, j-1>=0 조건들이 들어가서 복잡해짐
        //  => m+1 * n+1 로 선언하면 조건을 안써도됨
        int[][] right = new int[m+1][n+1];
        int[][] down = new int[m+1][n+1];
        
        right[1][1] = 1;
        down[1][1] = 1;
        
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(cityMap[i-1][j-1]==0) {       // 현재칸이 0이면 (왼쪽,위에서 다 올 수 있는 경우라면)
                    down[i][j] += (right[i][j-1]+down[i-1][j])%MOD;
                    right[i][j] += (right[i][j-1]+down[i-1][j])%MOD;
                }
                else if (cityMap[i-1][j-1]==2) { // 현재칸이 2면 (왼쪽이나 위 둘 중 한군데로만 오는 경우라면)
                    down[i][j] += down[i-1][j]%MOD;
                    right[i][j] += right[i][j-1]%MOD;
                }
            }
        }
        
        // answer = (right[m][n-1]+down[m-1][n])%MOD;
        answer = right[m][n]%MOD;
    
        return answer;
    }
}