using nDay12;

namespace aoc
{
    class Day12 : Day
    {
        List<string> RawInput;
        List<List<int>> ConvertedInput;
        List<List<Node>> RawNodeMap;
        Heightmap heightmap;

        public Day12()
        {
            RawInput = Utils.ParseInputToStringList(12);
            ConvertedInput = RawInput.Select(e => e.ToCharArray()
                                                   .Select(e => CharToHeight(e)).ToList())
                                     .ToList();
            RawNodeMap = new();
            heightmap = InputToHeightmap(ConvertedInput);

        }
        public override string Part1()
        {
            ClearDistances();
            string i = heightmap.GetShortestDistanceToEnd().ToString();

            return i;
        }

        public override string Part2()
        {
            List<int> AllDistances = new();
            foreach(List<Node> ln in RawNodeMap)
            {
                foreach(Node n in ln)
                {
                    if(n.Value == 0)
                        AllDistances.Add(heightmap.GetShortestDistanceFromNode(n));
                }
            }
            return AllDistances.Min().ToString();
        }

        public void ClearDistances()
        {
            foreach(List<Node> ln in RawNodeMap)
                ln.ForEach(e => e.DistanceFromStart = Int32.MaxValue);
        }
        public Heightmap InputToHeightmap(List<List<int>> c)
        {
            List<List<Node>> created = new();
            Node? s = null;
            Node? e = null;

            //step one, init node list
            for(int i = 0; i < c.Count; i++)
            {
                created.Add(new());
                for(int j = 0; j < c[i].Count; j++)
                {
                    created[i].Add(new Node(c[i][j]));
                }
            }

            //we need to find the start and end, and fix their values
            foreach(List<Node> nl in created)
            {
                foreach(Node n in nl)
                {
                     //find and fix start and end values
                    if(n.Value == 255)
                    {
                        s = n;
                        n.Value = 0;
                    }

                    if(n.Value == 256)
                    {
                        e = n;
                        n.Value = 'z' - 'a';
                    }
                }
            }

            //now we need to go through every node, creating links
            //to nodes where v1 - v2 > -1;
            for(int i = 0; i < created.Count; i++)
            {
                for(int j = 0; j < created[i].Count; j++)
                {
                    //down
                    if(i+1 < created.Count && created[i+1][j].Value < created[i][j].Value+2)
                    {
                        created[i][j].AddNeighbour(created[i+1][j]);
                        created[i+1][j].AddParent(created[i][j]);
                    }
                    //up
                    if(i-1 >= 0 && created[i-1][j].Value < created[i][j].Value+2)
                    {
                        created[i][j].AddNeighbour(created[i-1][j]);
                        created[i-1][j].AddParent(created[i][j]);
                    }
                    //right
                    if(j+1 < created[i].Count && created[i][j+1].Value < created[i][j].Value+2)
                    {
                        created[i][j].AddNeighbour(created[i][j+1]);
                        created[i][j+1].AddParent(created[i][j]);
                    }
                    //left
                    if(j-1 >= 0 && created[i][j-1].Value < created[i][j].Value+2)
                    {
                        created[i][j].AddNeighbour(created[i][j-1]);
                        created[i][j-1].AddParent(created[i][j]);
                    }
                    
                }
            }
            RawNodeMap = created;
            return new(s ?? throw new Exception("Start Node not found"),
                       e ?? throw new Exception("End Node not found"));
        }

        public int CharToHeight(char c)
        {
            if(c == 'S')
                return 255;
            if(c == 'E')
                return 256;
            
            if(c <= 'z' && c >= 'a')
                return c - 'a';
            
            if(c <= 'Z' && c >= 'A')
                return c - 'A' + ('a' - 'z'); // offset it to be after 'z'
            
            throw new Exception("Could not get height");
        }
    }
}