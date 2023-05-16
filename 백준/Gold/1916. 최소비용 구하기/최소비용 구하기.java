import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// G5. 최소비용 구하기
public class Main {
	
	static int n, m, start, end;
	static List<Info>[] bus;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine()); // 도시의 개수
		m = Integer.parseInt(br.readLine()); // 버스의 개수
		bus = new ArrayList[n+1]; // 버스 정보 저장
		for(int i=0; i<n+1; i++) {
			bus[i] = new ArrayList<>();
		}
		int a, b, p;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()); // 출발 도시
			b = Integer.parseInt(st.nextToken()); // 도착 도시
			p = Integer.parseInt(st.nextToken()); // 비용
			bus[a].add(new Info(b, p));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()); // 출발점 도시
		end = Integer.parseInt(st.nextToken()); // 도착점 도시
		
		int ans = dijk(); // 최소 비용 찾기
		
		System.out.println(ans); // 결과 출력
	}
	
	private static int dijk() {
		int[] pri = new int[n+1];
		Arrays.fill(pri, Integer.MAX_VALUE);
		pri[start] = 0;
		PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> o1.price-o2.price);
		pq.add(new Info(start, 0));
		
		while(!pq.isEmpty()) {
			Info cur = pq.poll();
			if(pri[cur.dest] < cur.price) continue;
			
			for(Info next : bus[cur.dest]) {
				if(pri[next.dest] > pri[cur.dest] + next.price) {
					pri[next.dest] = pri[cur.dest] + next.price;
					pq.add(new Info(next.dest, pri[cur.dest] + next.price));
				}
			}
		}
		
		return pri[end];
	}

	static class Info{
		int dest; // 도착 도시
		int price; // 버스 비용
		public Info(int dest, int price) {
			super();
			this.dest = dest;
			this.price = price;
		}
	}

}