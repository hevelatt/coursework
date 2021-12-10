/** @file valuutts.cpp
 * Ohjelma, jossa rahanvaihtotaulukko luetaan tiedostosta joka on muotoa:
 *   mk     1.0
 *   $      5.7
 *   ECU    6.5
 *   SKr    0.9
 *
 * Valmiin voit hakea esim:
 *   https://svn.cc.jyu.fi/srv/svn/ohj2/esimerkit/k2019/demot/tehtavat/cpp/valuutta/valuutts.cpp
 *
 * Kääntämisohjeet Eclipsellä:
 *  0) Käännösoptiot niin, että C++11 kääntyy
 *  1) Ota http://users.jyu.fi/~vesal/kurssit/ohj2/cppali/ali.zip
 *  2) Pura johon hakemistoon, esimerkissä tämä on e:\kurssit\ohj2\cppali
 *  3) Tee Eclipsessä uusi C++ projekti
 *  4) Project/properties/C/C++ General/Paths and Symbols
 *  5) Includes/CNU C++/Add...  e:\kurssit\ohj2\cppali  ruksi all-jutut (mutta ei workspace)
 *  6) Source location/Link folder.../Advanced Folder name:cppali Ruksi Link to ja nimeksi e:\kurssit\ohj2\cppali
 *  7) Jos teet Windowsissa ja haluat että komentoriviltä tulee äät oikein,
 *     niin Symbols/GNU C++/Add...   ja nimeksi DOSOUT ja arvoksi vaikka 1
 *  8) Build ja Aja
 *
 * Kääntämisohjeet CFree: https://tim.jyu.fi/view/kurssit/tie/ohj2/2019k/demot/demovinkkic2
 *
 * Ohjelma toteutettu STL:n talletusluokalla list
 * Täytynyt tehdä operattori != Valuutta-luokaan
 * @author Vesa Lappalainen
 * @version 15.4.1999
 */
#include <cstdio>
#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
#include "mjonotpp.h"
#include "streampr.h"

//using namespace std;
using std::string;
using std::ostream;
using std::istream;
using std::ifstream;
using std::endl;
using std::cin;

#include "dosout.h"

//---------------------------------------------------------------------------
class Valuutta {
  string valuutta;
  double maara;
public:
  int alusta(const string &st) {  // "mk 1.00"
    char val[100];
    if ( sscanf(st.c_str(),"%lf %s",&maara,val) != 2 ) return 1;
    valuutta = val;
    return 0;
  }
  int alusta(double d,const string &st) {
    maara = d;
    valuutta = st;
    return 0;
  }
  Valuutta(double d=1.0, const string &st = "mk") { alusta(d,st);           }
  Valuutta(const string &st)                      { alusta(st);             }
  double getMaara()             const              { return maara;           }
  const string &getValuutta()   const              { return valuutta;        }
  ostream &tulosta(ostream &os) const {
    cStreamPre pre(os,2);
    os << maara << " " << valuutta; return os;
  }
  int operator==(const Valuutta &val) const {
    return onko_samat(valuutta,val.valuutta+"*") == 0;
  }
  int operator!=(const Valuutta &val) const {
    return onko_samat(valuutta,val.valuutta+"*") != 0;
  }
};

ostream &operator<<(ostream &os,const Valuutta &valuutta) { return valuutta.tulosta(os); }

istream &operator>>(istream &is,Valuutta &valuutta) {
  string rivi;
  getline(is,rivi);
  valuutta.alusta(rivi);
  return is;
}

#include <list>
#include <algorithm>
#include <iterator>
using std::list;
using std::ostream_iterator;

//---------------------------------------------------------------------------
// Peritään list-luokasta
// Valuutat, johon lisätään muutama valuutoille ominainen lisäpiirre
class Valuutat : public list<Valuutta> {
  static string virhe;
public:
  Valuutat(int koko=0) : list<Valuutta>{} { ;  }
  const string &valuutta(const char *val) const {
    Valuutat::const_iterator i = find(begin(),end(),Valuutta(1.0,val));
    if ( i != end() ) return i->getValuutta();
    return virhe;
  }
  double kerroin(const Valuutta &val) const {
    Valuutat::const_iterator i = find(begin(),end(),val);
    if ( i != end() ) return i->getMaara();
    return 1.0;
  }
  const string &mk() const { return begin()->getValuutta(); }
  int lue(const string &nimi) {
    ifstream f(nimi.c_str()); if ( !f ) return 1;
    string rivi;
    while ( getline(f,rivi) ) { if ( rivi != "" ) push_back(Valuutta(rivi)); }
    return 0;
  }

};


ostream &operator<<(ostream &os, const Valuutat &v)
{
  copy(v.begin(),v.end(),ostream_iterator<Valuutta>(os,"\n"));
  return os;
}


string Valuutat::virhe("?$?");


//---------------------------------------------------------------------------
class Naytto {
  Valuutat *valuutat;
  Valuutta valuutta;
  char valuutta_jono[80];
public:
  Naytto(Valuutat *val) : valuutat{val} { valuutta_jono[0] = 0; }
  void tulosta() {
    double mk_maara = valuutat->kerroin(valuutta) * valuutta.getMaara();
    Valuutta mk(mk_maara,valuutat->mk());
    cout << valuutta << " on " << mk << endl;
  }
  int kysy();
};


int Naytto::kysy()
{
  string jono; double raha;

  cout << "Määrä ja valuutta>";  getline(cin,jono); // 5
  cout << jono << endl; // TIMissä jotta syöte näyttää paremmalta
  if ( jono == "" ) return 1;
  if ( jono == "loppu" ) return 1;
  if ( jono.length() >= sizeof(valuutta_jono) ) return 1; // suojaa ylivuodolta

  sscanf(jono.c_str(),"%lf%s",&raha,valuutta_jono);
  valuutta.alusta(raha,valuutat->valuutta(valuutta_jono));

  return 0;
}


//---------------------------------------------------------------------------
int main(void)
{
  Valuutat valuutat{20};
  if ( valuutat.lue("valuutat.dat") ) return 1;
  cout << valuutat;
  Naytto naytto(&valuutat);

  while ( naytto.kysy() == 0 )
    naytto.tulosta();

  cout << "Kiitos!" << endl;
  return 0;
}