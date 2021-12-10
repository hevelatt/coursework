#include <stdio.h>

/**
 * @brief Tulostaa lyhyet ohjeet ohjelman käyttöä varten
 */
void ohjeet(void)
{
  printf("Ohjelma laskee huoneen pinta-alan ja tilavuuden annettujen tietojen perusteella.\n");
}

/**
 * @brief Kysyy käyttäjältä alueen leveyden, pituuden ja korkeuden
 * @param pLeveys Kysyttävän leveyden osoitin
 * @param pPituus Kysyttävän pituuden osoitin
 * @param pKorkeus Kysyttävän korkeuden osoitin
 */
void kysy_mitat(double *pLeveys, double *pPituus, double *pKorkeus)
{
	printf("Anna huoneen leveys, pituus ja korkeus metreina>");
	scanf("%lf %lf %lf", &*pLeveys, &*pPituus, &*pKorkeus);
	printf("\n");
}

/**
 * @brief Laskee pinta-alan annetuilla leveyden ja pituuden arvoilla
 * @param leveys Alueen leveys
 * @param pituus Alueen pituus
 * @return Alueen pinta-ala
 * @code
 * <pre name="test">
 *   laske_ala(2.1, 1.2) ~~~ 2.52;
 *   laske_ala(0, 4.1) ~~~ 0;
 *   laske_ala(-1,3) ~~~ -3;
 * </pre>
 * @endcode
 */
double laske_ala(double leveys, double pituus)
{
	return leveys*pituus;
}

/**
 * @brief Laskee tilavuuden annetuilla pinta-alan ja korkeuden arvoilla
 * @param leveys Alueen pinta-ala
 * @param pituus Alueen korkeus
 * @return Alueen tilavuus
 * @code
 * <pre name="test">
 *   laske_ala(2.1, 1.2) ~~~ 2.52;
 *   laske_ala(0, 4.1) ~~~ 0;
 *   laske_ala(-1,3) ~~~ -3;
 * </pre>
 * @endcode
 */
double laske_tilavuus(double ala, double korkeus)
{
	return ala*korkeus;
}

/**
 * @brief Tulostaa annetut mitat
 * @param ala Tulostettava pinta-ala
 * @param tilavuus Tulostettava tialvuus
 */
void tulosta_mitat(double ala, double tilavuus)
{
	printf("Huoneen pinta-ala on %.2f ja tilavuus on %.2f \n", ala, tilavuus);
}

/**
 * @brief Pääohjelma, lasketaan pinta-alaa ja tilavuutta annetuilla arvoilla
 * @return 0
 */
int main(void)
{
	double leveys, pituus, korkeus, ala, tilavuus;
	ohjeet();
	kysy_mitat(&leveys, &pituus, &korkeus);
	ala = laske_ala(leveys, pituus);
	tilavuus = laske_tilavuus(ala, korkeus);
	tulosta_mitat(ala, tilavuus);
	return 0;
}