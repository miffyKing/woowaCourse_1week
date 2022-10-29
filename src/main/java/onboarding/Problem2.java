package onboarding;

import java.util.Stack;

public class Problem2 {

  public static String solution(String cryptogram) {
    String answer = "answer";
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < cryptogram.length(); i++) {
      if (stack.empty() == true) {
        stack.push(cryptogram.charAt(i));
        continue;
      }
      if (stack.peek() == cryptogram.charAt(i)) {
        stack.pop();
      } else if (stack.peek() != cryptogram.charAt(i)) {
        stack.push(cryptogram.charAt(i));
      }
    }
    StringBuilder sb = new StringBuilder();
    int size = stack.size();
    for (int i = 0; i < size; i++) {
      char chunk = stack.pop();
      sb.append(chunk);
    }
    sb.reverse();
    answer = sb.toString();
    return answer;
  }
}
