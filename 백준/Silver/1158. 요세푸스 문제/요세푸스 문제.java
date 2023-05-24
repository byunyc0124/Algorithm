import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Deque<Integer> josephus = new ArrayDeque<>();
		for(int i=1; i<=n; i++) { // 덱 세팅
			josephus.add(i); // 1 - n 까지의 숫자 세팅
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for(int i=0; i<n; i++) { // n개만큼 for문
			int cnt = 0;
			while(cnt<k) {
				int a = josephus.poll();
				if(cnt == k-1) {
					sb.append(a);
				}else {
					josephus.add(a);
				}
				cnt++;
			}
			if(i!=n-1) sb.append(", ");
		}
		
		sb.append(">");
		System.out.println(sb.toString());
	}

}