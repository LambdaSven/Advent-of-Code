namespace nDay03
{
    class Rucksack
    {
        private string Section1;
        private string Section2;
        public string Contents;
        public Rucksack(string e)
        {
            Contents = e;
            Section1 = e[..(e.Length/2)];
            Section2 = e[(e.Length/2)..];
        }

        public char GetIncorrectItem()
        {
            //Question text says only 1 intersection, so this should be fine
            return Section1.Intersect(Section2).First();
        }
    }
}