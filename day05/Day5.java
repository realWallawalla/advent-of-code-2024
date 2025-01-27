import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Day5 {
    public static void main(String[] args) throws IOException {
        final List<String> rules = new ArrayList<>();
        final List<String> updates = new ArrayList<>();

        try(var lineStream = Files.lines(Path.of("day05/input.txt"))) {
            List<String> lines = lineStream.toList();
            int breakIndex = 0;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).isEmpty()) {
                    breakIndex = i;
                    break;
                } else {
                    rules.add(lines.get(i));
                }
            }
            for (int i = breakIndex + 1; i < lines.size(); i++) {
                updates.add(lines.get(i));
            }
        } catch (IOException e) {
            System.out.println("could not read input file");
            return;
        }

        String part = System.getenv("part") == null ? "part" : System.getenv("part");
        System.out.println(
                part.equals("part2") ? new Day5().getPart2(rules, updates) : new Day5().getPart1(rules, updates));
    }

    private int getPart1(List<String> rules, List<String> updates) {
        Map<String, Set<Integer>> sortedRules = new HashMap<>();
        AtomicInteger sumOfMiddleNbr = new AtomicInteger();
        AtomicBoolean updateOk = new AtomicBoolean(false);
        for (String s : rules)
         {
            String[] split = s.split("\\|");
            sortedRules.computeIfAbsent(split[0], _k -> new HashSet<>()).add(Integer.valueOf(split[1]));
         };

        updates.forEach(update -> {
            String[] pages = update.split(",");
            for (int i = 0; i< pages.length; i++) {
                String page = pages[i];
                Set<Integer> afterPages = sortedRules.get(page);
                if (afterPages == null) {
                   continue;
                }
                for (int j = i + 1; j < pages.length - 1; j++) {
                    updateOk.set(afterPages.contains(Integer.valueOf(pages[j])));
                    if (!updateOk.get()) {
                          }
                }

            }
            if (updateOk.get()) {
                int midIndex = pages.length/2;
                sumOfMiddleNbr.addAndGet(Integer.parseInt(pages[midIndex]));
            }
        });
        return sumOfMiddleNbr.get();
    }

    private int getPart2(List<String> rules, List<String> updates) {
        return 0;
    }
}
