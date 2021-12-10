using System;
using System.Collections.Generic;
using Jypeli;
using Jypeli.Assets;
using Jypeli.Controls;
using Jypeli.Effects;
using Jypeli.Widgets;

/// @author Herman Lätti
/// @version 17.03.24
///
/// <summary>
/// Ponghop
/// </summary>
public class Ponghop : PhysicsGame
{
    /// <summary>Pelin perusmitta.</summary>
    private const int U = 32;
    /// <summary>Pelikentän ja -ikkunan leveys.</summary>
    private const int PELIN_LEVEYS = 40 * U;
    /// <summary>Pelikentän ja -ikkunan korkeus.</summary>
    private const int PELIN_KORKEUS = 22 * U;
    /// <summary>Pallon nopeus.</summary>
    private const int PALLON_NOPEUS = 12 * U;
    /// <summary>Mailan nopeus.</summary>
    private const int MAILAN_NOPEUS = 12 * U;
    /// <summary>Painovoiman suuruus.</summary>
    private const int PAINOVOIMA = 50 * U;
    /// <summary>Pelihahmon kävelynopeus.</summary>
    private const double PELAAJAN_KAVELYNOPEUS = 10 * U;
    /// <summary>Pelihahmon hyppäysvoima.</summary>
    private const double PELAAJAN_HYPPYVOIMA = 30 * U;

    /// <summary>Pelaajan 1 pisteet.</summary>
    private int pelaaja1Pisteet = 0;
    /// <summary>Pelaajan 2 pisteet.</summary>
    private int pelaaja2Pisteet = 0;

    /// <summary>
    /// Peli alkaa luomalla ensimmäinen kenttä.
    /// </summary>
    public override void Begin()
    {
        LuoKentta(0);
    }

    /// <summary>
    /// Luo "tasaisesti" kimpoilevan pallon.
    /// </summary>
    /// <param name="w">Pallon leveys.</param>
    /// <param name="h">Pallon korkeus.</param>
    /// <param name="x">Pallon x-koordinaatti.</param>
    /// <param name="y">Pallon y-koordinaatti.</param>
    /// <param name="vari">Pallon väri.</param>
    public void LuoPallo(double w, double h, double x, double y, Color vari)
    {
        PhysicsObject pallo = TeeMuoto(w, h, x, y, Shape.Circle, vari);

        pallo.IgnoresPhysicsLogics = true;
        pallo.IgnoresCollisionResponse = true;

        int[] pallonSuunta = { -1, 1 };
        Random rSuunta = new Random();
        pallo.Velocity = new Vector(pallonSuunta[rSuunta.Next(0, pallonSuunta.Length)], pallonSuunta[rSuunta.Next(0, pallonSuunta.Length)]).Normalize() * PALLON_NOPEUS;

        AddCollisionHandler(pallo, "vaakareunat", PalloKimpoaa);
        AddCollisionHandler(pallo, "pystyreunat", PalloKimpoaa);
        AddCollisionHandler(pallo, "mailat", PalloKimpoaa);
        AddCollisionHandler(pallo, "Pelaaja 1", PalloKimpoaa);
        AddCollisionHandler(pallo, "Pelaaja 2", PalloKimpoaa);
    }

    /// <summary>
    /// Kimmottaa kohteen (pallon) kentän reunoista, laatikoista ja mailoista.
    /// </summary>
    /// <param name="tormaaja">Törmäävä fysiikkaolio (pallo).</param>
    /// <param name="kohde">Fysiikkaolio johon törmätään.</param>
    public void PalloKimpoaa(PhysicsObject tormaaja, PhysicsObject kohde)
    {
        if (kohde.Tag == "vaakareunat")
        {
            //Pallo kimpoaa vaakareunasta
            tormaaja.Velocity = new Vector(tormaaja.Velocity.X, -1 * tormaaja.Velocity.Y);
        }
            
        if (kohde.Tag == "pystyreunat")
        {
            //Pallo kimpoaa pystyreunasta
            tormaaja.Velocity = new Vector(-1 * tormaaja.Velocity.X, tormaaja.Velocity.Y);

            //Pallo menee mailan ohi
            if(tormaaja.X < Level.Left + U)
                LisaaPiste(2);
            if(tormaaja.X > Level.Right - U)
                LisaaPiste(1);
        }

        //Pallo kimpoaa mailasta
        if (kohde.Tag == "mailat")
            tormaaja.Velocity = new Vector(tormaaja.X - kohde.X, tormaaja.Y - kohde.Y).Normalize() * PALLON_NOPEUS;

        //Pallo osuu pelaajaan
        if (kohde.Tag == "Pelaaja 1")
            LisaaPiste(2);

        if (kohde.Tag == "Pelaaja 2")
            LisaaPiste(1);
    }

    /// <summary>
    /// Luo laatikon määrätyillä parametreilla
    /// </summary>
    /// <param name="w">Laatikon leveys.</param>
    /// <param name="h">Laatikon korkeus.</param>
    /// <param name="x">Laatikon x-koordinaatti.</param>
    /// <param name="y">Laatikon y-koordinaatti.</param>
    /// <param name="vari">Laatikon väri.</param>
    public void LuoLaatikko(double w, double h, double x, double y, Color vari)
    {
        LuoLaatikkoKolmio(w, h / 2, x, y - h / 4, 0);
        LuoLaatikkoKolmio(w, h / 2, x, y + h / 4, 180);
        LuoLaatikkoKolmio(h, w / 2, x - w / 4, y, -90);
        LuoLaatikkoKolmio(h, w / 2, x + w / 4, y, 90);

        PhysicsObject laatikko = TeeMuoto(w, h, x, y, Shape.Rectangle, vari);
        laatikko.MakeStatic();
        laatikko.IgnoresPhysicsLogics = true;
        laatikko.StaticFriction = 0;
        laatikko.KineticFriction = 0;
        laatikko.Restitution = 0;
    }

    /// <summary>
    /// Luo tasasivuisen kolmion, jota käytetään laatikon luontiin.
    /// </summary>
    /// <param name="w">Kolmion kanta.</param>
    /// <param name="h">Kolmion korkeus.</param>
    /// <param name="x">Kolmion x-koordinaatti.</param>
    /// <param name="y">Kolmion y-koordinaatti.</param>
    /// <param name="kulmaAsteina">Kolmion kallistuskulma asteina.</param>
    /// <param name="vari">Kolmion väri.</param>
    public void LuoLaatikkoKolmio(double w, double h, double x, double y, double kulmaAsteina)
    {
        PhysicsObject kolmio = TeeMuoto(w, h, x, y, Shape.Triangle);
        kolmio.MakeStatic();

        kolmio.IgnoresPhysicsLogics = true;
        kolmio.IgnoresCollisionResponse = true;
        kolmio.IsVisible = false;

        kolmio.Angle = Angle.FromDegrees(kulmaAsteina);
        if (Math.Abs(kulmaAsteina) == 90) kolmio.Tag = "pystyreunat";
        else kolmio.Tag = "vaakareunat";
    }

    /// <summary>
    /// Luo ohjattavan mailan määrätyillä parametreilla.
    /// </summary>
    /// <param name="w">Mailan leveys.</param>
    /// <param name="h">Mailan korkeus.</param>
    /// <param name="x">Mailan x-koordinaatti.</param>
    /// <param name="y">Mailan y-koordinaatti.</param>
    /// <param name="vari">Mailan väri.</param>
    /// <param name="ylos">Mailan ylöspäin liikuttamiseen käytettävä näppäin.</param>
    /// <param name="alas">Mailan alaspäin liikuttamiseen käytettävä näppäin.</param>
    public void LuoMaila(double w, double h, double x, double y, Color vari, Key ylos, Key alas, string nimi)
    {
        PhysicsObject maila = TeeMuoto(w, h, x, y, Shape.Rectangle, vari);
        maila.IgnoresPhysicsLogics = true;
        maila.IgnoresCollisionResponse = true;
        maila.Tag = "mailat";

        Keyboard.Listen(ylos, ButtonState.Down, LiikutaMailaa, nimi + ": Liikuta mailaa ylös", new Vector(0, MAILAN_NOPEUS), maila);
        Keyboard.Listen(ylos, ButtonState.Released, LiikutaMailaa, null, Vector.Zero, maila);
        Keyboard.Listen(alas, ButtonState.Down, LiikutaMailaa, nimi + ": Liikuta mailaa alas", new Vector(0, -1 * MAILAN_NOPEUS), maila);
        Keyboard.Listen(alas, ButtonState.Released, LiikutaMailaa, null, Vector.Zero, maila);
    }

    /// <summary>
    /// Liikuttaa määrättyä mailaa.
    /// </summary>
    /// <param name="nopeus">Mailan nopeus vektorina./param>
    /// <param name="maila">Mailaa jota liikutetaan.</param>
    public void LiikutaMailaa(Vector nopeus, PhysicsObject maila)
    {
        maila.Velocity = nopeus;
    }

    /// <summary>
    /// Luo liikuteltavan tasohyppelypelihahmon.
    /// </summary>
    /// <param name="w">Pelihahmon leveys.</param>
    /// <param name="h">Pelihahmon korkeus.</param>
    /// <param name="x">Pelihahmon x-koordinaatti.</param>
    /// <param name="y">Pelihahmon y-koordinaatti.</param>
    /// <param name="vari">Pelihahmon väri.</param>
    /// <param name="hyppy">Näppäin, jota käytetään hyppäämiseen.</param>
    /// <param name="vasemmalle">Näppäin, jota käytetään vasemmalle liikkumiseen.</param>
    /// <param name="oikealle">Näppäin, jota käytetään oikealle liikkumiseen.</param>
    public void LuoPelaaja(double w, double h, double x, double y, Color vari, Key hyppy, Key vasemmalle, Key oikealle, string nimi)
    {
        PlatformCharacter pelaaja = new PlatformCharacter(w, h, Shape.Rectangle);
        pelaaja.Position = new Vector(x, y);
        pelaaja.Color = vari;
        Add(pelaaja);
        pelaaja.Tag = nimi;

        Keyboard.Listen(vasemmalle, ButtonState.Down, LiikutaPelaajaa, nimi + ": Liikuta pelihahmoa vasemmalle", pelaaja, "vasemmalle");
        Keyboard.Listen(oikealle, ButtonState.Down, LiikutaPelaajaa, nimi + ": Liikuta pelihahmoa vasemmalle", pelaaja, "oikealle");
        Keyboard.Listen(hyppy, ButtonState.Down, LiikutaPelaajaa, nimi + ": Pelihahmo hyppää", pelaaja, "hyppy");

        AddCollisionHandler(pelaaja, "vaakareunat", PelaajaTormaa);
    }

    /// <summary>
    /// Liikuttaa määrättyä pelihahmoa (hyppy ja kävely).
    /// </summary>
    /// <param name="pelaaja">Pelihahmo, jota liikutetaan.</param>
    /// <param name="suunta">Merkkijonotunniste suunnalle johon liikutaan ("vasemmalle", "oikealle", "hyppy").</param>
    public void LiikutaPelaajaa(PlatformCharacter pelaaja, string suunta)
    {
        if (suunta == "oikealle") pelaaja.Walk(PELAAJAN_KAVELYNOPEUS);
        if (suunta == "vasemmalle") pelaaja.Walk(-1 * PELAAJAN_KAVELYNOPEUS);
        if (suunta == "hyppy") pelaaja.Jump(PELAAJAN_HYPPYVOIMA);
    }

    /// <summary>
    /// Luo muodon (fysiikkaobjekti).
    /// </summary>
    /// <param name="w">Muodon leveys.</param>
    /// <param name="h">Muodon korkeus.</param>
    /// <param name="x">Muodon x-koordinaatti.</param>
    /// <param name="y">Muodon y-koordinaatti.</param>
    /// <param name="muoto">Muoto (Shape).</param>
    /// <param name="vari">Väri (Color).</param>
    /// <returns>Palauttaa muodon (fysiikkaobjektin).</returns>
    public PhysicsObject TeeMuoto(double w, double h, double x, double y, Shape muoto, Color vari)
    {
        PhysicsObject p = new PhysicsObject(w, h, muoto);
        p.Position = new Vector(x, y);
        p.Color = vari;
        Add(p);
        return p;
    }

    /// <summary>
    /// Luo värittömän (valkoisen) muodon (fysiikkaobjekti).
    /// </summary>
    /// <param name="w">Muodon leveys.</param>
    /// <param name="h">Muodon korkeus.</param>
    /// <param name="x">Muodon x-koordinaatti.</param>
    /// <param name="y">Muodon y-koordinaatti.</param>
    /// <param name="muoto">Muoto (Shape).</param>
    /// <param name="vari">Väri (Color).</param>
    /// <returns>Palauttaa muodon (fysiikkaobjektin).</returns>
    public PhysicsObject TeeMuoto(double w, double h, double x, double y, Shape muoto)
    {
        return TeeMuoto(w, h, x, y, muoto, Color.White);
    }

    /// <summary>
    /// Luo tason, josta pelihahmo voi hypätä alapuolelta.
    /// </summary>
    /// <param name="w">Tason leveys.</param>
    /// <param name="h">Tason korkeus.</param>
    /// <param name="x">Tason x-koordinaatti.</param>
    /// <param name="y">Tason y-koordinaatti.</param>
    /// <param name="vari">Tason väri.</param>
    public void LuoTaso(double w, double h, double x, double y, Color vari)
    {
        PhysicsObject taso = TeeMuoto(w, h, x, y, Shape.Rectangle, vari);
        taso.MakeStatic();
        taso.MakeOneWay();
    }

    /// <summary>
    /// Luo alaspäin liikkuvan tason, josta pelihahmo voi hypätä alapuolelta.
    /// </summary>
    /// <param name="w">Tason leveys.</param>
    /// <param name="h">Tason korkeus.</param>
    /// <param name="x">Tason x-koordinaatti.</param>
    /// <param name="y">Tason y-koordinaatti.</param>
    /// <param name="vari">Tason väri.</param>
    public void LuoLiikkuvaTaso(double w, double h, double x, double y, Color vari)
    {
        PhysicsObject taso = TeeMuoto(w, h, x, y, Shape.Rectangle, vari);
        taso.MakeStatic();
        taso.MakeOneWay();
        PhysicsObject kolmio = TeeMuoto(w, h, x, y, Shape.Triangle, vari);
        kolmio.Angle = Angle.FromDegrees(180);
        kolmio.IgnoresCollisionResponse = true;
        kolmio.IgnoresPhysicsLogics = true;

        Timer liikutaTasoa = new Timer();
        liikutaTasoa.Interval = 0.01;
        liikutaTasoa.Timeout += delegate
        {
            taso.Position = new Vector(taso.X, taso.Y - 1);
            kolmio.Position = new Vector(taso.Position.X, taso.Position.Y - w / 4);
            if (taso.Y < Level.Bottom)
            {
                taso.Destroy();
                liikutaTasoa.Stop();
                LuoLiikkuvaTaso(w, h, x, Level.Top, vari);
            }
        };
        liikutaTasoa.Start(); 
    }

    /// <summary>
    /// Luo numerolla määrätyn pelikentän.
    /// </summary>
    /// <param name="kentanNumero">Määrää luotavan kentän (0-2).</param>
    public void LuoKentta(int kentanNumero)
    {
        ClearAll();

        LuoPistelaskuri(Level.Left + 2 * U, Level.Top - 2 * U, Color.Yellow, pelaaja1Pisteet);
        LuoPistelaskuri(Level.Right - 2 * U, Level.Top - 2 * U, Color.Yellow, pelaaja2Pisteet);

        SetWindowSize(PELIN_LEVEYS, PELIN_KORKEUS);
        Level.Width = PELIN_LEVEYS;
        Level.Height = PELIN_KORKEUS;

        Level.Background.Color = Color.Brown;

        PhysicsObject ylareuna = Level.CreateTopBorder();
        PhysicsObject alareuna = Level.CreateBottomBorder();
        PhysicsObject vasenreuna = Level.CreateLeftBorder();
        PhysicsObject oikeareuna = Level.CreateRightBorder();
        ylareuna.Tag = alareuna.Tag = "vaakareunat";
        vasenreuna.Tag = oikeareuna.Tag = "pystyreunat";
        ylareuna.IgnoresCollisionResponse = alareuna.IgnoresCollisionResponse = vasenreuna.IgnoresCollisionResponse = oikeareuna.IgnoresCollisionResponse = true;

        Gravity = new Vector(0, -1 * PAINOVOIMA);

        LuoMaila(3 * U, 3 * U, Level.Left, 0, Color.DarkYellowGreen, Key.F, Key.C, "Pelaaja 1");
        LuoMaila(3 * U, 3 * U, Level.Right, 0, Color.Gold, Key.L, Key.Period, "Pelaaja 2");

        Keyboard.Listen(Key.F1, ButtonState.Pressed, ShowControlHelp, "Näytä kontrollit");
        Keyboard.Listen(Key.Escape, ButtonState.Pressed, ConfirmExit, "Lopeta peli");

        if (kentanNumero == 0)
        {
            LuoPallo(U, U, 0, -5 * U, Color.Beige);

            LuoPelaaja(U, U, -6 * U, 5  *U, Color.DarkYellowGreen, Key.W, Key.A, Key.D, "Pelaaja 1");
            LuoPelaaja(U, U, 6 * U, 5 * U, Color.Gold, Key.Up, Key.Left, Key.Right, "Pelaaja 2");

            LuoLaatikko(20 * U, 4 * U, 0, Level.Top - (3 * U), Color.Orange);
            LuoLaatikko(20 * U, 4 * U, 0, Level.Bottom + (3 * U), Color.Orange);
            LuoLaatikko(8 * U, 5 * U, 6 * U, 0, Color.Orange);
            LuoLaatikko(8 * U, 5 * U, -6 * U, 0, Color.Orange);

            LuoTaso(2 * U, 0.5 * U, U, 2 * U, Color.Crimson);
            LuoTaso(2 * U, 0.5 * U, -U, -2 * U, Color.Crimson);

            LuoLiikkuvaTaso(2 * U, 0.5 * U, 11 * U, Level.Top, Color.Blue);
            LuoLiikkuvaTaso(2 * U, 0.5 * U, -11 * U, Level.Top, Color.Blue);
        }

        if (kentanNumero == 1)
        {
            LuoPallo(U, U, 0, -8 * U, Color.Beige);

            LuoPelaaja(U, U, -2 * U, -2 * U, Color.DarkYellowGreen, Key.W, Key.A, Key.D, "Pelaaja 1");
            LuoPelaaja(U, U, 2 * U, 2 * U, Color.Gold, Key.Up, Key.Left, Key.Right, "Pelaaja 2");

            LuoLaatikko(4 * U, 4 * U, 2 * U, 0, Color.Orange);
            LuoLaatikko(4 * U, 4 * U, -2 * U, -4 * U, Color.Orange);

            LuoTaso(2 * U, 0.5 * U, 7 * U, -6 * U, Color.Crimson);
            LuoTaso(2 * U, 0.5 * U, -2 * U, 6 * U, Color.Crimson);
            LuoTaso(2 * U, 0.5 * U, 11 * U, -2 * U, Color.Crimson);
            LuoTaso(2 * U, 0.5 * U, -11 * U, -2 * U, Color.Crimson);

            LuoLiikkuvaTaso(2 * U, 0.5 * U, 5 * U, Level.Top, Color.Blue);
            LuoLiikkuvaTaso(2 * U, 0.5 * U, -5 * U, Level.Top, Color.Blue);
        }

        if (kentanNumero == 2)
        {
            LuoPallo(U, U, 0, 0, Color.Beige);

            LuoPelaaja(U, U, Level.Left + 5 * U, Level.Top - 2 * U, Color.DarkYellowGreen, Key.W, Key.A, Key.D, "Pelaaja 1");
            LuoPelaaja(U, U, Level.Right - 5 * U, Level.Top - 2 * U, Color.Gold, Key.Up, Key.Left, Key.Right, "Pelaaja 2");

            LuoLiikkuvaTaso(2 * U, 0.5 * U, Level.Left + 5 * U, Level.Top - 3 * U, Color.Blue);
            LuoLiikkuvaTaso(2 * U, 0.5 * U, Level.Right - 5 * U, Level.Top - 3 * U, Color.Blue);

            //vakiot liikkuvien tasojen silmukkaa varten
            const int RIVIT = 5;
            const int SARAKKEET = 5;
            const double VAAKAVALIKERROIN = 4 * U;
            const double PYSTYVALIKERROIN = 4.5 * U;
            const double RIVIN_ALOITUSPISTE = -12 * U;
            const double SARAKKEEN_ALOITUSPISTE = -12 * U;

            for (int i = 1; i < RIVIT + 1; i++)
            {
                for(int j = 1; j < SARAKKEET + 1; j++)
                {
                    if(RandomGen.NextBool())
                    LuoLiikkuvaTaso(2 * U, 0.5 * U, RIVIN_ALOITUSPISTE + j * VAAKAVALIKERROIN, SARAKKEEN_ALOITUSPISTE + i * PYSTYVALIKERROIN, Color.Blue);
                }
            }
        }
    }

    /// <summary>
    /// Luo pistelaskurin ja asettaa sen näyttöön annetuilla parametreilla.
    /// </summary>
    /// <param name="x">Pistelaskurin x-koordinaatti.</param>
    /// <param name="y">Pistelaskurin y-koordinaatti.</param>
    /// <param name="vari">Pistelaskurin tekstin vari (Color).</param>
    /// <param name="pisteet">Pistelaskurin alkuarvo.</param>
    public void LuoPistelaskuri(double x, double y, Color vari, int pisteet)
    {
        IntMeter pisteLaskuri;
        pisteLaskuri = new IntMeter(pisteet);

        Label pisteNaytto = new Label();
        pisteNaytto.X = x;
        pisteNaytto.Y = y;
        pisteNaytto.TextColor = vari;

        pisteNaytto.BindTo(pisteLaskuri);
        Add(pisteNaytto, 3);
    }

    /// <summary>
    /// Lisää pisteen oikealle pelaajalle ja aloittaa seuraavan kentän.
    /// </summary>
    /// <param name="pelaaja">Pelaaja, jolle piste lisätään (1 tai 2).</param>
    public void LisaaPiste(int pelaaja)
    {
        const int MAKSIMIPISTEET = 3;

        if(pelaaja == 1)
            pelaaja1Pisteet += 1;

        if (pelaaja == 2)
            pelaaja2Pisteet += 1;

        if (pelaaja1Pisteet + pelaaja2Pisteet < MAKSIMIPISTEET)
            LuoKentta(pelaaja1Pisteet + pelaaja2Pisteet);
        else
        {
            pelaaja1Pisteet = pelaaja2Pisteet = 0;
            LuoKentta(0);
        }
    }

    /// <summary>
    /// Käsittelee pelaajan törmöykset.
    /// </summary>
    /// <param name="tormaaja">Törmäävä fysiikkaolio (pelaaja).</param>
    /// <param name="kohde">Fysiikkaolio johon törmätään.</param>
    public void PelaajaTormaa(PhysicsObject tormaaja, PhysicsObject kohde)
    {
        if (tormaaja.Tag == "Pelaaja 1" && tormaaja.Y < Level.Bottom + U)
            LisaaPiste(2);
        if (tormaaja.Tag == "Pelaaja 2" && tormaaja.Y < Level.Bottom + U)
            LisaaPiste(1);
    }
}