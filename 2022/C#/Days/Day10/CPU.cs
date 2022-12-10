namespace nDay10
{
    class CPU
    {
        int Register;
        public Queue<Instruction> queue;

        public CPU()
        {
            Register = 1;
            queue = new();
        }

        public void Cycle()
        {
            if(queue.Count == 0) // if queue empty, noop
                return;

            if(queue.Peek().cycleCount > 1) // if we have more cycles to go, return
            {
                queue.Peek().cycleCount--;
                return;
            }
            
            Instruction i = queue.Dequeue();
            Register = i.opcode switch 
            {
                Opcode.noop => Register,
                Opcode.addx => Register + i.value,
                _ => throw new Exception($"Could not parse opcode {i}")

            };
        }
        public void PassInstruction(string s)
        {
            string[] sp = s.Split(" ");
            if(sp.Count() == 2)
                queue.Enqueue(new(ParseOpcode(sp[0]), Int32.Parse(sp[1])));
            else
                queue.Enqueue(new(ParseOpcode(sp[0]), 0));
        }

        public Opcode ParseOpcode(string s) => s switch 
        {
            "addx" => Opcode.addx,
            "noop" => Opcode.noop,
            _ => throw new Exception($"Could not parse opcode {s}")
        };

        public int Read() => Register;

        public override string ToString()
        {
            string ret = "";

            ret += $"REGISTER = {Register}\n";

            ret += String.Join("\n", queue.Select(e => $"{e.opcode} {e.value}"));

            return ret;
        }

        public bool CanExecute() => queue.Count > 0;
    }
}