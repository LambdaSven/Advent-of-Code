namespace nDay01
{
    public class Inventory
    {
        List<int> backpack;
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