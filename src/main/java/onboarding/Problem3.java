package onboarding;

public class Problem3 {

  public static int solution(int number) {
    int answer = 0;

    //예외처리 할 것?
    //1. 숫자가 1~10000인가?
    if (exceptionCheck(number) == -1) {
      System.out.println("Input is not valid");
    }
    for (int i = 1; i <= number; i++) {
      String curNum = String.valueOf(i);

      if (curNum.contains("3") || curNum.contains("6") || curNum.contains("9")) {
        for (int j = 0; j < curNum.length(); j++) {
          if (curNum.charAt(j) == '3' || curNum.charAt(j) == '6' || curNum.charAt(j) == '9') {
            answer++;
          }
        }
      }
    }
    //System.out.printf("answer is %d\n", ansCnt);
    return answer;
  }

  public static int exceptionCheck(int n) {
    if (n <= 0 || n > 10000) {
      return -1;
    }
    return 1;
  }
}
