import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int N, MOD;
	static int[][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		MOD = 1000000000;
		int ans = 0;
		dp = new int[101][10];
		for(int i=0; i<101; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[1][0] = 0;
		for(int i=1; i<10; i++) {
			dp[1][i] = 1;
		}
		for(int i=0; i<10; i++) {
			ans += fibo(N, i);
			ans %= MOD;
		}
		System.out.println(ans);
	}
	
	private static int fibo(int n, int d) {
		if(dp[n][d] == -1) {
			dp[n][d] = 0;
			if(d>0) {
				dp[n][d] += fibo(n-1, d-1);
			}
			if(d<9) {
				dp[n][d] += fibo(n-1, d+1);
				dp[n][d] %= MOD;
			}
		}
		return dp[n][d];
	}

}