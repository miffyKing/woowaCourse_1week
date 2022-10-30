package onboarding;

import java.util.Stack;

class Crypto {
  private String cryptogram;
  private static final int minLimit = 1;
  private static final int maxLimit = 1000;
  Crypto(String cryptogram) {
    lengthValidityCheck(cryptogram);
    lowercaseValidityCheck(cryptogram);
    this.cryptogram = cryptogram;
  }
  private void lengthValidityCheck(String cryptogram) {
    if (cryptogram.length() < minLimit || cryptogram.length() > maxLimit) {
      throw new IllegalArgumentException("암호문의 길이가 범위 밖입니다.");
    }
  }
  private void lowercaseValidityCheck(String cryptogram) {
    if (!cryptogram.equals(cryptogram.toLowerCase())) {
      throw new IllegalArgumentException("암호문에 영어 소문자가 아닌 문자가 있습니다.");
    }
  }
  private Stack<Character> stackMaker(Stack<Character> stack) {
    char tmp = 0;
    for (int i = 0; i < cryptogram.length(); i++) {
      if (stack.empty() == true) {
        if (tmp == cryptogram.charAt(i))
          continue;
        stack.push(cryptogram.charAt(i));
        continue;
      }
      if (stack.peek() == cryptogram.charAt(i)) {
        tmp = stack.pop();
      } else if (stack.peek() != cryptogram.charAt(i)) {
        if (tmp == cryptogram.charAt(i))
          continue;
        stack.push(cryptogram.charAt(i));
      }
    }
    return stack;
  }
  public String makeAnswer() {
    Stack<Character> stack = stackMaker(new Stack<>());
    StringBuilder sb = new StringBuilder();
    int size = stack.size();
    for (int i = 0; i < size; i++) {
      char chunk = stack.pop();
      sb.append(chunk);
    }
    sb.reverse();
    return sb.toString();
  }
}

public class Problem2 {
  public static String solution(String cryptogram) {
    Crypto cryptoClass = new Crypto(cryptogram);
    return cryptoClass.makeAnswer();
  }
}