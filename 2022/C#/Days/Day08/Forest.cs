namespace nDay08
{
    class Forest
    {
        List<List<Tree>> Grid;

        public Forest()
        {
            Grid = new();
        }

        public void AddRowOfTrees(string s)
        {
            List<Tree> row = s.ToCharArray().Select(e => new Tree(e - '0')).ToList();
            Grid.Add(row);
        }

        public int GetBestScenicScore()
        {
            int ret = 0;

            for (int i = 0; i < Grid.Count; i++)
            {
                for (int j = 0; j < Grid[i].Count; j++)
                {
                    int treescore = CalculateTreeScore(i, j);
                    if(treescore > ret)
                        ret = treescore;
                }
            }
            return ret;
        }
        public int CountVisibleFromOutside()
        {
            int ret = 0;

            for (int i = 0; i < Grid.Count; i++)
            {
                for (int j = 0; j < Grid[i].Count; j++)
                {
                    if (TreeIsVisible(i, j))
                        ret++;
                }
            }
            return ret;
        }

        private bool TreeIsVisible(int i, int j)
        {
            //need to cast rays in all 4 directions and count collisions
            bool[] collisions = {false, false, false, false};
            //up + down
            for (int x = 1; i + x < Grid.Count || i - x >= 0; x++)
            {
                if (i + x < Grid.Count)
                {
                    if (Grid[i + x][j].Height >= Grid[i][j].Height)
                        collisions[0] = true;
                }
                if (i - x >= 0)
                {
                    if (Grid[i - x][j].Height >= Grid[i][j].Height)
                        collisions[1] = true;
                }
            }

            //left + right
            for (int x = 1; j + x < Grid[i].Count || j - x >= 0; x++)
            {
                if (j + x < Grid[i].Count)
                {
                    if (Grid[i][j + x].Height >= Grid[i][j].Height)
                        collisions[2] = true;
                }
                if (j - x >= 0)
                {
                    if (Grid[i][j - x].Height >= Grid[i][j].Height)
                        collisions[3] = true;
                }
            }

            //any direction does not collide
            return collisions.Any(e => !e);
        }

        private int CalculateTreeScore(int i, int j)
        {
            //need to cast rays in all 4 directions and count collisions
            bool[] collisions = {false, false, false, false};
            int[] scores = {0, 0, 0, 0};
            //up + down
            for (int x = 1; i + x < Grid.Count || i - x >= 0; x++)
            {
                if (i + x < Grid.Count)
                {
                    if (Grid[i + x][j].Height >= Grid[i][j].Height)
                    {
                        collisions[0] = true;
                    }
                    
                    if (!collisions[0])
                    {
                        scores[0]++;
                    }
                }
                if (i - x >= 0)
                {
                    if (Grid[i - x][j].Height >= Grid[i][j].Height)
                    {
                        collisions[1] = true;
                    }
                    
                    if (!collisions[1])
                    {
                        scores[1]++;
                    }
                }
            }

            //left + right
            for (int x = 1; j + x < Grid[i].Count || j - x >= 0; x++)
            {
                if (j + x < Grid[i].Count)
                {
                    if (Grid[i][j + x].Height >= Grid[i][j].Height)
                    {
                        collisions[2] = true;
                    }
                    
                    if (!collisions[2])
                    {
                        scores[2]++;
                    }
                }
                if (j - x >= 0)
                {
                    if (Grid[i][j - x].Height >= Grid[i][j].Height)
                    {
                        collisions[3] = true;
                    }
                    
                    if (!collisions[3])
                    {
                        scores[3]++;
                    }
                }
            }

            //if we did  have a collision, we need to increment an extra time.
            for(int inx = 0; inx < 4; inx++)
            {
                if(collisions[inx])
                    scores[inx]++;
            }
            
            return scores.Aggregate(1, (a, b) => a * b);
        }

        public override string ToString()
        {
            return String.Join('\n', Grid.Select(e => String.Join("", e.Select(e => e.Height))));
        }
    }
}