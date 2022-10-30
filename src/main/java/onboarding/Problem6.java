package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

class Form {

  private List<List<String>> forms = new ArrayList<>();
  private final HashSet<String> realAnswer = new HashSet<String>();
  private final List<HashSet<String>> parseEachName = new ArrayList<HashSet<String>>(); // 별명별 부분문자열 set을 저장하는 배열

  Form(List<List<String>> form) {
    ValidityChecker vc = new ValidityChecker(form);
    this.forms = form;
  }

  List<String> getAnswer() {
    makeParseEachName();        //parseEachName 완성.
    findOverlap(0, parseEachName.size(), 1);
    findOverlap(parseEachName.size() - 1, -1, -1);     //이거 음수로 넘어가도 괜찮은지 꼭 확인. -> 두글자로 안잘리는 닉네임?
    System.out.println(realAnswer);
    return new ArrayList<>(realAnswer);
  }

  void makeParseEachName() {
    for (int i = 0; i < forms.size(); i++) {
      List<String> chunk = forms.get(i);
      HashSet<String> NameSubstring = getAllTwoWords(chunk.get(1));
      parseEachName.add(NameSubstring);
    }
  }

  HashSet<String> getAllTwoWords(String s) {
    HashSet<String> subStrings = new HashSet<String>();
    for (int i = 0; i < s.length() - 1; i++) {
      String parsedS = s.substring(i, i + 2);
      subStrings.add(parsedS);
    }
    return subStrings;
  }

  void findOverlap(int from, int to, int iterBy) {
    HashSet<String> parsedTotalSetDown = new HashSet<String>();
    for (int i = from; i != to; i = i + iterBy) {        // 위에서 아래로 내려가는 과정
      HashSet<String> hashEachName = new HashSet<String>();   // parseEachName에서 하나하나의 set
      hashEachName = parseEachName.get(i);
      Iterator<String> it = hashEachName.iterator();
      for (String iter : hashEachName) {
        if (parsedTotalSetDown.contains(iter)) { // 있다는 뜻은, 저 i 인덱스의 메일이 중복된 새2끼란 뜻
          realAnswer.add(forms.get(i).get(0));            // . 두개 쓴거 없애야함.
        }
        parsedTotalSetDown.add(iter);
      }
    }
  }
}
class ValidityChecker {

  ValidityChecker(List<List<String>> input) {
    emailValidityCheck(input);
    nicknameValidityCheck(input);
    wordLengthValidityCheck(input);
  }package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

  public class Prob7Prac {

    public static void main(String[] args) {
      String user = "mrko";
      List<List<String>> friends = List.of(
          List.of("donut", "andole"),
          List.of("donut", "jun"),
          List.of("donut", "mrko"),
          List.of("shakevan", "andole"),
          List.of("shakevan", "jun"),
          List.of("shakevan", "mrko")
      );
      List<String> visitors = List.of("bedi", "bedi", "donut", "bedi", "shakevan");

      //1. map을 이용해서 각 이름마다 친구들을 이어붙여준다. V
      //2. 전체 이름들이 담길 map을 만든다. 이때 value 값은 점수로 한다.       V
      //3. visitor 을 돌며 전체 이름이담길 map 에 넣어준다.    V
      //4. user의 친구이름들을 1.에서 만든 map에서 찾아 그 value들의 친구들의 점수를 증가시켜준다.
      //5. user의 친구들, user 자신을 빼고 정렬.

      HashMap<String, HashSet<String>> relationTable= new HashMap<>(); //도넛 - {안돌,준, 마코} 들 저장.
      HashMap <String, Integer> scoreTable = new HashMap<>();     // {도넛 - 20점} 저장
      for (int i = 0; i < friends.size(); i++) {
        System.out.println(friends.get(i).get(0));
        // 만약에 key가 없을 경우

        if (relationTable.containsKey(friends.get(i).get(0)) != true) {   // 도넛이 관계테이블의 키로 없을 떄
          HashSet <String> nameSet = new HashSet<>();
          nameSet.add(friends.get(i).get(1));
          relationTable.put(friends.get(i).get(0), nameSet);
        }
        if (relationTable.containsKey(friends.get(i).get(1)) != true) {   // 도넛이 관계테이블의 키로 없을 떄
          HashSet <String> nameSet = new HashSet<>();         //map에서 Value로 들어갈 set
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
        HashSet <String> userFriendsFriend = new HashSet<>();
        userFriendsFriend = relationTable.get(iter);        //마르코의 친구 도넛의 친구 리스트
        System.out.println(userFriendsFriend);
        for (String iter2 : userFriendsFriend) {
          int score = scoreTable.get(iter2);
          scoreTable.put(iter2, score+10);
        }
      }

      //5.  scoreTable 의 값을 확인하고, user과 이미 USER의 친구들을 제외한다.
      System.out.println(scoreTable);
      scoreTable.remove(user);
      for (String iter : relationTable.get(user)) {
        scoreTable.remove(iter);
      }

      //6. 정렬
      // Map.Entry 리스트 작성
      List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(scoreTable.entrySet());

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
      List<String> answer = new ArrayList<>();
      for (int i = 0; i < list_entries.size(); i++) {
        //answer.add(list_entries.get(i).getKey());
        //System.out.println(list_entries.get(i).getKey());
        answer.add(list_entries.get(i).getKey());
        if (i == 4)
          break;
      }
      System.out.println(answer);
      //return answer;
    }




  }

  private void emailValidityCheck(List<List<String>> input) {
    //@뒷부분에 email.com 만 있는가.
    //@앞부분이 숫자, 영어 소문자만 있는가
    for (List<String> strings : input) {
      String[] emailFront = strings.get(0).split("@");
      if (emailFront.length != 2) {   //@가 2개이상일때
        throw new IllegalArgumentException("이메일 양식 @@두개 를 만족하지 않습니다.");
      }
      if (!Pattern.matches("^[a-z0-9A-Z._-]*", emailFront[0])) {    //@앞이 영어,숫자등이 아닐때
        throw new IllegalArgumentException("이메일 양식을 만족하지 않습니다.");
      }
      if (!emailFront[1].equals("email.com")) { //@뒤가 email.com이 아닐때
        throw new IllegalArgumentException("이메일 양식을 만족하지 않습니다.");
      }
    }
  }
  private void nicknameValidityCheck(List<List<String>> input) {
    //닉네임은 한글만으로 되어있는가
    for (List<String> strings : input) {
      String Nickname = strings.get(1);
      if (!Pattern.matches("[가-힣]*$", Nickname)) {
        throw new IllegalArgumentException("닉네임이 한글이 아닙니다.");
      }
    }
  }
  private void wordLengthValidityCheck(List<List<String>> input) {
    //닉네임 길이가 1~20인가
    //이메일 11~20 미만
    //크루가 1~10000 이하인가?
    if (input.size() < 1 || input.size() > 10000) {
      throw new IllegalArgumentException("인원 수가 조건 범위 내에 있지 않습니다.");
    }
    for (List<String> strings : input) {
      String Nickname = strings.get(1);
      String Email = strings.get(0);
      if (Nickname.length() < 1 || Nickname.length() >= 20) {
        throw new IllegalArgumentException("닉네임의 길이가 조건 범위 내에 있지 않습니다.");
      }
      if (Email.length() < 11 || Email.length() >= 20) {
        throw new IllegalArgumentException("이메일의 길이가 조건 범위 내에 있지 않습니다.");
      }
    }
  }
}
public class Problem6 {
  public static List<String> solution(List<List<String>> forms) {
    Form form = new Form(forms);
    return form.getAnswer();
  }
}
