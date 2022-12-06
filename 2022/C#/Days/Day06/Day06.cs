using nDay06;

namespace aoc
{
    class Day06 : Day
    {
        string input;
        Datastream datastream;
        public Day06()
        {
            input = Utils.ParseInputToStringList(6).First();
            datastream = new(input);
        }
        public override string Part1()
        {
            return datastream.GetPacket(0).Data.Length.ToString();
        }

        public override string Part2()
        {
            return datastream.GetMessage(0).Data.Length.ToString();
        }
    }
}