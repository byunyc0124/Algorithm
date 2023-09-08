class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for(int i=left; i<=right; i++){ // left ~ right 확인
            if(isDivisorOdd(i)) answer -= i; // 약수의 개수가 홀수면 빼기
            else answer += i; // 짝수면 더하기
        }
        return answer;
    }
    
    public boolean isDivisorOdd(int num){
        if(num == 1) return true;
        int cnt = 2; // 1과 자기자신
        for(int i=2; i<num/2+1; i++){ // 2부터 절반까지만 확인
            if(num%i == 0) cnt++;
        }
        return cnt%2==0 ? false : true;
    }
}