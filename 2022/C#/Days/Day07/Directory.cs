namespace nDay07
{
    class Directory
    {
        internal String Name;
        internal Directory? Parent;
        internal List<Directory> Directories;
        internal List<File> Files;

        internal long? Size;

        public Directory(Directory? parent, string name)
        {
            Size = null;
            Parent = parent;
            Name = name;
            Directories = new();
            Files = new();
        }

        public long GetSize()
        {
            // this is to memoize, reduce computation. Only calculate once.
            // since we cannot create new directories or files after filesystem created,
            // this should be fine.
            if(Size == null)
            {
                long s = Files.Select(e => e.Size).Sum() + Directories.Select(e => e.GetSize()).Sum();
                Size = s;
            }
            return Size ?? throw new Exception("Could not calculate size");
        }
    }
}