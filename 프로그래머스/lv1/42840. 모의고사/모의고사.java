import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] one = new int[] {1, 2, 3, 4, 5};
        int[] two = new int[] {2, 1, 2, 3, 2, 4, 2, 5};
        int[] thr = new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int one_cor = 0, two_cor = 0, thr_cor = 0;
        for(int i=0; i<answers.length; i++){
            if(answers[i] == one[i%5]) one_cor++;
            if(answers[i] == two[i%8]) two_cor++;
            if(answers[i] == thr[i%10]) thr_cor++;
        }
        System.out.println(one_cor+" "+two_cor+" "+thr_cor);
        int max_cor = Math.max(one_cor, Math.max(two_cor, thr_cor));
        List<Integer> list = new ArrayList<>();
        if(max_cor == one_cor) list.add(1);
        if(max_cor == two_cor) list.add(2);
        if(max_cor == thr_cor) list.add(3);
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) answer[i] = list.get(i);
        return answer;
    }
}