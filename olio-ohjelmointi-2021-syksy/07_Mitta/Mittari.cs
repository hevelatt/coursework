namespace _07_Mitta
{
    class Mittari
    {
        private int _mitta;
        private static int taysi = 5;

        public Mittari()
        {
            _mitta = 0;
        }

        public void Lisaa()
        {
            if (_mitta < taysi)
            {
                _mitta++;
            }
        }

        public void Vahenna()
        {
            if (_mitta > 0)
            {
                _mitta--;
            }
        }

        public int Mitta()
        {
            return _mitta;
        }

        public bool Taynna()
        {
            return _mitta == taysi;
        }
    }
}