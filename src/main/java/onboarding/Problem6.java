package onboarding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Problem6 {

  public static List<String> solution(List<List<String>> forms) {
    List<String> answer = List.of("answer");

    ////////////////
    HashSet<String> realAnswer = new HashSet<String>();

    HashSet<String> parsedTotalSetDown = new HashSet<String>(); //모든 부분문자열을 넣을, 아래방향으로 진행시킬 set
    HashSet<String> parsedTotalSetUp = new HashSet<String>(); //모든 부분문자열을 넣을, 위으로 진행시킬 set
    List<HashSet<String>> parseEachName = new ArrayList<HashSet<String>>(); // 별명별 부분문자열 set을 저장하는 배열

    for (int i = 0; i < forms.size(); i++) {
      //부분문자열 만드는거 어케 할까?
      HashSet<String> NameSubstring = new HashSet<String>();
      NameSubstring = getSubstring(forms.get(i).get(1));      // {"이메일", "별명"} 중 별명
      parseEachName.add(NameSubstring);
    }           // 부분문자열을 ParseEachName 배열에 저장하는데 성공함.
    for (int i = 0; i < parseEachName.size(); i++) {        // 위에서 아래로 내려가는 과정
      HashSet<String> hashEachName = new HashSet<String>();   // parseEachName에서 하나하나의 set
      hashEachName = parseEachName.get(i);
      Iterator<String> it = hashEachName.iterator();
      //int index = 0;
      for (String iter : hashEachName) {
        if (parsedTotalSetDown.contains(iter) == true) { // 있다는 뜻은, 저 i 인덱스의 메일이 중복된 새2끼란 뜻
          realAnswer.add(forms.get(i).get(0));            // . 두개 쓴거 없애야함.
        }
        parsedTotalSetDown.add(iter);
      }
    }
    for (int i = parseEachName.size() - 1; i >= 0; i--) {     // 밑에서 위로 올라가는 과정.
      HashSet<String> hashEachName = new HashSet<String>();
      hashEachName = parseEachName.get(i);
      Iterator<String> it = hashEachName.iterator();
      //int index = 0;
      for (String iter : hashEachName) {
        if (parsedTotalSetUp.contains(iter) == true) { // 있다는 뜻은, 저 i 인덱스의 메일이 중복된 새2끼란 뜻
          realAnswer.add(forms.get(i).get(0));            // . 두개 쓴거 없애야함.
        }
        parsedTotalSetUp.add(iter);
      }
    }
    answer = new ArrayList<>(realAnswer);

    ////////////////
    return answer;
  }

  public static HashSet<String> getSubstring(String s) {
    HashSet<String> subStrings = new HashSet<String>();
    for (int i = 2; i <= s.length(); i++) {
      for (int j = 0; j <= s.length() - i; j++) {
        String parsedS = s.substring(j, j + i);
        //  System.out.println(parsedS);
        subStrings.add(parsedS);
      }
    }
    return subStrings;
  }

}
