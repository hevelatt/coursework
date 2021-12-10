module Parit where

x :: (Bool, Int, Int)
x = (True, 42, 10)

fstOfTriple :: forall a b c. (a,b,c) -> a
fstOfTriple kolmikko
 = case kolmikko of
     (a, _, _) -> a

sndOfTriple :: forall a b c. (a,b,c) -> b
sndOfTriple kolmikko
 = case kolmikko of
     (_, b, _) -> b

thdOfTriple :: forall a b c. (a,b,c) -> c
thdOfTriple kolmikko
 = case kolmikko of
     (_, _, c) -> c

sndPlusThdCaseOf :: (Bool,Int,Int) -> (Bool,Int)
sndPlusThdCaseOf kolmikko 
 = case kolmikko of
     (a, b, c) -> (a, b + c)

sndPlusThd :: (Bool,Int,Int) -> (Bool,Int)
sndPlusThd kolmikko = (fstOfTriple kolmikko, sndOfTriple kolmikko + thdOfTriple kolmikko)