class Solution {
    
    static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
    static boolean[][] visited;
    static int numberOfArea,maxSizeOfOneArea,cnt;
    
    static void dfs(int r, int c, int m, int n, int[][] picture) {
        for(int d=0; d<4; d++) {
            int nr = r+dr[d];
            int nc = c+dc[d];
            if(0<=nr && nr<m && 0<=nc && nc<n && !visited[nr][nc] && picture[r][c]==picture[nr][nc]) {
                visited[nr][nc] = true;
                cnt++;
                dfs(nr,nc,m,n,picture);
            }
        }
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        
        visited = new boolean[m][n];
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(picture[i][j]>0 && !visited[i][j]) {
                    visited[i][j]=true;
                    cnt=1;
                    dfs(i,j,m,n,picture);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
                    numberOfArea++;
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        
        return answer;
    }
}