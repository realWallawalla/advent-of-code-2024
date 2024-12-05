# This year im testing to do the puzzles in bash, I regret nothing

# How to build

    docker build -t aoc01:latest .

# How to run

The environment variable part specifies which part of the solution to run. part1=solution1, part2=solution2

    docker run -e part=part1 aoc01
