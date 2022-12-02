using nDay02;

namespace aoc
{
    class Day02 : Day
    {
        List<String> input;
        List<(Choice, Choice)> StrategyGuide;
        public Day02()
        {
            input = Utils.ParseInputToStringList(2);
            StrategyGuide = new();
        }
        public override string Part1()
        {
            StrategyGuide = Utils.ParseInputToStringList(2)
                                 .Select(e => (e[0], SimpleDecrypt(e[2])))
                                 .Select(a => (Convert(a.Item1), Convert(a.Item2)))
                                 .ToList();
            
            return StrategyGuide.Select(a => ScoreGame(a.Item1, a.Item2))
                                .Sum()
                                .ToString();
        }

        public override string Part2()
        {
            return Utils.ParseInputToStringList(2)
                        .Select(e => ComplexDecrypt(Convert(e[0]), e[2]))
                        .Select(e => ScoreGame(e.Item1, e.Item2))
                        .Sum()
                        .ToString();

        }

        private (Choice, Choice) ComplexDecrypt(Choice a, char b)
        {
            if(a == Choice.Rock)
            {
                if(b == 'X') // Lose to Rock
                    return (a, Choice.Scissors);
                if(b == 'Y') // Draw
                    return (a, a);
                if(b == 'Z') // Win to Rock
                    return (a, Choice.Paper);
            }
            else if (a == Choice.Paper)
            {
                if(b == 'X') // Lose to Paper
                    return (a, Choice.Rock);
                if(b == 'Y') // Draw
                    return (a, a);
                if(b == 'Z') // Win to Paper
                    return (a, Choice.Scissors);
            }
            else 
            {
                if(b == 'X') // Lose to Scissors
                    return (a, Choice.Paper);
                if(b == 'Y') // Draw
                    return (a, a);
                if(b == 'Z') // Win to Scissors
                    return (a, Choice.Rock);
            }
            throw new Exception("Could not Complex Decrypt");
        }
        private int ScoreGame(Choice a, Choice b)
        {
            int ChoiceScore;
            if(b == Choice.Rock)
            {
                if(a == Choice.Scissors)
                    ChoiceScore = 6;
                else if (a == b)
                    ChoiceScore = 3;
                else
                    ChoiceScore = 0;
            }
            else if (b == Choice.Paper)
            {
                if(a == Choice.Rock)
                    ChoiceScore = 6;
                else if (a == b)
                    ChoiceScore = 3;
                else
                    ChoiceScore = 0;
            }
            else
            {
                if(a == Choice.Paper)
                    ChoiceScore = 6;
                else if (a == b)
                    ChoiceScore = 3;
                else
                    ChoiceScore = 0;
            }
            return ChoiceScore + (int) b;
        }

        private char SimpleDecrypt(char e)
        {
            return (char) ((int)e - 23);
        }
        private Choice Convert(char a)
        {
            Choice? c = null;
            if (a == 'A')
                c = Choice.Rock;
            else if (a == 'B')
                c = Choice.Paper;
            else if (a == 'C')
                c = Choice.Scissors;
            
            return c ?? throw new Exception("Could not create Choice");
        }
    }
}