#include <stdio.h>
#define CURRENT_CENTURY 21

/**
 * @brief Kertoo, onko annettu vuosiluku tältä vuosisadalta
 * @param vuosi Vuosi, jota tarkastellaan
 * @return 1 jos vuosi on tältä vuosisadalta, muussa tapauksessa 0
 * @code
 * <pre name="test">
 *   talta_vuosisadalta(2000) === 1;
 *   talta_vuosisadalta(1999) === 0;
 *   talta_vuosisadalta(2099) === 1;
 *   talta_vuosisadalta(2100) === 0;
 * </pre>
 * @endcode
 */
int talta_vuosisadalta(int vuosi)
{
    if (vuosi/100+1 == CURRENT_CENTURY)
        return 1;
    return 0;
}

/**
 * @brief Kysyy käyttäjältä vuoden
 * @param vuosi Vuosi, jota kysytään
 * @return 1 jos vuosi on tältä vuosisadalta, muuten 0
 */
int kysy_vuosi(int *vuosi)
{
    printf("Anna vuosi>");
    if (scanf("%d", vuosi) < 1)
        return 0;
    return talta_vuosisadalta(*vuosi);
}

/**
 * @brief Tulostaa lyhyen ohjeen
 */
void ohjeet(void)
{
    printf("Ohjelma kertoo onko annettu vuosiluku tältä vuosisadalta.\n");
}

/**
 * @brief Pääohjelma, kysytään vuosilukua ja kerrotaan onko se tältä vuosisadalta
 * @return 0
 */
int _main(void)
{
    int vuosi;
    ohjeet();
    if (kysy_vuosi(&vuosi) == 0) {
        printf("Vuosi %d ei ole tältä vuosisadalta\n", vuosi);
        return 0;
    }
    printf("Vuosi %d on tältä vuosisadalta\n", vuosi);
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
 * @version 2020.07.28 22:48:44 // Generated by ComTest
 *
 */
class funktiotTest {


  // Generated by ComTest BEGIN
  /** testTalta_vuosisadalta9 */
  public: void testTalta_vuosisadalta9() {    // funktiot: 9
    assertEquals("From: funktiot line: 10", 1, talta_vuosisadalta(2000)); 
    assertEquals("From: funktiot line: 11", 0, talta_vuosisadalta(1999)); 
    assertEquals("From: funktiot line: 12", 1, talta_vuosisadalta(2099)); 
    assertEquals("From: funktiot line: 13", 0, talta_vuosisadalta(2100)); 
  } // Generated by ComTest END

  int runner(void) {
      int errors = 0;
      try { testTalta_vuosisadalta9(); } catch (...) { errors++; }
      if ( errors == 0 ) std::cout << "ok" << std::endl;
      return errors;
  }

};

int main(void) {
    funktiotTest t; t.runner();
    return 0;
}
