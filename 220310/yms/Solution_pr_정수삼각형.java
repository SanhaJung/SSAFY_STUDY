class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        for(int i=1; i<triangle.length; i++) {
            for(int j=0; j<triangle[i].length; j++) {
                // 제일 왼쪽 칸이면 바로 위에 숫자 누적
                if(j==0) triangle[i][j] += triangle[i-1][j];
                // 제일 오른쪽 칸도 바로 위에 숫자 누적
                else if(j==triangle[i].length-1) triangle[i][j] += triangle[i-1][j-1];  
                // 사이에 있는 칸이면 왼쪽 위, 오른쪽 위 중 더 큰 값을 누적
                else triangle[i][j] = Math.max(triangle[i][j],triangle[i][j]
                                               +Math.max(triangle[i-1][j],triangle[i-1][j-1]));
            }
        }
        
        for(int i=0; i<triangle[triangle.length-1].length; i++) {
            answer = Math.max(answer, triangle[triangle.length-1][i]);
        }
        
        return answer;
    }
}