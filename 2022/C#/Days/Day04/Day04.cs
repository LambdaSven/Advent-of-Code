using System.Text.RegularExpressions;
using nDay04;

namespace aoc
{
    class Day04 : Day
    {
        List<String> Input;
        List<(Assignment, Assignment)> Pairs;
        public override string Part1()
        {
            return Pairs.Where(e => !(e.Item1.Sections.Except(e.Item2.Sections).Any())
                                 || !(e.Item2.Sections.Except(e.Item1.Sections).Any()))
                        .Count()
                        .ToString();
        }

        public override string Part2()
        {
            return Pairs.Where(e => (e.Item1.Sections.Intersect(e.Item2.Sections).Any()))
                        .Count()
                        .ToString();
        }

        private (Assignment, Assignment) GetPair(string s)
        {
            List<int> m = Regex.Matches(s, @"\d+")
                                    .OfType<Match>()
                                    .Select(e => Int32.Parse(e.Value))
                                    .ToList();
            return (new Assignment(m[0], m[1]), new Assignment(m[2], m[3]));
        }

        public Day04()
        {
            Input = Utils.ParseInputToStringList(4);
            Pairs = Input.Select(e => GetPair(e)).ToList();
        }
    }
}
