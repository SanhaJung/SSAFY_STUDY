package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_17140_이차원배열과연산 {
	static int r, c, k;
	static int[][] A;

	static class Number implements Comparable<Number> {
		int n, count;

		public Number(int n, int count) {
			super();
			this.n = n;
			this.count = count;
		}

		@Override
		public int compareTo(Number o) {
			if (this.count == o.count)
				return this.n - o.n;
			return this.count - o.count;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// A[r][c] 값이 k가 되기 위한 최소 시간 구하기
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());

		A = new int[3][3];
		for (int i = 0; i < A.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < A[i].length; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if (A.length > r && A[0].length > c) {
			if (A[r][c] == k) {
				System.out.println(0);
				System.exit(0);
			}
		}

		int time = 0;
		while (true) {
			time++;
			if (time > 100) {
				time = -1;
				break;
			}

			int row = A.length; // 행 크기
			int col = A[0].length; // 열 크기

			if (row >= col)
				calcR();
			else
				calcC();

//			for (int[] a : A) {
//				System.out.println(Arrays.toString(a));
//			}
//			System.out.println();
			if (A.length > r && A[0].length > c) {
				if (A[r][c] == k)
					break;
			}
		}
		System.out.println(time);
		br.close();
	}
	// 공통 : 정렬을 하기 위해서는 각각의 수가 몇 번 나왔는지 알아야 한다.
	// 수의 등장 횟수가 커지는 순으로, 수가 커지는 순으로
	// 행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버린다.

	// R 연산
	// 배열 A의 모든 행에 대해서 정렬을 수행한다. 행의 개수 >= 열의 개수
	private static void calcR() {
		int max = -1;
		List<PriorityQueue<Number>> list = new ArrayList<PriorityQueue<Number>>();
		for (int i = 0; i < A.length; i++) {
			int[] row = A[i];

			HashMap<Integer, Integer> map = new HashMap<>();
			for (int j = 0; j < row.length; j++) {
				if (row[j] == 0) {
					continue;
				}
				if (map.containsKey(row[j]))
					map.replace(row[j], map.get(row[j]) + 1);
				else
					map.put(row[j], 1);
			}

			PriorityQueue<Number> pq = new PriorityQueue<>();
			for (int key : map.keySet()) {
				pq.add(new Number(key, map.get(key)));
			}
			max = Math.max(max, pq.size());
			list.add(pq);
		}

		A = new int[A.length][max * 2];
		for (int i = 0; i < A.length; i++) {
			PriorityQueue<Number> pq = list.get(i);
			int size = pq.size() * 2;
			if (size > 100)
				size = 100;
			for (int j = 0; j < size; j += 2) {
				Number num = pq.poll();
				A[i][j] = num.n;
				A[i][j + 1] = num.count;
			}
		}
	}

	// C 연산
	// 배열 A의 모든 열에 대해서 정렬을 수행한다. 행의 개수 < 열의 개수
	private static void calcC() {
		// 행 <-> 열 바꾸기
		int[][] temp = new int[A[0].length][A.length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = A[j][i];
			}
		}

		A = temp;
		calcR();
		temp = new int[A[0].length][A.length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = A[j][i];
			}
		}
		A = temp;
	}

}
