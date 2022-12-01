
namespace aoc
{
    public class AOC2022
    {
        
        public static void Main()
        {
            for (int i = 1; i < 26; i++)
            {
                if(DayExists(i))
                {
                    Day d = InstanciateDay(i);
                    String p1 = d.Part1();
                    String p2 = d.Part2();
                    Console.WriteLine($"Part {i}: {p1}, {p2}");
                }
            }
        }

        private static Day InstanciateDay(int i)
        {
            Type? t = Type.GetType($"aoc.Day{i.ToString("00")}");
            if(t != null)
            {
                Day? d = (Day?) Activator.CreateInstance(t);
                if(d == null)
                {
                    throw new Exception("Found Day, but could not instanciate");
                }
                else
                {
                    return d;
                }

            }
            throw new Exception("Could not instanciate Day");
        }

        public static bool DayExists(int i)
        {
            return Type.GetType($"aoc.Day{i.ToString("00")}") is not null;
        }
    }
}