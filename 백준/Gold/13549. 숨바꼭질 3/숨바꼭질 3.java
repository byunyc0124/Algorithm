import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, k, pos[], time;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
		k = Integer.parseInt(st.nextToken()); // 동생이 있는 위치
		pos = new int[200001]; // 위치
		pos[n] = 1;
		find();
		System.out.println(time);
	}

	private static void find() {
		Queue<Info> q = new ArrayDeque<>();
		q.add(new Info(n, 0));
		int x, t;
		while(!q.isEmpty()) {
			Info info = q.poll();
			x = info.curPos;
			t = info.time;
			
			if(x == k) {
				time = t;
				break;
			}
			
			if(2*x < 200000 && pos[2*x] != 1) {
				pos[2*x] = 1;
				q.add(new Info(2*x, t));
			}
			if(x-1 >= 0 && pos[x-1] != 1) {
				pos[x-1] = 1;
				q.add(new Info(x-1, t+1));
			}
			if(x+1 < 200000 && pos[x+1] != 1) {
				pos[x+1] = 1;
				q.add(new Info(x+1, t+1));
			}
		}
	}
	
	static class Info{
		int curPos, time; // 현재 위치, 걸린 시간

		public Info(int curPos, int time) {
			super();
			this.curPos = curPos;
			this.time = time;
		}
	}
}