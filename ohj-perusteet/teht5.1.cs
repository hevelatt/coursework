using System;

namespace Tehtava5._1
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;

            double eur; //Käyttäjän syöttämä euromäärä
            string inputCurrency; //Käyttäjän syöttämä valuutan nimi

            //Ohjelmaan voi lisätä uuden valuutan helposti, ks. Currency-luokka alempana
            Currency[] currencies = new Currency[] {
                new Currency("USD", 1.19, "$"),
                new Currency("GBP", 0.86, "£")
                //new Currency()
                //new Currency("jpy", 129.77)
            };

            //Sekä euromäärän että valuutan voi antaa myös argumentteina
            if (args.Length > 0)
            {
                eur = double.Parse(args[0]);
            }
            else
            {
                Console.WriteLine("Syötä rahamäärä (€):");
                eur = double.Parse(AskUser());
            }

            if (args.Length > 1)
            {
                inputCurrency = args[1]; //Valuutan voi antaa toisena argumenttina
            }
            else
            {
                PrintCurrencies(currencies);
                inputCurrency = AskUser();
            }

            while (true)
            {
                foreach (Currency currency in currencies)
                {
                    if (inputCurrency.Equals(currency.GetName(), comparisonType: StringComparison.OrdinalIgnoreCase))
                    {
                        //Jos käyttäjä syöttää validin valuutan nimen, niin tulostetaan vastaus ja lopetetaan ajo
                        Console.WriteLine("{0}: {1:n} {2}", 
                            inputCurrency, eur * currency.GetCourse(), currency.GetSymbol());
                        return;
                    }
                }
                //Jos valuuttaa ei löydetä tunnetuista valuutoista, käyttäjältä kysytään uudestaan
                Console.WriteLine("Valuuttaa ei tunneta!");
                PrintCurrencies(currencies);
                inputCurrency = AskUser();
            }
        }

        /// <summary>
        /// Tulostaa listan valuutoista
        /// </summary>
        /// <param name="currencies">Taulukko valuutoista, jotka tulostetaan</param>
        private static void PrintCurrencies(Currency[] currencies)
        {
            Console.WriteLine("Valitse valuutta, johon muunnetaan:");
            foreach (Currency currency in currencies)
            {
                Console.WriteLine(currency.GetName());
            }
        }

        /// <summary>
        /// Kysyy käyttäjältä tekstisyötettä
        /// </summary>
        /// <returns>Palauttaa tekstisyötteen</returns>
        private static string AskUser()
        {
            Console.Write("> ");
            return Console.ReadLine(); 
        }
    }

    //Tein valuutalle oman luokan ohjelman skaalautuvuutta parantaakseni
    class Currency
    {
        //Attribuutit
        private string name;
        private double course;
        private string symbol;

        /// <summary>
        /// Valuutan oletusmuodostaja
        /// </summary>
        public Currency()
        {
            name = "eur";
            course = 1;
            symbol = "€";
        }

        /// <summary>
        /// Valuutan muodostaja
        /// </summary>
        /// <param name="name">Valuutan nimi</param>
        /// <param name="course">Valuutan kurssi euroon nähden</param>
        public Currency(string name, double course)
        {
            this.name = name;
            this.course = course;
            symbol = name.ToUpper(); // Jos valuutalle ei anneta symbolia, niin symboli on nimi isoilla kirjaimilla
        }

        /// <summary>
        /// Valuutan muodostaja
        /// </summary>
        /// <param name="name">Valuutan nimi</param>
        /// <param name="course">Valuutan kurssi euroon nähden</param>
        /// <param name="symbol">Valuutan symboli</param>
        public Currency(string name, double course, string symbol)
        {
            this.name = name;
            this.course = course;
            this.symbol = symbol;
        }

        /// <summary>
        /// Saantimetodi valuutan nimelle
        /// </summary>
        /// <returns>Palauttaa valuutan nimen</returns>
        public string GetName()
        {
            return name;
        }

        /// <summary>
        /// Saantimetodi valuutan kurssille
        /// </summary>
        /// <returns>Palauttaa valuutan kurssin</returns>
        public double GetCourse()
        {
            return course;
        }

        /// <summary>
        /// Saantimetodi valuutan symbolille
        /// </summary>
        /// <returns>Palauttaa valuutan symbolin</returns>
        public string GetSymbol()
        {
            return symbol;
        }
    }
}