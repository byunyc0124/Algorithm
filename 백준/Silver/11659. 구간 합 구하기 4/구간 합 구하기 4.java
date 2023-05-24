import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[] numbers;
	static int[] sums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		numbers = new int[n+1]; // 숫자 저장 배열
		sums = new int[n+1]; // 숫자 합 저장 배열
		
		sums[0] = 0; // 초기화
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			if(i == 1) {
				sums[i] = numbers[i];
			}else if(i >= 2) {
				sums[i] = sums[i-1] + numbers[i];
			}
		}
		
		for(int k=0; k<m; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int sum = sums[j] - sums[i-1];
			System.out.println(sum);
		}
	}
}