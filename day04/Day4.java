import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day4 {
  public static void main(String[] args) throws IOException {
    final List<char[]> strings;
    try(var lines = Files.lines(Path.of("day04/input.txt"))) {
      strings = lines.map(String::toCharArray).toList();
    } catch (IOException e) {
      System.out.println("could not read input file");
      return;
    }

    String part = System.getenv("part") == null ? "part2" : System.getenv("part");
    System.out.println(
            part.equals("part2") ? new Day4().getPart2(strings) : new Day4().getPart1(strings));
  }

  private int getPart1(List<char[]> input) {
    int sumOfXmas = 0;
    for (int i = 0; i < input.size(); i++) {
      char[] row = input.get(i);
      for (int j = 0; j < row.length; j++) {
        if (row[j] == 'X') {
          sumOfXmas = sumOfXmas + findAnyXmas(input, i, j);
        }
      }
    }
    return sumOfXmas;
  }

  private int findAnyXmas(List<char[]> input, int i, int j) {
    int maxY = input.size();
    int maxX = input.get(i).length;
    int sumOfXmas = 0;
    // horizontal
    if ((j + 3) < maxX && input.get(i)[j + 1] == 'M' && input.get(i)[j + 2] == 'A' && input.get(i)[j + 3] == 'S') {
     sumOfXmas = sumOfXmas + 1;
    }
    if ((j - 3) >= 0 && input.get(i)[j - 1] == 'M' && input.get(i)[j - 2] == 'A' && input.get(i)[j - 3] == 'S') {
      sumOfXmas = sumOfXmas + 1;
    }
    // vetical
    if ((i + 3) < maxY && input.get(i + 1)[j] == 'M' && input.get(i + 2)[j] == 'A' && input.get(i + 3)[j] == 'S') {
      sumOfXmas = sumOfXmas + 1;
    }
    if ((i -3) >= 0 && input.get(i - 1)[j] == 'M' && input.get(i - 2)[j] == 'A' && input.get(i - 3)[j] == 'S') {
      sumOfXmas = sumOfXmas + 1;
    }
    // diagonal down
    if ((i + 3) < maxY && (j+3) < maxX && input.get(i + 1)[j + 1] == 'M' && input.get(i + 2)[j + 2] == 'A' && input.get(i + 3)[j + 3] == 'S') {
      sumOfXmas = sumOfXmas + 1;
    }
    if ((i + 3) < maxY && (j-3) >= 0 && input.get(i + 1)[j - 1] == 'M' && input.get(i + 2)[j - 2] == 'A' && input.get(i + 3)[j -3] == 'S') {
      sumOfXmas = sumOfXmas + 1;
    }
    // diagonal up
    if ((i - 3) >= 0 && (j - 3) >= 0 && input.get(i - 1)[j - 1] == 'M' && input.get(i - 2)[j - 2] == 'A' && input.get(i - 3)[j -3] == 'S') {
      sumOfXmas = sumOfXmas + 1;
    }
    if ((i - 3) >= 0 && (j + 3) < maxX  && input.get(i - 1)[j + 1] == 'M' && input.get(i - 2)[j + 2] == 'A' && input.get(i - 3)[j + 3] == 'S') {
      sumOfXmas = sumOfXmas + 1;
    }
      return sumOfXmas;
  }

  private int getPart2(List<char[]> input) {
    int sumOfXmas = 0;
    for (int i = 0; i < input.size(); i++) {
      char[] row = input.get(i);
      for (int j = 0; j < row.length; j++) {
        if (row[j] == 'A') {
          sumOfXmas = sumOfXmas + findMasX(input, i, j);
        }
      }
    }
    return sumOfXmas;
  }

  private int findMasX(List<char[]> input, int i, int j) {
    int maxY = input.size();
    int maxX = input.get(i).length;
    int sumOfMasX = 0;
    // out of bounds, there is no masX
    if ((i + 1) >= maxY || (j+1) >= maxX || (i-1) < 0 || (j-1) < 0) {
      return 0;
    }
    // combination 1
    if ( input.get(i - 1)[j + 1] == 'M'
            && input.get(i + 1)[j - 1] == 'S'
            && (input.get(i - 1)[j - 1] == 'M' && input.get(i + 1)[j + 1] == 'S'
            || input.get(i - 1)[j - 1] == 'S' && input.get(i + 1)[j + 1] == 'M')
    ) {
      sumOfMasX = sumOfMasX + 1;
    }
    // comibnation 2
    else if (input.get(i - 1)[j + 1] == 'S'
            && input.get(i + 1)[j - 1] == 'M'
            && (input.get(i - 1)[j - 1] == 'M' && input.get(i + 1)[j + 1] == 'S'
            || input.get(i - 1)[j - 1] == 'S' && input.get(i + 1)[j + 1] == 'M')
    ) {
      sumOfMasX = sumOfMasX + 1;
    }


    return sumOfMasX;
  }
}