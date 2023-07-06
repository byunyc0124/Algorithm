import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '(') st.add(s.charAt(i));
            else{
                if(st.size() == 0) return false;
                st.pop();
            }
        }
        return st.size()==0 ? true : false;
    }
}