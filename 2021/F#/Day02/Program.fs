open System
open System.IO

let getInput () =
  File.ReadAllLines("Input.txt")
  |> Seq.map(fun e -> e.Split(" "))
  |> Seq.map(fun a -> (a.[0], a.[1] |> int))

let move (a, b) str =
  match str with
  | "forward" -> (fun c -> (a + c, b))
  | "down" -> (fun c -> (a, b + c))
  | "up" -> (fun c -> (a, b - c))
  | _ -> failwith "String is Malformatted"

let part1 list = 
  Seq.fold
  <| (fun acc (a, b) -> move acc a <| b)
  <| (0, 0)
  <| list

let move2 (a, b, c) str =
  match str with
  | "forward" -> (fun e -> (a + e, b + (e * c), c))
  | "down" -> (fun e -> (a, b, c + e))
  | "up" -> (fun e -> (a, b, c - e))
  | _ -> failwith "String is Malformatted"
  
let part2 list = 
    Seq.fold
    <| (fun acc (a, b) -> move2 acc a <| b)
    <| (0, 0, 0)
    <| list
[<EntryPoint>]
let main argv =
  getInput () |> part1 |> (fun (a, b) -> a * b) |> printf("Part1: %d\n")
  getInput () |> part2 |> (fun (a, b, _) -> a * b) |> printf("Part1: %d\n")
  0 // return an integer exit code