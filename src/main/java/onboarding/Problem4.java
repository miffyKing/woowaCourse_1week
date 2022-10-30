package onboarding;

class Word {
  private String word;
  private static final int minLimit= 1;
  private static final int maxLimit= 1000;
  Word (String word) {
    lengthValidityCheck(word);
    this.word = word;
  }
  private void lengthValidityCheck(String word) {
    if (word.length() < minLimit || word.length() > maxLimit) {
      throw new IllegalArgumentException("암호문의 길이가 조건을 넘어섰습니다.");
    }
  }
  String getAnswer() {
    Classifier classifier = new Classifier(word);
    return classifier.getChangedString();
  }
}

class Classifier {

  private StringBuffer answer;
  private static final int upperSum = 'A'+'Z';
  private static final int lowerSum = 'a'+'z';
  private static final int isUpperCase = 0;
  private static final int isLowerCase= 1;
  private String classifier;
  Classifier (String word) {
    this.classifier = word;
  }
  String getChangedString() {
    StringBuilder sb = new StringBuilder();
    char chunk;
    for (int i = 0; i < classifier.length(); i++) {
      int classifyId = checkUpperLowerElse(classifier.charAt(i));
      sb.append(changeChunk(classifyId,classifier.charAt(i) ));
    }
    return sb.toString();
  }
  int checkUpperLowerElse(char chunk) {
    if (chunk >= 'a' && chunk <= 'z') {
      return isLowerCase;
    }
    if (chunk >= 'A' && chunk <= 'Z') {
      return isUpperCase;
    }
    return 2;
  }
  char changeChunk(int Id, char chunk) {
    if (Id == isLowerCase) {
      return (char)(lowerSum - chunk);
    }
    if (Id == isUpperCase) {
      return (char)(upperSum - chunk);
    }
    return chunk;
  }
}

public class Problem4 {
  public static String solution(String word) {
    Word word2 = new Word(word);
    return (word2.getAnswer());
  }
}
