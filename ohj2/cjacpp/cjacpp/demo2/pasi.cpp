 /** @file pasi.cpp
 * Ohjelmalla pelataan yksinkertaista pasianssia:
 *  0. pakka sotketaan
 *  1. ruvetaan laskemaan kädessä olevia kortteja pöydälle ja
 *     samalla lasketaan 1 2 3 4 .. 13 1 2 3 ..
 *  2. Jos laskuissa osutaan saman kortin kohdalle, joka laiteaan
 *     pöydälle, EI PELI MENE LÄPI
 *
 * @author Vesa Lappalainen
 * @version 28.11.1991
 */
#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <ctime>

using std::cout;
using std::ios;
using std::setw;
using std::endl;
using std::ostream;

//#include "dosout.h"

/*************************************************************************/
typedef enum {
  Hertta,Pata,Ruutu,Risti
} Maa_tyyppi;

typedef struct {
  Maa_tyyppi maa;
  int        arvo;
} Kortti_tyyppi;

typedef Kortti_tyyppi Pakka_tyyppi[52];


void jarjesta_pakka(Pakka_tyyppi pakka)
{
  Maa_tyyppi m;
  int a,i=0;

  for (m = Hertta; m <= Risti; m = (Maa_tyyppi)(m+1))
    for (a = 1; a <= 13; a++) {
      pakka[i].maa = m;
      pakka[i++].arvo =a;
    }
}


void sotke_pakka(Pakka_tyyppi pakka)
{
  int i,k;
  Kortti_tyyppi apu;

  jarjesta_pakka(pakka);

  for ( i=51; i>=0; i-- ) {
    k = rand() % (i+1);  /* Satunnaisluku välille [0-i] */
    apu      = pakka[i];
    pakka[i] = pakka[k];
    pakka[k] = apu;
  }
}

int pelaa_peli(Pakka_tyyppi pakka)
{
  int k;
  for (k=0; k<52; k++) {
    if ( pakka[k].arvo == (k%13)+1 ) return k+1;
  }
  return 0;
}

/*************************************************************************/

class Histo {
  int lkm;
  long *esiintymat;
public:
  Histo(int n);
  ~Histo();
  int lisaa(int i);
  long etsi_max() const;
  int piirra(ostream &os) const;
  Histo &operator=(const Histo&) = delete; // C++11 oletusmetodien poistaminen
private:
  Histo(const Histo&); // private copy constructor vrt oletusmetodin poistaminen
};

Histo::Histo(int n = 0)
{
}

Histo::~Histo()
{
}

/*************************************************************************/
int main(void)
{
  int p,lapi=0;
  long peleja=0;
  Pakka_tyyppi pakka;
  srand(time(NULL)); // randomize
  cout.setf(ios::showpoint | ios::fixed );
  cout.precision(2);

  while (lapi<100) {
    sotke_pakka(pakka);
    p = pelaa_peli(pakka);
    peleja++;
    if ( p==0 ) lapi++;
    if ( !(peleja % 1000) ) {
      cout << "Peli "   << setw(5) << peleja
           << ": Läpi " << setw(3) << lapi << " kertaa."
           << "  Tn: "  << setw(5) << 100.0*lapi/peleja << "%" << endl;
    }
  }
  
  Histo histo(30);
  // Histo histo2{histo}; // ei käänny
  Histo histo3(10);
  // histo3 = histo; // ei käänny
  
  return 0;
}