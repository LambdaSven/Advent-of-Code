module Part2

open System
open System.IO

let readFile path =
    let text = File.ReadAllText(path)

    text.Split("\n")
    |> Seq.map (fun e -> e.Split(" ")) // array of arrays of strings

let result = 
  readFile "Inputs/Day04.txt"
  |> Seq.filter (fun e -> 
    Seq.map (fun f -> Seq.sort f |> Seq.map string |> String.concat "") e
    |> Seq.distinct 
    |> Seq.length = e.Length)
  |> Seq.length