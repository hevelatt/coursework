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
 *   char sana2[] = "iiiabiii";
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
 * @brief Tulostaa onko annettu sana palindromi
 * @param jono Kirjaintaulukko jota tutkitaan
 */
void tulosta_palindromi(char jono[])
{
    if (onko_palindromi(jono)) {
        printf("Sana %s on palindromi\n", jono);
        return;
    }
    printf("Sana %s ei ole palindromi\n", jono);
}

/**
 * @brief Pääohjelma, tulostaa muutamasta sanasta ovatko ne palindromeja
 * @return 0
 */
int main(void)
{
    char sana1[] = "Saippuakivikauppias";
    tulosta_palindromi(sana1);
    char sana2[] = "InnostunutSonni";
    tulosta_palindromi(sana2);
    // char sana3[] = 
    //tulosta_palindromi("iiiiabiiii");
    return 0;
}