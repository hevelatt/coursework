module Main where
import Test.QuickCheck

-- reverse (l1 ++ l2) == reverse l2 ++ reverse l1
-- testi_3 :: forall a. Eq a => [a] -> [a] -> Bool -- QuickCheck ei hyväksy polytyyppejä
testi_3 :: [Int] -> [Int] -> Bool
testi_3 l1 l2 = reverse (l1 ++ l2) == reverse l2 ++ reverse l1
-- all (\x -> x < maximum xs) xs
testi_4 :: Int -> [Int] -> Bool
testi_4 eka xs = all (\x -> x <= maximum1 xs1) xs1
 where 
  xs1 = eka :| xs

main :: IO ()
main = do
  putTextLn "Testi 3"
  quickCheck testi_3
  putTextLn "Testi 4"
  quickCheckWith stdArgs{maxSuccess=10000} testi_4
  putStrLn "Hello TIEA341"
