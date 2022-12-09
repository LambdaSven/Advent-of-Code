using nDay09;

namespace aoc
{
    class Day09 : Day
    {
        List<String> raw;
        List<(Direction, int)> input;


        public Day09()
        {
            raw = Utils.ParseInputToStringList(9);
            input = raw.Select(e => e.Split(' '))
                        .Select(e => (CharToDirection(e[0][0]), Int32.Parse(e[1])))
                        .ToList();

        }

        public Direction CharToDirection(char c) => c switch 
        {
            'R' => Direction.Right,
            'L' => Direction.Left,
            'U' => Direction.Up,
            'D' => Direction.Down,
            _ => throw new Exception($"Could not parse char {c} into direction")
        };
        public override string Part1()
        {
            Rope rope = new(2);

            input.ForEach(e => rope.Move(e.Item1, e.Item2));

            return rope.GetTailSeenCount().ToString();
        }

        public override string Part2()
        {
            Rope rope = new(10);

            input.ForEach(e => rope.Move(e.Item1, e.Item2));

            return rope.GetTailSeenCount().ToString();
        }
    }
}