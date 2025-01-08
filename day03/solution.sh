#!/bin/bash

check_for_mul() {
	local arg1="$1"
	if [ "$arg1" = "mul(" ]; then
		return 0
	else
		return 1
	fi
}

multiply_parsed_numbers() {
	# substirng is xxx,yyy), need to parse out number x and y. they can be 1-3 big.
	local arg1="$1"

	IFS=',' read -ra to_be_parsed <<<"$arg1"
	local part1="${to_be_parsed[0]}"
	local part2="${to_be_parsed[1]}"
	local part2_last_char=$((${#part2} - 1))
	echo "$part1"

	if (("${#part1}" > 3 || "${#part2}" > 4)) && [[ ${part2:$part2_last_char:1} == ')' ]]; then
		echo -1
		return
	fi
	# exxlude )
	part2="${part2:0:${#part2}-1}"

	echo "$part2"
	local is_x_ok=0
	local is_y_ok=0
	for ((j = 0; j < ${#part1}; j++)); do
		local char="${part1:$j:1}"
		if [[ $char =~ ^[0-9]$ ]]; then
			is_x_ok=1
		else
			is_x_ok=0
			break
		fi
	done
	for ((j = 0; j < ${#part2}; j++)); do
		local char="${part2:$j:1}"
		if [[ $char =~ ^[0-9]$ ]]; then
			is_y_ok=1
		else
			is_y_ok=0
			break
		fi
	done
	if [[ $is_y_ok == 1 && $is_x_ok == 1 ]]; then
		echo $((part1 * part2))
	else
		echo -1
	fi
}

solution_part1() {
	local mul_max_length=12
	local chars_to_open_parath=4
	local chars_to_numbers=$((chars_to_open_parath + 1))
	local sum_of_products=0
	local chars_to_end=$((mul_max_length - chars_to_open_parath))
	while IFS='' read -r row || [ -n "$row" ]; do
		for ((j = 0; j < ${#row}; j++)); do
			if [[ "${row:j:1}" == 'm' ]] && ((j + 3 < ${#row})); then
				local max_chars_from_j=$((j + 11))
				local substring="${row:j:max_chars_from_j}"
				local chars_to_parath="${substring:0:chars_to_open_parath}"
				if check_for_mul "${chars_to_parath}" && ((max_chars_from_j < ${#row})); then
					local product
					potential_nums="${substring:chars_to_numbers:chars_to_end}"
					echo "$potential_nums"
					product=$(multiply_parsed_numbers "${potential_nums}")
					multiply_parsed_numbers "${potential_nums}"
					if ((product == -1)); then
						continue
					else
						sum_of_products=$((sum_of_products + product))
					fi
				fi
			fi
		done
	done <"input.txt"
	echo "$sum_of_products"
}

solution_part2() {
	while IFS= read -r row || [ -n "${row[*]}" ]; do
		echo "hello, part2" >"$(tty)"
	done <"input.txt"
}

if [ "$part" = "part2" ]; then
	solution_part2
else
	solution_part1
fi

printf "%s\n" "$sum"
