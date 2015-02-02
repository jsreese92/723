(* 

  Ring: bounded queue
  
  if the ring is full, and you add an item, then it 
  over-wries the oldest item

  new:    int         --> Ring
  add:    Ring x int  --> Ring  
  rem:    Ring        --> Ring 
  front:  Ring        --> int
  size:   Ring        --> int
  max:    Ring        --> int
  empty:  Ring        --> bool
  full:   Ring        --> bool

*)

datatype Ring =
  New of int
  | add of Ring * int
  ;

fun empty (New(n)) = true
  | empty (add(S,i)) = false
  ;

fun max (New(n)) = n
  | max (add(S,i)) = max(S)
  ;

fun size (New(n)) = 0
  | size(add(B,i)) = if size(B) = max(B)
                      then max(B)
                      else size(B) + 1
  ;

fun full (New(m)) = m=0
  | full (add(S,i)) = if size(S) >= max(S) - 1
                        then true
                        else false
  ;


fun rem (New(n)) = New(n)
  | rem (add(S,i)) = if empty(S) 
                      then S 
                      else add(rem(S),i)
  ;
    
exception emptyRing;

fun front (New(n)) = raise emptyRing
  | front(add(New(n),i)) = i
  | front(add(S,i)) = if full(S)
                        then front(add(rem(S),i))
                        else front(S)
  ;


(* test cases *)

val R = New(3);
val R1 = add(R, 1);
val R2 = add(R1, 2);
val R3 = add(R2, 3); (* [1,2,3] *)
front(R3); (* 1 *)
full(R3); (* true *)
val rem3 = rem(R3); (* [2,3] *)
front(rem3); (* 2 *)
full(rem3); (* false *)
val R4 = add(rem3, 4); (* [2,3,4] *)
front(R4); (* 2 *)
full(R4); (* true *)
val R5 = add(R4, 5); (*3, 4, 5 *)
front(R5); (* 3 *)
full(R5); (*true*)
