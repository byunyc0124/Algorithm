import java.util.*;
import java.io.*;
public class Solution {
    public int solution(int n) {
        int answer = 0;
        char[] ch = Integer.toString(n).toCharArray();
        for(int i=0; i<ch.length; i++){
            answer += ch[i] - '0';
        }
        return answer;
    }
}