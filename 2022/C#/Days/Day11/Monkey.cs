namespace nDay11
{
    class Monkey
    {
        Queue<long> items;
        Func<long, long> func;
        public int Divisor;
        public (int TrueMonkey, int FalseMonkey) Monkeys;
        public int count;
        List<long> initState;
        public Monkey(Func<long, long> f, int i, (int a, int b) pred, List<long> init)
        {
            count = 0;
            func = f;
            Divisor = i;
            items = new();
            Monkeys = pred;
            initState = init;
        }

        public void Enqueue(long i) => items.Enqueue(i);
        public long Dequeue() => items.Dequeue();

        public bool HasItems() => items.Count > 0;

        /*
        Round - Runs one round of monkey Business, and returns the
        item to be thrown to the required monkey
        */
        public long Round(long i)
        {
            count++;
            return func(i); // process
        }

        public int Test(long i)
        {
            if(i % Divisor == 0)
                return Monkeys.TrueMonkey;
            return Monkeys.FalseMonkey;
        }

        public void Reset()
        {
            count = 0;
            items = new();
            
            initState.ForEach(e => Enqueue(e));
        }
    }
}