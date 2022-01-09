package studyAlgoProgrammers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution_그래프_가장먼노드 {
	static ArrayList[] lists;
	static int depth, cnt;

	public static void main(String[] args) {
		int result = solution(6, new int[][] { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } });
		System.out.println(result);
	}

	static int solution(int n, int[][] edge) {
		int answer = 0;
		depth = 0;
		cnt = 0;
		lists = new ArrayList[n + 1];
		for (int i = 1; i < lists.length; i++) {
			lists[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < edge.length; i++) {
			lists[edge[i][0]].add(edge[i][1]);
			lists[edge[i][1]].add(edge[i][0]);
		}

		bfs(n);
		return cnt;
	}

	static void bfs(int n) {
		boolean[] v = new boolean[n + 1];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 1, 0 });
		v[1] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll(); // to, cnt
			ArrayList<Integer> list = lists[cur[0]];
			for (int i = 0; i < list.size(); i++) {
				if (!v[list.get(i)]) {
					if (depth == cur[1] + 1) {
						cnt++;
					} else if (depth < cur[1] + 1) {
						cnt = 1;
						depth = cur[1] + 1;
					}
					v[list.get(i)] = true;
					q.add(new int[] { list.get(i), cur[1] + 1 });
				}
			}
		}
	}
}
