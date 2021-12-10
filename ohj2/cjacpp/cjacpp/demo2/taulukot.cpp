// taul_d.cpp -esimerkki dynaamisesta taulukosta
#include <iostream>
using std::cout;
using std::ostream;
using std::endl;
#include <sstream>
using std::ostringstream;

/**
 * Luokka dynaamiselle int-taulukolle
 * @code
 * <pre name="test">
 *   ostringstream ss;
 *   Taulukko luvut(10);
 *   ss << luvut; ss.str() === "";
 *   luvut.lisaa(0); luvut.lisaa(2); luvut.lisaa(2);
 *   luvut.lisaa(4); luvut.lisaa(2);
 *   ss << luvut; ss.str() === "0 2 2 4 2 ";       ss.str("");
 *   Taulukko taul;
 *   taul.sijoita(luvut);
 *   ss << taul;  ss.str() === "0 2 2 4 2 ";       ss.str("");
 *   taul.lisaa(99);
 *   luvut.lisaa(88);
 *   ss << taul;  ss.str() === "0 2 2 4 2 99 ";    ss.str("");
 *   ss << luvut; ss.str() === "0 2 2 4 2 88 ";    ss.str("");
 * </pre>
 * @endcode
 */
class Taulukko {
  size_t max_koko;
  size_t lkm;
  int *alkiot;
  int vika;
public:
  Taulukko(size_t akoko=0) {
    vika = 0;
    max_koko = 0;
    alkiot = new int[akoko];  // new delete   // new [] -  delete []
    if ( alkiot ) 
        max_koko = akoko;
    lkm = 0;
  }
  ~Taulukko() { if ( max_koko != 0 ) max_koko = 0; delete [] alkiot;}

// Pitää olla jokaisessa luokassa jossa on "dynaamista tavaraa"
  void sijoita(const Taulukko &t) 
  {
    if (alkiot == t.alkiot) return;
    
    delete [] alkiot;
    alkiot = new int[t.max_koko];
    if ( alkiot ) 
        max_koko = t.max_koko;
    lkm = 0;
    lisaa(t.alkiot, t.lkm);
  }
  Taulukko &operator=(const Taulukko &t) { sijoita(t); return *this; }
  Taulukko(const Taulukko &t) { sijoita(t); } // Copy Constructor
  
  Taulukko operator+(const Taulukko &t) 
  { 
    Taulukko uusi{max_koko + t.max_koko}; // syntyy uusi olio joka plussauksella
    uusi.lisaa(alkiot, lkm);
    uusi.lisaa(t.alkiot, t.lkm);
    return uusi; 
  }

  int operator[](size_t i) const {
    if ( 0 <= i  &&  i < lkm ) return alkiot[i];
    return 0;
  }
  int &operator[](size_t i) {
    if ( 0 <= i  &&  i < lkm ) return alkiot[i];
    return vika;
  }

  int lisaa(int luku) {
    if ( lkm >= max_koko ) return 1;
    alkiot[lkm] = luku;
    lkm++;
    return 0;
  }
  ostream &tulosta(ostream &os=cout) const;

  int lisaa(std::initializer_list<int> lista) {
      for (int luku : lista) if ( lisaa(luku) ) return 1;
      return 0;
  }
  
  int lisaa(const int alkiot[], const size_t lkm)
  {
    for (size_t i = 0; i < lkm; i++)
        lisaa(alkiot[i]);
    return 0;
  }
};



ostream &Taulukko::tulosta(ostream &os) const
{
  size_t i;
  for (i=0; i < lkm; i++)
    os << alkiot[i] << " ";
  // os << endl;
  return os;
}

ostream &operator<<(ostream &os, const Taulukko &t) { return t.tulosta(os); }

int main(void)
{
  Taulukko luvut(7);
  luvut.lisaa(0); luvut.lisaa(2);
  luvut.sijoita(luvut);
  cout << luvut << endl;  // 0 2
  Taulukko taul;
  taul.sijoita(luvut);   // tai jopa  taul = luvut;
  cout << taul << endl;  // tulostaa saman kuin edellä
  cout << luvut << endl; // ja taas saman
  Taulukko t0;
  cout << t0 << endl; // ei tulosta mitään
  
  Taulukko t3 = luvut + taul;
  cout << t3 << endl;    // 0 2 0 2
  cout << luvut << endl; // 0 2
  cout << taul << endl; // 0 2
  cout << t0 + luvut + taul + t3 << endl; // 0 2 0 2 0 2 0 2
  // koska syntyy uusi olio joka plussauksella tällainen ketjuttaminen on tosi tehotonta
  
  return 0;
}