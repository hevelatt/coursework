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
int _main(void)
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
 * @version 2020.07.30 22:46:59 // Generated by ComTest
 *
 */
class kulkuneuvotTest {


  // Generated by ComTest BEGIN
  /** testKulkuneuvo27 */
  public: void testKulkuneuvo27() {    // kulkuneuvot: 27
    Kulkuneuvo kulkuneuvo; 
    assertEquals("From: kulkuneuvot line: 29", 0, kulkuneuvo.getNopeus(), 0.000001); 
    Kulkuneuvo kulkuneuvo2{ 20} ; 
    assertEquals("From: kulkuneuvot line: 31", 20, kulkuneuvo2.getNopeus(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testKulkuneuvo51 */
  public: void testKulkuneuvo51() {    // kulkuneuvot: 51
    Kulkuneuvo kulkuneuvo; 
    assertEquals("From: kulkuneuvot line: 53", 0, kulkuneuvo.getMatkustajia()); 
    kulkuneuvo.lisaa(20); 
    assertEquals("From: kulkuneuvot line: 55", 20, kulkuneuvo.getMatkustajia()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testKulkuneuvo70 */
  public: void testKulkuneuvo70() {    // kulkuneuvot: 70
    Kulkuneuvo kulkuneuvo; 
    kulkuneuvo.lisaa(20); assertEquals("From: kulkuneuvot line: 72", 20, kulkuneuvo.getMatkustajia()); 
    assertEquals("From: kulkuneuvot line: 73", 5, kulkuneuvo.vahenna(15)); assertEquals("From: kulkuneuvot line: 73", 5, kulkuneuvo.getMatkustajia()); 
    assertEquals("From: kulkuneuvot line: 74", -10, kulkuneuvo.vahenna(15)); assertEquals("From: kulkuneuvot line: 74", 0, kulkuneuvo.getMatkustajia()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testKulkuneuvo90 */
  public: void testKulkuneuvo90() {    // kulkuneuvot: 90
    Kulkuneuvo kulkuneuvo; 
    assertEquals("From: kulkuneuvot line: 92", 0, kulkuneuvo.getNopeus(), 0.000001); 
    kulkuneuvo.nopeuta(20); 
    assertEquals("From: kulkuneuvot line: 94", 20, kulkuneuvo.getNopeus(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testKulkuneuvo109 */
  public: void testKulkuneuvo109() {    // kulkuneuvot: 109
    Kulkuneuvo kulkuneuvo{ 20} ; 
    assertEquals("From: kulkuneuvot line: 111", 5, kulkuneuvo.hidasta(15), 0.000001); assertEquals("From: kulkuneuvot line: 111", 5, kulkuneuvo.getNopeus(), 0.000001); 
    assertEquals("From: kulkuneuvot line: 112", -10, kulkuneuvo.hidasta(15), 0.000001); assertEquals("From: kulkuneuvot line: 112", 0, kulkuneuvo.getNopeus(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testKulkuneuvo128 */
  public: void testKulkuneuvo128() {    // kulkuneuvot: 128
    StringOutput so; 
    Kulkuneuvo kulkuneuvo{ 20} ; 
    kulkuneuvo.lisaa(5); 
    kulkuneuvo.tulosta(std::cout); 
    assertEquals("From: kulkuneuvot line: 133", "", so.ero("nopeus 20, matkustajia 5")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testOperator148 */
  public: void testOperator148() {    // kulkuneuvot: 148
    StringOutput so; 
    Kulkuneuvo kulkuneuvo{ 20} ; 
    kulkuneuvo.lisaa(5); 
    Laiva laiva{ 50} ; 
    Lentokone lentokone{ 100, 200} ; 
    std::cout << kulkuneuvo << std::endl << laiva << std::endl << lentokone; 
    assertEquals("From: kulkuneuvot line: 155", "", so.ero("nopeus 20, matkustajia 5\nLaiva: maksiminopeus 50, nopeus 0, matkustajia 0\nLentokone: matkustajakapasiteetti 100, nopeus 200, matkustajia 0")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLaiva178 */
  public: void testLaiva178() {    // kulkuneuvot: 178
    Laiva laiva{ 50, 20} ; 
    assertEquals("From: kulkuneuvot line: 180", 20, laiva.getNopeus(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLaiva201 */
  public: void testLaiva201() {    // kulkuneuvot: 201
    Laiva laiva{ 50} ; assertEquals("From: kulkuneuvot line: 202", 0, laiva.getNopeus(), 0.000001); 
    assertEquals("From: kulkuneuvot line: 203", 0, laiva.nopeuta(30), 0.000001); assertEquals("From: kulkuneuvot line: 203", 30, laiva.getNopeus(), 0.000001); 
    assertEquals("From: kulkuneuvot line: 204", 10, laiva.nopeuta(30), 0.000001); assertEquals("From: kulkuneuvot line: 204", 50, laiva.getNopeus(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLaiva224 */
  public: void testLaiva224() {    // kulkuneuvot: 224
    StringOutput so; 
    Laiva laiva{ 50, 20} ; 
    laiva.tulosta(std::cout); 
    assertEquals("From: kulkuneuvot line: 228", "", so.ero("Laiva: maksiminopeus 50, nopeus 20, matkustajia 0")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLentokone252 */
  public: void testLentokone252() {    // kulkuneuvot: 252
    Lentokone lentokone{ 200, 20} ; 
    assertEquals("From: kulkuneuvot line: 254", 20, lentokone.getNopeus(), 0.000001); 
    assertEquals("From: kulkuneuvot line: 255", 200, lentokone.tilaa()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLentokone276 */
  public: void testLentokone276() {    // kulkuneuvot: 276
    Lentokone lentokone{ 50} ; assertEquals("From: kulkuneuvot line: 277", 50, lentokone.tilaa()); 
    assertEquals("From: kulkuneuvot line: 278", 0, lentokone.lisaa(30)); assertEquals("From: kulkuneuvot line: 278", 20, lentokone.tilaa()); 
    assertEquals("From: kulkuneuvot line: 279", 10, lentokone.lisaa(30)); assertEquals("From: kulkuneuvot line: 279", 0, lentokone.tilaa()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLentokone298 */
  public: void testLentokone298() {    // kulkuneuvot: 298
    Lentokone lentokone{ 50} ; assertEquals("From: kulkuneuvot line: 299", 50, lentokone.tilaa()); 
    assertEquals("From: kulkuneuvot line: 300", 0, lentokone.lisaa(30)); assertEquals("From: kulkuneuvot line: 300", 20, lentokone.tilaa()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLentokone314 */
  public: void testLentokone314() {    // kulkuneuvot: 314
    StringOutput so; 
    Lentokone lentokone{ 50, 20} ; 
    lentokone.tulosta(std::cout); 
    assertEquals("From: kulkuneuvot line: 318", "", so.ero("Lentokone: matkustajakapasiteetti 50, nopeus 20, matkustajia 0")); 
  } // Generated by ComTest END

  int runner(void) {
      int errors = 0;
      try { testKulkuneuvo27(); } catch (...) { errors++; }
      try { testKulkuneuvo51(); } catch (...) { errors++; }
      try { testKulkuneuvo70(); } catch (...) { errors++; }
      try { testKulkuneuvo90(); } catch (...) { errors++; }
      try { testKulkuneuvo109(); } catch (...) { errors++; }
      try { testKulkuneuvo128(); } catch (...) { errors++; }
      try { testOperator148(); } catch (...) { errors++; }
      try { testLaiva178(); } catch (...) { errors++; }
      try { testLaiva201(); } catch (...) { errors++; }
      try { testLaiva224(); } catch (...) { errors++; }
      try { testLentokone252(); } catch (...) { errors++; }
      try { testLentokone276(); } catch (...) { errors++; }
      try { testLentokone298(); } catch (...) { errors++; }
      try { testLentokone314(); } catch (...) { errors++; }
      if ( errors == 0 ) std::cout << "ok" << std::endl;
      return errors;
  }

};

int main(void) {
    kulkuneuvotTest t; t.runner();
    return 0;
}
