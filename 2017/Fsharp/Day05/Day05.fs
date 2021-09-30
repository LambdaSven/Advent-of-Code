open System.IO
open System.Threading
open System

let loadToList () = 
    let text = File.ReadAllText("Input.txt")
    text.Split("\n") 
        |> Seq.map(fun e -> e |> int)
        |> Seq.toArray

let part1 list =
    let rec runHelper (list: int array) index count =
      if index < 0 || index >= list.Length then 
        count
      else
        let value = list.[index]
        list.[index] <- list.[index] + 1
        runHelper list 
            <| index + value
            <| count  + 1

    runHelper list 0 0

let part2 list = 
    let rec runHelper (list: int array) index offset count =
        if index < 0 || index >= list.Length then
            count
        else
            let noffset = list.[index];
            list.[index] <- (if noffset > 2 then (-) else (+)) <| list.[index] <| 1
            runHelper list
                <| index + noffset
                <| noffset
                <| count + 1
    runHelper list 0 0 0


[<EntryPoint>]
let main argv =
    part1 <| loadToList() |> printf("Part 1: %d\n")
    part2 <| loadToList() |> printf("Part 2 : %d\n")
    0 // return an integer exit code
