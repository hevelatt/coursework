module Main where

kerroKuudellatoista :: Natural -> Natural
kerroKuudellatoista luku = luku * 16

onkoAnagrammi :: Text -> Text -> Bool
onkoAnagrammi teksti teksti2
  = let
     listana :: [Char]
     listana = toString teksti

     listana2 :: [Char]
     listana2 = toString teksti2
     
     lajiteltu = sort listana
     lajiteltu2 = sort listana2
    in lajiteltu == lajiteltu2

tulo :: [Natural] -> Natural
tulo [] = 1
tulo (eka : loput) = eka * (tulo loput) 

main :: IO ()
main = do
  putStrLn "Hello TIEA341"
