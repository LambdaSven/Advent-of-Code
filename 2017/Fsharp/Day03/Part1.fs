module Part1

open System

(*
  On the spiral, the following equations can formulate the points
  that exist directly on a horizontal or vertical with 1

  Above : 4n² - n + 1
  Left : 4n² + n + 1
  Below : 4n² + 3n + 1
  Right : 4n² - 3n + 1

  This will be a basis for our solution

  Additionally, the important diagonal for us is 
  Spawn : 4n² + 4n + 1
    //this diagonal represents the minimum value on every layer
*)

let eqn n b c = (4 * n * n ) + (b * n) + c

let north n = eqn n -1 1
let east n = eqn n -3 1 
let south n = eqn n 3 1
let west n = eqn n 1 1
let min n = eqn n 4 1

let target = 347991;

let result = 
  let rec findLayer n = 
    match n with
    | n when min n <= target -> findLayer <| n + 1
    | _ -> n
  let layer = findLayer 1
  let list = [abs (target - north layer); abs (target - west layer); abs (target - south layer); abs (target - west layer)]
  List.min list + layer


