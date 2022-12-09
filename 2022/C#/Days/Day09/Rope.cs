namespace nDay09
{
    class Rope
    {
        List<Point> Knots;
        HashSet<Point> TailSeen;

        public Rope(int k)
        {
            Knots = new();
            TailSeen = new();

            for(int i = 0; i < k; i++)
            {
                Knots.Add(new Point(0, 0));
            }

            TailSeen.Add(new Point(0,0));
        }

        public void Move(Direction d, int c)
        {
            for(int i = 0; i < c; i++)
            {
                Step(d);
            }
        }

        public void Step(Direction d)
        {
            Point Head = Knots[0];
            Knots[0] = d switch
            {
                Direction.Up => new Point(Head.x, Head.y+1),
                Direction.Down => new Point(Head.x, Head.y-1),
                Direction.Left => new Point(Head.x-1, Head.y),
                Direction.Right => new Point(Head.x+1, Head.y),
                _ => throw new Exception("Direction was not one of 4 allowed directions.")
            };

            FixTail(1);
        }

        public void FixTail(int node)
        {
            Point Head = Knots[node-1];
            Point Tail = Knots[node];

            int dx = Head.x - Tail.x;
            int dy = Head.y - Tail.y;

            int mx = Math.Abs(dx);
            int my = Math.Abs(dy);

            //if no value is 2 away, we are still valid
            if(mx < 2 && my < 2)
                return;

            int ny = Tail.y;
            int nx = Tail.x; 

            if(dy > 0) // tail below head
                ny++;

            if(dy < 0) // tail above head
                ny--;
            
            if(dx > 0) // tail to left of head
                nx++;
            
            if(dx < 0) // tail right of head
                nx--;

            Point p = new(nx, ny);

            Knots[node] = p;

            if(node == Knots.Count-1)
                TailSeen.Add(p);
            else
                FixTail(node+1); // recurse down the rope
        }

        public int GetTailSeenCount() => TailSeen.Count();
    }
}