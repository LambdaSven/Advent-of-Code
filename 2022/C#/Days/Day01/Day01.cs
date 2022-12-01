using nDay01;

namespace aoc
{
    class Day01 : Day
    {
        List<String> Input;
        List<Backpack> Backpacks;
        public Day01()
        {
            Input = Utils.ParseInputToStringList(1);

            Backpacks = InventoryBackpacks();
        }
        public override string Part1()
        {
            List<int> sums = Backpacks.Select(e => e.calorieSum()).ToList();

            return sums.Max().ToString();
        }

        public override string Part2()
        {
            List<int> sums = Backpacks.Select(e => e.calorieSum()).ToList();
            sums.Sort();
            sums.Reverse();
            int top3 = sums[0] + sums[1] + sums[2];

            return top3.ToString();
        }

        private List<Backpack> InventoryBackpacks()
        {
            List<Backpack> invs = new();

            Backpack i = new Backpack();
            invs.Add(i);

            foreach (String s in Input)
            {
                if (!String.IsNullOrEmpty(s))
                {
                    i.Pack(Int32.Parse(s));
                }
                else
                {
                    i = new Backpack();
                    invs.Add(i);
                }
            }
            return invs;
        }
    }
}