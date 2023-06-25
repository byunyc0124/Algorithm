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
	
	static int n, m, k;
	static int[] ope_idx; // 순열에 사용할 연산 정보 인덱스 배열
	static int[] nums; // 순열 임시 저장 배열
	static List<int[]> perm; // 모든 순열 저장 배열
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 배열 행의 크기
		m = Integer.parseInt(st.nextToken()); // 배열 열의 크기
		k = Integer.parseInt(st.nextToken()); // 연산 개수
		int[][] arr = new int[n][m]; // 배열 저장
		int[][] ope = new int[k][3]; // 연산 정보 저장
		ope_idx = new int[k]; // 연산에 대한 인덱스 저장
		nums = new int[k];
		perm = new ArrayList<int[]>();
		int ans = Integer.MAX_VALUE; // 결과
		for(int i=0; i<n; i++) { // 배열 저장
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<k; i++) { // 연산 정보 저장
			st = new StringTokenizer(br.readLine());
			ope[i][0] = Integer.parseInt(st.nextToken())-1; // r, 인덱스이므로 1씩 빼서 저장
			ope[i][1] = Integer.parseInt(st.nextToken())-1; // c, 인덱스이므로 1씩 빼서 저장
			ope[i][2] = Integer.parseInt(st.nextToken()); // s
			ope_idx[i] = i;
		}
		
		permutation(0, 0); // 연산 순열
		
		for(int i=0; i<perm.size(); i++) {
			int[][] temp = new int[n][m];
			for(int x=0; x<n; x++) {
				System.arraycopy(arr[x], 0, temp[x], 0, m);
			}
			
			for(int j=0; j<perm.get(i).length; j++) {
				int p = perm.get(i)[j];
				for(int k=0; k<ope[p][2]; k++) { // s만큼 돌려줘야 함
					turn(temp, ope[p][0], ope[p][1], ope[p][2]-k); // 순열 순서대로 돌려주기					
				}
			}
			
			// 하나의 순열에 대한 연산을 완료하면
			int res = Integer.MAX_VALUE;
			for(int j=0; j<n; j++) {
				int sum = 0;
				for(int k=0; k<m; k++) { // 한 행을 다 더해주고
					sum += temp[j][k];
				}
				res = Math.min(sum, res); // 최소값으로 갱신
			}
			
			ans = Math.min(ans, res); // 결과도 최소값으로 갱신
			
		}
		
		System.out.println(ans);
		
	}
	
	private static void turn(int[][] input, int r, int c, int s) {
		Queue<Integer> q = new ArrayDeque<>();
		/**
		 * (r-s+1, c-s) 부터 큐에 넣어줌
		 * 아래, 오른쪽, 위, 왼쪽 순으로 넣어줌
		 */
		for(int i=r-s+1; i<=(r+s); i++) {
			q.add(input[i][c-s]);
		}
		for(int j=c-s+1; j<=(c+s); j++) {
			q.add(input[r+s][j]);
		}
		for(int i=r+s-1; i>=(r-s); i--) {
			q.add(input[i][c+s]);
		}
		for(int j=c+s-1; j>=(c-s); j--) {
			q.add(input[r-s][j]);
		}
		
		q.add(q.poll());
		
		for(int i=r-s+1; i<=(r+s); i++) {
			input[i][c-s] = q.poll();
		}
		for(int j=c-s+1; j<=(c+s); j++) {
			input[r+s][j] = q.poll();
		}
		for(int i=r+s-1; i>=(r-s); i--) {
			input[i][c+s] = q.poll();
		}
		for(int j=c+s-1; j>=(c-s); j--) {
			input[r-s][j] = q.poll();
		}
	}
	
	private static void permutation(int cnt, int flag) {
		
		if(cnt == k) {
			perm.add(Arrays.copyOf(nums, k));
			return;
		}
		
		for(int i=0; i<k; i++) {
			if((flag & (1<<i)) != 0) continue;
			nums[cnt] = ope_idx[i];
			permutation(cnt+1, flag | (1<<i));
		}
	}

}