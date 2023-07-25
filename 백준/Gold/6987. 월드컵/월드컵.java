import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int ans;
	static int[][] games = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, 
							{1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 4}, {2, 5},
							{3, 4}, {3, 5}, {4, 5}};
	static int[] win, lose, tie;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 답을 저장
		
		for(int i=0; i<4; i++) { // 4가지 결과
			ans = 0;
			win = new int[6];
			lose = new int[6];
			tie = new int[6];
			int sum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<6; j++) { // 6개국
				win[j] = Integer.parseInt(st.nextToken());
				tie[j] = Integer.parseInt(st.nextToken());
				lose[j] = Integer.parseInt(st.nextToken());
				sum += win[j] + lose[j] + tie[j];
			}
			
			if(sum != 30) ans = 0;
			else {
				if(recursive(0)) ans = 1;
				else ans = 0;
			}
			
			sb.append(ans+" "); // 각 결과에 대한 불가능, 가능
		}
		
		System.out.println(sb.toString());
	}

	private static boolean recursive(int num) { // 게임 번호
		
		if(num >= 15) {
			return true;
		}
		
		if(win[games[num][0]] > 0 && lose[games[num][1]] > 0) { // 첫번째 팀이 이길 수 있다면
			win[games[num][0]]--;
			lose[games[num][1]]--;
			if(recursive(num+1)) return true;
			win[games[num][0]]++;
			lose[games[num][1]]++;
		}
		if(lose[games[num][0]] > 0 && win[games[num][1]] > 0) { // 첫 번째 팀이 질 수 있다면
			lose[games[num][0]]--;
			win[games[num][1]]--;
			if(recursive(num+1)) return true;
			lose[games[num][0]]++;
			win[games[num][1]]++;
		}
		if(tie[games[num][0]] > 0 && tie[games[num][1]] > 0) { // 두 팀이 비길 수 있다면
			tie[games[num][0]]--;
			tie[games[num][1]]--;
			if(recursive(num+1)) return true;
			tie[games[num][0]]++;
			tie[games[num][1]]++;
		}
		
		return false;
		
	}

}