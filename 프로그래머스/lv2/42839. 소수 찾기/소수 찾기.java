import java.util.*;
class Solution {    
    
    int[] nums, result;
    boolean[] visited;
    Set<Integer> numSet;
    
    public int solution(String numbers) {
        nums = new int[numbers.length()];
        visited = new boolean[numbers.length()];
        numSet = new HashSet<>();
        for(int i=0; i<numbers.length(); i++){
            nums[i] = numbers.charAt(i) - '0';
        }
        for(int i=1; i<=numbers.length(); i++){
            result = new int[i];
            permu(0, i);
        }
        Iterator<Integer> iter = numSet.iterator();
        int answer = 0;
        while(iter.hasNext()){
            int i = iter.next();
            System.out.println(i);
            if(isDecimal(i)) answer++;
        }
        return answer;
    }
    
    public void permu(int cnt, int r){
        if(cnt == r){
            int temp = 0;
            for(int i=0; i<result.length; i++){
                temp += result[i] * Math.pow(10, i);
            }
            numSet.add(temp);
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            result[cnt] = nums[i];
            permu(cnt+1, r);
            visited[i] = false;
        }
    }
    
    public boolean isDecimal(int num){
        if(num == 1 || num == 0) return false;
        for(int i=2; i<num; i++){
            if(num%i == 0) return false;
        }
        return true;
    }
}