import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// G2. 교환
public class Main {

    static int n, k, m, ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        n = Integer.parseInt(str); // 정수
        k = Integer.parseInt(st.nextToken()); // 연산 수행 횟수
        m = str.length(); // 자릿수

        if(m == 1 && k >= 1) { // 한자릿수는 1번이상 못바꿈 -> -1
            System.out.println(-1);
            return;
        }

        ans = Integer.MIN_VALUE;
        change();

        if(ans == Integer.MIN_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    private static void change() {
        Queue<Info> q = new ArrayDeque<>();
        Set<Integer>[] visited = new Set[k];
        for(int i=0; i<k; i++){
            visited[i] = new HashSet<>();
        }
        q.add(new Info(n, 0));
        while(!q.isEmpty()){
            Info info = q.poll();

            if(info.cnt == k){
                ans = Math.max(ans, info.num);
                continue;
            }

            int a, b, nnum;
            int size1, size2;
            for(int i=0; i<m-1; i++){
                for(int j=i+1; j<m; j++){
                    a = (int) (info.num % Math.pow(10, m-i) / Math.pow(10, m-i-1));
                    b = (int) (info.num % Math.pow(10, m-j) / Math.pow(10, m-j-1));
                    if(i == 0 && b == 0){ // 바꿨을 때 맨앞자리가 0이 된다면
                        continue;
                    }
                    if(a == b){
                        nnum = info.num;
                    }else{
                        nnum = info.num + (b-a) * (int)Math.pow(10, m-i-1);
                        nnum += (a-b) * (int)Math.pow(10, m-j-1);
                    }

                    size1 = visited[info.cnt].size();
                    visited[info.cnt].add(nnum);
                    size2 = visited[info.cnt].size();
                    if(size1 < size2) q.add(new Info(nnum, info.cnt+1));
                }
            }
        }

    }

    static class Info{
        int num; // 현재 수
        int cnt; // 연산 횟수

        public Info(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "num=" + num +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}