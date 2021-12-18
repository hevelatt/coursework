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
int main(void)
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