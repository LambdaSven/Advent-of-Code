#lang racket
;NOTE: input has been altered to replace ',' with ' '
;by using 'sed -i 's/,/\ /g' input.txt'
;if using this on your own input
;replace ',' with ' ' before running.
(define input(file->list "input.txt"))

(define (computer program ip)
    (define (add program ip)
        (list-set program (list-ref program (+ ip 3)) 
                          (+ (list-ref program (list-ref program (+ ip 1))) 
                             (list-ref program (list-ref program (+ ip 2))))))
    (define (mul program ip)
        (list-set program (list-ref program (+ ip 3)) 
                          (* (list-ref program (list-ref program(+ ip 1)))
                             (list-ref program (list-ref program (+ ip 2))))))
    (define (run program ip) 
        (cond ((= (list-ref program ip) 1) (run (add program ip) (+ ip 4)))
              ((= (list-ref program ip) 2) (run (mul program ip) (+ ip 4)))
              ((= (list-ref program ip) 99) program)))
    (run program ip))

(list-ref (computer (list-set (list-set input 1 12) 2 2) 0) 0)
