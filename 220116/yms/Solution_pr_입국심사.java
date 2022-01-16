class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        int max = 0;
        for(int i=0; i<times.length; i++)
            max = Math.max(max,times[i]);
        long left = 0;
        long right = (long) n * (long) max;
        
        while(left<=right) {
            long mid = (left+right)/2;
            long sum = 0;
            
            for(int i=0; i<times.length; i++) {
                sum += mid/times[i];
            }
            
            if(sum<n) {
                left = mid+1;
            }
            else {
                right = mid-1;
                answer = mid;
            }
        }
        
        return answer;
    }
}