import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m, ans;
    static ArrayList<Integer>[] list;
    static int[] visited;

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        list = new ArrayList[n];
        for(int i=0; i<n; i++){
            list[i] = new ArrayList<>();
        }
        int from, to;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            list[from].add(to);
            list[to].add(from);
        }

        ans = 0;
        visited = new int[n];
        for(int i=0; i<n; i++){
            visited[i] = 1;
            dfs(i, 1);
            if(ans == 1){
                System.out.println(ans);
                return;
            }
            visited[i] = 0;
        }

        System.out.println(ans);
    }

    private static void dfs(int index, int cnt) {
        if(cnt == 5){
            ans = 1;
            return;
        }

        for(int i=0; i<list[index].size(); i++){
            if(visited[list[index].get(i)] == 0){
                visited[list[index].get(i)] = 1;
                dfs(list[index].get(i), cnt+1);
                visited[list[index].get(i)] = 0;
            }
        }
    }
}