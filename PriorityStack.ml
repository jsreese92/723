(* 
 
  PSTACK: Priority stack

  Stack contains elements that are pairs, a value
  of some type and an integer priority (with 0 as
  a low priority)

  new:    int                 --> PSTACK
  push:   PSTACK x int x int  --> PSTACK 
  pop:    PSTACK              --> PSTACK
  size:   PSTACK              --> int
  empty:  PSTACK              --> bool
  topv:   PSTACK              --> int
  topp:   PSTACK              --> int

*)


datatype PSTACK =
  New of int
  | push of PSTACK * int * int
  ;

fun empty (New(n)) = true
  | empty (push(P,i,j)) = false
  ;

fun size (New(n)) = 0
  | size(push(P,i,j)) = size(P) + 1
  ;

exception emptyStack;

fun topp (New(n)) = raise emptyStack
  | topp(push(New(n),i,j)) = j
  | topp(push(P,i,j)) = if j >= topp(P)
                        then j
                        else topp(P)
  ;

fun topv (New(n)) = raise emptyStack
  | topv(push(New(n),i,j)) = i
  | topv(push(P,i,j)) = if j >= topp(P)
                        then i
                        else topv(P)
  ;

fun pop(New(n)) = New(n)
  | pop(push(New(m),i,j)) = New(m)
  | pop(push(P,i,j)) = if j >= topp(P) then P else push(pop(P), i, j)
  ;

val P = New(3);
val P1 = push(P, 1, 10);
val P2 = push(P1, 2, 10);
val P3 = push(P2, 3, 0); (* [(3,0)], [1,10], [2,10] *)
topv(P3); (* 2 *)
topp(P3); (* 10 *)
val P4 = pop(P3); (* [3,0], [1,10] *)
topv(P4); (* 1 *)
topp(P4); (* 10 *)
val P5 = push(P4, 4, 0); (* [3,0], [4,0], [1,10] *)
topv(P5); (* 1 *)
topp(P5); (* 10 *)
val P6 = pop(P5); (* [3,0], [4,0] *)
topv(P6); (* 4 *)
topp(P6); (* 0 *)
val P7 = pop(P6); (* [3,0] *)
topv(P7); (*3*)
topp(P7); (*0*)
