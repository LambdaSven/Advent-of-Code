module Part1

open System
open System.IO

let readFile path = 
  let text = File.ReadAllText(path)
  text.Split("").[0] |> Seq.toList |> List.map(fun e -> Char.GetNumericValue(e) |> int)

let result = 
  let list1 = readFile("Inputs/Day01.txt")
  // rotate the list by 1 element
  let list2 = list1.Tail @ [list1.Head]
  // take two lists as input, recurses until lists empty
  let rec iter (l1 : int list) (l2 : int list) sum =
    match l1 with
    | [] -> sum // empty
    | l1 when l1.Head = l2.Head -> // match 
      iter l1.Tail l2.Tail <| sum + l1.Head
    | _ -> // no match
      iter l1.Tail l2.Tail sum
  iter list1 list2 0