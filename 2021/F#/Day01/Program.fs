module Day01

open System
open System.IO

let getInput () =
  File.ReadAllLines("Input.txt")
  |> Seq.map(fun e -> e |> int)
  
let Part1 (input : int seq) =
  Seq.fold
  <| fun (a: int, b: int) e -> if e > b then (a + 1, e) else (a, e)
  <| (0, Seq.head input)
  <| Seq.tail input
  |> fst

let Part2 (input : int seq) =
  let input = Seq.toList input
  //ha = head of a, ta = tail of a
  let rec zip (a, b, c) =
    match (a, b, c) with
    | ha :: ta, hb :: tb, hc :: tc -> (ha, hb, hc) :: zip (ta, tb, tc)
    | _ -> []
  
  let zipped = zip(input, (List.tail input), ((List.tail >> List.tail) input))

  List.map
  <| fun (a, b, c) -> a + b + c
  <| zipped
  |> Part1

[<EntryPoint>]
let main argv =
  getInput () |> Part1 |> printf("Part 1: %d\n")
  getInput () |> Part2 |> printf("Part2 : %d\n")
  0