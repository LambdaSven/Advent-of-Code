#lang racket
(struct line(o const start end))
(define input(file->lines "input.txt"))
(define w1 (string-split (list-ref input 0) " "))
(define w2 (string-split (list-ref input 1) " "))

;THE FOLLOWING MUST BE DEFINED
; A) CYCLE THROUGH LIST 1
; 1 - GET DIRECTION
; 2 - CONSTRUCT NEW #<line>
; 3 - ADD LINE TO DESTINATION LINE

; B) CYCLE THROUGH LIST 2
; 1 - GET DIRECTION
; 2 - CHECK FOR INTERSECTION
; 3 - ADD INTERSECTION TO SOME LIST OF INTERSECTIONS

(define (getW1 w1 dest)
  (define (getDir s)
    (string-ref s 0))

  (define (getNum s)
    (string->number (substring s 1)))

  ;TODO - CONSTRUCT CONST VALUE INTO LINE
  (define (lineConstructor dir n prevn const)
    (cond ((char=? dir #\R) (line #\H prevn (+ prevn n) const))
          ((char=? dir #\L) (line #\H prevn (- prevn n) const))
          ((char=? dir #\U) (line #\V prevn (+ prevn n) const))
          ((char=? dir #\D) (line #\V prevn (- prevn n) const))))
  (define (iter src dest x y)
    (if (empty? src)
        dest
        (if (or (char=? (getDir (car src)) #\R) (char=? (getDir (car src)) #\L))
          (iter (cdr src) 
                (append dest (lineConstructor 
                  (getDir (car src))
                  (getNum (car src))
                  x y)))
          
          (iter (cdr src) 
                (append dest (lineConstructor
                  (getDir (car src))
                  (getNum (car src))
                  y x))))))
    (iter w1 dest 0 0))
                                                          
