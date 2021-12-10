module Main where

ceasar :: Int -> Text -> Text
ceasar numero salattava
  = let
     lista = toString salattava 
     salattu = map (eteenpäin numero) lista
    in fromString salattu

eteenpäin :: Int -> Char -> Char
eteenpäin 0 c = c
eteenpäin n c = succ (eteenpäin (n-1) c)

puraceasar :: Int -> Text -> Text
puraceasar numero salattava
  = let
     lista = toString salattava 
     salattu = map (taaksepäin numero) lista
    in fromString salattu

taaksepäin :: Int -> Char -> Char
taaksepäin 0 c = c
taaksepäin n c = pred (taaksepäin (n-1) c)

vigenere :: [Int] -> Text -> Text
vigenere avain salattava
  = let
     lista = toString salattava
     jatkettuAvain = cycle avain
     siirretty = zipWith eteenpäin jatkettuAvain lista
    in fromString siirretty

vigenerepurku :: [Int] -> Text -> Text
vigenerepurku avain salattava
  = let
     lista = toString salattava
     jatkettuAvain = cycle avain
     siirretty = zipWith taaksepäin jatkettuAvain lista
    in fromString siirretty

main :: IO ()
main = do
  putStrLn "Hello TIEA341"
