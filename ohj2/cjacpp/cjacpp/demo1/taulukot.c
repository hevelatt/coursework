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
 * @brief Pääohjelma, testataan aliohjelmia pienin_paikka ja pienin kuukausien pituudet sisältävällä taulukolla
 * ja tulostetaan vastaukset
 * @return 0
 */
int main(void)
{
    int k_pituudet[kuukausia] = {31,28,31,30,31,30,31,31,30,31,30,31};
    printf("%d\n", pienimman_paikka(k_pituudet, kuukausia));
    printf("%d\n", pienin(k_pituudet, kuukausia));
    return 0;
}