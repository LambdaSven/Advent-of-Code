// Learn more about F# at http://docs.microsoft.com/dotnet/fsharp

open System
open System.IO

type Program = 
    { Id : string
      Weight : int
      mutable CWeight : int
      Children : string list
      mutable Parent : Option<String>}

let ParseLine (str : string) : string * Program =
    (*
        Input is always in form s
            kpdmptf (48)
        or
            gtpew (23) -> iyxsjhc, fwvvidu, mdshsxt, yzvqo

        So parsing can be done trivially with splitting.
    *)
        
    let tokens = str.Split(" ")
    let id = tokens.[0]
    let weight = tokens.[1].Substring(1, tokens.[1].Length-2) |> int
    let children = tokens.[3..] |> Array.map(fun e -> e.Replace(",", ""))
    id, {Id = id; Weight = weight; CWeight = -1; Children = children |> Array.toList; Parent = None}


let readInput () = 
    let lines = File.ReadAllLines("Input.txt")
    lines |> Array.map (ParseLine) |> Array.fold (fun (m : Map<string, Program>) (id,obj) -> Map.add id obj m) Map.empty 

//utility method for printing a the program map. Used for Debubbing.
let printProgramList (map : Map<string, Program>) = 
     List.iter 
        <| (fun (_, e: Program) -> 
            printfn "%s:%d:%A:%s\n" e.Id e.Weight e.Children <| if e.Parent.IsSome then e.Parent.Value else "None") 
        <| Map.toList map
 
// go through every program, adding itself as a `Parent` Entry to all children.
let addParents (map : Map<string, Program>) : Map<string, Program>  = 
   Map.iter 
    <| fun k v -> 
        List.iter 
            <| fun e -> 
                map.[e].Parent <- Some(k)
            <| map.[k].Children
    <| map;
    map    

let RebalanceWeights (map : Map<string, Program>) : Map<string, Program> =
    //recursively get the weight of children 
    let rec getChildrenWeight (p : Program) =
        match p.Children with 
        | [] -> 0 
        | _ -> List.sumBy (fun e -> getChildrenWeight map.[e] + map.[e].Weight) p.Children

    Map.iter
    <| fun _ v -> 
        if v.CWeight = -1 then // we don't need to recalculate
            v.CWeight <- getChildrenWeight v
    <| map;
    map

// determine if all elements in a list are equal to eachother
let allEqual (list : int list) : bool = 
    if list <> [] then
        List.forall (fun e -> e = list.[0]) list
    else
        true

//utility function to print the weights, used for debugging
let printWeights (map : Map<string, Program>): unit = 
    Map.iter
    <| fun k v -> 
        let cints = 
            v.Children 
            |> List.map ((fun e -> map.[e]) >> (fun e -> e.Weight + e.CWeight)) 
        if not <| allEqual cints then printfn "%A" cints
    <| map

let getWrongChild (s : string) (map : Map<string, Program>) = 
    let children = map.[s].Children |> List.map (fun e -> map.[e])
    // Program list, children of unbalanced program

    let cints = children |> List.map (fun e -> e.Weight + e.CWeight)
    // int list, total weights of unbalanced programs

    let wrong = 
        List.groupBy
        <| fun e -> e.Weight + e.CWeight
        <| children
        // group by total weight of programs
        |> List.tryFind (fun (_, l) -> l.Length = 1)
        // find the grouping with only 1, the bad element
        |> Option.get
        |> snd
        |> List.exactlyOne
        // get the program

    let diff = abs <| List.max cints - List.min cints

    if wrong.Weight + wrong.CWeight < List.max cints then
        wrong.Weight + diff
    else
        wrong.Weight - diff

let part2 (map : Map<string, Program>) =
    let s = 
        Map.filter
        <| fun _ v -> 
            not (allEqual <| List.map ((fun e -> map.[e]) >> (fun e -> e.Weight + e.CWeight)) v.Children) 
            // find all unbalanced members of the list
        <| map
        |> Map.toList 
        |> List.sortByDescending (fun (_, p) -> p.Weight + p.CWeight) 
            // Get the smallest unbalanced program, which will bet the problematic one
        |> List.last // get the ID of said program
    getWrongChild <| fst s <| map



let Part1 (map : Map<string, Program>) = 
    Map.tryFindKey 
    <| (fun _ v -> v.Parent.IsNone)
    <| map

[<EntryPoint>]
let main argv =
    
    //part 1
    readInput () 
        |> addParents 
        |> Part1 
        |> Option.get
        |> printfn("Part 1: %s\n")
    
    //part 2
    readInput () 
        |> addParents
        |> RebalanceWeights
        |> part2
        |> printfn("Part 2: %d\n")
    0 // return an integer exit code