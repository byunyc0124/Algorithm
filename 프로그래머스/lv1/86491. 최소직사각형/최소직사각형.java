import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int max_w = 0, max_h = 0;
        for(int i=0; i<sizes.length; i++){
            if(sizes[i][0] > sizes[i][1]){
                max_w = Math.max(max_w, sizes[i][0]);
                max_h = Math.max(max_h, sizes[i][1]);
            }else{
                max_w = Math.max(max_w, sizes[i][1]);
                max_h = Math.max(max_h, sizes[i][0]);                
            }
        }
        return max_w * max_h;
    }
}