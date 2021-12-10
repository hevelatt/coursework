module Main where
import Test.QuickCheck

(<|) :: forall a b c. (b -> c) -> (a -> b) -> (a -> c)
f <| g = \seA -> f (g seA)
(|>) :: forall a b c. (a -> b) -> (b -> c) -> (a -> c)
f |> g = \seA -> g (f seA)

-- freqSort :: forall a. Ord a => [a] -> [a]
-- freqSort xs = concat (sortBy (comparing length) (group (sort xs)))

-- freqSort :: forall a. Ord a => [a] -> [a]
-- freqSort = sort |> group |> sortBy (comparing length) |> concat
-- freqSort :: forall a. Ord a => [a] -> [a]
-- freqSort = concat <| sortBy (comparing length) <| group <| sort
freqSort :: forall a. Ord a => [a] -> [a]
freqSort = concat . sortBy (comparing length) . group . sort

---------------------------------------------------------------------------------------

{- Tehtävänanto
 
 Hinnoitellaan postipaketteja.
 Paketti on voi olla
    * Kirje, korkeus enintään 2cm ja leveys ja pituus enintään 20cm
    * Pikkupaketti, max 20×20×20cm
    * Suurpaketti, jos ei ole muita
    * Merkattu Särkyväksi, jos asiakas niin haluaa

Kirje maksaa 3.90, pikkupaketti 5.95 ja suurpaketti 0.001€/cm³. Jos paketti
on särkyvä, hinta nousee 5€ + 20% alkuperäisen paketin hinnasta.
-}

-- TEHTÄVÄ. Lähetys voi olla myös kakkosluokkaa. Se on 10% halvempi.
--          Lisää koodiin.
-- TEHTÄVÄ. Paketti voi olla myös sylinterin muotoinen.


--  Tyypin nimi   Konstruktorit Tietotyypin kentät
--      ↓          ↓            ↓    ↓       ↓
data Paketti    = Suorakaide Natural Natural Natural
                | Sylinteri  Natural Natural
                   deriving (Show, Eq)
--                    ↑       ↑
--              'autokoodaa' 'Muuta merkkijonoksi' 'Tee vertailuoperaattori'

--                  Konstruktoreja voi olla useampia! (Ts. Pakettiluokka data on jokin näistä)
---                   ↓
data PakettiLuokka = Kirje 
                   | PikkuPaketti
                   | Suurpaketti Natural -- Olkoon tämä kenttä kuutiosenttejä
                  deriving (Show, Eq) 

-- Lähetys tietotyyppi. Lähetys sisältää paketin ja se voi olla eri tyyppinen.
data Lähetys = Särkyvä      Paketti
             | Ykkösluokka  Paketti
             | Kakkosluokka Paketti
          deriving (Show, Eq) 

-- Funktio joka laskee lähetykselle hinnan. Tämä on tämän tehtävän lopputuote.
hinta :: Lähetys -> Double
hinta lähetys 
  = case lähetys of
     Ykkösluokka  p  -> perus p
     Särkyvä      p  -> 5 + perus p * 1.2
     Kakkosluokka p -> perus p * 0.9
--                ↑
--              p on paketti. Kolme eri muuttujaa nimeltä p.
 where -- ← where liittyy funktion määritelmään. Ks. sisennys.
       --   sitä käytetään tekemään lokaaleja määritelmiä 
  -- perus p = perusHinta (luokittele p)
  perus = perusHinta . luokittele
--  ↑
-- tässä määritellään lokaali funktio. Sitä voi käyttää vain
-- hinta funktion sisällä. Eräänlainen apufunktio siis.

luokittele :: Paketti -> PakettiLuokka
luokittele paketti =  case paketti of
                       Sylinteri pituus säde
                         -- Sovitaan yksinkertaisuuden vuoksi, että kaikki paketit ovat
                         -- suurpaketteja. Voit halutessasi lisätä ehtoja, joilla 
                         -- sylinteri luokiteltaisiin johonkin toiseen pakettiluokkaan.
                         -> let
                              pohjanAla = pi * (fromIntegral $ säde*säde)
                              --                  ↑
                              --                Tämä muuttaa Natural → Double.
                              --                Kertolaskun molempien operandien täytyy 
                              --                olla samaa tyyppiä.
                              tilavuusDoublena = fromIntegral pituus * pohjanAla
                              -- pyöristettyTasamittaan = round tilavuusDoublena
                              --                         ↑
                              --                       Tämä pyöristää lähimpään
                              --                       kokonaislukuun.
                            in (round |> Suurpaketti) tilavuusDoublena

                         -- Yo. koodin voi kirjoittaa myös näin, jos ei halua nimetä välivaiheita.
                         -- -> Suurpaketti (pituus * round (pi * (fromIntegral (säde*säde) :: Double)))
                       Suorakaide pituus leveys korkeus 
                        | (korkeus <= 2) && (leveys <= 20) && (pituus <= 20)  
                            -> Kirje
                        
                        | (korkeus <= 20) && (leveys <= 20) && (pituus <= 20)  
                            -> PikkuPaketti
                        
                        | otherwise 
                            -> Suurpaketti (korkeus * leveys * pituus)

-- Funktio, joka kertoo pitääkö tälläinen lähetys laittaa pakettiautomaattiin vai kirjeautoon
-- (Lisäesimerkki casen käytöstä)
onkoOikeaPaketti :: PakettiLuokka -> Bool
onkoOikeaPaketti pakettiluokka 
  = case pakettiluokka of
     Kirje -> False
     PikkuPaketti ->  True
     Suurpaketti _ -> True 
--               ↑
--          Jos muuttujalle antaa nimen _,
--          niin se tarkoittaa, 'tässä pitää olla muuttujan sidonta, 
--          mutta en aio käyttää sidottua muuttujaa missään'

-- Lasketaan perusHinta paketille. Eli, ottamatta huomioon, onko paketti
-- särkyvä vai ei.
perusHinta :: PakettiLuokka -> Double
perusHinta luokka 
  = case luokka of
      Kirje -> 3.90
      PikkuPaketti -> 5.95
      Suurpaketti tilavuus -> 5.95 + 0.001 * fromIntegral tilavuus

-- Miten case ... of lause lasketaan. Nuoli alas tarkoittaa
-- aina yhtä sievennysaskelta:
-- perusHinta (Suurpaketti 4000)
--
-- ↓  (Korvataan perusHinta määritelmällään ja sidotaan luokka arvoon Suurpaketti 4000)
--
-- case (Suurpaketti 4000) of
--    Kirje -> 3.90
--    PikkuPaketti -> 5.95
--    Suurpaketti tilavuus -> 0.001 * fromIntegral tilavuus
--
-- ↓  (Suurpaketti ei ole kirje)
-- 
-- case (Suurpaketti 4000) of
--    PikkuPaketti -> 5.95
--    Suurpaketti tilavuus -> 0.001 * fromIntegral tilavuus
--
-- ↓ (Eikä se ole pikkupaketti)
--
-- case (Suurpaketti 4000) of
--    Suurpaketti tilavuus -> 0.001 * fromIntegral tilavuus
--
-- ↓ (Nyt löytyi vastinen. Otetaan nuolen oikea puoli ja sidotaan tilavuus arvoon 4000)
--
-- 0.001 * fromIntegral 4000
--
-- ↓ (Lasketaan fromIntegral)
--
-- 0.001 * 4000.0


hintaKasvaa :: Int -> Int -> Int  -> Int -- -> Int -> Int 
               -> Bool
hintaKasvaa pituus leveys korkeus muutosx 
  = (hinta <| Ykkösluokka) (Suorakaide (n pituus) (n leveys) (n korkeus))
      <= hinta (Ykkösluokka (Suorakaide 
                                (n pituus + n muutosx) 
                                (n leveys ) 
                                (n korkeus )))

 where
   -- n jokuInt = max 1 (fromIntegral (abs jokuInt))
   -- n = abs |> fromIntegral |> max 1
   n = max 1 . fromIntegral . abs

main :: IO ()
main = do
  putStrLn "Kasvaako hinta"
  quickCheck hintaKasvaa