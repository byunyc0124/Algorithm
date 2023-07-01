import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 범위: 10**(n-1) ~ (10**n - 1)
		Queue<Integer> pq = new LinkedList<>();
		pq.add(2);
		pq.add(3);
		pq.add(5);
		pq.add(7);
		
		while(!pq.isEmpty()) {
			int num = pq.poll() * 10;
			if(num >= (int)Math.pow(10, n)) {
				System.out.println(num / 10);
				break;
			}
			for(int i=0; i<10; i++) {
				if(isPrime(num+i)) {
					pq.add(num+i);
				}
			}
		}
		
		while(!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
    }
		
	private static boolean isPrime(int n) {
		if(n < 2) {
			return false;
		}
		for(int i=2; i*i<=n; i++) {
			if(n%i == 0) {
				return false;
			}
		}
		return true;
	}

}