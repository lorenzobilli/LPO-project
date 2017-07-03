type ident = Name of string;;

let name = function Name(str) -> str;

type 

  exp = Or of exp*exp | And of exp*exp | Eq of exp*exp | Lth of exp*exp | Add of exp*exp | Sub of exp*exp | Mul of exp*exp | Div of exp*exp 
      | Not of exp | Sign of exp | Top of exp | Pop of exp | Push of exp*exp | Append of exp*exp | Length of exp | List of exp_seq | Pair of exp*exp  
      | Fst of exp | Snd of exp | Num of int | Bool of bool | Ident of ident 

and

  exp_seq = SingleExp of exp | MoreExp of exp * exp_seq;;

type 
  stmt = Assign of ident*exp | Var of ident*exp | Print of exp | For of ident * exp * stmt_seq | If of exp * stmt_seq * stmt_seq | While of exp * stmt_seq

and
  stmt_seq = SingleStmt of stmt | MoreStmt of stmt * stmt_seq;;

type prog = Prog of stmt_seq;;

type value = Bool of bool | Int of int | List of value list | Pair of value * value;;

let rec print=function 
    Bool b -> if b then print_string "true" else print_string "false" 
  | Int i -> print_int i 
  | List l -> print_string "[ ";List.iter (fun x->print x; print_string " ") l;print_string "]" 
  | Pair(v1,v2) -> print_string "(";print v1;print_string ","; print v2;print_string ")";;

let println v = print v;print_newline();;

exception RuntimeError of string;;

let undef_var_error id=raise (RuntimeError ("Undefined variable "^name(id)));;

let conv_error ty=raise (RuntimeError("Could not convert value to "^ty));; 

let empty_envs = [];;

let empty_env id = undef_var_error id;;

let enter_scope envs = empty_env::envs;;

let exit_scope = function _::envs -> envs;;

let in_dom id env = try env id;true with RuntimeError _ -> false;;

let rec resolve id = function
    [] ->  undef_var_error id
  | env::envs -> if in_dom id env then env else resolve id envs;;

let look_up id envs = (resolve id envs) id;;

let loc_update env v id1 id2= if id1=id2 then v else env id2;; 

let rec update v id = function
    [] ->  undef_var_error id
  | env::envs -> if in_dom id env then (loc_update env v id)::envs else env::update v id envs;;

let new_fresh v id = function
    env::envs -> (loc_update env v id)::envs;;

let to_bool = function
    Bool b -> b |
    _ -> conv_error "bool";;

let to_int = function
    Int i -> i |
    _ -> conv_error "int";;

let to_list = function
    List l -> l |
    _ -> conv_error "list";;


let to_pair = function
    Pair(v1,v2)-> (v1,v2) |
    _ -> conv_error "pair";;

let top l = try (List.nth l (List.length l - 1)) with Failure _ -> raise (RuntimeError "top of empty list");;

let rec pop = function
    [] -> raise (RuntimeError "pop of empty list")
  |  [_] -> []
  | x::tl -> x::pop tl;;

let fst (v,_) = v;;

let snd (_,v) = v;;

let rec evExp envs=function 
    Or(exp1,exp2)  -> Bool(to_bool (evExp envs exp1) || to_bool(evExp envs exp2)) 
  | And(exp1,exp2)  -> Bool(to_bool (evExp envs exp1) && to_bool(evExp envs exp2)) 
  | Eq(exp1,exp2) -> Bool(evExp envs exp1=evExp envs exp2)
  | Lth(exp1,exp2) -> Bool(to_int (evExp envs exp1) < to_int(evExp envs exp2)) 
  | Add(exp1,exp2)  -> Int(to_int (evExp envs exp1) + to_int(evExp envs exp2)) 
  | Sub(exp1,exp2)  -> Int(to_int (evExp envs exp1) - to_int(evExp envs exp2)) 
  | Mul(exp1,exp2)  -> Int(to_int (evExp envs exp1) * to_int(evExp envs exp2)) 
  | Div(exp1,exp2)  -> Int(to_int (evExp envs exp1) / to_int(evExp envs exp2)) 
  | Not exp -> Bool (not (to_bool (evExp envs exp)))
  | Sign exp -> Int (- to_int (evExp envs exp))
  | Top exp -> top (to_list (evExp envs exp))
  | Pop exp -> List (pop (to_list (evExp envs exp)))
  | Push(exp1,exp2) -> List (to_list (evExp envs exp2) @ [evExp envs exp1])
  | Append(exp1,exp2) -> List (to_list (evExp envs exp1) @ to_list (evExp envs exp2))
  | Length exp -> Int(List.length (to_list (evExp envs exp)))
  | List exp_seq -> evExpSeq envs exp_seq
  | Pair(exp1,exp2) -> Pair(evExp envs exp1,evExp envs exp2)
  | Fst exp -> fst (to_pair(evExp envs exp))
  | Snd exp -> snd (to_pair(evExp envs exp))
  | Ident id -> look_up id envs
  | Num i -> Int i
  | Bool b -> Bool b

and 

  evExpSeq envs=function 
    SingleExp(exp) -> List [evExp envs exp]
  | MoreExp(exp,exp_seq) -> List (evExp envs exp::to_list (evExpSeq envs exp_seq));;


let rec evStmt envs=function
    Assign(id,exp) -> update (evExp envs exp) id envs
  | Var(id,exp) -> new_fresh (evExp envs exp) id envs
  | Print exp -> println(evExp envs exp); envs
  | For(id,exp,stmt_seq) ->  let lv=to_list (evExp envs exp) in 
        List.fold_left (fun prev_envs v -> exit_scope(evStmtSeq (new_fresh v id (enter_scope prev_envs)) stmt_seq)) envs lv
  |If(exp,stmt_seq1,stmt_seq2) -> if to_bool(evExp envs exp) then exit_scope(evStmtSeq (enter_scope envs) stmt_seq1) else exit_scope(evStmtSeq (enter_scope envs) stmt_seq2)
  |While(exp,stmt_seq) ->  if to_bool(evExp envs exp) then let envs1=exit_scope(evStmtSeq (enter_scope envs) stmt_seq) in 
          exit_scope(evStmt (enter_scope envs1) (While(exp,stmt_seq))) else envs

and 

  evStmtSeq envs=function 
    SingleStmt(stmt) -> evStmt envs stmt
  | MoreStmt(stmt,stmt_seq) -> evStmtSeq (evStmt envs stmt) stmt_seq;;

let evProg = function Prog(stmt_seq) -> evStmtSeq (enter_scope empty_envs) stmt_seq; ();;

