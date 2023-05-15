import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Info> pq = new PriorityQueue<>();
		int time = 0, answer = 0, cnt = 0, index = 0;
		while(cnt < jobs.length) { // 모든 디스크를 다 처리할 때까지 반복
			for(int i=index; i<jobs.length; i++) {
				if(jobs[i][0] <= time) {
					pq.offer(new Info(jobs[i][0], jobs[i][1]));
					index++;
				}
			}
			if(pq.isEmpty()) {
				time++;
			}else {
				Info info = pq.poll();
				time += info.time;
				answer += time - info.req;
				cnt++;
			}
		}
		return answer / jobs.length;
    }
    
    public class Info implements Comparable<Info>{
		int req, time; // 요청되는 시점, 소요시간

		public Info(int req, int time) {
			super();
			this.req = req;
			this.time = time;
		}

		@Override
		public int compareTo(Info o) {
			if(this.time == o.time) return this.req - o.req;
			return this.time - o.time;
		}
	}
}