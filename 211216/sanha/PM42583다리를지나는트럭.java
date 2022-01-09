package a12algo;
import java.util.*;
import java.io.*;
public class PM42583다리를지나는트럭 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(100, 100, new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10, 10}));
	}
	
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int second = 1;
        
        Deque<int[]> queue = new ArrayDeque<>();
        
        int rest_weight = weight;
        int truck_in = 0;
        int truck_out = 0;
        
        while(truck_out<truck_weights.length){
            // 다리가 지탱할 수 있는 무게가 남아 있으면
            if(truck_in<truck_weights.length && rest_weight-truck_weights[truck_in]>=0){
                rest_weight-=truck_weights[truck_in];
                System.out.println("in"+second);
                queue.offer(new int[] {second, truck_in});
                truck_in++;
            }
            // 트럭이 들어온 시간이 다리의 길이와 같으면 다리 지남
            if(queue.peek()!=null && second-queue.peek()[0]==bridge_length-1){
                truck_out = queue.peek()[1];
                rest_weight+=truck_weights[truck_out];
                System.out.println("out"+second);
                queue.poll();
                truck_out++;
            }
            
            second++;
        }
        return second;
    }

}
