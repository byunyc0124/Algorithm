import java.util.*;
class Solution {
    public int solution(String s) {
        s += s;
        int answer = 0;
        Stack<Character> st = new Stack<>();
        int index = 0;
        for(int i=0; i<s.length()/2; i++){
            if(s.charAt(i) == ')' || s.charAt(i) == ']' | s.charAt(i) == '}') continue;
            st = new Stack<>();
            st.add(s.charAt(i));
            index = 1;
            while(index < s.length()/2){
                if(s.charAt(i+index) == ')'){ // 닫힌 괄호라면 pop
                    if(st.size() == 0){
                        st.add(')');
                        break;
                    }else{
                        if(st.peek() == '(') {
                            st.pop();
                        }else{
                            break;
                        }
                    }
                }else if(s.charAt(i+index) == ']'){ // 닫힌 괄호라면 pop
                    if(st.size() == 0){
                        st.add(')');
                        break;
                    }else{
                        if(st.peek() == '[') {
                            st.pop();
                        }else{
                            break;
                        }
                    }          
                }else if(s.charAt(i+index) == '}'){ // 닫힌 괄호라면 pop
                    if(st.size() == 0){
                        st.add(')');
                        break;
                    }else{
                        if(st.peek() == '{') {
                            st.pop();
                        }else{
                            break;
                        }
                    }               
                }else{ // 열린괄호라면 추가
                    st.add(s.charAt(i+index));
                }
                index++;
            }
            if(st.isEmpty()) answer++;
        }
        return answer;
    }
}