module Main where
import Test.QuickCheck

-- (B)inäärinen (H)aku(P)uu
data BHP avain
 = Tyhjä
 | Yksi avain
 | Haara avain 
  (BHP avain)
  (BHP avain)
 deriving Show

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

onkoPuussa :: forall a. Ord a => a -> BHP a -> Bool
onkoPuussa _etsitty Tyhjä = False
onkoPuussa etsitty (Yksi avain) = etsitty == avain
onkoPuussa etsitty (Haara avain vasen oikea)
 = let
    onkoVasemmalla = onkoPuussa etsitty vasen
    onkoOikealla = onkoPuussa etsitty oikea
   --  tulos
   --   | avain == etsitty = True
   --   | etsitty < avain = onkoVasemmalla
   --   | otherwise = onkoOikealla
   -- in tulos
   in case compare etsitty avain of
    EQ -> True
    LT -> onkoVasemmalla
    GT -> onkoOikealla
   -- in case etsitty == avain of
   --  True -> True
   --  False | etsitty < avain -> onkoVasemmalla
   --        | otherwise -> onkoOikealla

haePienin :: forall a . BHP a -> Maybe a
haePienin Tyhjä = Nothing
haePienin (Yksi avain) = Just avain
haePienin (Haara avain vasen _oikea)
 = case haePienin vasen of
    Nothing -> Just avain
    Just a -> Just a

lisää :: forall a . Ord a => a -> BHP a -> BHP a
lisää lisättävä Tyhjä = Yksi lisättävä
lisää lisättävä (Yksi avain)
 | lisättävä < avain = Haara avain (Yksi lisättävä) Tyhjä
 | otherwise = Haara avain Tyhjä (Yksi lisättävä)
lisää lisättävä (Haara avain vasen oikea)
 | lisättävä < avain = Haara avain (lisää lisättävä vasen) oikea
 | otherwise = Haara avain vasen (lisää lisättävä oikea)
-- = let
--    lisääVasempaan = lisää lisättävä vasen
--    lisääOikeaan = lisää lisättävä oikea 
--   in case lisättävä < avain of
--    True -> Haara avain lisääVasempaan oikea
--    False -> Haara avain vasen lisääOikeaan

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

-- Jos puu on Tyhjä tai Yksi niin triviaalia
-- Jos puu on haara missä ei ole poistetavaa alkiota:
--     Poista oikealta tai vasemmalta
-- Jos puu on haara ja sen alkio on poistettava:
--     Poista sen oikeasta alipuusta pienin alkio ja tee pienimmästä uusi arvo tälle haaralle
--     Jos oikeaa alipuuta ei ole, korvaa vain kokonaan vasemmalla alipuulla.

poista :: forall a . Ord a => a -> BHP a -> BHP a
poista _ Tyhjä = Tyhjä
poista poistettava (Yksi arvo)
 | poistettava == arvo = Tyhjä
 | otherwise = Yksi arvo
poista poistettava (Haara arvo vasen oikea)
 | poistettava < arvo = Haara arvo (poista poistettava vasen) oikea
 | poistettava > arvo = Haara arvo vasen (poista poistettava oikea)
 | otherwise
    = case irroitaPienin oikea of
       Nothing -- Jos oikealta ei löydy pienintä se on sama kuin jos oikea olisi Tyhjä
        -> vasen 
       Just (pienin, oikeaIlmanPienintä) 
        -> Haara pienin vasen oikeaIlmanPienintä

listaPuuksi :: forall a. Ord a => [a] -> BHP a
listaPuuksi [] = Tyhjä
listaPuuksi (eka:loput)
 = lisää eka (listaPuuksi loput)

-- Päteekö kaikille x ja xs, onkoPuussa x (listaPuuksi (x:xs))
-- onkoPuussa x (lisää x puu) pätee kaikille x:lle ja kaikille puu:ille
-- Entäpä not (onkoPuussa x (poista x puu))? 

testi_1 :: Int -> [Int] -> Bool
testi_1 eka loput = onkoPuussa eka puu
 where puu = listaPuuksi (eka:loput)

testi_2 :: Int -> [Int] -> Bool
testi_2 x xs = onkoPuussa x (lisää x puu)
 where puu = listaPuuksi xs

testi_3 :: Int -> [Int] -> Bool
testi_3 x xs = not (onkoPuussa x (poista x puu))
 where puu = listaPuuksi (ordNub xs)

validi :: forall a. Ord a => BHP a -> Bool
validi Tyhjä = True
validi (Yksi _) = True
validi (Haara arvo vasen oikea) 
 = case (vasen, oikea) of 
    ((Haara vasenArvo _ _), (Haara oikeaArvo _ _))
     -> vasenArvo < arvo && arvo <= oikeaArvo && loputValideja
    ((Haara vasenArvo _ _), (Yksi oikeaArvo))
     -> vasenArvo < arvo && arvo <= oikeaArvo && loputValideja
    ((Haara vasenArvo _ _), (Tyhjä))
     -> vasenArvo < arvo                      && loputValideja
    ((Yksi vasenArvo), (Haara oikeaArvo _ _))
     -> vasenArvo < arvo && arvo <= oikeaArvo && loputValideja
    ((Yksi vasenArvo), (Yksi oikeaArvo))
     -> vasenArvo < arvo && arvo <= oikeaArvo && loputValideja
    ((Yksi vasenArvo), (Tyhjä))
     -> vasenArvo < arvo                      && loputValideja
    ((Tyhjä), (Haara oikeaArvo _ _))
     -> arvo <= oikeaArvo                     && loputValideja
    ((Tyhjä), (Yksi oikeaArvo))
     -> arvo <= oikeaArvo                     && loputValideja
    ((Tyhjä), (Tyhjä))
     ->                                          loputValideja
    where loputValideja = validi vasen && validi oikea

järjestetyksiListaksi :: forall a. Ord a => BHP a -> [a]
järjestetyksiListaksi puu
 = case irroitaPienin puu of
    Nothing -> []
    Just (pieninAlkio, puuIlmanPienintä) -> pieninAlkio:(järjestetyksiListaksi puuIlmanPienintä)

main :: IO ()
main = do
  putTextLn "Testi 1"
  quickCheck testi_1
  putTextLn "Testi 2"
  quickCheck testi_2
  putTextLn "Testi 3"
  quickCheck testi_3
  putStrLn "Hello TIEA341"
