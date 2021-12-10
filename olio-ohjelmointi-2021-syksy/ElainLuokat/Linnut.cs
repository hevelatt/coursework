namespace ElainLuokat
{
    public abstract class Linnut : Elain
    {
        private double siipivali;

        public bool AsetaSiipivali(double siipivali)
        {
            if (siipivali < 0)
            {
                return false;
            }
            this.siipivali = siipivali;
            return true;
        }

        public double PalautaSiipivali()
        {
            return this.siipivali;
        }
    }
}
