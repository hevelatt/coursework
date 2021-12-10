module Main where
import Data.Char
--esimerkki    = {- Tähän joku lista, vaikkapa [1,3,2,6,5,4] -}
--eka          = {- Esimerkin eka alkio -}
--esimerkinLoput = {- Esimerkki ilman ekaa -} 
--haluttuTulos = {- Mitä pitäisi listalla tulla tulokseksi? -}
--funktionTulosLopuille = {- Mikä olisi tulos esimerkille ilman ekaa alkiota? -}
--funktionTulosTyhjälle = {- Mitä tyhjän lista tuottaa?-}
--{- Miten eka ja funktionTulosLopuille yhdistetään halutuksi tulokseksi? -}

--esimerkki                     = [1,3,2,6,5,4]
--eka                           = 1
--esimerkinLoput                = [3,2,6,5,4]
--partition even esimerkki      = ([1,3,5],[2,6,4])
--partition even esimerkinLoput = ([3,5],[2,6,4])
--partition even []             = ([],[])
--(1:[3,5],[2,6,4])

partition :: forall a. (a -> Bool) -> [a] -> ([a],[a])
partition _f [] = ([],[])
partition f (eka : loput) = 
 let
   partitionLopuille = partition f loput
 in case partitionLopuille of
      (vasen, oikea) | f eka -> (vasen, eka:oikea)
                     | otherwise -> (eka:vasen, oikea)
--partition f (eka : loput) = 
-- let
--   (ehtoEiTäyty,ehtoTäyttyy) = partition f loput
-- in (f eka) of
--      True -> (ehtoEiTäyty,eka:ehtoTäyttyy) 
--      False -> (eka:ehtoEiTäyty,ehtoTäyttyy) 

--esimerkki             = [1,7,8,12] [2,3,10]
--eka                   = 1
--esimerkinLoput        = [7,8,12] [2,3,10]
--haluttuTulos          = [1,2,3,7,8,10,12]
--funktionTulosLopuille = [2,3,7,8,10,12]
--funktionTulosTyhjälle = []
--eka:funktionTulosLopuille

merge :: Ord a => [a] -> [a] -> [a]
merge [] [] = []
merge [] lista2 = lista2
merge lista1 [] = lista1
merge (eka1:loput1) (eka2:loput2)
 | eka1 < eka2 = eka1:(merge loput1 (eka2:loput2))
 | otherwise = eka2:(merge (eka1:loput1) loput2)

-- esimerkki1         = [3,4,3,5,6,1]
-- loput              = [4,3,5,6,1]
-- eka                = 3
-- maksimiLopuista    = Just 6
-- maksimi esimerkki1 = Just 6

-- esimerkki2         = [6,3,4,3,5,1]
-- loput              = [3,4,3,5,1]
-- eka                = 6
-- maksimiLopuista    = Just 5
-- maksimi esimerkki2 = Just 6

maksimi :: forall a. Ord a => [a] -> Maybe a
maksimi [] = Nothing
maksimi (eka:loput)
 = let
    maksimiLopuista = maksimi loput :: Maybe a

    --teeLopullinenTulos :: a -> Maybe a -> Maybe a
    --teeLopullinenTulos seEka seMaksimiLopuista
    --  = case seMaksimiLopuista of
    --     Nothing -> Just seEka
    --     Just x | seEka > x -> Just seEka
    --            | otherwise -> seMaksimiLopuista
    teeLopullinenTulos :: a -> Maybe a -> Maybe a
    teeLopullinenTulos seEka (Just seMaksimiLopuista)
     | seEka > seMaksimiLopuista = Just seEka
     | otherwise = Just seMaksimiLopuista
    teeLopullinenTulos seEka Nothing = Just seEka

   in teeLopullinenTulos eka maksimiLopuista

-- esimerkki1         = [3,4,3,5,6,1]
-- loput              = [4,3,5,6,1]
-- eka                = 3
-- minimiLopuista     = Just 1
-- minimi esimerkki1  = Just 1

-- esimerkki2         = [0,3,4,3,5,1]
-- loput              = [3,4,3,5,1]
-- eka                = 0
-- minimiLopuista     = Just 0
-- minimi esimerkki2  = Just 1

minimi :: forall a. Ord a => [a] -> Maybe a
minimi [] = Nothing
minimi (eka:loput)
 = let 
    minimiLopuista = minimi loput :: Maybe a
    yhdiste :: a -> Maybe a -> Maybe a
    yhdiste seEka Nothing = Just seEka
    yhdiste seEka (Just seMinimiLopuista)
     | seMinimiLopuista < seEka = Just seMinimiLopuista
     | otherwise = Just seEka
    in yhdiste eka minimiLopuista

-- minimi :: forall a. Ord a => [a] -> Maybe a
-- minimi [] = Nothing
-- minimi (eka:loput) 
--  = case minimi loput of
--     Nothing -> Just eka
--     Just x | eka < x -> Just eka
--            | otherwise -> minimi loput -- otherwise -> Just x

-- minimi :: forall a. Ord a => [a] -> Maybe a
-- minimi [] = Nothing
-- minimi (eka:loput) 
--  | m == Nothing = Just eka
--  | Just eka < m = Just eka
--  | otherwise = m
--  where m = minimi loput

-- esimerkki0             = 6 [3,4,1,5,6,1]
-- loput                  = 6 [4,1,5,6,1]
-- eka                    = 3 /= 6
-- onListassa loput 6     = True
-- onListassa esimerkki1  = True

-- esimerkki1             = 3 [3,4,1,5,6,1]
-- loput                  = 3 [4,1,5,6,1]
-- eka                    = 3 == 3
-- onListassa 3 loput     = False
-- onListassa esimerkki1  = True

-- esimerkki2             = 6 [0,3,4,3,5,1]
-- loput                  = 6 [3,4,3,5,1]
-- eka                    = 0 /= 6
-- onListassa 6 loput     = False
-- onListassa esimerkki2  = False

-- esimerkki3             = 6 []
-- loput                  = 6 []
-- eka                    = []
-- onListassa 6 loput     = False
-- onListassa esimerkki2  = False

onListassa :: forall a. Eq a => a -> [a] -> Bool
onListassa _ [] = False
onListassa arvo (eka:loput) -- = (arvo == eka) || onListassa arvo loput
 | arvo == eka = True
 | otherwise = onListassa arvo loput

-- esimerkki1          = (\x -> x > 1) [3,4,1,1,1]
-- loput               = (\x -> x > 1) [4,1,1,1]
-- eka                 = 3 > 1
-- laskeLkm loput      = 1
-- laskeLkm esimerkki1 = 2 -- = 1+1+0

laskeLkm :: forall a. (a -> Bool) -> [a] -> Natural
laskeLkm _ [] = 0
laskeLkm f (eka:loput)
 | f eka = 1 + laskeLoput
 | otherwise = laskeLoput
  where 
    laskeLoput = laskeLkm f loput

--keskiarvo :: [Double] -> Maybe Double
--keskiarvo [] = Nothing
--keskiarvo (eka:loput) = undefined
--keskiarvoApu :: Double -> [Double] -> Maybe Double
--keskiarvoApu välitulos [] = Just välitulos
--keskiarvoApu !välitulos (eka:loput)
-- = keskiarvoApu (välitulos + eka) loput --Just välitulos + keskiarvoApu välitulos loput

-- esimerkki1         = [3,4,3,5,6,1]
-- loput              = [4,3,5,6,1]
-- eka                = 3
-- maksimiLopuista    = Just 6
-- maksimi esimerkki1 = Just 6

-- esimerkki2         = [6,3,4,3,5,1]
-- loput              = [3,4,3,5,1]
-- eka                = 6
-- maksimiLopuista    = Just 5
-- maksimi esimerkki2 = Just 6

maksimiAcc :: forall a. Ord a => [a] -> Maybe a
maksimiAcc lista = maksimiApu0 Nothing lista
 where
  maksimiApu0 :: Maybe a -> [a] -> Maybe a
  maksimiApu0 välitulos [] = välitulos
  maksimiApu0 välitulos (eka:loput)
   = maksimiApu0 (päivitäVälitulos välitulos eka) loput

  päivitäVälitulos :: Maybe a -> a -> Maybe a
  päivitäVälitulos välitulos eka
   = case välitulos of
      Just x | eka > x -> Just eka
             | otherwise -> välitulos
      Nothing -> Just eka

-------------------------------------------------------------------

-- Hoitaa tyhjän listan ja siirtää laskemisen maksimiApu-funktiolle
maksimiA :: forall a. Ord a => [a] -> Maybe a

maksimiA [] 
    = Nothing

maksimiA (eka : loput) 
    = Just (maksimiApu eka loput)   

-- Tekee varsinaisen laskennan.
maksimiApu :: forall a. Ord a => a -> [a] -> a

maksimiApu suurinTähänMennessä [] 
    = suurinTähänMennessä

maksimiApu !suurinTähänMennessä (eka : loput) 
    = maksimiApu (max suurinTähänMennessä eka) loput

keskiarvo :: [Double] -> Maybe Double
keskiarvo [] = Nothing
keskiarvo (eka:loput) = Just (keskiarvoApu (eka,1) loput)
keskiarvoApu :: (Double,Double) -> [Double] -> Double
keskiarvoApu (summa,lkm) [] = summa/lkm
keskiarvoApu (!summa,!lkm) (eka:loput)
 = keskiarvoApu (summa+eka,lkm+1) loput

main :: IO ()
main = do
  putStrLn "Hello TIEA341"
