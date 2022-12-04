namespace nDay04
{
    class Assignment
    {
        public List<int> Sections;
        int Start, End;

        public Assignment(int start, int end)
        {
            Start = start;
            End = end;
            Sections = Enumerable.Range(start, end-start+1).ToList();
        }
    }
}