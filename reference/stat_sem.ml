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

type static_type = Int | Bool | List of static_type | Prod of static_type * static_type;;

exception TypeError of string;;

let rec toString=function
    Int -> "int" | Bool -> "bool" | List(ty) -> toString(ty)^" list" | Prod(ty1,ty2) -> "("^toString(ty1)^" * "^toString(ty2)^")";;

let error_message found expected="Found type "^found^" expected "^expected;;

let emit_error found expected=raise (TypeError(error_message (toString found) (toString expected)));; 

let check_type found expected=if found=expected then found else raise (emit_error found expected);;

let elem_type = function
    List ty -> ty
  | ty -> raise (TypeError(error_message (toString ty) "list"));;

let check_list = function
    List ty -> List ty
  | ty -> raise (TypeError(error_message (toString ty) "list"));;

let get_fst = function
    Prod(ty,_) -> ty
  | ty -> raise (TypeError(error_message (toString ty) "product"));;

let get_snd = function
    Prod(_,ty) -> ty
  | ty -> raise (TypeError(error_message (toString ty) "product"));;

let empty_env id = raise (TypeError ("Undefined variable "^name(id)));;

let update env ty id1 id2 = if id2=id1 then ty else env id2;;

let rec wfExp env=function 
    Or(exp1,exp2) | And(exp1,exp2) -> check_type (wfExp env exp1) Bool;  check_type (wfExp env exp2) Bool
  | Eq(exp1,exp2) -> let expected=wfExp env exp1 in check_type (wfExp env exp2) expected
  | Lth(exp1,exp2) -> check_type (wfExp env exp1) Int;  check_type (wfExp env exp2) Int; Bool
  | Add(exp1,exp2) | Sub(exp1,exp2) | Mul(exp1,exp2) | Div(exp1,exp2) -> check_type (wfExp env exp1) Int;  check_type (wfExp env exp2) Int
  | Not exp -> check_type (wfExp env exp) Bool
  | Sign exp -> check_type (wfExp env exp) Bool
  | Top exp -> elem_type (wfExp env exp)
  | Pop exp -> check_list(wfExp env exp) 
  | Push(exp1,exp2) -> let expected=List(wfExp env exp1) in check_type (wfExp env exp2) expected
  | Append(exp1,exp2) -> let expected=check_list(wfExp env exp1) in check_type (wfExp env exp2) expected
  | Length exp -> check_list(wfExp env exp);Int
  | List exp_seq -> List (wfExpSeq env exp_seq)
  | Pair(exp1,exp2) -> Prod(wfExp env exp1,wfExp env exp2)
  | Fst exp -> get_fst(wfExp env exp)
  | Snd exp -> get_snd(wfExp env exp)
  | Num _ -> Int
  | Bool _ -> Bool
  | Ident id -> env id

and 

  wfExpSeq env=function 
    SingleExp(exp) -> wfExp env exp
  | MoreExp(exp,exp_seq) -> let expected=wfExp env exp in check_type (wfExpSeq env exp_seq) expected; expected;;

let rec wfStmt env=function
    Assign(id,exp) -> let expected=env id in check_type (wfExp env exp) expected;env
  | Var(id,exp) -> update env (wfExp env exp) id
  | Print exp -> wfExp env exp; env
  | For(id,exp,stmt_seq) ->  let ty=elem_type (wfExp env exp) in let env2 = update env ty id in wfStmtSeq env2 stmt_seq; env 
  | If(exp,stmt_seq1,stmt_seq2) -> check_type (wfExp env exp) Bool; wfStmtSeq env stmt_seq1; wfStmtSeq env stmt_seq2; env 
  | While(exp,stmt_seq) -> check_type (wfExp env exp) Bool; wfStmtSeq env stmt_seq; env

and 

  wfStmtSeq env=function 
    SingleStmt(stmt) -> wfStmt env stmt
  | MoreStmt(stmt,stmt_seq) -> wfStmtSeq (wfStmt env stmt) stmt_seq;;

let wfProg = function Prog(stmt_seq) -> wfStmtSeq empty_env stmt_seq;();;

