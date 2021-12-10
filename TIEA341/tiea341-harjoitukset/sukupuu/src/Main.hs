module Main where
import Prelude hiding ((>=>),(>>=))
import qualified Data.Map as Map
data Suhde = Isä | Äiti | Sisarus deriving (Eq,Ord,Show)
type Nimi = Text

type SukuPuu = Map (Nimi,Suhde) Nimi

isä :: Nimi -> SukuPuu -> Maybe Nimi
isä n = Map.lookup (n,Isä)

äiti :: Nimi -> SukuPuu -> Maybe Nimi
äiti n = Map.lookup (n,Äiti)

isoIsä :: Nimi -> SukuPuu -> Maybe Nimi
isoIsä n p = case (isä n p) of       -- Miksei isä (isä n) tai jopa isä |> isä?
            Nothing -> Nothing
            Just m  -> isä m p
isoIsä2 :: SukuPuu -> Nimi -> Maybe Nimi
isoIsä2 sukupuu = vaihda isä sukupuu 
                   >=> vaihda isä sukupuu
                  -- >=> vaihda äiti sukupuu 
                  -- >=> vaihda äiti sukupuu 
vaihda :: forall a b c. (a -> b -> c) -> (b -> a -> c)
vaihda f = \x y -> f y x

isoÄiti :: Nimi -> SukuPuu -> Maybe Nimi
isoÄiti n p = case (äiti n p) of -- Miksei äiti (äiti n) tai jopa äiti |> äiti?
            Nothing -> Nothing
            Just m  -> äiti m p
isoÄiti2 :: SukuPuu -> Nimi -> Maybe Nimi
isoÄiti2 sukupuu = vaihda äiti sukupuu >=> vaihda äiti sukupuu

(<|) :: forall a b c. (b -> c) -> (a -> b) -> (a -> c)
f <| g = \seA -> f (g seA)
(|>) :: forall a b c. (a -> b) -> (b -> c) -> (a -> c)
f |> g = \seA -> g (f seA)

(>=>) :: forall a b c. (a -> Maybe b) -> (b -> Maybe c) -> (a -> Maybe c)
f >=> g = \x -> case f x of 
                 Just a -> g a 
                 Nothing -> Nothing

-- ovatkoSerkkuja :: SukuPuu -> Nimi -> Nimi -> Maybe Bool
-- ovatkoSerkkuja sukupuu nimi1 nimi2 
--  = case isoIsä nimi1 sukupuu of 
--     Nothing -> Nothing
--     Just isoIsäN1 -> case isoIsä nimi2 sukupuu of 
--                       Nothing -> Nothing
--                       Just isoIsäN2 -> Just (isoIsäN2 == isoIsäN1)
-- Ei riitä

(>>=) :: Maybe a -> (a -> Maybe b) -> Maybe b
Just a >>= f = f a 
Nothing >>= _f = Nothing

ovatkoSerkkuja :: SukuPuu -> Nimi -> Nimi -> Maybe Bool
ovatkoSerkkuja sukupuu nimi1 nimi2 
 = isoIsä nimi1 sukupuu >>= (\isoIsäN1 -> 
   isoIsä nimi2 sukupuu >>= (\isoIsäN2 -> 
   Just (isoIsäN1 == isoIsäN2)))
-- = isoIsä nimi1 sukupuu >>= teeEkalleIsoIsälle
--    where
--     teeEkalleIsoIsälle isoIsäN1 = isoIsä nimi2 sukupuu >>= teePäätös isoIsäN1 
--     teePäätös isoIsäN1 isoIsäN2 = Just (isoIsäN1 == isoIsäN2)

sisarus :: Nimi -> SukuPuu -> Maybe Nimi
sisarus n = Map.lookup (n,Sisarus)

(>>==) :: forall a b. Maybe a -> (a -> Maybe b) -> Maybe b
ehkäA >>== aEhkäBksi = case ehkäA of
                         Just seA -> aEhkäBksi seA 
                         Nothing -> Nothing 
sekvenssoi :: [Maybe a] -> Maybe [a]
sekvenssoi _ = undefined              

main :: IO ()
main = do
  putStrLn "Hello TIEA341"