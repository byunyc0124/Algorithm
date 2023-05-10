import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n, d, c, infecTime[];
	static List<Info>[] depenList;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		StringTokenizer st = null;
		for(int tc=0; tc<T; tc++) { // 테케만큼 반복
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
			d = Integer.parseInt(st.nextToken()); // 의존성 개수
			c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터의 번호
			depenList = new ArrayList[n+1]; // 의존 정보 저장할 리스트
			for(int i=0; i<n+1; i++) { // 리스트 초기화
				depenList[i] = new ArrayList<>();
			}
			int a, b, s;
			for(int i=0; i<d; i++) { // 의존성 입력
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken()); // 감염될 컴퓨터
				b = Integer.parseInt(st.nextToken()); // 감영시킬 컴퓨터
				s = Integer.parseInt(st.nextToken()); // 감염 시간
				depenList[b].add(new Info(a, s));
			}
			infection(); // 감염 시작
			int answerC = 0, answerT = 0; // 총 감염되는 컴퓨터 수, 마지막 컴퓨터가 감염되기까지 걸리는 시간
			for(int i=1; i<n+1; i++) {
				if(infecTime[i] != Integer.MAX_VALUE) {
					answerC++;
					answerT = Math.max(answerT, infecTime[i]);
				}
			}
			System.out.println(answerC + " " + answerT);
		}
	}
	
	private static void infection() {
		infecTime = new int[n+1];
		Arrays.fill(infecTime, Integer.MAX_VALUE);
		infecTime[c] = 0;
		PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> o1.time-o2.time);
		pq.add(new Info(c, 0));
		
		while(!pq.isEmpty()) {
			Info info = pq.poll();
			if(infecTime[info.index] < info.time) continue;
			
			for(Info next : depenList[info.index]) {
				if(infecTime[next.index] > next.time + infecTime[info.index]) {
					infecTime[next.index] = next.time + infecTime[info.index];
					pq.add(new Info(next.index, infecTime[next.index]));
				}
			}
		}
	}

	static class Info{
		int index; // 의존하는 컴퓨터 -> 해당 컴퓨터가 바이러스에 걸리면 next도 걸림
		int time; // 감염되기까지 걸리는 시간
		public Info(int index, int time) {
			super();
			this.index = index;
			this.time = time;
		}
	}
}