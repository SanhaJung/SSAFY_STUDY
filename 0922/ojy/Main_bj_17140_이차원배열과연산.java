package hw0922;
import java.io.*;
import java.util.*;
public class Main_bj_17140_이차원배열과연산 {
	static class Num{
        int num;
        int count;

        public Num(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int [][]arr = new int[101][101];
        for(int i=1;i<=3;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=3;j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }
        int row =3;
        int col = 3;
        PriorityQueue<Num>queue = new PriorityQueue<>(new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                int freq = o1.count-o2.count;
                if(freq == 0)
                    return o1.num - o2.num;
                return freq;
            }
        });
        int time = 0;
        while (true) {
            if (arr[r][c] == k) break;
            if (time == 100) {
                time = -1;
                break;
            }
            time++;
            if (row >= col) { //R연산
                for(int i=1;i<=row;i++) {
                    Map<Integer, Integer> map = new HashMap<>();
                    List<Num> list = new ArrayList<>();
                    for(int j=1;j<=col;j++){
                        if(arr[i][j]!=0)
                        {
                            if(map.containsKey(arr[i][j]))
                                map.put(arr[i][j],map.get(arr[i][j])+1);
                            else
                                map.put(arr[i][j],1);
                        }
                    }
                    for(Map.Entry<Integer,Integer>entry: map.entrySet()){
                        list.add(new Num(entry.getKey(), entry.getValue()));
                    }
                    Collections.sort(list, new Comparator<Num>() {
                        @Override
                        public int compare(Num o1, Num o2) {
                            int freq = o1.count-o2.count;
                            if(freq == 0)
                                return o1.num - o2.num;
                            return freq;
                        }
                    });
                    int cnt=0;
                    for(int j=0;j<list.size();j++){
                        if(j==49)break;
                        arr[i][++cnt] = list.get(j).num;
                        arr[i][++cnt] = list.get(j).count;
                    }
                    col = Math.max(col,cnt);
                    for(int j=cnt+1;j<=col;j++)
                        if(arr[i][j]!=0)
                            arr[i][j]=0;
                }
            } else {        //C연산
                for (int i = 1; i <= col; i++) {
                    Map<Integer, Integer> map = new HashMap<>();
                    List<Num> list = new ArrayList<>();
                    for (int j = 1; j <= row; j++) {
                        if (arr[j][i] != 0) {
                            if (map.containsKey(arr[j][i]))
                                map.put(arr[j][i], map.get(arr[j][i]) + 1);
                            else
                                map.put(arr[j][i], 1);
                        }
                    }
                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        list.add(new Num(entry.getKey(), entry.getValue()));
                    }
                    Collections.sort(list, new Comparator<Num>() {
                        @Override
                        public int compare(Num o1, Num o2) {
                            int freq = o1.count - o2.count;
                            if (freq == 0)
                                return o1.num - o2.num;
                            return freq;
                        }
                    });
                    int cnt = 0;
                    for (int j = 0; j < list.size(); j++) {
                        if (j == 49) break;
                        arr[++cnt][i] = list.get(j).num;
                        arr[++cnt][i] = list.get(j).count;
                    }
                    row = Math.max(row,cnt);
                    for(int j=cnt+1;j<=row;j++){
                        if(arr[j][i]!=0)
                            arr[j][i]=0;
                    }
                }
            }
//            for(int i=1;i<=row;i++){
//                for(int j=1;j<=col;j++){
//                    System.out.print(arr[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }
        System.out.println(time);
    }
}
