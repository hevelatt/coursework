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
int _main(void)
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
 * @version 2020.07.30 22:04:38 // Generated by ComTest
 *
 */
class linjaautoTest {


  // Generated by ComTest BEGIN
  /** testLinjaAuto21 */
  public: void testLinjaAuto21() {    // linjaauto: 21
    LinjaAuto linkki; 
    assertEquals("From: linjaauto line: 23", 0, linkki.tilaa()); 
    LinjaAuto linkki2(20); 
    assertEquals("From: linjaauto line: 25", 20, linkki2.tilaa()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLinjaAuto38 */
  public: void testLinjaAuto38() {    // linjaauto: 38
    LinjaAuto linkki(20); assertEquals("From: linjaauto line: 39", 20, linkki.tilaa()); 
    assertEquals("From: linjaauto line: 40", 0, linkki.lisaa(15)); assertEquals("From: linjaauto line: 40", 5, linkki.tilaa()); 
    assertEquals("From: linjaauto line: 41", 5, linkki.lisaa(10)); assertEquals("From: linjaauto line: 41", 0, linkki.tilaa()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLinjaAuto58 */
  public: void testLinjaAuto58() {    // linjaauto: 58
    StringOutput so; 
    LinjaAuto linkki{ 20} ; 
    linkki.lisaa(5); 
    linkki.tulosta(); 
    assertEquals("From: linjaauto line: 63", "", so.ero("Matkustajia 5, mahtuu vielä 15\n")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLinjaAuto76 */
  public: void testLinjaAuto76() {    // linjaauto: 76
    LinjaAuto linkki(20); assertEquals("From: linjaauto line: 77", 20, linkki.tilaa()); 
    linkki.lisaa(20); assertEquals("From: linjaauto line: 78", 0, linkki.tilaa()); 
    assertEquals("From: linjaauto line: 79", 15, linkki.vahenna(5)); assertEquals("From: linjaauto line: 79", 5, linkki.tilaa()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLinjaAuto94 */
  public: void testLinjaAuto94() {    // linjaauto: 94
    LinjaAuto linkki(20); assertEquals("From: linjaauto line: 95", 20, linkki.tilaa()); 
    linkki.lisaa(20); assertEquals("From: linjaauto line: 96", 0, linkki.tilaa()); 
    assertEquals("From: linjaauto line: 97", 15, linkki.vahenna(5)); assertEquals("From: linjaauto line: 97", 5, linkki.tilaa()); 
    assertEquals("From: linjaauto line: 98", -5, linkki.vahenna(20)); assertEquals("From: linjaauto line: 98", 20, linkki.tilaa()); 
  } // Generated by ComTest END

  int runner(void) {
      int errors = 0;
      try { testLinjaAuto21(); } catch (...) { errors++; }
      try { testLinjaAuto38(); } catch (...) { errors++; }
      try { testLinjaAuto58(); } catch (...) { errors++; }
      try { testLinjaAuto76(); } catch (...) { errors++; }
      try { testLinjaAuto94(); } catch (...) { errors++; }
      if ( errors == 0 ) std::cout << "ok" << std::endl;
      return errors;
  }

};

int main(void) {
    linjaautoTest t; t.runner();
    return 0;
}
