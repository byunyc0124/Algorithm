import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// G2. 미확인 도착지
public class Main {

    static int n, m, t, s, g, h;
    static List<Info>[] road;
    static List<Integer> dest;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 교차로 개수
            m = Integer.parseInt(st.nextToken()); // 도로 개수
            t = Integer.parseInt(st.nextToken()); // 목적지 후보의 개수
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()); // 예술가들의 출발지
            g = Integer.parseInt(st.nextToken()); // g와 h 교차로 사이에 있는 도로를 지나감
            h = Integer.parseInt(st.nextToken());
            road = new List[n+1];
            for(int i=0; i<n+1; i++){
                road[i] = new ArrayList<>();
            }
            int a, b, d; // a와 b 사이에 길이 d의 양방향 도로가 있음
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());
                road[a].add(new Info(b, d));
                road[b].add(new Info(a, d));
            }
            dest = new ArrayList<>(); // 목적지 후보
            for(int i=0; i<t; i++){
                dest.add(Integer.parseInt(br.readLine()));
            }

            int[] result1 = dijk(s); // s에서 시작
            int sg = result1[g]; // s에서 g까지의 최단 비용
            int sh = result1[h]; // s에서 h까지의 최단 비용
            int[] result2 = dijk(g); // g에서 시작
            int gh = result2[h]; // g에서 h까지의 최단 비용
            int[] result3 = dijk(h); // h에서 시작
            int hg = result3[g]; // h에서 g까지의 최단 비용
            for(int i=t-1; i>=0; i--){
                if(sg + gh + result3[dest.get(i)] != result1[dest.get(i)]
                        && sh + hg + result2[dest.get(i)] != result1[dest.get(i)]) dest.remove(i);
            }

            dest.sort((d1, d2) -> d1.compareTo(d2));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<dest.size(); i++){
                sb.append(dest.get(i)+" ");
            }
            System.out.println(sb.toString());
        }
    }

    private static int[] dijk(int start) {
        PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> (a.len - b.len));
        int[] p = new int[n+1];
        Arrays.fill(p, Integer.MAX_VALUE);
        p[start] = 0;
        pq.add(new Info(start, 0));

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            if(p[cur.index] < cur.len) continue;

            for (Info next : road[cur.index]){
                if(p[next.index] <= p[cur.index] + next.len) continue;
                p[next.index] = p[cur.index] + next.len;
                pq.add(new Info(next.index, p[next.index]));
            }
        }
        return p;
    }

    static class Info{
        int index;
        int len;

        public Info(int index, int len) {
            this.index = index;
            this.len = len;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "index=" + index +
                    ", len=" + len +
                    '}';
        }
    }
}