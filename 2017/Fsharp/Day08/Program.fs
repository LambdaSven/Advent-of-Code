// Learn more about F# at http://docs.microsoft.com/dotnet/fsharp

open System
open System.IO

type expr = 
    { 
        OperatingRegister : string
        Instruction : int -> int -> int
        Argument : int
        ConditionalRegister : string
        Condition : int -> int -> bool
        ConditionalValue : int
    }

let inc x y =
    x + y

let dec x y =
    x - y

let ParseExpr (str : string) =
    let split = str.Split(" ")
    let reg = split.[0]
    let instruction = 
        match split.[1] with
            | "inc" -> inc
            | "dec" -> dec
            | x -> failwithf "%s is not a valid instruction" x
    let arg = split.[2] |> int

    let conditionalReg = split.[4]
    let condition = 
        match split.[5] with
            | ">" -> (>)
            | "<" -> (<)
            | "<=" -> (<=)
            | ">=" -> (>=)
            | "!=" -> (<>)
            | "==" -> (=)
            | x -> failwithf "%s is not a valid conditional" x
    let conditionVal = split.[6] |> int    
    
    {OperatingRegister = reg;
    Instruction = instruction;
    Argument = arg;
    ConditionalRegister = conditionalReg;
    Condition = condition;
    ConditionalValue = conditionVal;}

let readInput () = 
    File.ReadAllLines ("input.txt") 
        |> Array.map(ParseExpr) 

let execute (expr : expr) (map : Map<string, int>) = 
    let mutable retMap = map
    // add keys if needed
    if not <| retMap.ContainsKey expr.OperatingRegister then
        retMap <- retMap.Add (expr.OperatingRegister, 0)
    if not <| retMap.ContainsKey expr.ConditionalRegister then
        retMap <- retMap.Add (expr.ConditionalRegister, 0)

    if expr.Condition retMap.[expr.ConditionalRegister] expr.ConditionalValue then
        retMap <- retMap.Add (expr.OperatingRegister, (expr.Instruction retMap.[expr.OperatingRegister] expr.Argument))

    retMap

let run (program : expr[]) =
    Array.fold
    <| fun m e -> execute e m
    <| Map.empty
    <| program

let part1 (map : Map<string, int>) = 
    map 
    |> Map.toList 
    |> List.map snd 
    |> List.max

let runCached (program : expr[]) =
    let mutable map = Map.empty
    Array.fold
    <| fun l e -> 
         map <- execute e map
         map :: l
    <| List<Map<string, int>>.Empty
    <| program
    
let part2 (prog : list<Map<string, int>>) = 
    List.map
    <| part1
    <| prog
    |> List.max

[<EntryPoint>]
let main argv =

    //part 1
    readInput ()
    |> run
    |> part1
    |> printfn "Part 1: %d"

    //part 2 
    readInput ()
    |> runCached
    |> part2
    |> printfn "Part 2: %d"
    0 // return an integer exit code