import java.util.*;
class Solution {
    public String solution(String s) {
        Character[] ch = s.chars()
                .mapToObj(c -> (char) c)
                .toArray(Character[]::new);
        Arrays.sort(ch, Collections.reverseOrder());
        System.out.println(Arrays.toString(ch));
        StringBuilder answer = new StringBuilder();
        for(int i=0; i<ch.length; i++){
            answer.append(ch[i]);
        }
        return answer.toString();
    }
}