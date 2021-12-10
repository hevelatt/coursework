module Main where
import System.Environment ( getArgs )

laskeRivit :: Text -> Natural
laskeRivit txt = genericLength (lines txt)

epätyhjiäRivejä :: Text -> Natural
epätyhjiäRivejä txt 
 = let 
    rivit = lines txt :: [Text]
    epätyhjät = filter onkoEpätyhjä rivit -- filter (\rivi -> not (rivi == "")) rivit
    onkoEpätyhjä teksti
     | teksti == "" = False
     | otherwise = True
   in genericLength epätyhjät

sanaaRivillä :: Text -> [Natural]
sanaaRivillä txt 
 = let
    rivit = lines txt :: [Text]
    sanat = map words rivit :: [[Text]]
    sanoja = map genericLength sanat :: [Natural]
   in sanoja

sanaaKeskimäärin :: Text -> Maybe Natural
sanaaKeskimäärin txt
 = case txt of
    "" -> Nothing
    _ ->
     let 
      sanoja = sanaaRivillä txt :: [Natural]
      sanat_lkm = sum sanoja :: Natural
      rivit_lkm = genericLength sanoja :: Natural
      sanat_lkm_double = fromIntegral sanat_lkm :: Double
      rivit_lkm_double = fromIntegral rivit_lkm :: Double
      keskiarvo = sanat_lkm_double / rivit_lkm_double :: Double
      pyöristetty_ka = floor keskiarvo :: Natural
     in 
      case () of
       _ | rivit_lkm == 0 -> Nothing
         | otherwise -> Just pyöristetty_ka

sanojaKeskimäärin :: Text -> Maybe Double
sanojaKeskimäärin teksti
 | rivienMäärä > 0 = Just keskiarvo
 | otherwise = Nothing
 where
  rivit = lines teksti
  sanat = words teksti

  keskiarvo = sanojenMäärä / rivienMäärä

  rivienMäärä, sanojenMäärä :: Double
  rivienMäärä = genericLength rivit
  sanojenMäärä = genericLength sanat

{-
mitta :: forall a. [a] -> Int
mitta lista
 = case lista of
    [] -> 0
    _:loput -> 1 + mitta loput
-}

mitta :: forall a. [a] -> Int
mitta [] = 0
mitta (_:loput) = 1 + mitta loput

{-
kaikkiOnTrue :: [Bool] -> Bool
kaikkiOnTrue lista
 = case lista of
    [] -> True
    eka:loput | eka -> kaikkiOnTrue loput
              | otherwise -> False
-}

{-
kaikkiOnTrue :: [Bool] -> Bool
kaikkiOnTrue [] = True
kaikkiOnTrue (eka:loput)
 | eka = kaikkiOnTrue loput
 | otherwise = False
-}

kaikkiOnTrue :: [Bool] -> Bool
kaikkiOnTrue [] = True
kaikkiOnTrue (eka:loput) = eka && kaikkiOnTrue loput
-- [True,False,True]
-- eka = True, loput = False
-- kaikkiLoputOnTrue = False
-- haluttuTulos = False = eka && kaikkiLoputOnTrue

mäppää :: forall a b. (a -> b) -> [a] -> [b]
mäppää _ [] = []
mäppää f (eka:loput) = (f eka):(mäppää f loput) -- https://stackoverflow.com/a/24613093

{-
suodata :: forall a. (a -> Bool) -> [a] -> [a]
suodata _ [] = []
suodata f (eka:loput)
 = case () of
    _ | f eka -> (eka:(suodata f loput))
      | otherwise -> suodata f loput
--=case f eka of
--  True -> (eka:(suodata f loput))
--  False -> suodata f loput
-}

suodata :: forall a. (a -> Bool) -> [a] -> [a]
suodata _ [] = []
suodata f (eka:loput)
 | f eka = eka:(suodatettuLoppuLista)
 | otherwise = suodatettuLoppuLista
 where
  suodatettuLoppuLista = suodata f loput

{-
pienin :: forall a. (Ord a) => [a] -> Maybe a
pienin [] = Nothing
pienin (eka:loput)
 = let
    ehkäpienin = pienin loput
   in teejotain eka ehkäpienin
-}

--tyhjätPois :: forall a. [Maybe a] -> [a]


main :: IO ()
main = do
  argumentit <- getArgs
  case argumentit of
    [tiedostonNimi] -> do
      sisältö <- readFileText tiedostonNimi
      print (laskeRivit sisältö)
      print (epätyhjiäRivejä sisältö)
      print (sanaaRivillä sisältö)
      print (sanaaKeskimäärin sisältö)
    _ -> putTextLn "Anna yksi argumentti"
