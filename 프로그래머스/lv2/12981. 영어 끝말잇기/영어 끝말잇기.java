import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int n, String[] words) {
        String prev, cur;
        int index = -1, prevsize, nextsize;
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        for(int i=1; i<words.length; i++){
            prev = words[i-1];
            cur = words[i];
            prevsize = set.size();
            set.add(cur);
            nextsize = set.size();
            if(prevsize == nextsize || prev.charAt(prev.length()-1) != cur.charAt(0)){
                // 중복된 값을 넣은건지 확인 || 끝말을 이었는지 확인
                index = i;
                break;
            }            
        }
        if(index == -1) return new int[] {0, 0}; // 탈락자 발생X의 경우
        int[] answer = new int[2];
        answer[0] = index%n + 1;
        answer[1] = index/n + 1;
        return answer;
    }
}