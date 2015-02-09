(* 
 
  HOF: P X F X G --> R
  R:   int X el

  If P is greater than 1000 or less than -1000, F is applied to el. Else, G is applied to el

*)

fun HOF P F G = let fun R(x,y) = if P(x) > 1000 orelse P(x) < -1000
                                  then F(y)
                                  else G(y)
  in R 
  end;

fun sq(i) = i*i; (* square *)
fun du(i) = 2*i; (* double *)
fun qu(i) = 4*i; (* quadruple *)

val R = HOF sq du qu;
R(100, 5);
R(10, 5);
