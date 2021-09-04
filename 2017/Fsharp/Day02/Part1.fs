module Part1

open System
open System.IO

let readFile path = 
  let text = File.ReadAllText(path)
  text.Split("\n") 
    |> Seq.toList 
    |> List.map(fun e -> e.Split("\t")) // array of arrays of strings
    |> List.map(fun e -> e |> Seq.toList |> List.map(fun f -> f |> int))

let result = 
  List.fold (fun acc elem -> 
                acc + List.max elem - List.min elem) 
   0 
   (readFile "Inputs/Day02.txt")
