import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day5 {
    public static void main(String[] args) throws IOException {
        final List<char[]> strings;
        try(var lines = Files.lines(Path.of("day05/input.txt"))) {
            strings = lines.map(String::toCharArray).toList();
        } catch (IOException e) {
            System.out.println("could not read input file");
            return;
        }

        String part = System.getenv("part") == null ? "part2" : System.getenv("part");
        System.out.println(
                part.equals("part2") ? new Day5().getPart2(strings) : new Day5().getPart1(strings));
    }

    private int getPart1(List<char[]> input) {
        return 0;
    }

    private int getPart2(List<char[]> input) {
        return 0;
    }


}
