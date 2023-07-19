import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n]; // 탑들의 높이를 저장해두는 배열
		Stack<Integer> tower = new Stack<>(); // 탑들의 번호를 이용하여 높이를 비교할 스택 
		StringBuilder sb = new StringBuilder(); // 결과값을 저장할 스트링빌더
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		sb.append(0).append(" "); // 맨 처음 탑은 수신할 수 있는 탑이 없음
		tower.add(1); // 맨 처음 탑의 높이를 넣음 -> 인덱스 + 1 로 계산
		for(int i=1; i<n; i++) {
			boolean isOkay = false;
			while(!tower.isEmpty()) {
				int a = tower.peek();
				if(arr[a-1] > arr[i]) {
					sb.append(a).append(" "); // 수신한 탑의 번호 저장
					isOkay = true; // 수신 성공
					break;
				}
				tower.pop();
			}
			tower.add(i+1);
			if(!isOkay) { // 수신한 탑이 없다면
				sb.append(0).append(" ");
			}
		}
		
		System.out.println(sb.toString());
	}

}