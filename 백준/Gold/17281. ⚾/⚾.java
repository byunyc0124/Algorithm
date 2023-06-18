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

	static int n, ans, win; // 이닝 수
	static int[][] player;
	static int[] nums;
	static List<int[]> playerPerm;
	static int[] base;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 이닝 수
		player = new int[10][n]; // 각 번호 당 이닝에서 얻는 결과 기록
		StringTokenizer st = null;
		for(int i=0; i<n; i++) { // n번의 이닝 진행
			 st = new StringTokenizer(br.readLine());
			 for(int j=0; j<9; j++) { // 총 9명
				 player[j+1][i] = Integer.parseInt(st.nextToken());
			 }
		}
		
		nums = new int[9];
		playerPerm = new ArrayList<int[]>();
		perm(0, 1);
		for (int i = 0; i < playerPerm.size(); i++) {
			//System.out.println(Arrays.toString(playerPerm.get(i)));
			game(playerPerm.get(i));
		}
		
		System.out.println(ans);
	}

	private static void game(int[] is) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		for(int i=0; i<is.length; i++) {
			q.add(is[i]);
		}
		
		win = 0;
		int cur = 0; // 현재 타자
		for(int i=0; i<n; i++) { // n번의 이닝 진행
			int out = 0;
			base = new int[3]; // 1루 2루 3루
			while(out < 3) { // 아웃 3번이면 이닝 종료
				cur = q.poll();
				switch (player[cur][i]) {
					case 0: // out
						out++;
						q.add(cur);
						continue;
					case 1: // 안타 -> 한 루씩 진루
						hit1();
						break;
					case 2: // 2루타 -> 두 루씩 진루
						hit2();
						break;
					case 3: // 3루타 -> 세 루씩 진루
						hit3();
						break;
					case 4: // 홈런 -> 모두 홈으로
						hit4();
						break;
				}
				

				q.add(cur);
			}
		}
		
		ans = Math.max(win, ans);
	}

	private static void hit1() {
		if(base[2] == 1) { // 3루 주자가 홈으로
			base[2] = 0;
			win++;
		}
		if(base[1] == 1) { // 2루 주자가 3루로
			base[1] = 0;
			base[2] = 1;
		}
		if(base[0] == 1) { // 1루 주자가 2루로
			base[0] = 0;
			base[1] = 1;
		}
		base[0] = 1; // 타자가 2루로
	}

	private static void hit2() {
		for(int i=1; i<3; i++) {
			if(base[i] == 1) {
				base[i] = 0;
				win++;
			}
		}
		if(base[0] == 1) { // 1루 주자가 3루로
			base[0] = 0;
			base[2] = 1;
		}
		base[1] = 1; // 타자가 2루로
	}

	private static void hit3() {
		for(int i=0; i<3; i++) {
			if(base[i] == 1) {
				base[i] = 0;
				win++;
			}
		}
		base[2] = 1; // 타자가 3루로
	}

	private static void hit4() { // 홈런
		win++; // 타자가 홈으로
		for(int i=0; i<3; i++) { // 모든 루를 탐색
			if(base[i] == 1) {
				base[i] = 0;
				win++;
			}
		}
	}

	private static void perm(int cnt, int flag) {
		if(cnt == 3) { // 4번째 자리는
			nums[cnt] = 1; // 무조건 1번타자
			perm(cnt+1, flag | (1<<1));
		}
		
		if(cnt == 9) { // 총 9명 배치하면
			playerPerm.add(Arrays.copyOf(nums, nums.length)); // 저장하고
			return; // 종료
		}
		
		for(int i=2; i<10; i++) { // 2번부터 9번까지만 배치
			if((flag & (1<<i)) != 0) continue;
			nums[cnt] = i;
			perm(cnt+1, flag | (1<<i));
		}
		
	}

}