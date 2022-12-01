namespace nDay01
{
    public class Backpack
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