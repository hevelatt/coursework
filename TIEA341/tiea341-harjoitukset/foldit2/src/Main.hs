module Main where
import Data.Char

maksimiAAlkuperäinen :: forall a. Ord a => [a] -> Maybe a 
maksimiAAlkuperäinen = foldlPilkku päivitäMax Nothing 
 where
  päivitäMax välitulos eka
   = case välitulos of
      Nothing -> Just eka 
      Just !väli -> Just (max eka väli)

summaFoldilla :: forall a. Num a => [a] -> a 
-- summaFoldilla = foldlPilkku (+) 0
summaFoldilla = foldlPilkku (\alkio välitulosSumma -> alkio + välitulosSumma) 0

foldlPilkku :: forall t1 t2. (t1 -> t2 -> t1) -> t1 -> [t2] -> t1
foldlPilkku päivitäVälitulos tyhjä lista = apu tyhjä lista
 where
  apu välitulos [] = välitulos
  apu !välitulos (eka:loput)
   = apu (päivitäVälitulos välitulos eka) loput

yksikäänEiFalse :: [Bool] -> Bool
yksikäänEiFalse = foldr (&&) True

onListassa :: forall a. Eq a => a -> [a] -> Bool
onListassa etsitty = foldr (\alkio onkoLopuissa -> alkio == etsitty || onkoLopuissa) False

-- etsi :: forall a. (a -> Bool) -> [a] -> Maybe a 
-- etsi f = foldr onkoTrue Nothing
--  where
--   onkoTrue alkio onkoLoputTrue
--    = case f alkio of
--       True -> Just alkio
--       False -> onkoLoputTrue

etsi :: forall a. (a -> Bool) -> [a] -> Maybe a 
etsi haku = foldr f Nothing
 where
  f :: a -> Maybe a -> Maybe a
  f eka lopuistaEhkäLöydetty
   | haku eka = Just eka
   | otherwise = lopuistaEhkäLöydetty

data EkaVaiToka = Eka | Toka

partition :: forall a . (a -> EkaVaiToka) -> [a] -> ([a],[a])
partition ekaanVaiTokaan = foldr f ([],[])
 where
  f :: a -> ([a],[a]) -> ([a],[a])
  f eka (lista1,lista2)
   = case ekaanVaiTokaan eka of
      Eka -> (eka:lista1,lista2)
      Toka -> (lista1,eka:lista2)

-- sekaisin :: [Char] -> (Bool,[Char])
-- sekaisin = foldr isoksiTaiPieneksi (True,[])
--  where 
--   isoksiTaiPieneksi eka loputSekaisin
--    = case loputSekaisin of 
--       (True, loputMerkit) -> (False, (toUpper eka):loputMerkit)
--       (False, loputMerkit) -> (True, (toLower eka):loputMerkit)

sekaisin :: [Char] -> (Bool,[Char])
sekaisin = foldr f (True,[])
 where
  f :: Char -> (Bool, [Char]) -> (Bool, [Char])
  f eka (olikoIso,loputSekaisin)
   | olikoIso = (False, toUpper eka : loputSekaisin)
   | otherwise = (True, toLower eka : loputSekaisin)

poistaViimeinen_ :: forall a. Eq a => a -> [a] -> (Bool,[a])
poistaViimeinen_ x = foldr f (True, [])
 where 
  f :: a -> (Bool,[a]) -> (Bool,[a])
  f eka (eiOlePoistettu,loputJostaEhkäPoistettu)
   | eka == x && eiOlePoistettu = (False, loputJostaEhkäPoistettu)
   | otherwise = (eiOlePoistettu, eka : loputJostaEhkäPoistettu)

poistaViimeinen :: forall a. Eq a => a -> [a] -> [a]
poistaViimeinen poistettava lista
 = case poistaViimeinen_ poistettava lista of
    (_, lista2) -> lista2

-----------

-- nollaaJälkeen n lista = foldr f tyhjä lista
-- nollaaJälkeen 2 [1,2,3,4,5,6]
-- f 1 [2,0,0,0,0] -> [1,2,0,0,0,0]
-- nollaaJälkeen 2 [2,3,4,5,6]
-- f 2 [0,0,0,0] -> [2,0,0,0,0]
-- nollaaJälkeen 2 [3,4,5,6]
-- f 3 [0,0,0] -> [0,0,0,0]

data OnkoNähty = OnNähtyN | EiOllaNähtyN deriving Eq 

type Tulos a = OnkoNähty -> [a]

nollaaJälkeen_ :: forall a. (Eq a, Num a) => a -> [a] -> (OnkoNähty -> [a])
nollaaJälkeen_ n lista = foldr f tyhjä lista 
 where 
  tyhjä _ = [] 
  f :: a -> (OnkoNähty -> [a]) -> OnkoNähty -> [a]
  f listanEka tulosLopulle EiOllaNähtyN
   | listanEka == n = listanEka : tulosLopulle OnNähtyN
   | otherwise      = listanEka : tulosLopulle EiOllaNähtyN
  f _listanEka tulosLopulle OnNähtyN = 0 : tulosLopulle OnNähtyN
       
nollaaJälkeen :: forall a. (Eq a, Num a) => a -> [a] -> [a]
nollaaJälkeen n lista = nollaaJälkeen_ n lista EiOllaNähtyN

---

ota2 :: (Eq t, Num t) => t -> [a] -> [a]
ota2 _n [] = []
ota2 n (eka:loput)
 | n == 0 = []
 | otherwise = eka : ota2 (n-1) loput

ota3 n lista = (foldr (\eka floput m -> if m > 0 then eka:floput (m-1) else [])
                       (const []) lista) n

ota_ :: forall a. [a] -> (Natural -> [a])
ota_ = foldr f tyhjä
 where
  tyhjä :: Natural -> [a]
  tyhjä _ = []
  f :: a -> (Natural -> [a]) -> (Natural -> [a])
  f listanEka tulosLopuille 
   = (\kuinkaMontaVielä 
       -> case kuinkaMontaVielä > 0 of
           True -> listanEka : tulosLopuille (kuinkaMontaVielä - 1)
           False -> [])

ota :: forall a. Natural -> [a] -> [a]
ota n lista = ota_ lista n

main :: IO ()
main = do
 putStrLn "Hello TIEA341"
