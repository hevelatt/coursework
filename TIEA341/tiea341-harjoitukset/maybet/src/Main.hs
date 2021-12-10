module Main where
  
teeMaybe :: forall a. a -> Maybe a
teeMaybe a = Just a

onkoTyhjä :: forall a. Maybe a -> Bool
onkoTyhjä a
 = case a of 
    Just _a -> False
    Nothing -> True

onkoTäysi :: forall a. Maybe a -> Bool
onkoTäysi a
 = case a of 
    Just _a -> True
    Nothing -> False

oletusarvolla :: forall a. a -> Maybe a -> a 
oletusarvolla oletusarvo ehkä
 = case ehkä of
    Just a -> a
    Nothing -> oletusarvo

{-
kutsuMaybellä1 :: forall a b. (a -> b) -> Maybe a -> Maybe b
kutsuMaybellä1 f ehkä
 = case ehkä of
    Just a -> Just (f a)
    Nothing -> Nothing
-}

kutsuMaybellä1 :: forall a b. (a -> b) -> Maybe a -> Maybe b
kutsuMaybellä1 f (Just a) = Just (f a)
kutsuMaybellä1 _ _ = Nothing


kutsuMaybellä2 :: forall a b c. (a -> b -> c) -> Maybe a -> Maybe b -> Maybe c
kutsuMaybellä2 f (Just a) (Just b) = Just (f a b)
kutsuMaybellä2 _ _ _ = Nothing

{-
kutsuMaybellä2 :: forall a b c. (a -> b -> c) -> Maybe a -> Maybe b -> Maybe c
kutsuMaybellä2 f ehkä1 ehkä2
 = case (ehkä1, ehkä2) of 
    (Just a, Just b) -> Just (f a b)
    _ -> Nothing
-}

-- case of -rakenteella
{-
kutsuMaybellä2 :: forall a b c. (a -> b -> c) -> Maybe a -> Maybe b -> Maybe c
kutsuMaybellä2 f ehkä1 ehkä2
 = case ehkä1 of 
    Just a 
     -> case ehkä2 of 
         Just b -> Just (f a b)
         Nothing -> Nothing
    Nothing 
     -> Nothing
-}

{-
kutsuMaybellä3 :: forall a b c d. (a -> b -> c -> d) -> Maybe a -> Maybe b -> Maybe c -> Maybe d
kutsuMaybellä3 f ehkä1 ehkä2 ehkä3
 = case ehkä1 of 
    Just a -> kutsuMaybellä2 (f a) ehkä2 ehkä3
    Nothing -> Nothing
-}

kutsuMaybellä3 :: forall a b c d. (a -> b -> c -> d) -> Maybe a -> Maybe b -> Maybe c -> Maybe d
kutsuMaybellä3 f (Just a) (Just b) (Just c) = Just (f a b c)
kutsuMaybellä3 _ _ _ _ = Nothing

main :: IO ()
main = do
  putStrLn "Hello TIEA341"
