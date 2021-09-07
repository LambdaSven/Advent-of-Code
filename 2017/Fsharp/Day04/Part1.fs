module Part1

open System
open System.IO

let readFile path =
    let text = File.ReadAllText(path)

    text.Split("\n")
    |> Seq.toList
    |> List.map (fun e -> e.Split(" ") |> Seq.toList) // array of arrays of strings

let result = 
  readFile "Inputs/Day04.txt"
  |> List.filter (fun e -> List.distinct e |> List.length = e.Length)
  |> List.length