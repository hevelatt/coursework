module Funktiot2 where

yhdistä :: (b -> c) -> (a -> b) -> (a -> c)
yhdistä g f x = g (f x)
