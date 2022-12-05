namespace nDay05
{
    class CargoBay
    {
        public List<Stack<Crate>> Bays;

        public CargoBay()
        {
            Bays = new();
        }

        public void Load(int Bay, Crate crate)
        {
            if(Bay > Bays.Count)
            {
                initBays(Bay - Bays.Count);
            }
            Bays[Bay-1].Push(crate);
        }

        private void initBays(int v)
        {
            for(int i = 0; i < v; i++)
            {
                Bays.Add(new Stack<Crate>());
            }
        }

        public void Move9000(int Number, int Source, int Destination)
        {
            Source--;
            Destination--; // decrement to convert to the number they need to be for array access

            foreach(int i in Enumerable.Range(0, Number))
            {
                if(Bays[Source].Count == 0)
                    break;
                Bays[Destination].Push(Bays[Source].Pop());
                //pop off source, push onto destination
            }
        }
        public void Move9001(int Number, int Source, int Destination)
        {
            Source--;
            Destination--; // decrement to convert to the number they need to be for array access

            Stack<Crate> temp = new Stack<Crate>(); // to allow moving in order, we use a third stack

            foreach(int i in Enumerable.Range(0, Number))
            {
                if(Bays[Source].Count == 0)
                    break;
                temp.Push(Bays[Source].Pop());
            }

            while(temp.Count > 0)
            {
                Bays[Destination].Push(temp.Pop());
            }
        }


        public override string ToString()
        {
            string output = "";
            int index = 1;
            foreach(Stack<Crate> s in Bays)
            {
                List<Crate> l = s.ToList();
                output += $"{index++} {String.Join(" ", l)}\n";
            }
            return output;
        }

        internal void FlipBays()
        {
            for(int i = 0; i < Bays.Count; i++)
            {
                Bays[i] = new Stack<Crate>(Bays[i]);
            }
        }

        internal void ClearBays()
        {
            Bays.ForEach(e => e = new());
        }
    }
}