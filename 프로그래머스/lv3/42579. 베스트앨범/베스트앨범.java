import java.util.*;
import java.util.stream.Stream;

class Solution {
    
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> hash = new HashMap<>();
        for(int i=0; i<genres.length; i++){ // 가장 많이 재생된 장르 가져오기
            if(hash.containsKey(genres[i])){ // 이미 저장된 키(장르)라면
                hash.put(genres[i], hash.get(genres[i]) + plays[i]); // 재생횟수를 더해서 넣어줌
            }else hash.put(genres[i], plays[i]); // 아니라면 그냥 재생횟수를 넣어줌
        }
        List<String> keyList = new ArrayList<>(); // 많이 재생된 장르 순서대로 정렬될 리스트
        int max_genre = 0; // 속한 노래가 많이 재생된 장르
        String key = " "; // 장르
        while (hash.size() > 0){ // hashmap에서 하나씩 제거하면서 리스트에 추가
            max_genre = 0; // 최대값 - 재생횟수
            for(String i : hash.keySet()){
                if(max_genre < hash.get(i)){
                    max_genre = hash.get(i);
                    key = i;
                }
            }
            keyList.add(key); // 가장 많이 재생된 장르를 리스트에 추가하고
            hash.remove(key); // 해쉬에선 제거
        }

        List<Song> playList = null; // 각 장르별 노래 저장 -> 각 장르 별로 초기화
        StringBuilder sb = new StringBuilder(); // 결과 저장
        for (int i=0; i<keyList.size(); i++){ // 정렬된 장르 순서대로 반복문
            playList = new ArrayList<>();
            for(int j=0; j<genres.length; j++){
                if(genres[j].equals(keyList.get(i))){
                    playList.add(new Song(j, plays[j]));
                }
            }
            Collections.sort(playList); // 정렬 -> 재생횟수 내림차순 -> 고유번호 오름차순
            if(playList.size() >= 2){ // 해당 장르에 포함된 곡이 2개 이상이면
                for(int j=0; j<2; j++){ // 2개만 뽑아서 저장
                    sb.append(playList.get(j).index+" ");
                }
            }else{ // 1개라면 1개만 저장
                sb.append(playList.get(0).index+" ");
            }
        }

        return Stream.of(sb.toString().split(" ")).mapToInt(Integer::parseInt).toArray(); // StringBuilder -> String[] -> int[] 결과 출력
    }
    
    public class Song implements Comparable<Song> {
        int index; // 고유 번호
        int play; // 재생된 횟수
        public Song(int index, int play){
            this.index = index;
            this.play = play;
        }

        @Override
        public int compareTo(Song o) {
            if(this.play == o.play) return Integer.compare(this.index, o.index); // 재생횟수가 같다면 고유번호로 정렬(오름차순)
            return Integer.compare(o.play, this.play); // 재생횟수로 정렬(내림차순)
        }
    }
}