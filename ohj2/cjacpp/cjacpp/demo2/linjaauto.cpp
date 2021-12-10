#include <iostream>
using std::cout;
using std::endl;

class LinjaAuto {
    int kapasiteetti;
    int matkustajia = 0;
    
public:
    LinjaAuto(int kapasiteetti);
    int lisaa(int matkustajia);
    void tulosta() const;
    int tilaa() const;
    int vahenna(int matkustajia);
};

/**
 * @brief Linja-auton muodostaja
 * @param kapasiteetti Linja-auton matkustajakapasiteetti
 * @code
 * <pre name="test">
 * LinjaAuto linkki;
 * linkki.tilaa() === 0;
 * LinjaAuto linkki2(20);
 * linkki2.tilaa() === 20;
 * </pre>
 * @endcode
 */
LinjaAuto::LinjaAuto(int kapasiteetti = 0) : kapasiteetti{kapasiteetti} 
{
}

/**
 * @brief Lisää matkustajia linja-autoon
 * @param matkustajia Kuinka monta matkustajaa lisätään
 * @return 0 jos kaikki lisättävät mahtuvat linja-autoon, muuten kuinka monta matkustajaa jäi yli
 * @code
 * <pre name="test">
 *  LinjaAuto linkki(20); linkki.tilaa() === 20;
 *  linkki.lisaa(15) === 0; linkki.tilaa() === 5;
 *  linkki.lisaa(10) === 5; linkki.tilaa() === 0;
 * </pre>
 * @endcode
 */
int LinjaAuto::lisaa(int matkustajia)
{
    int yli = (this->matkustajia += matkustajia) - kapasiteetti;
    if (yli > 0) {
        this->matkustajia = kapasiteetti; 
        return yli;
    }
    return 0;
}

/**
 * @brief Tulostaa linja-auton tiedot std::cout -tietovirtaan
 * @code
 * <pre name="test">
 *  StringOutput so;
 *  LinjaAuto linkki{20};
 *  linkki.lisaa(5);
 *  linkki.tulosta();
 *  so.ero("Matkustajia 5, mahtuu vielä 15\n") === "";
 * </pre>
 * @endcode
 */
void LinjaAuto::tulosta() const
{
    std::cout << "Matkustajia " << matkustajia << ", mahtuu vielä " << tilaa() << std::endl;
}

/**
 * @brief Kertoo kuinka paljon linja-autossa on tilaa
 * @return Kuinka paljon linja-autossa on tilaa
 * @code
 * <pre name="test">
 *  LinjaAuto linkki(20); linkki.tilaa() === 20;
 *  linkki.lisaa(20); linkki.tilaa() === 0;
 *  linkki.vahenna(5) === 15; linkki.tilaa() === 5;
 * </pre>
 * @endcode
 */
int LinjaAuto::tilaa() const
{
    return kapasiteetti - matkustajia;
}

/**
 * @brief Vähentää linja-auton matkustajia
 * @param matkustajia Kuinka monta matkustajaa vähennetään
 * @return Matkustajien lukumäärä vähennyksen jäljiltä, negatiivinen luku tarkoittaa kuinka monta jäi vähentämättä 
 * yritettäessä vähentää enemmän matkustajia kuin linja-autossa oli matkustajia
 * @code
 * <pre name="test">
 *  LinjaAuto linkki(20); linkki.tilaa() === 20;
 *  linkki.lisaa(20); linkki.tilaa() === 0;
 *  linkki.vahenna(5) === 15; linkki.tilaa() === 5;
 *  linkki.vahenna(20) === -5; linkki.tilaa() === 20;
 * </pre>
 * @endcode
 */
int LinjaAuto::vahenna(int matkustajia)
{
    int ali = this->matkustajia -= matkustajia;
    if (ali < 0) this->matkustajia = 0;
    return ali;
}

/**
 * @brief Pääohjelma, testataan LinjaAuto-luokan toimintaa
 * @return 0
 */
int main(void)
{
    LinjaAuto pikkubussi(10), isobussi(45);
    pikkubussi.lisaa(4); pikkubussi.tulosta(); // Matkustajia 4, mahtuu vielä 6
    isobussi.lisaa(30); isobussi.tulosta(); // Matkustajia 30, mahtuu vielä 15
    int yli = pikkubussi.lisaa(15); // yli = 15-6 = 9
    isobussi.lisaa(yli);
    pikkubussi.tulosta(); // Matkustajia 10, mahtuu vielä 0
    isobussi.tulosta(); // Matkustajia 39, mahtuu vielä 6
    if ( isobussi.tilaa() ) // true
        cout << "Isoon bussiin mahtuu!" << endl;
    int vajaa = pikkubussi.vahenna(12); // 10-12 = -2
    if ( vajaa < 0 ) cout << "Pikkubussissa ei edes ole näin montaa!" << endl; // true
    pikkubussi.tulosta(); // Matkustajia 0, mahtuu vielä 10

    return 0;
}