import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n) {
        int answer = n+1;
        String nBinary = Integer.toBinaryString(n), aBinary;
        int nCnt = 0, aCnt = 0;
        for(int i=0; i<nBinary.length(); i++){ // n의 이진수의 1의 개수
            if(nBinary.charAt(i) == '1') nCnt++;
        }
        while(true){
            aBinary = Integer.toBinaryString(answer);
            aCnt = 0;
            for(int i=0; i<aBinary.length(); i++){ // n의 이진수의 1의 개수
                if(aBinary.charAt(i) == '1') aCnt++;
            }
            if(nCnt == aCnt){
                return answer;
            }
            answer++;
        }
    }
}