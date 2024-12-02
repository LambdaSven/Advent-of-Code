let lines = Tooling.Utilities.read_lines "./inputs/day02.txt"

let line str = String.split_on_char ' ' str |> List.filter((<>) "") |> List.map int_of_string
let numInput = List.map line lines

let rec higher = function 
    | a::b::t when (a - b > 0) && (a - b < 4) -> higher (b::t)
    | [_] -> true
    | _ -> false

let rec lower = function 
    | a::b::t when a - b < 0 && a - b > -4 -> lower (b::t)
    | [_] -> true
    | _ -> false

let highlist = List.map higher numInput |> List.filter((=) true)
let lowlist = List.map lower numInput |> List.filter((=) true)

let highDamper list = 
    let rec _highDamper x = function
        | a::b::c::t when (a-b > 0) && (a-b < 4) && (b-c > 0) && (b-c < 4) -> _highDamper x (b::c::t)
        | _::b::c::t when (b-c > 0) && (b-c < 4) && x > 0 -> _highDamper (x-1) (b::c::t)
        | a::_::c::t when (a-c > 0) && (a-c < 4) && x > 0 -> _highDamper (x-1) (a::c::t)
        | a::b::_::t when (a-b > 0) && (a-b < 4) && x > 0 -> _highDamper (x-1) (a::b::t)
        | [a;b] when ((a-b > 0) && (a-b < 4)) || x > 0 -> true
        | [_] -> true
        | _ -> false
    in
    _highDamper 1 list

let lowDamper list = 
    let rec _lowDamper x = function
        | a::b::c::t when (a-b < 0) && (a-b > -4) && (b-c <0) && (b-c > -4) -> _lowDamper x (b::c::t)
        | _::b::c::t when (b-c < 0) && (b-c > -4) && x > 0 -> _lowDamper (x-1) (b::c::t)
        | a::_::c::t when (a-c < 0) && (a-c > -4) && x > 0 -> _lowDamper (x-1) (a::c::t)
        | a::b::_::t when (a-b < 0) && (a-b > -4) && x > 0 -> _lowDamper (x-1) (a::b::t)
        | [a;b] when ((a-b < 0) && (a-b > -4)) || x > 0 -> true
        | [_] -> true
        | _ -> false
    in
    _lowDamper 1 list

let highDampedList = List.map highDamper numInput |> List.filter ((=) true)
let lowDampedList = List.map lowDamper numInput |> List.filter ((=) true)

let part1 = string_of_int @@ List.length highlist + List.length lowlist

let part2 = string_of_int @@ List.length highDampedList + List.length lowDampedList
