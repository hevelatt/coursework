module Main where

data JokoTaiMolemmat a b = Vasen a a | Oikea b b | Molemmat a b deriving Show

rakennaVasemmasta :: forall a b. a -> JokoTaiMolemmat a b
rakennaVasemmasta a = Vasen a a

rakennaOikeasta :: forall a b. b -> JokoTaiMolemmat a b
rakennaOikeasta b = Oikea b b

rakennaMolemmista :: forall a b. a -> b -> JokoTaiMolemmat a b
rakennaMolemmista a b = Molemmat a b

yhteensä :: JokoTaiMolemmat Natural Natural -> Natural
yhteensä jokotai = case jokotai of
                    Vasen x y -> x + y
                    Oikea x y -> x + y
                    Molemmat x y -> x + y

---- Esimerkki yhteensä-funktion toiminnasta:
---- TIEA341> yhteensä (rakennaMolemmista 5 7)
---- 12
----
---- TIEA341> yhteensä (rakennaMolemmista 3 2)
---- 5
--data X
--data Y
--data Tavoite
--x :: forall a b. (X -> a) -> b
--x _ = undefined
--y :: forall a. a -> Y 
--y _ = undefined
--tavoite :: X -> Y -> Tavoite
--tavoite _ _ = undefined
--
--tehtävä :: Tavoite
--tehtävä = tavoite (x y) (y x)

main :: IO ()
main = do
  putStrLn "Hello TIEA341"
