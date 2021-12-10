namespace ElainLuokat
{
    public abstract class Nisakkaat : Elain
    {
        private bool onKarvapeite;

        public bool OnKarvapeite 
        {
            get 
            { 
                return onKarvapeite; 
            }
            set 
            { 
                onKarvapeite = value; 
            }
        }

        // Yhdistetään Nisakas-luokkaan.
        public abstract void MiltaNaytan();

        public void Mieliruokani()
        {
            System.Console.WriteLine("Tykkaan jostain hyvasta ruuasta!");
        }
    }
}
