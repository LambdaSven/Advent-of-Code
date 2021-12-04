// Learn more about F# at http://docs.microsoft.com/dotnet/fsharp
open System
open System.IO

// This is kind of horrendeous imo
let CheckMatch (list: string list list) =
  let success = ["x"; "x"; "x"; "x"; "x"] // 5 x
  match list with
  | _ when list.[0] = success -> true
  | _ when list.[1] = success -> true
  | _ when list.[2] = success -> true
  | _ when list.[3] = success -> true
  | _ when list.[4] = success -> true 
  | _ when (List.map (List.item 0) list) = success -> true
  | _ when (List.map (List.item 1) list) = success -> true
  | _ when (List.map (List.item 2) list) = success -> true
  | _ when (List.map (List.item 3) list) = success -> true
  | _ when (List.map (List.item 4) list) = success -> true
  | _ -> false


let boardMove number board =
  List.map
  <| (fun e -> List.map (fun f -> if f = number then "x" else f) e)
  <| board

let getInput () = 
  let raw = File.ReadAllLines("Input.txt")
  let ins = (Seq.head raw).Split(",") |> Seq.toList
  let boards = 
    Seq.tail raw 
    |> Seq.toList 
    |> List.filter (fun e -> e <> "") 
    |> List.map(
      (fun e -> e.Split()) 
      >> Seq.filter(fun e -> e <> "") 
      >> Seq.toList)
  
  let rec Chunk count list = 
    match list with 
    | [] -> []
    | _ -> Seq.take count list :: Chunk count list.[count..]
  (ins, List.map
  <| (Seq.toList)
  <| Chunk 5 boards)


let Part1 (ins_raw, boards_raw) =
  let move_all_boards num boards =
    List.map
    <| (fun e -> boardMove num e)
    <| boards
  
  let check_all_boards (boards : string list list list) = 
    List.tryFindIndex
    <| CheckMatch
    <| boards

  let rec helper boards ins lastIns = 
    let x = check_all_boards boards
    match x with
    | None -> helper <| move_all_boards (List.head ins) boards <| List.tail ins <| List.head ins
    | Some x -> 
      (List.map (fun e -> List.map (fun f -> if f <> "x" then int f else 0) e) boards.[x]
      |> List.concat
      |> List.sum) * (int lastIns)

  helper boards_raw ins_raw "0"

(*
  Very similar to part 1, except we check forall with our condition,
  we also hold the previous state of the boards, so that we can refer to the
  state before the last board won.
*)
let Part2 (ins_raw, boards_raw) =
  let move_all_boards num boards =
    List.map
    <| (fun e -> boardMove num e)
    <| boards
  
  let check_all_boards (boards : string list list list) = 
    List.forall
    <| (CheckMatch)
    <| boards

  let rec helper boards ins lastIns lastState = 
    let x = check_all_boards boards
    match x with
    | true -> 
      ((List.map (fun e -> List.map (fun f -> if f <> "x" then int f else 0) e) (List.find (fun e -> not <| CheckMatch e) lastState)
      |> List.concat
      |> List.sum) - int lastIns) * (int lastIns) // we subtract last instruction because we're using the list from before the number was played
    | false -> helper <| move_all_boards (List.head ins) boards <| List.tail ins <| List.head ins <| boards

  helper boards_raw ins_raw "0" boards_raw


[<EntryPoint>]
let main argv =
  getInput () |> Part1 |> printf "Part 1: %d\n"
  getInput () |> Part2 |> printf "Part 2: %d\n"
  0 // return an integer exit code