import java.util.*;

class Solution {
    public long solution(long n) {
        char[] ch = Long.toString(n).toCharArray();
        Character[] ch1 = new Character[ch.length];
        for (int i = 0; i < ch.length; i++) {
           ch1[i] = ch[i];
        }
        Arrays.sort(ch1, Collections.reverseOrder());
        long answer = 0;
        for(int i=0; i<ch1.length; i++){
            answer += Math.pow(10, ch1.length-i-1) * (ch1[i] - '0');
        }
        return answer;
    }
}