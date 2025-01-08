#!/bin/bash

is_increasing() {
	local arg1="$1"
	local arg2="$2"

	if [ "$arg1" -lt "$arg2" ]; then
		echo 1
	else
		echo 0
	fi
}

check_predicate() {
	local diff="$1"
	local condition="$2"
	if ((condition == 1)); then
		# Predicate for row_incr == 1
		[ "$diff" -gt -1 ] || [ "$diff" -lt -3 ]
	else
		# Predicate for row_incr != 1
		[ "$diff" -lt 1 ] || [ "$diff" -gt 3 ]
	fi
}
solution_part1() {
	local nbr_safe_rows=0
	local is_safe=0
	while IFS=' ' read -r -a row || [ -n "${row[*]}" ]; do
		row_incr=$(is_increasing "${row[0]}" "${row[1]}")
		is_safe=1

		for ((i = 0; i < ${#row[@]} - 1; i++)); do
			diff=$((row[i] - row[i + 1]))
			if check_predicate "$diff" "$row_incr"; then
				is_safe=0
				break
			fi
		done

		nbr_safe_rows=$((nbr_safe_rows + is_safe))
	done <"input.txt"
	echo $nbr_safe_rows
}

solution_part2() {
	local nbr_safe_rows=0
	while IFS=' ' read -r -a row || [ -n "${row[*]}" ]; do
		row_incr=$(is_increasing "${row[0]}" "${row[1]}")
		is_safe=1
		for ((i = 0; i < ${#row[@]} - 1; i++)); do
			break_outer_loop=0
			diff=$((row[i] - row[i + 1]))
			if check_predicate "$diff" "$row_incr"; then
				unset "row[$i]"
				new_row=("${row[@]}")
				row_incr=$(is_increasing "${new_row[0]}" "${new_row[1]}")
				for ((j = i; j < ${#new_row[@]} - 1; j++)); do
					diff=$((new_row[j] - new_row[j + 1]))
					if check_predicate "${diff}" "$row_incr"; then
						is_safe=0
						break_outer_loop=1
						break
					fi
				done
			fi
			if ((break_outer_loop == 1)); then
				break
			fi
		done
		nbr_safe_rows=$((nbr_safe_rows + is_safe))
	done <"input.txt"
	echo $nbr_safe_rows
}

if [ "$part" = "part2" ]; then
	solution_part2
else
	solution_part1
fi

printf "%s\n" "$sum"
