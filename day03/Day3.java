import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Day3 {
  public static void main(String[] args) throws IOException {
    final String input;
    try(var lines = Files.lines(Path.of("day03/input.txt"))) {
      input = lines.collect(Collectors.joining());
    } catch (IOException e) {
      System.out.println("could not read input file");
      return;
    }
    String part = System.getenv("part") == null ? "part2" : System.getenv("part");
    System.out.println(
            part.equals("part2") ? new Day3().getPart2(input) : new Day3().getPart1(input));
  }

  private int getPart1(String input) {
    int sumOfProducts = 0;
    CharacterIterator iterator = new CharacterIterator(input);
    while (iterator.hasNext()) {
      if (iterator.next() == 'm') {
        if (iterator.hasNext3chars() && iterator.next() == 'u' && iterator.next() == 'l' && iterator.next() == '(') {
          sumOfProducts = sumOfProducts + iterator.nextUntilNumbersExtracted();
        }
      }
    }
    return sumOfProducts;
  }

  private int getPart2(String input) {
    int sumOfProducts = 0;
    boolean isDo = true;
    for (int i = 0; i < input.length(); i++) {
      if (isDo && input.charAt(i) == 'm') {
        String mulStr = input.substring(i, i + 4);
        if (mulStr.equals("mul(")) {
          sumOfProducts = sumOfProducts + findProduct(input.substring(i+4, i + 12));
        }
      } else if (input.charAt(i) == 'd') {
        String doStr = input.substring(i, i + 4);
        String dontStr = input.substring(i, i + 7);
        if (doStr.equals("do()")) {
          isDo = true;
        }
        if (dontStr.equals("don't()")) {
          isDo = false;
        }
        System.out.println(isDo);
      }
    }
      return sumOfProducts ;
    }

  private int findProduct(String subStr) {
    String[] numStrs = subStr.split(",");
    if (numStrs.length != 2) {
      return 0;
    }
    String regex = "[0-9]+";
    String num1 = numStrs[0];
    String num2 = numStrs[1];
    int i = num2.indexOf(")");
    if (i != -1) {
      num2 = num2.substring(0, i);
    }
    if (!num1.matches(regex)) {
      return 0;
    }
    if (!num2.matches(regex)) {
      return 0;
    }
    return Integer.parseInt(num1) * Integer.parseInt(num2);
  }
}

class CharacterIterator implements Iterator<Character> {

  private final String str;
  private int pos = 0;

  public CharacterIterator(String str) {
    this.str = str;
  }

  public boolean hasNext() {
    return pos < str.length();
  }

  public boolean hasNext3chars() {
    return pos + 3 < str.length();
  }

  public int nextUntilNumbersExtracted() {
    String str1 = "";
    int counter1 = 0;
    while (true) {
      char num = str.charAt(pos++);
      counter1 += 1;
      if (Character.isDigit(num) && counter1 <= 3) {
        str1 = str1 + num;
      } else if (counter1 != 0 && num == ',') {
        break;
      } else {
        return 0; // invalid mul syntax
      }
    }
    String str2 = "";
    int counter2 = 0;
    while (true) {
      char num2 = str.charAt(pos++);
      counter2 += 1;
      if (Character.isDigit(num2) && counter2 <= 3) {
        str2 = str2 + num2;
      } else if (counter2 != 0 && num2 == ')') {
        break;
      } else {
        return 0; // invalild mul syntax
      }
    }
    return Integer.parseInt(str1) * Integer.parseInt(str2);
  }

  public Character next() {
    return str.charAt(pos++);
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }

  public boolean nextUntilDoOrDont() {
    String doOrDont = "";
    if (str.charAt(pos++) == 'o') {
      char nextChar = str.charAt(pos++);
      if (nextChar == '(' && str.charAt(pos++) == ')') {
        return true;
      } else if (nextChar == 'n') {

      }
    }
     
    System.out.println("after while: " + doOrDont);
    if (doOrDont.equals("o()")) {
      System.out.println("in dostring if");
      return true;
    } else if (doOrDont.equals("on'")) {
      
    }

    if (doOrDont.equals("on't()")) {
      System.out.println("in ont() if");
      return false;
    }
     return false;
    }
}
