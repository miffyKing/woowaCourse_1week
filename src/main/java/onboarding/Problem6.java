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
