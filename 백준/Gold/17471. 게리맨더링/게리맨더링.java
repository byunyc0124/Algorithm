import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static long ans;
	static int[] people;
	static ArrayList<Integer>[] arr;
	static int[] index;
	static int[] nums;
	static List<int[]> combiList;

	public static void main(String[] args) throws IOException{
		ans = Integer.MAX_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 구역의 개수
		StringTokenizer st = new StringTokenizer(br.readLine()); // 구역의 인구
		people = new int[n]; // 인구수 저장
		for(int i=0; i<n; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		arr = new ArrayList[n];		
		for(int i=0; i<n; i++) {
			arr[i] = new ArrayList<>(); // 초기화
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			for(int j=0; j<x; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i].add(num-1); // 인덱스로 저장
			}
		}
		
		index = new int[n];
		for(int i=0; i<n; i++) {
			index[i] = i;
		}
		combiList = new ArrayList<>();
		for(int i=1; i<(n/2+1); i++) {
			nums = new int[i];
			combi(0, 0, i);
		}
		for (int[] combi : combiList) {
			int[] result1 = bfs(combi);
			int sum1 = result1[0], v1 = result1[1];
			int[] notCombi = new int[n-combi.length];
			int index = 0;
			for(int i=0; i<n; i++) {
				boolean isContain = false;
				for(int j=0; j<combi.length; j++) {
					if(combi[j] == i) {
						isContain = true;
						break;
					}
				}
				if(!isContain) {
					notCombi[index++] = i;
				}
			}
			int[] result2 = bfs(notCombi);
			int sum2 = result2[0], v2 = result2[1];
			if(v1 + v2 == n) {
				ans = Math.min(ans, Math.abs(sum1-sum2));
			}
		}
		
		if(ans != Integer.MAX_VALUE) {
			System.out.println(ans);
		}else {
			System.out.println(-1);
		}
	}
	
	static int[] bfs(int[] g) {
		int[] result = new int[2]; // 0: 인구수합, 1: 방문한 노드의 개수
		int start = g[0];
		Queue<Integer> q = new ArrayDeque<Integer>();
		List<Integer> visited = new ArrayList<>();
		visited.add(start);
		q.add(start);
		int sum = 0;
		while(!q.isEmpty()) {
			int v = q.poll();
			sum += people[v];
			for (int i : arr[v]) {
				boolean isVisited = false;
				boolean isContain = false;
				for(int j=0; j<visited.size(); j++) {
					if(visited.get(j) == i) {
						isVisited = true;
						break;
					}
				}
				for(int j=0; j<g.length; j++) {
					if(g[j] == i) {
						isContain = true;
						break;
					}
				}
				if(!isVisited && isContain) {
					q.add(i);
					visited.add(i);
				}
			}
		}
		result[0] = sum;
		result[1] = visited.size();
		return result;
	}
	
	static void combi(int start, int cnt, int end) {
		if(cnt == end) {
			combiList.add(Arrays.copyOf(nums, nums.length));
			return;
		}
		
		for(int i=start; i<n; i++) {
			nums[cnt] = index[i];
			combi(i+1, cnt+1, end);
		}
	}

}