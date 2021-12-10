module Main where
import Test.QuickCheck

{-
Paketti on voi olla

Kirje, korkeus enintään 2cm ja leveys ja pituus enintään 20cm
Pikkupaketti, max 20×20×20cm
Suurpaketti, jos ei ole muita
Merkattu Särkyväksi, jos asiakas niin haluaa

Kirje maksaa 3.90, pikkupaketti 5.95 ja suurpaketti 0.001€/cm³. 
Jos paketti on särkyvä, hinta nousee 5€ + 20% alkuperäisen paketin 
hinnasta.
-}

data Paketti 
 = Suorakaide Natural Natural Natural
 | Sylinteri Natural Natural
 deriving (Show, Eq)

data PakettiLuokka 
 = Kirje 
 | Pikkupaketti 
 | Suurpaketti Double -- tilavuus kuutiosentteinä
 deriving (Show, Eq)

data Lähetys 
 = Särkyvä Paketti
 | Ykkösluokka Paketti
 | Kakkosluokka Paketti
 deriving (Show, Eq)

luokittele :: Paketti -> PakettiLuokka
luokittele paketti 
 = case paketti of 
    Suorakaide pituus leveys korkeus
     | onkoKirje paketti 
      -> Kirje
     | onkoPikkupaketti paketti 
      -> Pikkupaketti
     | otherwise 
      -> Suurpaketti (fromIntegral (pituus * leveys * korkeus))
    Sylinteri korkeus säde
     | onkoKirje paketti 
      -> Kirje
     | onkoPikkupaketti paketti
      -> Pikkupaketti
     | otherwise
      -> Suurpaketti (pi * fromIntegral (säde * säde * korkeus))

perusHinta :: PakettiLuokka -> Double
perusHinta luokka 
 = case luokka of 
    Kirje 
     -> 3.90
    Pikkupaketti 
     -> 5.95
    Suurpaketti tilavuus 
     -> 5.95 + tilavuus * 0.001

laskeHinta :: Lähetys -> Double
laskeHinta lähetys
 = case lähetys of 
    Ykkösluokka p 
     -> perus p
    Särkyvä p 
     -> 5 + perus p * 1.2
    Kakkosluokka p 
     -> perus p * 0.9
 where
  perus p = perusHinta (luokittele p)

onkoKirje :: Paketti -> Bool
onkoKirje paketti
 = case paketti of 
    Suorakaide pituus leveys korkeus 
     -> (korkeus <= 2) && (leveys <= 20) && 
        (pituus <= 20)
    Sylinteri korkeus säde 
     -> (korkeus <= 2) && (säde * 2 <= 20)

onkoPikkupaketti :: Paketti -> Bool
onkoPikkupaketti paketti 
 = case paketti of 
    Suorakaide pituus leveys korkeus 
     -> (korkeus > 2)  && (korkeus <= 20) && 
        (leveys <= 20) && (pituus <= 20)
    Sylinteri korkeus säde 
     -> (korkeus > 2) && (korkeus <= 20) && 
        (säde * 2 <= 20)

{-
onkoSuurpaketti :: Paketti -> Bool
onkoSuurpaketti paketti 
 = case paketti of 
    Suorakaide pituus leveys korkeus 
     -> (korkeus > 20) || (leveys > 20) ||
        (pituus > 20)
    Sylinteri korkeus säde 
     -> (korkeus > 20) || (säde * 2 > 20)
-}

testi_1 :: Int -> Int -> Int -> Int -> Bool
testi_1 a b c d
 = let
    pituus = fromIntegral (abs a) :: Natural
    leveys = fromIntegral (abs b) :: Natural
    korkeus = fromIntegral (abs c) :: Natural
    kerroin = fromIntegral (abs d) :: Natural
    paketti = Suorakaide pituus leveys korkeus
    suurempiPaketti = Suorakaide (pituus*kerroin) (leveys*kerroin) (korkeus*kerroin)
   in laskeHinta (Ykkösluokka paketti) <= laskeHinta (Ykkösluokka suurempiPaketti) 

hintaKasvaa :: Int -> Int -> Int -> Int -> Int -> Int -> Bool
hintaKasvaa pituus leveys korkeus muutosx muutosy muutosz
    = laskeHinta (Ykkösluokka (Suorakaide (n pituus) (n leveys) (n korkeus))) 
    <= laskeHinta (Ykkösluokka (Suorakaide (n pituus + n muutosx) (n leveys + n muutosy) (n korkeus + n muutosz))) 
 where
  n jokuInt = max 1 (fromIntegral (abs jokuInt))

main :: IO ()
main = do
  quickCheck hintaKasvaa
  putStrLn "Hello TIEA341"
