#include <stdio.h>
#include <ctype.h>

/**
 * @brief Palauttaa onko merkkijono (kirjaintaulukko) palindromi
 * @param jono Jono, jota tarkastellaan
 * @param lkm Jonon pituus
 * @return 1 jos on palindromi, muuten 0
 * @code
 * <pre name="test">
 *   char sana1[] = "SaippUaKIvikAuppias";
 *   char sana2[] = "eipalindromi";
 *   onko_palindromi(sana1) === 1;
 *   onko_palindromi(sana2) === 0;
 * </pre>
 * @endcode
 */
int onko_palindromi(char jono[])
{
    int i, j;
    int k = 0;
    int lkm = 0;
    while(*(jono+k)){
        lkm++;
        k++;
    }
    for (i = 0, j = lkm - 1; i < j; i++, j--)
        if (toupper(*(jono + i)) != toupper(*(jono + j)))
            return 0;
    return 1;
}

/**
 * @brief 
 * @return 
 */
int _main(void)
{
    char sana1[] = "Saippuakivikauppias";
    char sana2[] = "eipalindromi";
    if (onko_palindromi(sana1))
        printf("%s", sana1);
    if (onko_palindromi(sana2))
        printf("%s", sana2);
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
 * @version 2020.07.28 22:49:10 // Generated by ComTest
 *
 */
class palindromiTest {


  // Generated by ComTest BEGIN
  /** testOnko_palindromi10 */
  public: void testOnko_palindromi10() {    // palindromi: 10
    char sana1[] = "SaippUaKIvikAuppias"; 
    char sana2[] = "eipalindromi"; 
    assertEquals("From: palindromi line: 13", 1, onko_palindromi(sana1)); 
    assertEquals("From: palindromi line: 14", 0, onko_palindromi(sana2)); 
  } // Generated by ComTest END

  int runner(void) {
      int errors = 0;
      try { testOnko_palindromi10(); } catch (...) { errors++; }
      if ( errors == 0 ) std::cout << "ok" << std::endl;
      return errors;
  }

};

int main(void) {
    palindromiTest t; t.runner();
    return 0;
}
