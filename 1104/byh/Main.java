package a1104;

import java.util.*;
import java.io.*;

class Main {
    static class Dice {
        int top, bottom, west, east, south, north;

        public Dice() {
            this.top = 0;
            this.bottom = 0;
            this.west = 0;
            this.east = 0;
            this.south = 0;
            this.north = 0;
        }

        public void moveEast() {
            int temp = top;
            top = west;
            west = bottom;
            bottom = east;
            east = temp;
        }

        public void moveWest() {
            int temp = top;
            top = east;
            east = bottom;
            bottom = west;
            west = temp;
        }

        public void moveSouth() {
            int temp = top;
            top = north;
            north = bottom;
            bottom = south;
            south = temp;
        }

        public void moveNorth() {
            int temp = top;
            top = south;
            south = bottom;
            bottom = north;
            north = temp;
        }

    }

    static int n, m, x, y, k;
    static int[][] map;
    static int[] dir;
    static int[] dx = {0, 0, -1, 1};    // 동서북남
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dir = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            dir[i] = Integer.parseInt(st.nextToken());
        }

        solution();
    }

    static void solution() {
        Dice dice = new Dice();
        int nx, ny;

        for (int i = 0; i < k; i++) {
            int direction = dir[i] - 1;
            nx = x + dx[direction];
            ny = y + dy[direction];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                // 동쪽은 0, 서쪽은 1, 북쪽은 2, 남쪽은 3
                if (direction == 0) dice.moveEast();
                else if (direction == 1) dice.moveWest();
                else if (direction == 2) dice.moveNorth();
                else if (direction == 3) dice.moveSouth();

                // 숫자 복사
                if (map[nx][ny] == 0)
                    map[nx][ny] = dice.bottom;
                else {
                    dice.bottom = map[nx][ny];
                    map[nx][ny] = 0;
                }

                // 맨 윗면 출력
              
                System.out.println(dice.top);

                x = nx;
                y = ny;
            }
        }
    }
}