using nDay07;
using Directory = nDay07.Directory;

namespace aoc 
{
    class Day07 : Day
    {
        Filesystem filesystem;
        List<String> input;

        public Day07()
        {
            filesystem = new();
            input = Utils.ParseInputToStringList(7);
            initFileSystem();
        }
        public override string Part1()
        {
            long sum = filesystem.Root.GetSize() <= 100000 ? filesystem.Root.GetSize() : 0;

            Stack<Directory> stack = new();
            stack.Push(filesystem.Root);

            while(stack.Count > 0)
            {
                Directory pwd = stack.Pop();
                foreach(Directory d in pwd.Directories)
                {
                    if(d.GetSize() <= 100000)
                        sum += d.GetSize();
                    
                    stack.Push(d);
                }
            }

            return sum.ToString();
        }

        public override string Part2()
        {
            const long diskSize = 70000000;
            const long requiredSpace = 30000000;

            long usedSpace = filesystem.Root.GetSize();
            long unusedSpace = diskSize - usedSpace;

            long target = requiredSpace - unusedSpace;
            long distanceFromTarget = usedSpace - target; // root is the starting case

            Directory smallestValidDir = filesystem.Root;

            Stack<Directory> stack = new();
            stack.Push(filesystem.Root);

            while(stack.Count > 0)
            {
                Directory pwd = stack.Pop();
                foreach(Directory d in pwd.Directories)
                {
                    if(d.GetSize() >= target)
                    {
                        long x = d.GetSize() - target;
                        distanceFromTarget = x;
                        smallestValidDir = d;
                    }
                    stack.Push(d);
                }
            }
            return smallestValidDir.GetSize().ToString();
        }

        public void initFileSystem()
        {
            foreach(string s in input)
            {
                string[] temp = s.Split(' ');
                if(temp[0] == "$")
                {
                    if(temp[1] == "cd")
                        filesystem.ChangeDirectory(temp[2]);
                }
                else
                {
                    if(temp[0] == "dir")
                        filesystem.MakeDirectory(temp[1]);
                    else
                        filesystem.MakeFile(temp[1], long.Parse(temp[0]));
                }
            }
        }
    }
}