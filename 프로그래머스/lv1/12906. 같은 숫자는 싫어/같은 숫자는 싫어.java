import java.util.*;

// 순서 유지해야 하므로 set 불가능
// 먼저 들어간게 먼저 나와야 함 -> 큐 사용
public class Solution {
    public int[] solution(int []arr) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(arr[0]);
        int pre = arr[0];
        for(int i=1; i<arr.length; i++) {
            if(arr[i] == pre) continue;
            q.add(arr[i]);
            pre = arr[i];
        }
        int s = q.size();
        int[] answer = new int[s];
        for(int i=0; i<s; i++) answer[i] = q.poll();
        return answer;
    }
}