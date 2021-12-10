module Funktiot where

kutsu3Kertaa :: forall a. (a -> a) -> a -> a
--kutsu3Kertaa f x = f (f (f x))
kutsu3Kertaa seFunktio seArvo
 = let
     ensimmainenArvo = seFunktio seArvo
     toinenArvo = seFunktio ensimmainenArvo
     kolmasArvo = seFunktio toinenArvo
    in kolmasArvo

kutsuParia :: forall a b. (a, a -> b) -> b
kutsuParia pari = snd pari (fst pari) 

molemmille :: forall a t. (a -> t) -> (a, a) -> (t, t)
molemmille f pari = (f (fst pari), f (snd pari))

kutsuNKertaa :: forall a. Int -> (a -> a) -> (a -> a)
kutsuNKertaa n f 
 = case n of 
     0 -> (\seA -> seA)
     m -> let
           kutsutaanNMiinusYksiKertaa = kutsuNKertaa (m-1) f
          in (\seA -> kutsutaanNMiinusYksiKertaa (f seA))
--kutsuNKertaa 0 _f x = x 
--kutsuNKertaa n f x = kutsuNKertaa (n-1) f (f x)
--https://stackoverflow.com/a/22603772
