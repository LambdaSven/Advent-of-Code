let read_file file =
  In_channel.with_open_bin file In_channel.input_all

(* read lines *)
let read_lines file =
  let contents = In_channel.with_open_bin file In_channel.input_all in
  String.split_on_char '\n' contents

let squash_lines file = List.fold_left (^) "" @@ read_lines file
