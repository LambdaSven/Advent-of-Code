namespace nDay12
{
    class Node
    {
        public int Value;
        public int DistanceFromStart;
        public List<Node> Parents;
        public List<Node> Neighbours;
        public Node(int i)
        {
            DistanceFromStart = Int32.MaxValue;
            Parents = new();
            Neighbours = new();
            Value = i;
        }

        public void AddNeighbour(Node n) => Neighbours.Add(n);

        public void AddParent(Node n) => Parents.Add(n);
    }
}