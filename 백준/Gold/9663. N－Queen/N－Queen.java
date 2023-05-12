import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int n, brd[], ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 퀸 개수
		brd = new int[n];
		queen(0);
		System.out.println(ans);
	}

	private static void queen(int cnt) {
		if(cnt == n) {
			ans++;
			return;
		}
		
		for(int i=0; i<n; i++) {
			brd[cnt] = i;
			if(!isPossible(cnt, i)) continue;
			queen(cnt+1);
		}
	}

	private static boolean isPossible(int cur, int pos) {
		for(int i=0; i<cur; i++) {
			if(brd[i] == pos) return false; // 세로 혹은 가로로 같은 위치
			if(cur - i == Math.abs(brd[cur] - brd[i])) return false; // 대각선
		}
		return true;
	}
}