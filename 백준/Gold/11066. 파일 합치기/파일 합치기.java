import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// G3. 파일 합치기
// dp[i][i] = files[i]
// dp[i][i+1] = files[i] + files[i+1]
// dp[i][i+2] = Math.min(
//   dp[i][i] + dp[i+1][i+2] + sum(i, j),
//   dp[i][i+1] + dp[i+2][i+2] + sum(i, j)
// )
// => dp[i][j] = dp[i][mid] + dp[mid+1][j] + sum(i, j)
public class Main {
    static int k, files[], sum[], dp[][];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for(int tc=1; tc<=T; tc++){
            k = Integer.parseInt(br.readLine()); // 소설을 구성하는 장의 수
            files = new int[k+1]; // 파일의 크기 저장
            sum = new int[k+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=k; i++){
                files[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + files[i];
            }

            dp = new int[k+1][k+1];
            int to;
            for(int i=1; i<=k; i++){
                for(int from=1; from+i<=k; from++){
                    to = from + i;
                    dp[from][to] = Integer.MAX_VALUE;
                    for(int mid=from; mid<to; mid++){
                        dp[from][to] = Math.min(dp[from][to], dp[from][mid] + dp[mid+1][to] + sum(from, to));
                    }
                }
            }
            System.out.println(dp[1][k]);
        }
    }

    static int sum(int from, int to){ // from에서 to까지의 파일 합
        return sum[to] - sum[from-1];
    }
}