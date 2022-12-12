using System.Text.RegularExpressions;
using nDay11;

namespace aoc
{
    class Day11 : Day
    {
        List<Monkey> Monkeys;
        List<string> RawInput;
        int totalDivisor = 1;
        public Day11()
        {
            Monkeys = new();
            RawInput = Utils.ParseInputToStringList(11);
            for(int i = 0;;i += 7)
            {
                List<string> s = RawInput.Take(new Range(i, i+7)).ToList();
                if(s.Count < 6)
                    break;
                
                Monkeys.Add(MakeMonkey(s));
            }
        }

        public Monkey MakeMonkey(List<string> spec)
        {
            List<long> items = new();
            Func<long, long> func = new(e => e);
            int divisor = 0;
            int T = 0;
            int F = 0;

            foreach(string s in spec)
            {
                if(s.Contains("Starting items:"))
                {
                    items = Regex.Matches(s, @"\d+")
                                 .OfType<Match>()
                                 .Select(e => Int64.Parse(e.Value))
                                 .ToList();
                }
                if(s.Contains("Operation"))
                {
                    string t = s.Substring(s.IndexOf('=') + "= old ".Count());
                    switch (t[0])
                    {
                        case '+':
                            if(t.Contains("old"))
                                func = e => e + e;
                            else
                                func = e => e + Int32.Parse(t[1..]);
                            break;
                        case '*':
                            if(t.Contains("old"))
                                func = e => e * e;
                            else
                                func = e => e * Int32.Parse(t[1..]);
                            break;
                    }
                }
                if(s.Contains("Test"))
                {
                    divisor = Int32.Parse(Regex.Match(s, @"\d+").Value);
                }
                if(s.Contains("true"))
                {
                    T = Int32.Parse(Regex.Match(s, @"\d+").Value);
                }
                if(s.Contains("false"))
                {
                    F = Int32.Parse(Regex.Match(s, @"\d+").Value);
                }
            }
            Monkey m = new Monkey(func, divisor, (T, F), items);
            totalDivisor *= divisor;
            return m;
        } 
        public override string Part1()
        {   
            Monkeys.ForEach(e => e.Reset());

            for(int inx = 0; inx < 20; inx++)
            {
                foreach(Monkey m in Monkeys)
                {
                    while(m.HasItems())
                    {
                        long i = m.Dequeue();
                        i = m.Round(i);
                        i /= 3;
                        Monkey to = Monkeys[m.Test(i)];
                        to.Enqueue(i);
                    }
                }
            }

            List<int> top2 = Monkeys.Select(e => e.count).ToList();
            top2.Sort();
            top2.Reverse(); // descending
            top2 = top2.Take(2).ToList();
            return $"{(top2[0] * top2[1])}";
        }

        public override string Part2()
        {
            Console.WriteLine(totalDivisor);
            Monkeys.ForEach(e => e.Reset());
            int[] samples = {1, 20, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
            for(int inx = 0; inx < 10000; inx++)
            {
                foreach(Monkey m in Monkeys)
                {
                    while(m.HasItems())
                    {
                        long i = m.Dequeue();
                        i = m.Round(i);
                        Monkey to = Monkeys[m.Test(i)];
                        to.Enqueue(i % totalDivisor);
                    }
                }
            }

            List<int> top2 = Monkeys.Select(e => e.count).ToList();
            top2.Sort();
            top2.Reverse(); // descending
            top2 = top2.Take(2).ToList();
            return $"{((long)top2[0] * top2[1])}";
        }
    }
}