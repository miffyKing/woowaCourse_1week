package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.ls.LSOutput;

class Money {

  private int money;
  private int[] moneyList;// = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};
  private List<Integer> answer = new ArrayList<>();

  Money(int money, int[] moneylist) {
    rangeValidityCheck(money);
    this.money = money;
    this.moneyList = moneylist;
  }

  private void rangeValidityCheck(int money) {
    if (money < 1 || money > 1000000) {
      throw new IllegalArgumentException("금액의 범위가 틀립니다..");
    }
  }

  List<Integer> getAnswer() {
    for (int i = 0; i < moneyList.length; i++) {
      answer.add(money / moneyList[i]);
      money %= moneyList[i];
    }
    return answer;
  }
}

public class Problem5 {

  public static List<Integer> solution(int money) {
    List<Integer> answer = new ArrayList<Integer>();
    int[] moneyList = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};

    Money money1 = new Money(money, moneyList);
    return (money1.getAnswer());
  }
}
