class Solution {
    public int solution(int[] a) {
        int answer = 2;                 // 양쪽 끝은 항상 가능하기 때문에 초기값을 2로 설정
        if(a.length==1) return 1; 
        
        int left = a[0];
        int right = a[a.length-1];
        
        // 1. 양쪽 끝은 항상 가능
        // 2. 왼쪽, 오른쪽 중 최소 한 곳이 현재 풍선번호보다 크다면 가능하다는 의미임
        //      => 마지막에 본인보다 작은 번호의 풍선이 남으면 찬스를 사용하면 되기 때문에
        for(int i=0; i<a.length; i++) {
            if(left > a[i]) {             // 왼쪽 풍선들이 현재 풍선보다 크다면 가능함
                answer++;       
                left = a[i];
            }
            if(right > a[a.length-1-i]) { // 오른쪽 풍선들이 현재 풍선보다 크다면 가능함
                answer++;
                right = a[a.length-1-i];
            }
        }
        
        // 위 반복문이 끝나고나면 가장 작은 번호의 풍선만 중복으로 체크됨
        //      => 왼쪽, 오른쪽 다 비교했을 때 두 곳보다 작은 경우는 최소값밖에 없기 때문에 
        
        return answer-1;    // 중복 계산된 1 빼고 리턴
    }
}