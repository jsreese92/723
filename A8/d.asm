  1 NAME = diningPhilosophers ;
  2 INPUTS = ;
  3 STATES = 4 ;
  4 CUBES = 100;
  5 MOORE-OUTPUTS =
  6   Peating1, Peating2, Peating3, Pthinking1, Pthinking2, Pthinking3, Pfork1, Pfork2, Pfork3,
  7   TgrabForks1, TgrabForks2, TgrabForks3, TreleaseForks1, TreleaseForks2, TreleaseForks3,
  8   ;
  9 // initial state
 10 #0  000111111111000
 11     1
 12     2
 13     3
 14
 15 // philosopher 1 takes the forks from state 0
 16 #1  100011001000100
 17     0
 18
 19 // philosopher 2 takes the forks from state 0
 20 #2  010101010000010
 21     0
 22
 23 // philosopher 3 takes the forks from state 0
 24 #3  001110100000001
 25     0
