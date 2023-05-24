import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<absHeap> hp = new PriorityQueue<>(); // 최소 힙
        for(int i=0; i<n; i++){
            int x = Integer.parseInt(br.readLine());
            if(x != 0){ // x가 0이 아니라면 배열에 x값 추가
                hp.add(new absHeap(x, Math.abs(x)));
            }else{ // x가 0이라면 배열에서 절댓값이 가장 작은 값 출력 후 배열에서 제거
                if(hp.isEmpty()){ // 배열이 비어있다면 0을 출력
                    System.out.println(0);
                }else{
                    System.out.println(hp.poll().x);
                }
            }
        }
    }

    static class absHeap implements Comparable<absHeap> {
        int x;
        int absx;

        public absHeap(int x, int absx){
            this.x = x;
            this.absx = absx;
        }

        @Override
        public int compareTo(absHeap o) {
            if(this.absx == o.absx){
                return this.x - o.x;
            }
            return this.absx - o.absx;
        }
    }
}