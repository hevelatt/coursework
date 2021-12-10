using System;
using System.Globalization;

namespace _05_Velka
{
    class Velka
    {
        private double _saldo;
        private double _korkoProsentti;

        public Velka(double saldoAlussa, double korkoProsentti)
        {
            _saldo = saldoAlussa;
            _korkoProsentti = korkoProsentti;
        }

        public void TulostaSaldo()
        {
            // Saldon ulkoasu helppo muuttaa haluttuun kulttuuriin.
            Console.WriteLine(_saldo.ToString("C", CultureInfo.CreateSpecificCulture("fi-FI")));
        }

        public void OdotaVuosi()
        {
            _saldo = _saldo * (1 + _korkoProsentti);
        }
    }
}
