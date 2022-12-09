
namespace aoc
{
    public class AOC2022
    {
        
        public static void Main(string[] args)
        {
            if(args.Count() == 1)
            {
                ArgMain(args);
                return;
            }
            for (int i = 1; i < 26; i++)
            {
                if(DayExists(i))
                {
                    Day d = InstanciateDay(i);
                    String p1 = d.Part1();
                    String p2 = d.Part2();
                    Console.WriteLine($"Day {i}:\n  Part 1: {p1},  Part 2: {p2}");
                }
            }
        }

        public static void ArgMain(string[] args)
        {
            try
            {
                int day = Int32.Parse(args[0]);
                if(DayExists(day))
                {
                    Day d = InstanciateDay(day);
                    String p1 = d.Part1();
                    String p2 = d.Part2();
                    Console.WriteLine($"Day {day}:\n  Part 1: {p1},  Part 2: {p2}");
                }
                else
                {
                    Console.WriteLine($"Could not Find Day {day}, have you made the Class?");
                    return;
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"Could not Solve Day {args[0]}\n\t Exception: {e}");
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