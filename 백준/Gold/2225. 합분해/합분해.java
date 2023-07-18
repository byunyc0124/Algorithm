import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{

	static int n, k, MOD;
	static int dp[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		if(k==1) { // 초기값과 연관 있는 부분
			System.out.println(1);
			return;
		}
		
		MOD = 1000000000;
		dp = new int[k+1][n+1];
		for(int i=0; i<k+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		for(int i=0; i<n+1; i++) {
			dp[1][i] = 1; // 길이가 1개면 경우의 수는 무조건 1
			dp[2][i] = i+1; // 길이가 2개면 경우의 수는 n+1
		}
		for(int i=0; i<k+1; i++) {
			dp[i][0] = 1; // 합이 0이 되는 경우의 수는 무조건 1			
		}
		
		int ans = fibo(k, n);
		
		System.out.println(ans%MOD);
	}

	private static int fibo(int k2, int n2) {
		if(dp[k2][n2] == -1) {
			dp[k2][n2] = 0;
			for(int i=n2; i>=0; i--) {
				dp[k2][n2] += fibo(k2-1, i);
			    dp[k2][n2] %= MOD;
			}	
		}
		return dp[k2][n2];
	}

}