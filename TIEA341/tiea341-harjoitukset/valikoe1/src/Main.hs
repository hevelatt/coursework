module Main where
import System.Environment

-- Tässä apufunktio, jolla saadaan välimerkit kätevästi pois.
-- Sitä ei tarvitse muuttaa
filterText :: (Char -> Bool) -> Text -> Text
filterText ehto = fromString . filter ehto . toString

-- Täydennä tämä tehtävänannon mukaiseksi.
filterIT :: Text -> Text
filterIT syöte 
  = let
     ilmanVälimerkkejä :: Text
     ilmanVälimerkkejä 
        = filterText (\x -> notElem x [',','.',';',':','"','\'']) syöte 

     riveinä = lines ilmanVälimerkkejä :: [Text] -- ["kissa ja", "kala"]
     sanalistana = map words riveinä :: [[Text]] -- [["kissa", "ja"], ["kala"]]
     -- huonotPois :: [Text] -> [Text]
     -- huonotPois rivi = filter (\x -> notElem x ["sekä", "että", "jos", "vaikka", "kuin", "ja", "niin"]) rivi
     -- ilmanSanoja = map huonotPois sanalistana :: [[Text]]
     ilmanSanoja = map (filter (\x -> notElem x ["sekä", "että", "jos", "vaikka", "kuin", "ja", "niin"])) sanalistana :: [[Text]] -- [["kissa"],["kala"]]
     
     rivit = lines ilmanVälimerkkejä :: [Text] -- ["kissa ja", "kala"]

    in unlines (poistaRiveistä (\sana -> notElem sana ["sekä", "että", "jos", "vaikka", "kuin", "ja", "niin"]) rivit) -- unlines (map unwords ilmanSanoja) -- unlines ["kissa", "kala"] -> "kissa\nkala"

-- jos vaikka kuin ja niin

poistaSanoista :: (Text -> Bool) -> [Text] -> [Text]
poistaSanoista _ehto [] = []
poistaSanoista ehto (eka:loput) -- "kissa":"ja"
 | ehto eka = eka:(poistaSanoista ehto loput)
 | otherwise = poistaSanoista ehto loput

poistaRiveistä :: (Text -> Bool) -> [Text] -> [Text]
poistaRiveistä _ehto [] = []
poistaRiveistä ehto (eka:loput) = (unwords (poistaSanoista ehto (words eka))):(poistaRiveistä ehto loput)

-- Tässä pääohjelma. Sitä ei tarvitse muuttaa, mutta voit kokeilla
-- ohjelmaasi sen avulla.
--
-- Komentoriviltä: 
--   1) `stack build` 
--   2) `stack exec <ohjelmasi nimi> src/Main.hs` (tai joku muu tekstitiedosto)
--
--  Replistä:
--   :main src/Main.hs 
--   (tai joku muu tekstitiedosto kuin src/Main.hs)
--
--   Voit myös kokeilla aliohjelmaasi ihan normaalisti replistä.
main :: IO ()
main = do
  argumentit <- getArgs
  case argumentit of
    [tiedostonNimi] -> do
              sisältö <- readFileText tiedostonNimi
              putTextLn (filterIT sisältö)
    _ -> putTextLn "Anna tiedostonime argumenttina"