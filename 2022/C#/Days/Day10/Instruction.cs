namespace nDay10
{
    class Instruction
    {
        public Opcode opcode;
        public int value;
        public int cycleCount;
        public Instruction(Opcode o, int v)
        {
            opcode = o;
            value = v;

            cycleCount = o switch 
            {
                Opcode.noop => 1,
                Opcode.addx => 2,
                _ => throw new Exception("Could not parse opcode into cycle count")
            };
        }
    }
}