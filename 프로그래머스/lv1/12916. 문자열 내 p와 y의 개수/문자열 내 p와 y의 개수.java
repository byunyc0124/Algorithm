import java.util.*;

class Solution {
    boolean solution(String s) {
        int pCnt = 0, yCnt = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == 'P' || s.charAt(i) == 'p') pCnt++;
            if(s.charAt(i) == 'Y' || s.charAt(i) == 'y') yCnt++;
        }
        return true ? pCnt == yCnt : false;
    }
}