import java.util.*;

class Solution {
    
    int[] nums, visited; // 순열에 사용할 배열과 방문체크 배열
	List<int[]> permuList; // 모든 순열을 저장할 리스트
    
    public int solution(int k, int[][] dungeons) {
        nums = new int[dungeons.length]; // 각각의 순열을 저장
		visited = new int[dungeons.length]; // 방문 체크
		permuList = new ArrayList<int[]>();
		permu(0, dungeons.length); // 순열 시작
		int cur = k, answer = -1; // 현재 피로도와 결과
		for(int i=0; i<permuList.size(); i++) { // 구한 모든 순열 탐색
			cur = k; // 각 순열에 대해 피로도 초기화
            // cnt = 0;
			for(int j=0; j<dungeons.length; j++) { // 각 순열에 대한 모든 던전 탐색
				if(cur < dungeons[permuList.get(i)[j]][0]) { // 현재 피로도가 던전의 최소 필요도보다 작으면
					//answer = Math.max(answer, cnt); // 지금까지 돈 던전의 개수로 최대값 갱신
					break;
				}
				cur -= dungeons[permuList.get(i)[j]][1]; // 피로도 감소
                answer = Math.max(answer, j+1);
                // cnt++;
			}
		}
        return answer;
    }
    
    public void permu(int start, int end) { // 현재 만들고 있는 순열의 인덱스, 던전의 개수
		if(start == end) { // 던전의 개수만큼 순열을 만들었으면 종료
			permuList.add(Arrays.copyOf(nums, nums.length)); // 순열 저장
			return;
		}
		
		for(int i=0; i<end; i++) { 
			if(visited[i] == 1) continue; // 이미 체크되었으면 다른 던전번호 탐색
			visited[i] = 1; // 방문 체크
			nums[start] = i; // 저장
			permu(start+1, end); // 다음 순열 돌리러 감
			visited[i] = 0; // 방문 체크 해제
		}
	}
}