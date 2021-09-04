module Part2

open System
open System.IO

let readFile path = 
  let text = File.ReadAllText(path)
  text.Split("").[0] |> Seq.toList |> List.map(fun e -> Char.GetNumericValue(e) |> int)

let rotate (list : int list) =
  let halves = List.splitAt (list.Length/2) list
  snd halves @ fst halves

let result = 
  let list1 = readFile("Inputs/Day01.txt")
  let list2 = rotate list1
  // using fold2 for part2 instead of explicit recursion
  List.fold2 (fun acc elem1 elem2 -> 
                   if elem1 = elem2 then acc + elem1 else acc) 0 list1 list2