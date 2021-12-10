#include <stdio.h>
#define kuukausia 12

/**
 * @brief Palauttaa kokonaislukutaulukon pienimmän alkion indeksin
 * @param t Taulukko, josta etsitään pienimmän alkion indeksiä
 * @param lkm Taulukon alkioiden lukumäärä
 * @return Taulukon pienimmän alkion indeksi
 * @code
 * <pre name="test">
 *   int k_pituudet[kuukausia] = {31,28,31,30,31,30,31,31,30,31,30,31};
 *   pienimman_paikka(k_pituudet, kuukausia) === 1;
 *   int testi[3] = {-15,-20,-10};
 *   pienimman_paikka(testi, 3) === 1;
 *   pienimman_paikka(testi, 2) === 1;
 *   pienimman_paikka(testi, 1) === 0;
 *   pienimman_paikka(testi, 0) === 0;
 * </pre>
 * @endcode
 */
int pienimman_paikka(int t[], int lkm)
{
    int pienin = *t;
    int paikka = 0;
    int i;
    for (i = 0; i < lkm; i++)
        if (*(t + i) < pienin) {
            pienin = *(t + i);
            paikka = i;
        }
    return paikka;
}

/**
 * @brief Palauttaa kokonaislukutaulukon pienimmän alkion arvon
 * @param t Taulukko, josta pienintä alkiota etsitään
 * @param lkm Taulukon koko
 * @return Taulukon pienimmän alkion arvo
 * @code
 * <pre name="test">
 *   int k_pituudet[kuukausia] = {31,28,31,30,31,30,31,31,30,31,30,31};
 *   pienin(k_pituudet, kuukausia) === 28;
 *   int testi[3] = {-15,-20,10};
 *   pienin(testi, 3) === -20;
 *   pienin(testi, 2) === -20;
 *   pienin(testi, 1) === -15;
 *   pienin(testi, 0) === -15;
 * </pre>
 * @endcode
 */
int pienin(int t[], int lkm)
{
    return *(t + pienimman_paikka(t, lkm));
}

/**
 * @brief 
 * @return 
 */
int _main(void)
{
    int k_pituudet[kuukausia] = {31,28,31,30,31,30,31,31,30,31,30,31};
    printf("%d\n", pienimman_paikka(k_pituudet, kuukausia));
    printf("%d\n", pienin(k_pituudet, kuukausia));
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
 * @version 2020.07.28 20:30:38 // Generated by ComTest
 *
 */
class taulukotTest {


  // Generated by ComTest BEGIN
  /** testPienimman_paikka10 */
  public: void testPienimman_paikka10() {    // taulukot: 10
    int k_pituudet[kuukausia] = { 31,28,31,30,31,30,31,31,30,31,30,31} ; 
    assertEquals("From: taulukot line: 12", 1, pienimman_paikka(k_pituudet, kuukausia)); 
    int testi[3] = { -15,-20,-10} ; 
    assertEquals("From: taulukot line: 14", 1, pienimman_paikka(testi, 3)); 
    assertEquals("From: taulukot line: 15", 1, pienimman_paikka(testi, 2)); 
    assertEquals("From: taulukot line: 16", 0, pienimman_paikka(testi, 1)); 
    assertEquals("From: taulukot line: 17", 0, pienimman_paikka(testi, 0)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPienin40 */
  public: void testPienin40() {    // taulukot: 40
    int k_pituudet[kuukausia] = { 31,28,31,30,31,30,31,31,30,31,30,31} ; 
    assertEquals("From: taulukot line: 42", 28, pienin(k_pituudet, kuukausia)); 
    int testi[3] = { -15,-20,10} ; 
    assertEquals("From: taulukot line: 44", -20, pienin(testi, 3)); 
    assertEquals("From: taulukot line: 45", -20, pienin(testi, 2)); 
    assertEquals("From: taulukot line: 46", -15, pienin(testi, 1)); 
    assertEquals("From: taulukot line: 47", -15, pienin(testi, 0)); 
  } // Generated by ComTest END

  int runner(void) {
      int errors = 0;
      try { testPienimman_paikka10(); } catch (...) { errors++; }
      try { testPienin40(); } catch (...) { errors++; }
      if ( errors == 0 ) std::cout << "ok" << std::endl;
      return errors;
  }

};

int main(void) {
    taulukotTest t; t.runner();
    return 0;
}
