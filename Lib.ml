(* 
 
  LIB (Library)
  new:                        --> LIB
  add:  LIB X BOOK            --> LIB
  rem:                        --> LIB
  cko:  LIB x BOOK x PERSON   --> LIB
  cki:  LIB x BOOK x PERSON   --> LIB
  wait: LIB x BOOK x PERSON   --> LIB
  off:  LIB x BOOK x PERSON   --> LIB
  has:  LIB x BOOK            --> boolean
  here: LIB x BOOK            --> int
  num:  LIB x BOOk            --> int

  new  makes a LIB with no books, no wait lists, etc.
  add  puts another copy of a book in the library
  rem  takes a copy out of the library
  cko  allows a person to check out the book
  cki  checks the book back in;
        if there is a person on wait list it does a cko to that person.
  wait puts person at the back of a waiting list for a book
  off  takes a person out of the waiting list for a book
  has  tells if a book exists in the Library (even if all copies are
        checked out
  here tells how many copies of a book are available for checking out
  num  tells how many copies total of a book exist in the library

*)

datatype LIB = 
  New
  | add of LIB * int 
  | cko of LIB * int * string
  | wait of LIB * int * string
  ;

fun has (New, b1) = false
  | has (add(L,b2), b1) = if b1 = b2
                            then true
                            else has(L,b1)
  | has (cko(L,b2,p1), b1) = has(L,b1)
  | has (wait(L,b2,p1), b1) = has(L,b1)
  ;

fun rem (New, b1) = New
  | rem(add(L,b2), b1) = if b1 = b2 
                          then L
                          else add(rem(L,b1),b2)
  | rem(cko(L,b2,p1), b1) = rem(L,b1)
  | rem(wait(L,b2,p1), b1) = rem(L,b1)
  ;

fun num (New, b1) = 0
  | num(add(L,b2), b1) = if b1 = b2
                          then num(L,b1) + 1
                          else num(L,b1)
  | num(cko(L,b2,p1), b1) = num(L,b1)
  | num(wait(L,b2,p1), b1) = num(L,b1)
  ;

fun here (New, b1) = 0
  | here(add(L,b2), b1) = if b1 = b2
                            then here(L,b1) + 1
                            else here(L,b1)
  | here(cko(L,b2,p1),b1) = if b1 = b2
                              then if here(L,b1) = 0
                                    then 0
                                    else here(L,b1) -1
                              else here(L,b1)
  | here(wait(L,b2,p1), b1) = here(L,b1)
  ;

fun off(New, b1, p1) = New
  | off(add(L,b2), b1, p1) = off(L,b1,p1)
  | off(cko(L,b2,p1), b1, p2) = off(L,b1,p2)
  | off(wait(L,b2,p1), b1, p2) = if p2 = p1
                                  then L
                                  else wait(off(L,b2,p1), b1, p2)
  ; 

(* helper function to get front of waiting list for a book *)
fun frontWL(New,i) = ""
  | frontWL(add(L,b2), b1) = frontWL(L,b1)
  | frontWL(cko(L,b2,p1), b1) = frontWL(L,b1)
  | frontWL(wait(L,b2,p1), b1) = if b1 = b2
                                  then p1
                                  else frontWL(L,b1)
  ;

fun cki (New, b1, p1) = New
  | cki(add(L,b2), b1, p1) = cki(L,b1,p1)
  | cki(cko(L,b2,p1), b1, p2) = if b1 = b2
                                  then if frontWL(L,b1) = "" (* wait list empty*)
                                        then L
                                        else cko(off(L,b1,p2), b1, p2)
                                  else cki(L,b1,p2)
  | cki(wait(L,b2,p1), b1, p2) = if b1 = b2 
                                  then if frontWL(L,b1) = ""
                                        then cki(cko(L,b1,p1), b1, p2)
                                        else wait(cki(L,b1,p2), b2, p1)
                                  else wait(cki(L,b1,p2), b2, p1)
  ;

val L = New;
val L = add (L, 1);
val L = add (L, 2);
val L = add (L, 2);
val L = add (L, 3);
val L = add (L, 3);
val L = add (L, 3);

here(L,1); (* 1 *)
num(L,1); (* 1 *)
here(L,2); (* 2 *)
num(L,2); (* 2 *)
here(L,3); (* 3 *)
num(L,3); (* 3 *)

val L = cko(L, 1, "A");
here(L,1); (* 0 *)
num(L,1); (* 1 *)

val L = cki(L, 1, "A");
here(L,1); (* 1 *)
num(L,1); (* 1 *)

val L = cko(L, 1, "A");
here(L,1); (* 0 *)
num(L,1); (* 1 *)

val L = wait(L, 1, "B");

val L = cki(L, 1, "A");
here(L,1); (* 0 because automatically checked out to B b/c of wait list *)
num(L,1); (* 1 *)

has(L, 1); (* true *)

