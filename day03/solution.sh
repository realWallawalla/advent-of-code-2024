#!/bin/bash

solution_part1() {
	while IFS=' ' read -r -a row || [ -n "${row[*]}" ]; do
		echo "hello"
	done <"input.txt"
	echo 0
}

solution_part2() {
	while IFS=' ' read -r -a row || [ -n "${row[*]}" ]; do
		echo "hello"
	done <"input.txt"
	echo $nbr_safe_rows
}

if [ "$part" = "part2" ]; then
	solution_part2
else
	solution_part1
fi

printf "%s\n" "$sum"
