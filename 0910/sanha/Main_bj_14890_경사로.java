package a0908.study;

import java.io.*;
import java.util.*;

public class Main_bj_14890_경사로 {
	static int N,L,result = 0;
	// 행렬, 각각 모두 검사해야하므로 전치시켜서 각각 저장
    static int arr[][],arr2[][];
    static boolean visit[];
    
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14890_경사로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st  = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
        arr2 = new int[N][N];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int val = Integer.parseInt(st.nextToken());
                arr[i][j] = val;
                arr2[j][i] = val;  // 전치행렬 저장
            }
        }
        
        for(int i=0; i<N; i++) {
        	// 행마다 함수 반복
            solve(i, arr);
            solve(i, arr2);
        }
        System.out.println(result);
    }
    private static void solve(int index, int[][] array) {
        visit = new boolean[N];
        
        for(int i=0; i<N-1; i++) {
            // array[index][i+1]를 검사하므로 N-1 포함 안함
            if(array[index][i] != array[index][i+1]) {      // 같을때는 pass
                int diff = array[index][i] - array[index][i+1];
                // 차이가 1이상 나면 지나갈 수 X
                if(diff != -1 && diff != 1)
                	// 함수니깐 return 해버리면 그 경우를 마칠 수 있음
                    return;
                
                //왼쪽이 더 높은 경우 => 오른쪽으로 경사가 내려간다. L 만큼 경사로 설치, 현재 인덱스부터 설치
                if(diff == 1) {  // --__
                    for(int j=1; j<=L; j++) {
                    	// 인덱스 조작할때는 꼭 범위 검사
                        if(i+j >= N || visit[i+j])
                            return;
                        
                        // L만큼 평지인지 확인
                        if(array[index][i] - 1 == array[index][i+j])
                            visit[i+j] = true;
                        else
                            return;
                    }
                }
                /// 오른쪽이 더 높은 경우 => 왼쪽으로 경사가 내려간다. L 만큼 경사로 설치, 다음 인덱스부터 설치
                else if(diff == -1) {  // __--
                    for(int j=0; j<L; j++) {
                        if(i<j || visit[i-j])
                            return;
                        
                        if(array[index][i] == array[index][i-j])
                            visit[i-j] = true;
                        else
                            return;
                    }
                }
            }
        }
        result++;

	}

}
