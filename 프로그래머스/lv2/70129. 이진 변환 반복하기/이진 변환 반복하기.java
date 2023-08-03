import java.util.*;
import java.io.*;

class Solution {
    int count, remove;
    public int[] solution(String s) {
        change(s);
        int[] ans = {count, remove};
        return ans;
    }
    
    public void change(String s){
        if(s.equals("1")){
            return;
        }
        
        count++;
        
        int cnt = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '1') cnt++;
        }
        remove += s.length() - cnt;
        String result = Integer.toBinaryString(cnt);
        change(result);
    }
}