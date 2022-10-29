package onboarding;

class Num {
  private int num;
  private static final int minLimit = 1;
  private static final int maxLimit = 10000;

  Num(int num) {
    rangeValidityCheck(num);
    this.num = num;
  }
  private void rangeValidityCheck(int num) {
    if (num < minLimit || num > maxLimit) {
      throw new IllegalArgumentException("입력값이 범위를 넘어있습니다..");
    }
  }
  public int returnAnswer() {
    int ansCnt = 0;
    for (int i = 0; i <= num; i++) {
        ansCnt += contain369(i);
    }
    return ansCnt;
  }
  private int contain369(int index) {
    String curNum = String.valueOf(index);
    int tmp = 0;
    if (curNum.contains("3") || curNum.contains("6") || curNum.contains("9")) {
      tmp += numOf369InString(curNum);
    }
    return tmp;
  }
  private int numOf369InString(String curNum) {
    int cnt = 0;
    for (int j = 0; j < curNum.length(); j++) {
      if (curNum.charAt(j) == '3' || curNum.charAt(j) == '6' || curNum.charAt(j) == '9') {
        cnt++;
      }
    }
    return cnt;
  }
}
public class Problem3 {

  public static int solution(int number) {
    Num num = new Num(number);
    return num.returnAnswer();
  }
}


