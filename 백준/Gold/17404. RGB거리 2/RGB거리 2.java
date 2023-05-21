import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, price[][], dp[][], ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 집의 수
        StringTokenizer st = null;
        price = new int[n][3]; // 각 집을 칠하는 비용 저장
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            price[i][0] = Integer.parseInt(st.nextToken()); // R
            price[i][1] = Integer.parseInt(st.nextToken()); // G
            price[i][2] = Integer.parseInt(st.nextToken()); // B
        }

        dp = new int[n][3]; // 각각 r g b로 칠했을 때의 경우의 수
        ans = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){ // 첫 집을 r g b로 칠함
            dp[0][i] = price[0][i];
            for(int j=0; j<3; j++){
                if(i != j) dp[0][j] = 1001;
            }
            color(1, i);
        }

        System.out.println(ans);
    }

    private static void color(int cur, int first) {
        if(cur == n){
            for(int i=0; i<3; i++){
                if(i != first) ans = Math.min(ans, dp[cur-1][i]);
            }
            return;
        }

        dp[cur][0] = price[cur][0];
        dp[cur][1] = price[cur][1];
        dp[cur][2] = price[cur][2];

        dp[cur][0] += Math.min(dp[cur-1][1], dp[cur-1][2]);
        dp[cur][1] += Math.min(dp[cur-1][0], dp[cur-1][2]);
        dp[cur][2] += Math.min(dp[cur-1][1], dp[cur-1][0]);

        color(cur+1, first);
    }
}