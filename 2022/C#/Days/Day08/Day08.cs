using nDay08;

namespace aoc
{
    class Day08 : Day
    {
        Forest forest;
        List<String> input;
        public Day08()
        {
            forest = new();
            input = Utils.ParseInputToStringList(8);

            input.ForEach(e => forest.AddRowOfTrees(e));
        }
        public override string Part1()
        {
            return forest.CountVisibleFromOutside().ToString();
        }

        public override string Part2()
        {
            return forest.GetBestScenicScore().ToString();
        }
    }
}