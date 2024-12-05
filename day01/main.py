from os import environ
from typing import List


def get_solution_part1(list1: List[int], list2: List[int]) -> int:
    list1.sort()
    list2.sort()
    sum_of_diffs: int = 0
    for i in range(0, len(list1)):
        sum_of_diffs = sum_of_diffs + abs(list2[i] - list1[i])
    return sum_of_diffs


def get_solution_part2(list1: List[int], list2: List[int]) -> int:
    list1.sort()
    list2.sort()
    count_num = {}
    sum_sim_score = 0
    for i in range(0, len(list2)):
        if list2[i] in count_num:
            count_num[list2[i]] = count_num[list2[i]] + 1
        else:
            count_num[list2[i]] = 1

    for i in range(0, len(list1)):
        sum_sim_score = sum_sim_score + list1[i] * count_num.get(list1[i], 0)

    return sum_sim_score


if __name__ == "__main__":
    input = open("input.txt").read().splitlines("\n")
    list1: List[int] = []
    list2: List[int] = []
    for s_arr in input:
        strnums = s_arr.split()
        list1.append(int(strnums[0]))
        list2.append(int(strnums[1]))
    answer = (
        get_solution_part2(list1, list2)
        if environ.get("part") == "part2"
        else get_solution_part1(list1, list2)
    )
    print(answer)
