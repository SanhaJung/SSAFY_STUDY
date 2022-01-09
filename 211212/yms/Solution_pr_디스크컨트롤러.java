import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);           // 요청시간을 오름차순을 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> Integer.compare(a[1],b[1]));
        // 소요시간을 오름차순으로 정렬하는 우선순위 큐
        
        int time = 0;   // 현재 시간
        int cnt = 0;    // 처리 완료한 작업의 개수
        int idx = 0;    // 현재 우선순위 큐에 들어간 작업의 인덱스
        
        while(cnt < jobs.length) {            
            while (idx < jobs.length && jobs[idx][0] <= time) { // 현재까지 걸린 시간보다 요청시간이 작거나 같은 경우
				pq.offer(jobs[idx++]);
			}
            
            if(!pq.isEmpty()) {          
                int[] cur = pq.poll();
                answer += cur[1]+time-cur[0];   // 작업 소요시간 + 현재 시간 - 요청 시간
                time += cur[1];                 // 소요 시간만큼 총 시간 증가
                cnt++;                   
            }            
            else {                      // 현재까지 걸린 시간내에 처리할 수 있는 작업이 없는 경우
                time = jobs[idx][0];    // 그 다음 순서 작업의 요청 시간으로 시간 값 증가 
            }
        }
        
        return answer/jobs.length;
    }
}