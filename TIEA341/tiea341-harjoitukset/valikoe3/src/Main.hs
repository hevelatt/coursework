module Main where
import Test.QuickCheck
import Data.Char -- Tämä heti module rivin alle

data Osa = Eka | Toka | Kolmas

modulo3 :: Int -> Osa
modulo3 n = case mod n 3 of
                1 -> Eka
                2 -> Toka
                _ -> Kolmas
                
kirjainLuokka :: Char -> Osa
kirjainLuokka c 
    | isUpper c = Eka
    | isSpace c = Kolmas
    | otherwise = Toka

jaaKolmeenOsaan :: forall a. (a -> Osa) -> [a] -> ([a],[a],[a])
jaaKolmeenOsaan _f [] = ([],[],[])
jaaKolmeenOsaan f (eka:loput)
 = let 
    (lista1, lista2, lista3) = jaaKolmeenOsaan f loput
   in case f eka of
    Eka -> (eka:lista1, lista2, lista3)
    Toka -> (lista1, eka:lista2, lista3)
    Kolmas -> (lista1, lista2, eka:lista3)

data BHP avain = Tyhjä 
               | Yksi avain
               | Haara avain
                    (BHP avain)
                    (BHP avain)
                deriving Show

irroitaPienin :: forall a . BHP a -> Maybe (a, BHP a)
irroitaPienin Tyhjä 
 = Nothing
irroitaPienin (Yksi avain) 
 = Just (avain, Tyhjä)
irroitaPienin (Haara avain vasen oikea)
 = case irroitaPienin vasen of
    Nothing 
     -> Just (avain, oikea)
    Just (pieninAlkio, vasenEiPienintä)
     -> Just (pieninAlkio, Haara avain vasenEiPienintä oikea)

järjestetyksiListaksi :: forall a. Ord a => BHP a -> [a]
järjestetyksiListaksi puu
 = case irroitaPienin puu of
    Nothing 
     -> []
    Just (pieninAlkio, puuIlmanPienintä) 
     -> pieninAlkio:(järjestetyksiListaksi puuIlmanPienintä)

esimerkkiPuu :: BHP Int
esimerkkiPuu 
 = Haara 8 
  (Haara 3 
   (Yksi 1) 
   (Haara 6 
    (Yksi 4) 
    (Yksi 7)
   )
  )
  (Haara 10 
   Tyhjä 
   (Haara 14 
    (Yksi 13) 
    Tyhjä
   )
  )

-- Eka testi, tämän saat ilmaiseksi!
-- Hypoteesi: unwords (words x) on sama kuin x kaikille x:
testi_1 :: [Char] -> Bool
testi_1 merkkilista = unwords (words (fromString merkkilista)) == fromString merkkilista
-- Tulos: Ei pitänyt paikkaansa! Jos on vain välilyöntejä, ne katoavat!

-- Testi 2:
-- Hypoteesi: reverse (reverse x) on sama kuin x kaikille x:
-- Oletan että pitää paikkansa
testi_2 :: [Char] -> Bool
testi_2 merkkilista = reverse (reverse merkkilista) == merkkilista
-- Tulos: Piti paikkansa!

-- Testi 3:
-- Hypoteesi: length x + length y on sama kuin length x++y kaikille x ja y:
-- Oletan että pitää paikkansa
testi_3 :: [Char] -> [Char] -> Bool
testi_3 lista1 lista2 = length lista1 + length lista2 == length (lista1 ++ lista2)
-- Tulos: Piti paikkansa!

-- Testi 4:
-- Hypoteesi: toString (fromString x) on sama kuin x kaikille x:
-- Oletan että pitää paikkansa
testi_4 :: [Char] -> Bool
testi_4 merkkilista = toString (fromString merkkilista :: Text) == merkkilista
-- Tulos: Piti paikkansa!

-- Testi 5:
-- Hypoteesi: length (unwords (words x)) on sama kuin length x kaikille x:
-- Oletan että ei toimi, samasta syystä kuin ensimmäinen testi (katoavat välilyönnit)
testi_5 :: [Char] -> Bool
testi_5 merkkilista = length (toString (unwords (words (fromString merkkilista)))) == length merkkilista
-- Tulos: Ei pitänyt paikaansa! Jos vain välilyöntejä ne katoavat!

main :: IO ()
main = do
  putTextLn "Testi 1"
  quickCheck testi_1
  putTextLn "Testi 2"
  quickCheckWith stdArgs{maxSuccess=1000} testi_2
  putTextLn "Testi 3"
  quickCheckWith stdArgs{maxSuccess=10000} testi_3
  putTextLn "Testi 4"
  quickCheck testi_4
  putTextLn "Testi 5"
  quickCheckWith stdArgs{maxSuccess=500} testi_5
