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

int _main(void)
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
#include <iostream>
#include <fstream>
#include <cstring>
#include <cstdio>
#include <sstream>

namespace comtestCppNameSpace
{

//#define assertEquals(s,e,g) { if ( comtestCppNameSpace::assertEqualsImpl((s),(e),(g)) ) return; }
//#define assertEqualsDelta(s,e,g,delta) { if ( comtestCppNameSpace::assertEqualsImpl((s),(e),(g),(delta)) ) return; }
#define assertEquals comtestCppNameSpace::assertEqualsImpl

#include <unistd.h>
#include <fcntl.h>

class StringOutput;
class StringOutput2;
StringOutput* outputCapturer = 0;

class StringInput
{
    FILE* mock_input;

public:
    StringInput(const char* inp)
    {
        FILE* mock_input = freopen("mockinput", "w+", stdin);
        fputs(inp, mock_input);
        fseek(mock_input, 0, SEEK_SET);
    }

    ~StringInput()
    {
        freopen(NULL, "r", stdin);
        remove("mockinput");
        // fclose(mock_input);
    }
};

class StringOutput
{
    const char *mockname;
    FILE *stream;
    int fd;
    fpos_t pos;
    std::string lines;
    int filenumber;
    

public:
    StringOutput(bool err = false)
    {
        if ( err ) {
            stream = stderr;
            mockname = "mocstderr";
            filenumber = STDERR_FILENO;
        } else {
            stream = stdout;
            mockname = "mockoutput";
            filenumber = STDOUT_FILENO;
        }
        begin();
    }

    void begin()
    {
        fflush(stream);
        fgetpos(stream, &pos);
        fd = dup(filenumber);
        freopen(mockname, "w", stream);
        if ( stream == stdout ) outputCapturer = this;
    }

    ~StringOutput()
    {
        if ( stream == stdout ) outputCapturer = 0;
        end();
    }

    void end()
    {
        if(fd < 0)
            return;
        // freopen(NULL, "w", stdout);
        // remove("mockoutput");
        // fclose(mock_output);
        fflush(stream);
        dup2(fd, filenumber);
        close(fd);
        clearerr(stream);
        fsetpos(stream, &pos);
        fd = -1;
        std::ifstream t(mockname);
        std::stringstream buffer;
        buffer << t.rdbuf();
        lines = buffer.str(); 
        remove(mockname);
    }

    std::string get()
    {
        end();
        return lines;
    }

    const std::string ero(const char* s)
    {
        std::string b = get();
        if(b == s)
            return "";
        return "was: " + b + " should be: " + s;
    }
};


void endCapture()
{
    if(outputCapturer)
        outputCapturer->end();
}

template <class T1> class assertion_traits
{
public:
    static bool equal(const T1& x, const T1& y)
    {
        return x == y;
    }
};

template <> class assertion_traits<const char*>
{
public:
    static bool equal(const char* x, const char* y)
    {
        if(x == 0 && y == 0)
            return true;
        if(x == 0 || y == 0)
            return false;
        return std::strcmp(x, y) == 0;
    }
};

template <> class assertion_traits<char*>
{
public:
    static bool equal(const char* x, char* y)
    {
        if(x == 0 && y == 0)
            return true;
        if(x == 0 || y == 0)
            return false;
        return std::strcmp(x, y) == 0;
    }
};

template <class T1> int assertEqualsImpl(const char* msg, const T1& expected, const T1& actual)
{
    if(assertion_traits<T1>::equal(expected, actual))
        return 0;
    endCapture();
    std::cout << msg << ": expected [" << expected << "] actual [" << actual << "]" << std::endl;
    throw int(1);
}

int assertEqualsImpl(const char* msg, const char* expected, char* actual)
{
    if(assertion_traits<char*>::equal(expected, actual))
        return 0;
    endCapture();
    if(expected == 0)
        std::cout << msg << ": expected [null] actual [" << actual << "]" << std::endl;
    else if(actual == 0)
        std::cout << msg << ": expected [" << expected << "] actual [null]" << std::endl;
    else
        std::cout << msg << ": expected [" << expected << "] actual [" << actual << "]" << std::endl;
    throw int(1);
}

template <class T1> int assertEqualsImpl(const char* msg, const char* expected, const T1& actual)
{
    if(assertion_traits<T1>::equal(expected, actual))
        return 0;
    endCapture();
    if(expected == 0)
        std::cout << msg << ": expected [null] actual [" << actual << "]" << std::endl;
    else if(&actual == 0)
        std::cout << msg << ": expected [" << expected << "] actual [null]" << std::endl;
    else
        std::cout << msg << ": expected [" << expected << "] actual [" << actual << "]" << std::endl;
    throw int(1);
}

int assertEqualsImpl(const char* msg, int expected, int actual)
{
    if(expected == actual)
        return 0;
    endCapture();
    std::cout << msg << ": expected [" << expected << "] actual [" << actual << "]" << std::endl;
    throw int(1);
}

int assertEqualsImpl(const char* msg, long expected, long actual)
{
    if(expected == actual)
        return 0;
    endCapture();
    std::cout << msg << ": expected [" << expected << "] actual [" << actual << "]" << std::endl;
    throw int(1);
}

int assertEqualsImpl(const char* msg, double expected, double actual, double delta)
{
    if(-delta <= (actual - expected) && (actual - expected) <= delta)
        return 0;
    endCapture();
    std::cout << msg << ": expected [" << expected << "] actual [" << actual << "]" << std::endl;
    throw int(1);
}

#define StringInput comtestCppNameSpace::StringInput
#define StringOutput comtestCppNameSpace::StringOutput
}

/**
 * Test class made by ComTest
 * @version 2020.07.31 12:09:13 // Generated by ComTest
 *
 */
class taulukotTest {


  // Generated by ComTest BEGIN
  /** testTaulukko12 */
  public: void testTaulukko12() {    // taulukot: 12
    ostringstream ss; 
    Taulukko luvut(10); 
    ss << luvut; assertEquals("From: taulukot line: 15", "", ss.str()); 
    luvut.lisaa(0); luvut.lisaa(2); luvut.lisaa(2); 
    luvut.lisaa(4); luvut.lisaa(2); 
    ss << luvut; assertEquals("From: taulukot line: 18", "0 2 2 4 2 ", ss.str()); ss.str(""); 
    Taulukko taul; 
    taul.sijoita(luvut); 
    ss << taul; assertEquals("From: taulukot line: 21", "0 2 2 4 2 ", ss.str()); ss.str(""); 
    taul.lisaa(99); 
    luvut.lisaa(88); 
    ss << taul; assertEquals("From: taulukot line: 24", "0 2 2 4 2 99 ", ss.str()); ss.str(""); 
    ss << luvut; assertEquals("From: taulukot line: 25", "0 2 2 4 2 88 ", ss.str()); ss.str(""); 
  } // Generated by ComTest END

  int runner(void) {
      int errors = 0;
      try { testTaulukko12(); } catch (...) { errors++; }
      if ( errors == 0 ) std::cout << "ok" << std::endl;
      return errors;
  }

};

int main(void) {
    taulukotTest t; t.runner();
    return 0;
}
