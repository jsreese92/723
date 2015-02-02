(*
  
  SSTACK: bounded stack

  When you push an item on a full stack, it "mashes" the bottom 
  item out of the stack to make room at the top

  new:    int           --> SSTACK
  push:   SSTACK x int  --> SSTACK
  pop:    SSTACK        --> SSTACK
  top:    SSTACK        --> int
  size:   SSTACK        --> int
  max:    SSTACK        --> int
  empty:  SSTACK        --> bool
  full:   SSTACK        --> bool

*)

datatype SSTACK =
  New of int
  | push of SSTACK * int
  ;

fun empty (New(n)) = true
  | empty (push(S,i)) = false
  ;

fun max (New(n)) = n
  | max (push(S,i)) = max(S)
  ;

fun size (New(n)) = 0
  | size(push(S,i)) = if size(S) = max(S)
                      then max(S)
                      else size(S) + 1
  ;

fun full (New(n)) = n=0
  | full (push(S,i)) = if size(S) >= max(S) - 1
                        then true
                        else false
  ;

(* defined to squash the stack *)
fun rem (New(n)) = New(n)
  | rem (push(S,i)) = if empty(S) 
                      then S 
                      else push(rem(S),i)
  ;

fun pop(New(n)) = New(n)
  | pop(push(S,i)) = if full(S)
                      then pop(push(rem(S),i))
                      else S
  ;

exception emptyStack;

fun top (New(n)) = raise emptyStack
  | top(push(S,i)) = i
  ;

val S = New(3);
val S1 = push(S,0);
val S2 = push(S1,1);
val S3 = push(S2,2); (*  [0,1,2] *)
top(S3); (* 2 *)
full(S3);  (* true *)
val rem1 = pop(S3); (* [0,1]  *)
top(rem1); (* 1 *)
full(rem1); (* false *)
val S4 = push(rem1,3); (* [0,1,3] *)
top(S4); (* 3 *)
full(S4); (* true *)
val S5 = push(S4,4); (* [1,3,4] *)
top(S5); (* 4 *)
val S6 = pop(pop(S5)); (* [1] *)
top(S6); (* 1 *)
val S7 = pop(S6); (*[]*)
empty(S7); (*true*)
