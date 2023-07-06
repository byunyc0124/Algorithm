import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1-o2);
        for(int i=0; i<scoville.length; i++) pq.add(scoville[i]);
        int cur = pq.peek(), answer = 0, mixed;
        while(cur < K){
            if(pq.size() < 2) return -1;
            mixed = pq.poll();
            mixed += pq.poll() * 2;
            pq.add(mixed);
            cur = pq.peek();
            answer++;
        }
        return answer;
    }
}