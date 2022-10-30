package onboarding;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {

  @Nested
  class Problem1Test {

    @Test
    void case1() {
      List<Integer> pobi = List.of(97, 98);
      List<Integer> crong = List.of(197, 198);
      int result = 0;
      assertThat(Problem1.solution(pobi, crong)).isEqualTo(result);
    }

    @Test
    void case2() {
      List<Integer> pobi = List.of(131, 132);
      List<Integer> crong = List.of(211, 212);
      int result = 1;
      assertThat(Problem1.solution(pobi, crong)).isEqualTo(result);
    }

    @Test
    void case3() {
      List<Integer> pobi = List.of(99, 102);
      List<Integer> crong = List.of(211, 212);
      int result = -1;
      assertThat(Problem1.solution(pobi, crong)).isEqualTo(result);
    }
  }

  @Nested
  class Problem2Test {

    @Test
    void case1() {
      String cryptogram = "browoanoommnaon";
      String result = "brown";
      assertThat(Problem2.solution(cryptogram)).isEqualTo(result);
    }

    @Test
    void case2() {
      String cryptogram = "zyelleyz";
      String result = "";
      assertThat(Problem2.solution(cryptogram)).isEqualTo(result);
    }
    @Test
    void case3() {
      String result = "rown";
      String cryptogram = "bbbrowooooon";

      assertThat(Problem2.solution(cryptogram)).isEqualTo(result);
    }
//    @Test
//    void case3() {
//      String cryptogram = "abc QWe";
//      String result = "";
//      assertThat(Problem2.solution(cryptogram)).isEqualTo(result);
//    }
  }

  @Nested
  class Problem3Test {

    @Test
    void case1() {
      int number = 13;
      int result = 4;
      assertThat(Problem3.solution(number)).isEqualTo(result);
    }
    @Test
    void case2() {
      int number = 33;
      int result = 14;
      assertThat(Problem3.solution(number)).isEqualTo(result);
    }
    @Test
    void case3() {
      int number = 9999;
      int result = 12000;
      assertThat(Problem3.solution(number)).isEqualTo(result);
    }
    @Test
    void case4() {
      int number = 1;
      int result = 0;
      assertThat(Problem3.solution(number)).isEqualTo(result);
    }
//
//    @Test
//    void case5() {
//      int number = 0;
//      int result = 0;
//      assertThat(Problem3.solution(number)).isEqualTo(result);
//    }
  }

  @Nested
  class Problem4Test {

    @Test
    void case1() {
      String word = "I love you";
      String result = "R olev blf";
      assertThat(Problem4.solution(word)).isEqualTo(result);
    }
  }

  @Nested
  class Problem5Test {

    @Test
    void case1() {
      int money = 50_237;
      List<Integer> result = List.of(1, 0, 0, 0, 0, 2, 0, 3, 7);
      assertThat(Problem5.solution(money)).isEqualTo(result);
    }

    @Test
    void case2() {
      int money = 15_000;
      List<Integer> result = List.of(0, 1, 1, 0, 0, 0, 0, 0, 0);
      assertThat(Problem5.solution(money)).isEqualTo(result);
    }
  }

  @Nested
  class Problem6Test {
    @Test
    void case1() {
      List<List<String>> forms = List.of(
          List.of("jm@email.com", "제"),
          List.of("jason@email.com", "제이슨"),
          List.of("woniee@email.com", "워니"),
          List.of("mj@email.com", "엠제이"),
          List.of("nowm@email.com", "이제엠")
      );
      List<String> result = List.of("jason@email.com", "jm@email.com", "mj@email.com");
      assertThat(Problem6.solution(forms)).isEqualTo(result);
    }

    @Test
    void noOneMatches() {
      List<List<String>> forms = List.of(
          List.of("jm@email.com", "일일"),
          List.of("jason@email.com", "일이"),
          List.of("woniee@email.com", "일삼"),
          List.of("mj@email.com", "일사"),
          List.of("nowm@email.com", "일오")
      );
      List<String> result = List.of();
      assertThat(Problem6.solution(forms)).isEqualTo(result);
    }

    @Test
    void noCrew() {
      List<List<String>> forms = List.of();
      List<String> result = List.of();
      assertThat(Problem6.solution(forms)).isEqualTo(result);
    }

    @Test
    void emailLength() {
      List<List<String>> forms = List.of(
          List.of("j123456789789789@email.com", "제이엠"),
          List.of("jason@email.com", "제이슨"),
          List.of("woniee@email.com", "워니"),
          List.of("mj@email.com", "엠제이"),
          List.of("nowm@email.com", "이제엠")
      );
      List<String> result = List.of("jason@email.com", "j123456789789789@email.com", "mj@email.com");
      assertThat(Problem6.solution(forms)).isEqualTo(result);
    }

    @Test
    void emailForm() {
      List<List<String>> forms = List.of(
          List.of("@email.com", "제이엠"),
          List.of("jason@email.com", "제이슨"),
          List.of("woniee@email.com", "워니"),
          List.of("mj@email.com", "엠제이"),
          List.of("nowm@email.com", "이제엠")
      );
      List<String> result = List.of("jason@email.com", "j@email.com", "mj@email.com");
      assertThat(Problem6.solution(forms)).isEqualTo(result);
    }

    @Test
    void emailForm2() {
      List<List<String>> forms = List.of(
          List.of("jm@naver.com", "제이엠"),
          List.of("jason@email.com", "제이슨"),
          List.of("woniee@email.com", "워니"),
          List.of("mj@email.com", "엠제이"),
          List.of("nowm@email.com", "이제엠")
      );
      List<String> result = List.of("jason@email.com", "j@naver.com", "mj@email.com");
      assertThat(Problem6.solution(forms)).isEqualTo(result);
    }

    @Test
    void emailForm3() {
      List<List<String>> forms = List.of(
          List.of("@email.com@naver.com", "제이엠"),
          List.of("jason@email.com", "제이슨"),
          List.of("woniee@email.com", "워니"),
          List.of("mj@email.com", "엠제이"),
          List.of("nowm@email.com", "이제엠")
      );
      List<String> result = List.of("jason@email.com", "@email.com@naver.com", "mj@email.com");
      assertThat(Problem6.solution(forms)).isEqualTo(result);
    }

    @Test
    void nicknameKorNum() {
      List<List<String>> forms = List.of(
          List.of("jm@email.com", "제2엠"),
          List.of("jason@email.com", "제2슨"),
          List.of("woniee@email.com", "워니"),
          List.of("mj@email.com", "엠제2"),
          List.of("nowm@email.com", "이제엠")
      );
      List<String> result = List.of("jason@email.com", "jm@email.com", "mj@email.com");
      assertThat(Problem6.solution(forms)).isEqualTo(result);
    }

    @Test
    void nicknameKorEng() {
      List<List<String>> forms = List.of(
          List.of("jm@email.com", "제e엠"),
          List.of("jason@email.com", "제e슨"),
          List.of("woniee@email.com", "워니"),
          List.of("mj@email.com", "엠제e"),
          List.of("nowm@email.com", "이제엠")
      );
      List<String> result = List.of("jason@email.com", "jm@email.com", "mj@email.com");
      assertThat(Problem6.solution(forms)).isEqualTo(result);
    }
  }

  @Nested
    class Problem7Test {
        @Test
        void case1() {
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
            List<String> result = List.of("andole", "jun", "bedi");
            assertThat(Problem7.solution(user, friends, visitors)).isEqualTo(result);
        }
    }
}
