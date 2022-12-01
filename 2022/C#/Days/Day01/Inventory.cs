namespace nDay01
{
    public class Inventory
    {
        List<int> backpack;
        public Inventory(List<String> l)
        {
            backpack = new();

            foreach(String s in l)
            {
                backpack.Add(Int32.Parse(s));
            }
        }

        public Inventory()
        {
            backpack = new();
        }

        public void Pack(int i)
        {
            backpack.Add(i);
        }

        public int calorieSum()
        {
            return backpack.Sum();
        }
    }
}