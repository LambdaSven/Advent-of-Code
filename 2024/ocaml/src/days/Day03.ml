let line = Tooling.Utilities.squash_lines "./inputs/day03.txt"

let r = Str.regexp {|mul(\([0-9][0-9]?[0-9]?\),\([0-9][0-9]?[0-9]?\))|}

let rec calcLine s i l = 
    match (try Str.search_forward r s i with Not_found -> -1) with 
    | x when x = -1 -> l
    | x -> calcLine s (x+1) (((int_of_string (Str.matched_group 1 s)) * (int_of_string (Str.matched_group 2 s)))::l)

let backDont s i = try Str.search_backward (Str.regexp {|don't()|}) s i with Not_found -> -1
let backDo s i = try Str.search_backward (Str.regexp {|do()|}) s i with Not_found -> -1

let rec calcLine2 s i l = 
    match (try Str.search_forward r s i with Not_found -> -1) with
    | x when x = -1 -> l
    | x -> 
        let a = int_of_string (Str.matched_group 1 s)
        in let b = int_of_string(Str.matched_group 2 s) 
        in match (backDo s x) - (backDont s x) with
        | y when y > 0 || y = 0 -> calcLine2 s (x+1) ((a * b)::l)
        | _ -> calcLine2 s (x+1) l

let part1 = string_of_int @@ List.fold_left ( + ) 0 @@ calcLine line 0 []

let part2 = string_of_int @@ List.fold_left ( + ) 0 @@ calcLine2 line 0 []
