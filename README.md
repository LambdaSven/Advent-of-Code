# Advent of code 2019

I will post my solutions to the 2019 Advent of code here, as well as post explaination for specific choices made
I will try to make all solutions generally applicable, to be functional with arbitrary inputs, [mostly] regardless of length. As long as the input follows the rules of the problem given, these solutions should work.

Every program will be in C using the standard library. If I need to use extra header files they will all be provided and built myself.

# Day 1
__Part 1__

Simple enough, I pass the input through the command line and do the arithmetic required.

__Part 2__

I felt like a recursive approach made sense here, so that's the approach I took. Again, a simple problem, but it is still only day 1 after all.

__Post Completion Clean Up__

For some reason in my original solution, I did `i /= 3` and `i -= 2` as two seperate expressions. I simplified it to `i = i/3 - 2`

# Day 2
__Part 1__

Good practice for Malloc and pointers, though in retrospect I really didn't need dedicated functions for addition and multiplication. It felt right at the time to do it that way, more in the spirit of making an Intcode computer

__Part 2__

Not much had to be changed here, In retrospect I would have saved some performance by instead of reading the file every time simply copying `*arr` to `*arr_original` or something along those lines, but I think the performance benefits wouldn't be very noticable. 

__Post Completion Clean up__

Most of the cleaning up I did post initial completion revolved around the phrase `add(ip + *(ip+i+1), ip + *(ip+i+2), ip + *(ip+i+3));` that was in the original code. I went through a few revisions deciding how to alter it, and eventually decided on making `ip` an actual pointer, and finalize the expression as `add(program[*(ip+1)], program[*(ip+2)], program + *(ip + 3))`.

I also cleaned up the while loop in `readFile()`. Instead of using a sentinel I use the return value of `fscanf` itself, terminating only when it no longer is able to parse the input in the format `%d,`. 
