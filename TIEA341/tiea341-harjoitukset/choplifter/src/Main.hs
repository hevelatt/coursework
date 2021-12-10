module Main where
import Graphics.Gloss
import Graphics.Gloss.Interface.Pure.Game 
import Graphics.Gloss.Geometry.Angle
import Graphics.Gloss.Data.Vector
import Prelude hiding (Down)


main :: IO ()
main = play 
        (InWindow "Choplifter" (700,800) (0,0))
        (light red)
        24
        (Peli 0 (0,0) 0 0)
        piirräPeli
        reagoi
        päivitäPeliä

reagoi :: Event -> Choplifter -> Choplifter 
reagoi tapahtuma peli 
 = case tapahtuma of         
    EventKey (Char 'w') Down _ _ -> muutaTehoa peli 3
    EventKey (Char 's') Down _ _ -> muutaTehoa peli (-3)
    EventKey (Char 'a') Down _ _ -> kallista (-3) peli
    EventKey (Char 'd') Down _ _ -> kallista 3 peli
    _ -> peli

päivitäPeliä :: Float -> Choplifter -> Choplifter
päivitäPeliä aikaEdellisestä edellinenTila 
 = case edellinenTila of 
    Peli aika (kopteriX, kopteriY)
              teho kulma
     -> let
         (vX,vY) = kulmaJaTehoNopeudeksi teho kulma
        in Peli (aika + aikaEdellisestä) 
                (kopteriX+aikaEdellisestä*50*vX, 
                max 0 (kopteriY+aikaEdellisestä*50*vY - 2))
                teho 
                kulma

kulmaJaTehoNopeudeksi :: Float -> Float -> (Float,Float)
kulmaJaTehoNopeudeksi teho kulma 
 = rotateV (- degToRad kulma) (0,teho)

piirräPeli :: Choplifter -> Picture 
piirräPeli peli = case peli of 
                   Peli aika (kopteriX, kopteriY) 
                             _teho kulma
                    -> let 
                        kopteriKuva = rotate kulma (scale 0.4 0.4 (kopteri 20 aika))
                        peliKuva = translate kopteriX kopteriY kopteriKuva <> maa
                       in scale 0.5 0.5 (translate 0 (-100) peliKuva)

kallista :: Float -> Choplifter -> Choplifter
kallista muutos peli = case peli of
                        Peli aika paikka teho kulma 
                         -> Peli aika paikka teho (kulma + muutos)

muutaTehoa :: Choplifter -> Float -> Choplifter
muutaTehoa peli muutos
 = case peli of 
    Peli aika paikka teho kulma
     -> Peli aika paikka (teho+muutos) kulma

data Choplifter
 = Peli
    Float -- Aika pelin alusta
    (Float, Float) -- Missä kopteri on
    Float -- Teho
    Float -- kallistus

maa :: Picture
maa = color ((dark . dark . dark) green) (translate 0 (-500) (rectangleSolid 5000 1000))

kopteri :: Float -> Float -> Picture
kopteri teho aika = translate 0 125 (color white runko)
 where
  runko = circleSolid 100 
            <> lapa
            <> translate 0 100 (rectangleSolid 30 30)
            <> jalka 50 (-70)
            <> jalka (-50) (-70)
  lapa = translate 0 125 (rectangleSolid (400 * sin (aika * teho)) 20)
  jalka x y = translate x y (rectangleSolid 40 60) <> translate x (y-30) (circleSolid 20)