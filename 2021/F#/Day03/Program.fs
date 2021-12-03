// Learn more about F# at http://docs.microsoft.com/dotnet/fsharp

open System
open System.IO

let getInput () =
  File.ReadAllLines("Input.txt")
  |> Seq.map(fun e -> Seq.toList e |> List.map(fun f -> int f - int '0'))
  |> Seq.toList

let Part1 (list : int list list ) =
  let res = 
    List.fold
      <| (List.map2 (+))
      <| list.Head
      <| list.Tail
  let bin = 
    List.map
      <| (fun e -> if e >= list.Length/2 then "1" else "0")
      <| res
      |> String.concat ""

  let gamma = Convert.ToInt32(bin, 2);
  gamma * (~~~gamma &&& (pown 2 list.Head.Length ) - 1)

let OxyRating (list : int list list) =
  let _, res =
    List.fold
    <| (fun (acc, oList) e ->
      let sum = 
        List.map (fun (f : int list) -> f.[e]) oList
        |> List.sum
      if sum >= (int <| Math.Ceiling (float oList.Length / 2.)) && oList.Length > 1 then
        (List.append [1] acc), (List.filter (fun f -> f.[e] = 1) oList)
      elif oList.Length > 1 then
        (List.append [0] acc), (List.filter (fun f -> f.[e] = 0) oList)
      else
        acc, oList
    )
    <| (List.Empty, list)
    <| [0 .. list.Head.Length-1]

  let bin = 
    List.map
      <| (string)
      <| List.head res
      |> String.concat ""
  Convert.ToInt32(bin, 2);

let C0Rating (list : int list list) =
  let _, res =
    List.fold
    <| (fun (acc, oList) e ->
      let sum = 
        List.map (fun (f : int list) -> f.[e]) oList
        |> List.sum
      if sum >= (int <| Math.Ceiling (float oList.Length / 2.)) && oList.Length > 1 then
        (List.append [0] acc), (List.filter (fun f -> f.[e] = 0) oList)
      elif oList.Length > 1 then
        (List.append [1] acc), (List.filter (fun f -> f.[e] = 1) oList)
      else
        acc, oList
    )
    <| (List.Empty, list)
    <| [0 .. list.Head.Length-1]

  let bin = 
    List.map
      <| (string)
      <| List.head res
      |> String.concat ""
  Convert.ToInt32(bin, 2);

let Part2 (list : int list list) =
  C0Rating list * OxyRating list



[<EntryPoint>]
let main argv =
  getInput () |> Part1 |> printf("Part 1: %d\n")
  getInput () |> Part2 |> printf("Part 2: %d\n")
  0 // return an integer exit code