module Part2

open System
open System.IO

let readFile path =
    let text = File.ReadAllText(path)

    text.Split("\n")
    |> Seq.toList
    |> List.map (fun e -> e.Split("\t")) // array of arrays of strings
    |> List.map (fun e -> e |> Seq.toList |> List.map (fun f -> f |> int))

let divisor (elem: int list) =
    let fst =
        List.find (fun e -> List.exists (fun f -> max e f % min e f = 0 && e <> f) elem) elem

    let snd =
        List.findBack (fun e -> List.exists (fun f -> max e f % min e f = 0 && e <> f) elem) elem

    max fst snd / min fst snd

let result =
    List.fold (fun acc elem -> acc + divisor elem) 0 (readFile "Inputs/Day02.txt")
