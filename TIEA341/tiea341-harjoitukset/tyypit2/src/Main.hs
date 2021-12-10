{-# LANGUAGE TypeApplications #-}
module Main where

otaToka :: forall eka. forall toka. (eka, toka) -> toka
otaToka (_x, y) = y 
-- otaToka pari = case pari of
--                 (_,y) -> y

otaIntBool :: (Int, Bool) -> Bool
otaIntBool = otaToka @Int @Bool

{-
words :: Text -> [Text]
sort :: forall a. Num a => [a] -> [a]
take :: forall a. Int -> [a] -> [a]
reverse :: forall a. [a] -> [a]
-}

pisimmät :: Text -> [Text]
pisimmät syöte
  = let
     sanat          = words syöte
     järjestyksessä = sort @Text sanat :: [Text]
     käänteisessä   = reverse @Text järjestyksessä :: [Text]
     ekat           = take @Text 5 käänteisessä :: [Text]
    in ekat

f :: [a] -> Maybe a
f [] = Nothing
--f (eka:_loput) = Just eka
f lista = case lista of
            [a,_b,_c] -> Just a
            _ -> Nothing

-- f [1,2,3] = Just 1
-- f palauttaa listan ensimmäisen alkion

--t2 :: Text -> Text
--t2 = unwords . map reverse . words

class Puoliryhmä a where
  (+++) :: a -> a -> a
-- 1
instance Puoliryhmä Natural where
  vasen +++ oikea = oikea + vasen
  -- (+++) vasen oikea = vasen + oikea

-- 2
data Väri = Punainen | Keltainen | Oranssi 
  deriving Show

--instance Puoliryhmä Väri where
--  Punainen +++ Punainen = Punainen
--  Punainen +++ Keltainen = Oranssi
--  Punainen +++ Oranssi = Oranssi
--  Oranssi +++ Keltainen = Oranssi
--  Oranssi +++ Oranssi = Oranssi
--  Oranssi +++ Punainen = Oranssi
--  Keltainen +++ Keltainen = Keltainen
--  Keltainen +++ Punainen = Oranssi
--  Keltainen +++ Oranssi = Oranssi

instance Puoliryhmä Väri where
  vasen +++ oikea = case (vasen, oikea) of
                      (Punainen,Keltainen) -> Oranssi
                      (Punainen,Punainen) -> Punainen
                      _ -> Oranssi

instance Puoliryhmä [a] where
  vasen +++ oikea = vasen ++ oikea

{-#LANGUAGE TypeApplications#-}
module Main where

filterText :: (Char -> Bool) -> Text -> Text
filterText ehto teksti = fromString (filter ehto (toString teksti))

-- Erikoista forall -tyyppiset alilausekkeet  alla olevassa funktiossa.
-- Tehtävässä on helpotuksena @?? merkinnät, joihin
-- on tarpeen täydentää. Muita kuin merkattuja kohtia ei tarvitse
-- täydentää.
--
filterIT :: Text -> Text
filterIT syöte
  = let
     ilmanVälimerkkejä
        = filterText (\x -> notElem x [',','.',';',':','"','\'']) syöte

     riveinä = lines ilmanVälimerkkejä

     sanalistana = map @?? @?? words riveinä

     huonotPois :: [Text] -> [Text]
     huonotPois rivi = filter (\x -> notElem x ["sekä","niin"]) rivi

     ilmanSanoja = map @?? @?? huonotPois sanalistana

     rivitKoottuna = map @?? @?? unwords ilmanSanoja

     tekstiYhdessä = unlines rivitKoottuna

    in tekstiYhdessä


main :: IO ()
main = do
  putStrLn "Hello TIEA341"
