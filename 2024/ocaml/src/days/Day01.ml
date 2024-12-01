let lines = Tooling.Utilities.read_lines "./inputs/day01.txt"

let split str = 
    String.split_on_char ' ' str
    |> List.filter((<>) "")
    |> List.map int_of_string
    |> fun x -> 
        match x with 
        | h::t -> (h, List.hd @@ List.rev t)
        | _ -> (0, 0)

let (list1, list2) = List.map split lines 
    |> List.fold_left (fun acc e -> (fst e :: fst acc, snd e :: snd acc)) ([], [])

let list1 = List.sort (-) list1
let list2 = List.sort (-) list2

let diffs = List.map2 (fun x y -> abs @@ x - y) list1 list2

let rec count x acc = function
    | h::t when h = x -> count x (acc+1) t 
    | [h] when h = x-> acc+1
    | _::t -> count x acc t
    | _ -> acc

let similarity = List.map (fun e -> e * (count e 0 list2)) list1

let part1 = string_of_int @@ List.fold_left (+) 0 diffs

let part2 = string_of_int @@ List.fold_left (+) 0 similarity
