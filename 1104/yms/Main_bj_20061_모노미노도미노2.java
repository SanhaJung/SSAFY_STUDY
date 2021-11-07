package bj20061;

import java.util.*;
import java.io.*;

public class Main_bj_20061_모노미노도미노22 {
    
    static int score; 
    static int[][] blue, green;
    
    public static void move(int t, int x, int y) {			// 입력 받은 값대로 블럭을 입력
        int idx = 0;
        if(t==1){	// 1*1
                blue[x][0] = 1;
                green[0][y] = 1;
                idx = 1;
                while(idx < 6 && blue[x][idx] == 0) {		// 다음칸 검사 후 0이면 (비어있으면) 이동
                    blue[x][idx-1] = 0;
                    blue[x][idx] = 1;
                    idx++;
                }
                idx = 1;
                while(idx < 6 && green[idx][y] == 0) {
                    green[idx-1][y] = 0;
                    green[idx][y] = 1;
                    idx++;
                }
        }
        else if (t==2) {	// 1*2
                blue[x][0] = 1;
                blue[x][1] = 1;
                green[0][y] = 1;
                green[0][y+1] = 1;
                idx = 2;								// 열을 2칸 차지하므로 파란색일땐 2부터 검사
                while(idx < 6 && blue[x][idx] == 0) {
                    blue[x][idx-2] = 0;
                    blue[x][idx] = 1;
                    idx++;
                }
                idx = 1;
                while(idx < 6 && green[idx][y] == 0 && green[idx][y+1] == 0) {	// 열을 2칸 차지하므로 초록색일땐 열 2칸에 한군데라도 걸치면 멈춰야함
                    green[idx-1][y] = 0;
                    green[idx-1][y+1] = 0;
                    green[idx][y] = 1;
                    green[idx][y+1] = 1;
                    idx++;
                }
        }
        else {	// 2*1
                blue[x][0] = 1;
                blue[x+1][0] = 1;
                green[0][y] = 1;
                green[1][y] = 1;
                idx = 1;
                while(idx < 6 && blue[x][idx] == 0 && blue[x+1][idx] == 0) {	// 행을 2칸 차지하므로 파란색일땐 행 2칸에 한군데라도 걸치면 멈춰야함
                    blue[x][idx-1] = 0;
                    blue[x+1][idx-1] = 0;
                    blue[x][idx] = 1;
                    blue[x+1][idx] = 1;
                    idx++;
                }
                idx = 2;														// 행을 2칸 차지하므로 초록색일땐 2부터 검사
                while(idx < 6 && green[idx][y] == 0) {
                    green[idx-2][y] = 0;
                    green[idx][y] = 1;
                    idx++;
                }
        }
    }
    
    public static void getScore() {				// 맨 아랫 줄 부터 해당 배열에 맞는 열이나 행이 4개로 꽉 찼으면, score를 1 더해주고 clean 메서드 실행 
        for(int i = 5; i >= 2; i--) {
            int count = 0;
            for(int j = 0; j < 4; j++) {
                if(green[i][j] == 0)
                    break;
                else
                    count++;
            }
            if(count == 4) {
                score++;
                clear_green(i);
                i++;
            }
        }
    
        for(int i = 5; i >= 2; i--) {
            int count = 0;
            for(int j = 0; j < 4; j++) {
                if(blue[j][i] == 0)
                    break;
                else
                    count++;
            }
            if(count == 4) {
                score++;
                clear_blue(i);
                i++;
            }
        }
    }
    
    public static void clear_green(int line) {		// 득점 한 이후 해당 줄의 위에 있는 블록들을 1칸씩 내림
    	for(int i = line; i > 0; i--) {
    		for(int j = 0; j < 4; j++) {
    			green[i][j] = green[i-1][j];
    		}
    	}
    }
    
    public static void clear_blue(int line) {
    	for(int j = line; j > 0; j--) {
    		for(int i = 0; i < 4; i++) {
    			blue[i][j] = blue[i][j-1];
    		}
    	}
    }
    
    public static void pushGreen(int count) {		// 진한 칸들을 1칸씩 밀어내고, 연한 칸을 0 으로 초기화
        for(int i = 5; i >= 2; i--) {
            for(int j = 0; j < 4; j++)
                green[i][j] = green[i-count][j];
        }
        
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 4; j++)
                green[i][j] = 0;
    }
    
    public static void pushBlue(int count) {
        for(int i = 5; i >= 2; i--) {
            for(int j = 0; j < 4; j++)
                blue[j][i] = blue[j][i-count];
        }
        
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 4; j++)
                blue[j][i] = 0;
    }
    
    public static int check_green() {		// 연한 칸에 있는 블록의 수를 세어줌 ( 1 or 2)
        int count = 0;
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++)
                if(green[i][j] == 1) {
                    count++;
                    break;
                }
        }
        
        return count;
    }
    
    public static int check_blue() {
        int count = 0;
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++)
                if(blue[j][i] == 1) {
                    count++;
                    break;
                }
        }
        
        return count;
    }
    
    public static int count() {					// 연산이 모두 끝난 뒤 blue, green 배열에 블록이 있는 칸의 개수 출력
        int count = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 6; j++) {
                if(blue[i][j] == 1)
                    count++;
                if(green[j][i] == 1)
                    count++;
            }
        }
    
        return count;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        score = 0;
        blue = new int[4][6];
        green = new int[6][4];
        int N = Integer.parseInt(in.readLine());
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int t = Integer.parseInt(st.nextToken());	
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            move(t, x, y);
            getScore();
            pushGreen(check_green());
            pushBlue(check_blue());
        }
        
        System.out.println(score + "\n" + count());
    }
}