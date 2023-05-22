import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int k;
	static String[] words; // 입력받은 단어 배열
	static char[] combiNum, numbers; // 조합에 사용할 배열과 만들어진 조합을 임시 저장할 배열
	static List<List<Character>> numList; // 만들어진 모든 조합을 저장할 리스트

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());		
		words = new String[n];
		numList = new ArrayList<>();
		Set<Character> setChar = new HashSet<>();
		setChar.add('a');
		setChar.add('n');
		setChar.add('t');
		setChar.add('i');
		setChar.add('c');
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			words[i] = str.substring(4, str.length()-4);
			char[] ch = words[i].toCharArray();
			for(int j=0; j<ch.length; j++) {
				setChar.add(ch[j]);
			}
		}

		if(k < 5) { // anta-tica가 모든 단어에 포함되어 있음 - a,n,t,i,c는 알아야 하나라도 읽을 수 있는 가능성 - 5개보다 더 적게 가르칠 수 있다면 아무것도 읽을 수 없음 
			System.out.println(0);
			return;
		}

		setChar.remove('a');
		setChar.remove('n');
		setChar.remove('t');
		setChar.remove('i');
		setChar.remove('c');
		
		combiNum = new char[setChar.size()];
		if((k-5) > setChar.size()) numbers = new char[setChar.size()];
		else numbers = new char[k-5];
		Iterator<Character> iter = setChar.iterator();
		for(int i=0; i<setChar.size(); i++) {
			combiNum[i] = iter.next();
		}
		
		dice4(0, 0);
		
		int ans = 0;
		for(int i=0; i<numList.size(); i++) { // 조합 배열
			int cnt = 0; // 읽을 수 있는 단어 카운트
			for(int j=0; j<words.length; j++) { // 입력받은 단어 배열
				boolean isOkay = true;
				char[] ch = words[j].toCharArray();
				for(int x=0; x<ch.length; x++) {
					if(ch[x] == 'a' || ch[x] == 'n' || ch[x] == 't' || ch[x] == 'i' || ch[x] == 'c') continue;
					if(!numList.get(i).contains(ch[x])){
                        isOkay = false;
                        break;
                    }
				}
				if(isOkay) cnt++;
			}
			ans = Math.max(ans, cnt);
		}
		
		System.out.println(ans);
		
	}
	
	private static void dice4(int cnt, int start) { // cnt : 기존까지 던져진 주사위 수, start: 현주사위의 눈의 시작
		
		if(cnt == numbers.length) {
			List<Character> list = new ArrayList<>();
			for(int i=0; i<numbers.length; i++) {
				list.add(numbers[i]);
			}
			numList.add(list);
			return;
		}
		
		for(int i=start; i<combiNum.length; i++) {
			numbers[cnt] = combiNum[i];
			dice4(cnt+1, i+1);
			
		}
	}

}