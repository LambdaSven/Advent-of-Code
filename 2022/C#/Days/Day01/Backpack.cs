namespace nDay01
{
    class Backpack
    {
        List<int> contents;
        public Backpack()
        {
            contents = new();
        }

        public void Pack(int i)
        {
            contents.Add(i);
        }

        public int calorieSum()
        {
            return contents.Sum();
        }
    }
}