#include <iostream>

class Kulkuneuvo {
    double nopeus;
    int matkustajia = 0;
public:
    Kulkuneuvo(double nopeus);
    virtual ~Kulkuneuvo();
    
    virtual int lisaa(int matkustajia);
    virtual int vahenna(int matkustajia);
    virtual int getMatkustajia() const { return matkustajia; }
    
    virtual double nopeuta(double nopeus);
    virtual double hidasta(double nopeus);
    virtual double getNopeus() const { return nopeus; }
    
    virtual std::ostream& tulosta(std::ostream &os) const;
};

std::ostream& operator<<(std::ostream &os, const Kulkuneuvo &kulkuneuvo);

/**
 * @brief Kulkuneuvon muodostaja
 * @param nopeus Kulkuneuvon nopeus
 * @code
 * <pre name="test">
 *  Kulkuneuvo kulkuneuvo;
 *  kulkuneuvo.getNopeus() ~~~ 0;
 *  Kulkuneuvo kulkuneuvo2{20};
 *  kulkuneuvo2.getNopeus() ~~~ 20;
 * </pre>
 * @endcode
 */
Kulkuneuvo::Kulkuneuvo(double nopeus = 0) : nopeus{nopeus}
{
}

/**
 * @brief Kulkuneuvon tuhoaja
 */
Kulkuneuvo::~Kulkuneuvo()
{
}

/**
 * @brief Lisää matkustajia kulkuneuvoon
 * @param matkustajia Kuinka monta matkustajaa lisätään
 * @return 0
 * @code
 * <pre name="test">
 *  Kulkuneuvo kulkuneuvo;
 *  kulkuneuvo.getMatkustajia() === 0;
 *  kulkuneuvo.lisaa(20);
 *  kulkuneuvo.getMatkustajia() === 20;
 * </pre>
 * @endcode
 */
int Kulkuneuvo::lisaa(int matkustajia)
{
    this->matkustajia += matkustajia;
    return 0;
}

/**
 * @brief Vähentää matkustajia kulkuneuvosta
 * @param matkustajia Kuinka monta matkustajaa vähennetään
 * @return Kuinka monta jäi jäljelle, negatiivinen tarkoittaa kuinka monta vähennettiin yli
 * @code
 * <pre name="test">
 *  Kulkuneuvo kulkuneuvo;
 *  kulkuneuvo.lisaa(20); kulkuneuvo.getMatkustajia() === 20;
 *  kulkuneuvo.vahenna(15) === 5; kulkuneuvo.getMatkustajia() === 5;
 *  kulkuneuvo.vahenna(15) === -10; kulkuneuvo.getMatkustajia() === 0;
 * </pre>
 * @endcode
 */
int Kulkuneuvo::vahenna(int matkustajia)
{
    int ali = this->matkustajia -= matkustajia;
    if (ali < 0) this->matkustajia = 0;
    return ali;
}

/**
 * @brief Kasvattaa kulkuneuvon nopeutta
 * @param nopeus Kuinka paljon kasvatetaan nopeutta
 * @return 0
 * @code
 * <pre name="test">
 *  Kulkuneuvo kulkuneuvo;
 *  kulkuneuvo.getNopeus() ~~~ 0;
 *  kulkuneuvo.nopeuta(20);
 *  kulkuneuvo.getNopeus() ~~~ 20;
 * </pre>
 * @endcode
 */
double Kulkuneuvo::nopeuta(double nopeus)
{
    this->nopeus += nopeus;
    return 0;
}

/**
 * @brief Hiljennetään nopeutta
 * @param nopeus Kuinka paljon hidastetaan
 * @return Kuinka paljon nopeutta jäi jäljelle, negatiivinen tarkoittaa kuinka paljon hidastettiin yli
 * @code
 * <pre name="test">
 *  Kulkuneuvo kulkuneuvo{20};
 *  kulkuneuvo.hidasta(15) ~~~ 5; kulkuneuvo.getNopeus() ~~~ 5;
 *  kulkuneuvo.hidasta(15) ~~~ -10; kulkuneuvo.getNopeus() ~~~ 0;
 * </pre>
 * @endcode
 */
double Kulkuneuvo::hidasta(double nopeus)
{
    double ali = this->nopeus -= nopeus;
    if (ali < 0) this->nopeus = 0;
    return ali;
}

/**
 * @brief Tulostaa kulkuneuvon tiedot
 * @param os Tietovirta johon tulostetaan
 * @return Tietovirta johon tulostettu kulkuneuvon tiedot
 * @code
 * <pre name="test">
 *  StringOutput so;
 *  Kulkuneuvo kulkuneuvo{20};
 *  kulkuneuvo.lisaa(5);
 *  kulkuneuvo.tulosta(std::cout);
 *  so.ero("nopeus 20, matkustajia 5") === "";
 * </pre>
 * @endcode
 */
std::ostream& Kulkuneuvo::tulosta(std::ostream &os) const
{
    return os << "nopeus " << nopeus << ", matkustajia " << matkustajia;
}

/**
 * @brief Tökkäysoperaattori
 * @param os Tietovirta johon tökätään
 * @param kulkuneuvo Kulkuneuvo joka tökätään
 * @return Tietovirta johon kulkuneuvo on tökätty
 * @code
 * <pre name="test">
 *  StringOutput so;
 *  Kulkuneuvo kulkuneuvo{20};
 *  kulkuneuvo.lisaa(5);
 *  Laiva laiva{50};
 *  Lentokone lentokone{100, 200};
 *  std::cout << kulkuneuvo << std::endl << laiva << std::endl << lentokone;
 *  so.ero("nopeus 20, matkustajia 5\nLaiva: maksiminopeus 50, nopeus 0, matkustajia 0\nLentokone: matkustajakapasiteetti 100, nopeus 200, matkustajia 0") === "";
 * </pre>
 * @endcode
 */
std::ostream& operator<<(std::ostream &os, const Kulkuneuvo &kulkuneuvo)
{
    return kulkuneuvo.tulosta(os);
}

class Laiva : public Kulkuneuvo {
    double maksiminopeus;
public:
    Laiva(double maksiminopeus, double nopeus);
    virtual ~Laiva() override;
    virtual double nopeuta(double nopeus) override;
    virtual std::ostream& tulosta(std::ostream &os) const override;
};

/**
 * @brief Laivan muodostaja
 * @param maksiminopeus Laivan maksiminopeus
 * @param nopeus Laivan nopeus
 * @code
 * <pre name="test">
 *  Laiva laiva{50, 20};
 *  laiva.getNopeus() ~~~ 20;
 * </pre>
 * @endcode
 */
Laiva::Laiva(double maksiminopeus, double nopeus = 0) : Kulkuneuvo{nopeus}, maksiminopeus{maksiminopeus}
{
}

/**
 * @brief Laivan destruktori, tulostaa cout tiedon laivan uppoamisesta
 */
Laiva::~Laiva()
{
    std::cout << "Laiva upposi" << std::endl;
}

/**
 * @brief Kasvattaa laivan nopeutta
 * @param nopeus Kuinka paljon kasvatetaan nopeutta
 * @return Kuinka paljon kasvatettiin nopeutta maksiminopeuden yli, 0 jos ei ylitetty maksiminopeutta
 * @code
 * <pre name="test">
 *  Laiva laiva{50}; laiva.getNopeus() ~~~ 0;
 *  laiva.nopeuta(30) ~~~ 0; laiva.getNopeus() ~~~ 30;
 *  laiva.nopeuta(30) ~~~ 10; laiva.getNopeus() ~~~ 50;
 * </pre>
 * @endcode
 */
double Laiva::nopeuta(double nopeus)
{
    Kulkuneuvo::nopeuta(nopeus);
    double yli = getNopeus() - maksiminopeus;
    if (yli > 0) {
        Kulkuneuvo::hidasta(yli);
        return yli;
    }
    return 0;
}

/**
 * @brief Laivan tietojen tulostus
 * @param os Mihin tietovirtaan tulostetaan
 * @return Tietovirta johon tulostettu
 * @code
 * <pre name="test">
 *  StringOutput so;
 *  Laiva laiva{50, 20};
 *  laiva.tulosta(std::cout);
 *  so.ero("Laiva: maksiminopeus 50, nopeus 20, matkustajia 0") === "";
 * </pre>
 * @endcode
 */
std::ostream& Laiva::tulosta(std::ostream &os) const
{
    return Kulkuneuvo::tulosta(os << "Laiva: maksiminopeus " << maksiminopeus << ", ");
}

class Lentokone : public Kulkuneuvo {
    int matkustajakapasiteetti;
public:
    Lentokone(int matkustajakapasiteetti, double nopeus);
    virtual ~Lentokone() override;
    virtual int lisaa(int matkustajia) override;
    virtual int tilaa() const;
    virtual std::ostream& tulosta(std::ostream &os) const override;
};

/**
 * @brief Lentokoneen muodostaja
 * @param matkustajakapasiteetti Kuinka monta matkustajaa lentokoneeseen mahtuu
 * @param nopeus Lentokoneen nopeus
 * @code
 * <pre name="test">
 *  Lentokone lentokone{200, 20};
 *  lentokone.getNopeus() ~~~ 20;
 *  lentokone.tilaa() === 200;
 * </pre>
 * @endcode
 */
Lentokone::Lentokone(int matkustajakapasiteetti, double nopeus = 0) : Kulkuneuvo{nopeus}, matkustajakapasiteetti{matkustajakapasiteetti}
{
}

/**
 * @brief Lentokoneen tuhoaja, tulostaa cout tiedon lentokoneen putoamisesta
 */
Lentokone::~Lentokone()
{
    std::cout << "Lentokone tippui" << std::endl;
}

/**
 * @brief Lisää matkustajia lentokoneeseen
 * @param matkustajia Kuinka monta matkustajaa lisätään
 * @return Kuinka monta matkustajaa jäi yli kun lisättiin
 * @code
 * <pre name="test">
 *  Lentokone lentokone{50}; lentokone.tilaa() === 50;
 *  lentokone.lisaa(30) === 0; lentokone.tilaa() === 20;
 *  lentokone.lisaa(30) === 10; lentokone.tilaa() === 0;
 * </pre>
 * @endcode
 */
int Lentokone::lisaa(int matkustajia)
{
    Kulkuneuvo::lisaa(matkustajia);
    int yli = getMatkustajia() - matkustajakapasiteetti;
    if (yli > 0) {
        Kulkuneuvo::vahenna(yli);
        return yli;
    }
    return 0;
}

/**
 * @brief Kertoo monta matkustajaa lentokoneeseen vielä mahtuu
 * @return Kuinka monta matkustajaa mahtuu
 * @code
 * <pre name="test">
 *  Lentokone lentokone{50}; lentokone.tilaa() === 50;
 *  lentokone.lisaa(30) === 0; lentokone.tilaa() === 20;
 * </pre>
 * @endcode
 */
int Lentokone::tilaa() const
{
    return matkustajakapasiteetti - getMatkustajia();
}

/**
 * @brief Tulostaa lentokoneen tiedot tietovirtaan
 * @param os Tietovirta johon tulostetaan
 * @return Tietovirta johon tiedot tulostettiin
 * @code
 * <pre name="test">
 *  StringOutput so;
 *  Lentokone lentokone{50, 20};
 *  lentokone.tulosta(std::cout);
 *  so.ero("Lentokone: matkustajakapasiteetti 50, nopeus 20, matkustajia 0") === "";
 * </pre>
 * @endcode
 */
std::ostream& Lentokone::tulosta(std::ostream &os) const
{
    return Kulkuneuvo::tulosta(os << "Lentokone: matkustajakapasiteetti " << matkustajakapasiteetti << ", ");
}

/**
 * @brief Pääohjelma, testataan Kulkuneuvoa ja siitä perittyä Laivaa ja Lentokonetta
 * @return 0
 */
int main(void)
{
    Kulkuneuvo kulkuneuvo{20};
    std::cout << kulkuneuvo << std::endl;
    kulkuneuvo.lisaa(15); 
    kulkuneuvo.hidasta(5);
    std::cout << kulkuneuvo << std::endl;
    std::cout << "Ei edes näin monta matkustajaa " << kulkuneuvo.vahenna(20) << std::endl; 
    std::cout << "Ei voida pudottaa nopeutta enempää " << kulkuneuvo.hidasta(20) << std::endl;
    std::cout << kulkuneuvo << std::endl;
    
    Laiva *laiva = new Laiva{50};
    std::cout << *laiva << std::endl;
    laiva->nopeuta(30);
    std::cout << *laiva << std::endl;
    std::cout << "Ei voida kasvattaa nopeutta enempää " << laiva->nopeuta(30) << std::endl;
    std::cout << *laiva << std::endl;
    delete laiva;
    
    Lentokone lentokone{100};
    std::cout << lentokone << std::endl << "Tilaa lentokoneessa: " << lentokone.tilaa() << std::endl;
    lentokone.lisaa(60);
    std::cout << lentokone << std::endl << "Tilaa lentokoneessa: " << lentokone.tilaa() << std::endl;
    std::cout << "Matkustajia jäi yli " << lentokone.lisaa(60) << std::endl;
    std::cout << lentokone << std::endl << "Tilaa lentokoneessa: " << lentokone.tilaa() << std::endl;
    
    return 0;
}