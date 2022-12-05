namespace nDay05
{
    class Crate
    {
        char Contents;
        public Crate(char c)
        {
            Contents = c;
        }

        public override string ToString()
        {
            return "" + Contents;
        }
    }
}