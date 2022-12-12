namespace nDay12
{
    class Heightmap
    {
        Node Start;
        Node End;

        public Heightmap(Node s, Node e)
        {
            Start = s;
            End = e;
        }

        public int GetShortestDistanceToEnd()
        {
            Start.DistanceFromStart = 0;

            Stack<Node> stack = new();
            stack.Push(Start);
            while(stack.Count > 0)
            {
                Node current = stack.Pop(); // parent
                foreach(Node n in current.Neighbours) // n is the child to calculate
                {
                    int minParent = n.Parents.Select(e => e.DistanceFromStart).Min();
                    if(n.DistanceFromStart != minParent + 1 && n != Start)
                    {
                        n.DistanceFromStart = minParent + 1;
                        stack.Push(n);
                    }
                }
            }
            return End.DistanceFromStart;
        }

        public int GetShortestDistanceFromNode(Node s)
        {
            s.DistanceFromStart = 0;

            Stack<Node> stack = new();
            stack.Push(s);
            while(stack.Count > 0)
            {
                Node current = stack.Pop(); // parent
                foreach(Node n in current.Neighbours) // n is the child to calculate
                {
                    int minParent = n.Parents.Select(e => e.DistanceFromStart).Min();
                    if(n.DistanceFromStart != minParent + 1 && n != s)
                    {
                        n.DistanceFromStart = minParent + 1;
                        stack.Push(n);
                    }
                }
            }
            return End.DistanceFromStart;
        }
    }
}