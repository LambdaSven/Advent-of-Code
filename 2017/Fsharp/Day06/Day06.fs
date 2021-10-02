// Learn more about F# at http://docs.microsoft.com/dotnet/fsharp

open System
open System.IO

// Define a function to construct a message to print
let GetInput () =
    File.ReadAllText("Input.txt").Split("\t") |> Seq.map(fun e -> e |> int) |> Seq.toArray;

let toSet (list : int array) =
    Array.fold(fun x y -> x.ToString() + " " + y.ToString()) "" list

let part1 (list: int array) =
    let rec rebalanceHelper (list : int array) (index : int) count (set : string Set) cycles =
        if count = 0 then
            if set.Contains(toSet list) then
                cycles 
            else
                let max = Array.max(list)
                let index = Array.findIndex(fun e -> e = max) list
                let nset = Set.add <| toSet list <| set
                list.[index] <- 0
                rebalanceHelper list
                    <| ((%) <| index + 1 <| list.Length)
                    <| max
                    <| nset
                    <| cycles + 1
        else
            list.[index] <- list.[index] + 1
            rebalanceHelper list 
                <| ((%) <| index + 1 <| list.Length) 
                <| count - 1
                <| set
                <| cycles

    let max = Array.max(list)
    let index = Array.findIndex(fun e -> e = max) list
    list.[index] <- 0
    rebalanceHelper list 
        <| ((%) <| index + 1 <| list.Length)
        <| max
        <| Set.empty
        <| 1 

let part2 (list: int array) =
    let rec rebalanceHelper (list : int array) (index : int) count (set : Map<string, int>) cycles =
        if count = 0 then
            if set.ContainsKey(toSet list) then
                cycles - set.[toSet list]
            else
                let max = Array.max(list)
                let index = Array.findIndex(fun e -> e = max) list
                let nset = Map.add <| toSet list <| cycles <| set
                list.[index] <- 0
                rebalanceHelper list
                    <| ((%) <| index + 1 <| list.Length)
                    <| max
                    <| nset
                    <| cycles + 1
        else
            list.[index] <- list.[index] + 1
            rebalanceHelper list 
                <| ((%) <| index + 1 <| list.Length) 
                <| count - 1
                <| set
                <| cycles

    let max = Array.max(list)
    let index = Array.findIndex(fun e -> e = max) list
    list.[index] <- 0
    rebalanceHelper list 
        <| ((%) <| index + 1 <| list.Length)
        <| max
        <| Map.empty
        <| 1 

[<EntryPoint>]
let main argv =
    part1 <| GetInput () |> printf("%d\n")
    part2 <| GetInput () |> printf("%d\n")
    0 // return an integer exit code