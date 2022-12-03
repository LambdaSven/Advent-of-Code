using nDay03;

namespace aoc
{
    class Day03 : Day
    {
        List<Rucksack> Rucksacks;

        public Day03()
        {
            Rucksacks = Utils.ParseInputToStringList(3)
                             .Select(e => new Rucksack(e))
                             .ToList();
        }
        public override string Part1()
        {
            return Rucksacks.Select(e => e.GetIncorrectItem())
                             .Select(e => GetPriority(e))
                             .Sum()
                             .ToString();
        }

        public override string Part2()
        {
            return Batch(3, Rucksacks).Select(e => e[0].Contents.Intersect(e[1].Contents).Intersect(e[2].Contents).First())
                                      .Select(e => GetPriority(e))
                                      .Sum()
                                      .ToString();
                              
        }

        public List<List<Rucksack>> Batch(int batchSize, List<Rucksack> l)
        {
            int inx = 0;
            return l.Select((e, i) => (e, inx++))
                    .GroupBy(e => e.Item2 / batchSize)
                    .Select(e => e.Select(f => f.Item1).ToList())
                    .ToList();

        }
        public int GetPriority(char c)
        {
            return (int) c switch 
            {
                (> 96) and (< 123) => c - 96,
                (> 64) and (< 91) => c - 64 + 26,
                _ => throw new Exception($"Could not calculate Priority of {(char) c}")
            };
        }
    }
}