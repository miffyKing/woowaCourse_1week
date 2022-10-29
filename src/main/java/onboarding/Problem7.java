package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

public class Problem7 {

  public static List<String> solution(String user, List<List<String>> friends,
      List<String> visitors) {
    List<String> answer = new ArrayList<>();

    HashMap<String, HashSet<String>> relationTable = new HashMap<>(); //도넛 - {안돌,준, 마코} 들 저장.
    HashMap<String, Integer> scoreTable = new HashMap<>();     // {도넛 - 20점} 저장
    for (int i = 0; i < friends.size(); i++) {
      // 만약에 key가 없을 경우
      if (relationTable.containsKey(friends.get(i).get(0)) != true) {   // 도넛이 관계테이블의 키로 없을 떄
        HashSet<String> nameSet = new HashSet<>();
        nameSet.add(friends.get(i).get(1));
        relationTable.put(friends.get(i).get(0), nameSet);
      }
      if (relationTable.containsKey(friends.get(i).get(1)) != true) {   // 도넛이 관계테이블의 키로 없을 떄
        HashSet<String> nameSet = new HashSet<>();         //map에서 Value로 들어갈 set
        nameSet.add(friends.get(i).get(0));                 //set에 친구 박고 map에 넣기
        relationTable.put(friends.get(i).get(1), nameSet);
      }
      // 이미 관계테이블에 이미 key가 있는 경우.
      relationTable.get(friends.get(i).get(0)).add(friends.get(i).get(1));
      relationTable.get(friends.get(i).get(1)).add(friends.get(i).get(0));
      ////최종적으로 관계 테이블 완성 (1.)
      scoreTable.put(friends.get(i).get(0), 0);
      scoreTable.put(friends.get(i).get(1), 0);
      /// 전체 이름과 점수를 담을 SCoreTable 에 이름들을 다 집어넣는다.
    }

    for (int i = 0; i < visitors.size(); i++) {
      if (scoreTable.containsKey(visitors.get(i)) == true) {
        int score = scoreTable.get(visitors.get(i));
        score++;
        scoreTable.put(visitors.get(i), score);
      }
      if (scoreTable.containsKey(visitors.get(i)) == false) {
        scoreTable.put(visitors.get(i), 1);
      }
    }                 //visitor 들 순회하며 찐따들 넣어주고, 이미 있는 이름이면 1 증가.

    //4. user의 친구이름들을 1.에서 만든 map에서 찾아 그 value들의 친구들의 점수를 증가시켜준다.

    for (String iter : relationTable.get(user)) {
      HashSet<String> userFriendsFriend = new HashSet<>();
      userFriendsFriend = relationTable.get(iter);        //마르코의 친구 도넛의 친구 리스트
      for (String iter2 : userFriendsFriend) {
        int score = scoreTable.get(iter2);
        scoreTable.put(iter2, score + 10);
      }
    }

    //5.  scoreTable 의 값을 확인하고, user과 이미 USER의 친구들을 제외한다.
    scoreTable.remove(user);
    for (String iter : relationTable.get(user)) {
      scoreTable.remove(iter);
    }

    //6. 정렬
    // Map.Entry 리스트 작성
    List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(
        scoreTable.entrySet());

    // 비교함수 Comparator를 사용하여 오름차순으로 정렬
    Collections.sort(list_entries, new Comparator<Entry<String, Integer>>() {
      // compare로 값을 비교
      public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
        // 내림 차순 정렬
        int res = obj2.getValue() - obj1.getValue();
        if (res == 0) {
          obj1.getKey().compareTo(obj2.getKey());
        }
        return res;
      }
    });

    for (int i = 0; i < list_entries.size(); i++) {
      answer.add(list_entries.get(i).getKey());
        if (i == 4) {
            break;
        }
    }
    return answer;
  }

}

