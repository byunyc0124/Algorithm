import java.util.*;
import java.io.*;
class Solution {
    public String solution(String s) {
        int center = s.length() / 2;
        StringBuilder answer = new StringBuilder();
        if(s.length() % 2 == 0) {
            answer.append(s.charAt(center-1));
            answer.append(s.charAt(center));
        }
        else answer.append(s.charAt(center));
        return answer.toString();
    }
}