import java.util.*;
import java.io.*;

class Solution
{
    public int solution(String s)
    {
        Stack<Character> st = new Stack<>();
        char ch;
        for(int i=0; i<s.length(); i++){
            ch = s.charAt(i);
            if(st.isEmpty()){
                st.add(ch);
            }else{
                if(st.peek() == ch){
                    st.pop();
                }else{
                    st.add(ch);
                }
            }
        }
        if(st.isEmpty()) return 1;
        else return 0;
    }
}