namespace _08_Maksukortti
{
    public class Maksukortti
    {
        private double saldo;
        public Maksukortti(double alkusaldo)
        {
            saldo = alkusaldo;
        }

        public override string ToString()
        {
            return "Kortilla on rahaa " + saldo + " euroa";
        }

        public void SyoEdullisesti()
        {
            double uusiSaldo = saldo - 2.6;
            if (uusiSaldo >= 0)
            {
                saldo = uusiSaldo;
            }
        }

        public void SyoMaukkaasti()
        {
            double uusiSaldo = saldo - 4.6;
            if (uusiSaldo >= 0)
            {
                saldo = uusiSaldo;
            }
        }

        public void LataaRahaa(double lisarahaa)
        {
            if (lisarahaa < 0)
            {
                return;
            }
            saldo += lisarahaa;
        }
    }
}
