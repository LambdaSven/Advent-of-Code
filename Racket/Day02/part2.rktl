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
    (cond ;check opcode at (list-ref program ip)
          ((= (list-ref program ip) 1) (run (add program ip) (+ ip 4)))
          ((= (list-ref program ip) 2) (run (mul program ip) (+ ip 4)))
          ((= (list-ref program ip) 99) program)))

  (run program ip))

(define (find_input program n v)
  ;sets address 1 and 2 to n and v, respsectively, runs the computer, and checks equality
  (if (= 
        (list-ref (computer (list-set (list-set program 1 n) 2 v) 0) 0) 
        19690720)
    (+ (* 100 n) v)
    (if (= n 99)
      (find_input program 0 (modulo (+ v 1) 100))
      (find_input program (+ n 1) v))))

(define (search program) (find_input program 0 0))

(search input)
