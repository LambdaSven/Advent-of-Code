using System.Text.RegularExpressions;
using nDay05;

namespace aoc
{
    class Day05 : Day
    {
        private List<String> input;
        private CargoBay cargoBay;

        public Day05()
        {
            input = Preprocess(Utils.ParseInputToStringList(5));
            cargoBay = new();
        }

        private void initBay()
        {
            cargoBay.ClearBays();
            foreach(string s in input)
            {
                if(s.Contains('[')) // is diagram
                {
                    List<String> matches = Regex.Matches(s, @"\[.\]").OfType<Match>().Select(e => e.Value).ToList();
                    for(int i = 0; i < matches.Count; i++)
                    {
                        if(matches[i] != "[ ]")
                          cargoBay.Load(i+1, new Crate(matches[i][1]));
                    }
                }
            }
            //need to flip stacks once loaded in, since reading is done top down
            cargoBay.FlipBays();
        }
        public override string Part1()
        {
            initBay();
            foreach(String s in input)
            {
                if(!s.Contains("move"))
                    continue;
                
                List<int> moves = Regex.Matches(s, @"\d+").OfType<Match>().Select(e => Int32.Parse(e.Value)).ToList();

                cargoBay.Move9000(moves[0], moves[1], moves[2]);
            }
            return String.Join("", cargoBay.Bays.Select(e => e.Peek()));
        }

        public override string Part2()
        {
            initBay();
            foreach(String s in input)
            {
                if(!s.Contains("move"))
                    continue;
                
                List<int> moves = Regex.Matches(s, @"\d+").OfType<Match>().Select(e => Int32.Parse(e.Value)).ToList();

                cargoBay.Move9001(moves[0], moves[1], moves[2]);
            }
            return String.Join("", cargoBay.Bays.Select(e => e.Peek()));
        }

        public List<String> Preprocess(List<String> s)
        {
            return s.Select(e => FillEmpty(e)).ToList();
        }

        public string FillEmpty(String s)
        {
            string output = Regex.Replace(s, "]    ", "] [ ]");

            while(output != s)
            {
                s = output;
                output = Regex.Replace(s, "]    ", "] [ ]");
            }
            return output;
        }
    }
}