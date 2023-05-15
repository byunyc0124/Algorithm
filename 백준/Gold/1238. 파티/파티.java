import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, x, INF;
    static List<Node> road[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 마을의 개수(정점)
        m = Integer.parseInt(st.nextToken()); // 도로의 개수(간선)
        x = Integer.parseInt(st.nextToken()); // 파티 장소
        road = new List[n+1]; // 도로 정보 저장
        for(int i=0; i<n+1; i++){
            road[i] = new ArrayList<>();
        }
        int s, e, t;
        for(int i=0; i<m; i++){ // 도로 정보
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()); // 시작점
            e = Integer.parseInt(st.nextToken()); // 끝점
            t = Integer.parseInt(st.nextToken()); // 소요시간
            road[s].add(new Node(e, t));
        }

        // 로직
        INF = Integer.MAX_VALUE;
        int[] comeback = dijkstra(x); // 파티 후 돌아오는 최소비용
        int max = 0, temp;
        for(int i=1; i<n+1; i++){ // 출발점이 각 학생의 마을
            temp = toParty(i); // 각 학생의 마을에서 파티마을까지 최소비용
            max = Math.max(temp + comeback[i], max);
        }

        // 출력
        System.out.println(max);
    }
    
    public static int toParty(int start){ // 파티로 가는 최소 비용
        return dijkstra(start)[x];
    }

    public static int[] dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, 0));

        int dis[] = new int[n+1];
        Arrays.fill(dis, INF);
        dis[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(dis[now.idx] < now.cost) continue;

            for(Node next : road[now.idx]) {
                int cost = next.cost + dis[now.idx];
                if(dis[next.idx] > cost) {
                    dis[next.idx] = cost;
                    pq.add(new Node(next.idx, cost));
                }
            }
        }
        return dis;
    }

    static class Node{
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}