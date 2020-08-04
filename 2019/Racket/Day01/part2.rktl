#lang racket 

(define input (file->list "input.txt"))
(define (calcFuel fuel) 
    (-(floor(/ fuel 3)) 2))

(define (calcFuelR fuel)
    (define x (calcFuel fuel))
    (if (> 0 x) 0 (+ x (calcFuelR x))))
 
(foldl (lambda(elem v)
    (+ v (calcFuelR elem)))
    0
    input)
