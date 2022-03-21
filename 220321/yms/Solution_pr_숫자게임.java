import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
                
//         Arrays.sort(B);
        
//         for(int i=0; i<A.length; i++) {
//             int start = 0;
//             int end = B.length-1;
//             int idx = 0;
//             int min = 1000000001;
//             while(start<=end) {
//                 int mid = (start+end)/2;
//                 if(B[mid]>A[i]) {
//                     end = mid-1;
//                     if(B[mid]<min) {
//                         min = B[mid];
//                         idx = mid;
//                     }
//                 }
                
//                 else if (B[mid]<=A[i])
//                     start = mid+1;
//             }
//             if(min<1000000001) {
//                 B[idx] = -1;
//                 answer++;
//             }
//         }
       
            
        Arrays.sort(A);
        Arrays.sort(B);
        
        int aidx = 0;
        int bidx = 0;
        
        while(aidx<A.length && bidx <B.length) {
            if(A[aidx] < B[bidx]) {
                answer++;
                aidx++;
            }
            bidx++;
        }
        
         return answer;
	} 
}