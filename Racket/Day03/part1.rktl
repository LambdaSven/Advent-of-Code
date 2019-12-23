#lang racket
(struct line(o start end))
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

  (define (lineConstructor dir n prevn)
    (cond ((char=? dir #\R) (line #\H prevn (+ prevn n)))
          ((char=? dir #\L) (line #\H prevn (- prevn n)))
          ((char=? dir #\U) (line #\V prevn (+ prevn n)))
          ((char=? dir #\D) (line #\V prevn (- prevn n)))))
  
  (map ;??

(define (getout2 pair outlist)
  (if (pair? pair)
      (if (list? outlist)
          (getout2 (car pair) (append outlist (cdr pair)))
          (getout2 (car pair) (list outlist (cfr pair))))
      (append outlist pair)))


:

