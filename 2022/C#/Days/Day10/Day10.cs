using System.Text.RegularExpressions;
using nDay10;

namespace aoc
{
    class Day10 : Day
    {
        CPU cpu;
        List<string> input;

        public Day10()
        {
            cpu = new();
            input = Utils.ParseInputToStringList(10);
        }
        public override string Part1()
        {
            input.ForEach(e => cpu.PassInstruction(e));

            int sum = 0;
            int[] samples = {20, 60, 100, 140, 180, 220};

            for(int i = 1; i <= 220; i++)
            {
                if(samples.Contains(i))
                {
                    sum += i * cpu.Read();
                }
                cpu.Cycle();

            }
            return sum.ToString();
        }

        public override string Part2()
        {
            cpu = new();
            CRT crt = new();

            input.ForEach(e => cpu.PassInstruction(e));

            while(cpu.CanExecute())
            {
                crt.Draw(cpu.Read());
                cpu.Cycle();
            }

            return $"\n{Regex.Replace(crt.getDisplay(), ".{40}", "$0\n")}";    
        }
    }
}