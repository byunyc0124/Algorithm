import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int n, k, ans, durability;
	static int[] belt, robot;
	static Deque<Integer> q;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 길이가 n인 컨베이어 벨트
		k = Integer.parseInt(st.nextToken()); // 내구도 0인 칸의 개수 k개 이상이면 종료
		belt = new int[n*2];
		robot = new int[n*2];
		q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n*2; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		ans = 0;
		durability = 0; // 내구도가 0인 벨트의 개수
		
		while(durability < k) {
			rotation(); // 1. 벨트와 로봇 함께 회전
			move(); // 2. 로봇 이동
			if(belt[0] > 0) { // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면
				robot[0] = 1; // 올리는 위치에 로봇을 올리고 
				belt[0]--; // 내구도 감소		
				if(belt[0] == 0) {
					durability++;
				}
			}
			ans++;
		}
		System.out.println(ans);
	}
	private static void move() {
		for(int i=n-2; i>=0; i--) { // 내리는 위치 전부터 탐색
			if(robot[i] == 1) {
				if(robot[i+1] == 0 && belt[i+1] >= 1) {
					robot[i+1] = 1;
					robot[i] = 0;
					belt[i+1]--;
					if(i+1 == n-1) {
						robot[i+1] = 0;
					}
					if(belt[i+1] == 0) {
						durability++;
					}
				}
			}
		}
	}
	private static void rotation() {
		for(int i=0; i<n*2; i++) {
			q.add(belt[i]);
		}
		q.addFirst(q.pollLast());
		for(int i=0; i<n*2; i++) {
			belt[i] = q.poll();
		}
		
		for(int i=0; i<n*2; i++) {
			q.add(robot[i]);
		}
		q.addFirst(q.pollLast());
		for(int i=0; i<n*2; i++) {
			robot[i] = q.poll();
		}
		// TODO: 내리는 위치에 로봇이 있을 경우 내리기
		if(robot[n-1] == 1) {
			robot[n-1] = 0;
		}
	}

}