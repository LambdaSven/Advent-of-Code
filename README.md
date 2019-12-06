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

Not much had to be changed here, In retrospect I would have saved some performance by instead of reading the file every time simply copying `*arr` to `*arr_original` or something along those lines, but I think the performance benefits wouldn't be very noticable as long as I `free()` the memory before performing `readFile` again. 

__Post Completion Clean up__

Most of the cleaning up I did post initial completion revolved around the phrase `add(ip + *(ip+i+1), ip + *(ip+i+2), ip + *(ip+i+3));` that was in the original code. I went through a few revisions deciding how to alter it, and eventually decided on making `ip` an actual pointer, and finalize the expression as `add(program[*(ip+1)], program[*(ip+2)], program + *(ip + 3))`.

I also cleaned up the while loop in `readFile()`. Instead of using a sentinel I use the return value of `fscanf` itself, terminating only when it no longer is able to parse the input in the format `%d,`. 

I switched to using `memcpy` so that the code looked nicer and was less prone to leaks

# Day 3

__Part 1__

This one was a struggle for me. I initially tried using a 2D array, but that was not going anywhere so I switched to using Structs to store all the points. I still have a ~2 minute runtime though. I think I could improve it by using `realloc` less not searching the entire `points` array every time I add a point.


__Part 2__

Once I had part 1 finished this one was really easy. Add an intersections Struct and record distances. Piece of cake. Maybe I could use that intersection struct as an idea to improve the performance of part 1 (and also this part. Same underlying code after all)

__Post Completion Clean up__

__TODO__: Make run faster, it is *abysmally* slow right now

  More specifically, remove the check for *every* node; rather. Simply add all nodes from wire 1 without question, and then only add nodes from wire 2 to the ints struct iff they are an intersection. 

# Day 4

__Part 1__

This was a really easy problem. Simply seperate out the digits (with `/` and `%`) and do the some basic logic.

__Part 2__

This solution relies on the probability of the out of bounds array values not being exactly equal to `num[j]`. I figure the probility is so low that it's not a problem.

# Day 5

__Part 1__

Yay! More Intcode computer! I love this problem. This did take me longer than expected though, mostly dealing with minor things involving the paramater modes.

__Part 2__

This was a pretty simple extension from part 1, once that was complete it was pretty simple to add more opcodes and alter the IP

# Day 6

__Part 1__

This was a pretty simple problem that just took a while to type. Essentially I create a tree of `struct planet`, where each planet holds it's own id and it's parents. I then go through every planet, working up the tree until I hit `COM`, adding up all the steps along the way.

__Part 2__

During the reading of the file and the creation of `struct planet* map` I store the index of `YOU` and `SAN` respectively. Then I work up from `YOU` and `SAN` until I hit `COM`, adding the distance traversed to each planet along the way in a new part of the struct. Then I go through every planet, finding adding up `planet.distSAN + planet.distYOU`. The smallest one is the correct answer.
