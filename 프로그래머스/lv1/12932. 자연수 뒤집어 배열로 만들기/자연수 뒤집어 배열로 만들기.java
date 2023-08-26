import java.util.*;
class Solution {
    public int[] solution(long n) {
        char[] ch = Long.toString(n).toCharArray();
        int[] answer = new int[ch.length];
        for(int i=ch.length-1; i>=0; i--){
            answer[ch.length-i-1] = ch[i] - '0';
        }
        return answer;
    }
}