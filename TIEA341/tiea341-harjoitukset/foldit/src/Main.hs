{-#OPTIONS_GHC -fno-warn-missing-signatures#-}
module Main where
import Prelude hiding (map,foldr)

map :: forall a b. (a -> b) -> [a] -> [b]
map f lista 
 = foldr (\eka loputMäpätty -> f eka : loputMäpätty) [] lista
summa :: forall a. Num a => [a] -> a
summa = foldr (\eka kissa -> eka + kissa) 0

foldr :: (t1 -> t2 -> t2) -> t2 -> [t1] -> t2
foldr _laitaYhteen tyhja []
 = tyhja
foldr laitaYhteen tyhja (eka : loput)
 = let
    loputFoldattu = foldr laitaYhteen tyhja loput
   in laitaYhteen eka loputFoldattu

---------------------------------------------------

alkupMaksimiA :: forall a. Ord a => [a] -> Maybe a
alkupMaksimiA = omaFoldl Nothing Just (\eka loput -> max loput eka)
alkupSummaA :: forall a . Num a => [a] -> a
alkupSummaA = omaFoldl 0 (\x -> x) (\eka loput -> eka + loput) 

-- maksimiA :: forall a b. Ord a => Ord b => b -> (a -> b) -> (a -> a -> a) -> [a] -> b
-- 
-- maksimiA tyhja _f _yhdiste [] = tyhja
-- maksimiA _tyhja f yhdiste (eka : loput) = f (maksimiApu eka loput)
--  where
--     maksimiApu :: a -> [a] -> a
--     maksimiApu suurinTähänMennessä [] 
--         = suurinTähänMennessä
-- 
--     maksimiApu !suurinTähänMennessä (eka1 : loput1) 
--         = maksimiApu (yhdiste eka1 suurinTähänMennessä) loput1
-- 
-- summaA :: forall a b. Num a => Num b => b -> (a -> b) -> (a -> a -> a) -> [a] -> b
-- 
-- summaA tyhja _f _yhdiste [] = tyhja
-- summaA _tyhja f yhdiste (eka : loput) = f (summaApu eka loput)
--  where
--     summaApu :: a -> [a] -> a
--     summaApu summaNyt [] 
--         = summaNyt
-- 
--     summaApu !summaNyt (eka1 : loput1) 
--         = summaApu (yhdiste eka1 summaNyt) loput1

omaFoldl :: forall a b. b -> (a -> b) -> (a -> a -> a) -> [a] -> b
omaFoldl tyhja _f _yhdiste [] = tyhja
omaFoldl _tyhja f yhdiste (eka : loput) = f (foldlApu eka loput)
 where
    foldlApu :: a -> [a] -> a
    foldlApu tulosNyt [] = tulosNyt
    foldlApu !tulosNyt (eka1 : loput1) = foldlApu (yhdiste eka1 tulosNyt) loput1

---------------------------------------------------------

summafold1 :: forall a . Num a => [a] -> a
-- summafold1 lista = foldl' (id (+)) 0 lista
summafold1 = foldl' (+) 0

foldl_ :: forall a b. (a -> a -> a) -> b -> (a -> b) -> [a] -> b
foldl_ _f tyhja _lopullinen [] = tyhja
foldl_ f _tyhja lopullinen (eka : loput) = lopullinen (foldlApu eka loput)
 where
    foldlApu :: a -> [a] -> a
    foldlApu tulosNyt [] = tulosNyt
    foldlApu !tulosNyt (eka1 : loput1) = foldlApu (f eka1 tulosNyt) loput1

summafold2 :: forall a . Num a => [a] -> a
summafold2 = foldl_ (+) 0 id

minimi :: forall a . Ord a => [a] -> Maybe a
minimi = foldl_ min Nothing Just

minimi' :: forall a . Ord a => [a] -> Maybe a
minimi' = foldl' haeMin Nothing
 where
  haeMin välitulos alkio 
   = case välitulos of
      Nothing -> Just alkio
      Just pienin -> Just (min alkio pienin)

arvoväli :: forall a . (Ord a, Num a) => [a] -> a
arvoväli lista = (foldl_ max 0 id lista) - (foldl_ min 0 id lista)

arvoväli' :: forall a . (Ord a, Num a) => [a] -> a
arvoväli' lista = lopuksi (foldl' f (Nothing, Nothing) lista)
 where
  lopuksi :: (Maybe a, Maybe a) -> a 
  lopuksi ehkäRajat 
   = case ehkäRajat of
      (Just pienin, Just suurin) -> suurin - pienin
      _ -> 0
  f :: (Maybe a, Maybe a) -> a -> (Maybe a, Maybe a)
  -- f (ehkäPienin, ehkäSuurin) alkio -- ei toimi
  --  = ( teeMaybelle (min alkio) ehkäPienin -- ei toimi
  --    , teeMaybelle (max alkio) ehkäSuurin) -- ei toimi
  f (ehkäPienin, ehkäSuurin) alkio
   = ( case ehkäPienin of 
        Nothing -> Just alkio
        Just pienin -> Just (min pienin alkio)
      ,
       case ehkäSuurin of
        Nothing -> Just alkio
        Just suurin -> Just (max suurin alkio)
     )

--teeMaybelle :: forall a b. (a->b) -> Maybe a -> Maybe b
--teeMaybelle _f Nothing = Nothing
--teeMaybelle f (Just x) = Just (f x)

--data EkaVaiToka = Eka | Toka
--partition :: forall a . (a -> EkaVaiToka) -> [a] -> ([a],[a])
--partition 
-- = foldr

main :: IO ()
main = do
  putStrLn "Hello TIEA341"
