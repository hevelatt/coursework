{-# LANGUAGE KindSignatures #-}
module Main where

data Lista a
 = Yksi a
 | Kaksi a a
 | Kolme a a a
 deriving Show

esim :: Lista Bool
esim = Kaksi True False

kasvataYhdellä :: Lista Int -> Lista Int
--kasvataYhdellä (Yksi x) = Yksi (x + 1)
--kasvataYhdellä (Kaksi x y) = Kaksi (x + 1) (y + 1)
--kasvataYhdellä (Kolme x y z) = Kolme (x + 1) (y + 1) (z + 1)
kasvataYhdellä lista
 = case lista of
    Yksi x -> Yksi (x + 1)
    Kaksi x y -> Kaksi (x + 1) (y + 1)
    Kolme x y z -> Kolme (x + 1) (y + 1) (z + 1)

data Kenttä (a::Type) (b::Type)
 = Tyhjä 
 | Yksiarvoinen a
 | Kaksiarvoinen a b
 | Sisäkkäinen a (Kenttä a b)
 deriving Show

esimerkkiKenttä :: Kenttä Text Int
esimerkkiKenttä = Kaksiarvoinen "Kalle" 5

esimerkkiKenttä2 :: Kenttä Text Int
esimerkkiKenttä2 = Sisäkkäinen "Henkilö" esimerkkiKenttä

kasvataHenkilönIkää :: Kenttä Text Int -> Kenttä Text Int
kasvataHenkilönIkää kenttä
 = case kenttä of 
    Sisäkkäinen otsikko sisä
     -> case sisä of
         Kaksiarvoinen nimi ikä -> Sisäkkäinen otsikko (Kaksiarvoinen nimi (ikä + 1))
         Yksiarvoinen a -> Sisäkkäinen otsikko (Yksiarvoinen a)
         Sisäkkäinen a b -> Sisäkkäinen otsikko (Sisäkkäinen a b)
         Tyhjä -> Sisäkkäinen otsikko (Tyhjä)
    Kaksiarvoinen nimi ikä
     -> Kaksiarvoinen nimi (ikä + 1)
    Yksiarvoinen a 
     -> Yksiarvoinen a
    Tyhjä
     -> Tyhjä

main :: IO ()
main = do
 putStrLn "Hello TIEA341"