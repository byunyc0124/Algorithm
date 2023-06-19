import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// G4. 타임머신
public class Main {

    static int n, m;
    static long[] price;
    static Info[] bus;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        m = Integer.parseInt(st.nextToken()); // 버스 노선의 개수
        bus = new Info[m]; // 버스 정보 저장
        int a, b, c;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); // 시작 도시
            b = Integer.parseInt(st.nextToken()); // 도착 도시
            c = Integer.parseInt(st.nextToken()); // 버스를 타고 이동하는데 걸리는 시간
            bus[i] = new Info(a, b, c);
        }

        price = new long[n+1];
        boolean isCycle = bellman();
        if(isCycle){ // 음수간선순환발생
            System.out.println(-1);
        }else{
            for(int i=2; i<n+1; i++){
                if(price[i] == Integer.MAX_VALUE) System.out.println(-1);
                else System.out.println(price[i]);
            }
        }
    }

    private static boolean bellman() {
        Arrays.fill(price, Integer.MAX_VALUE);
        price[1] = 0;

        int a, b, c;
        for(int i=1; i<n; i++){ // 모든 정점(도시)만큼 반복
            for(Info info : bus){ // 모든 간선(버스 노선)을 확인
                a = info.start;
                b = info.end;
                c = info.time;
                if(price[a] == Integer.MAX_VALUE) continue;
                if(price[b] > price[a] + c){
                    price[b] = price[a] + c;
                }
            }
        }

        for(int i=1; i<n+1; i++){ // 모든 정점(도시)만큼 반복
            for(Info info : bus){ // 모든 간선(버스 노선)을 확인
                a = info.start;
                b = info.end;
                c = info.time;
                if(price[a] == Integer.MAX_VALUE) continue;
                if(price[b] > price[a] + c){ // 더 갱신할 게 있다면 음수 간선 순환 발생
                    return true;
                }
            }
        }
        return false;
    }

    static class Info{
        int start, end; // 시작, 도착 도시
        int time; // 버스 시간
        public Info(int start, int end, int time){
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }
}