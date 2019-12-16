#lang racket 

(define input (file->list "input.txt"))

(foldl (lambda (elem v)
        (+ v (- (floor(/ elem 3)) 2)))
    0
    input)
