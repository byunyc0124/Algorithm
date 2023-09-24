import java.util.*;
class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        for(int i=0; i<d.length; i++){
            if(budget - d[i] < 0) break;
            answer++;
            budget -= d[i];
        }
        return answer;
    }
}