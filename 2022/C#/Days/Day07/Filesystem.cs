namespace nDay07
{
    class Filesystem
    {
        internal Directory Root = new Directory(null, "/");
        internal Directory PresentWorkingDirectory;

        public Filesystem()
        {
            PresentWorkingDirectory = Root;
        }
        public void MakeDirectory(string name)  
        {
            if(!PresentWorkingDirectory.Directories.Select(e => e.Name).Contains(name))
                PresentWorkingDirectory.Directories.Add(new Directory(PresentWorkingDirectory, name));
        }

        public void MakeFile(string name, long size)
        {
            if(!PresentWorkingDirectory.Files.Select(e => e.Name).Contains(name))
                PresentWorkingDirectory.Files.Add(new File(name, size));
        }

        public void ChangeDirectory(string s)
        {
            if(s == "..")
              PresentWorkingDirectory = PresentWorkingDirectory.Parent ?? Root;
            else if (s == "/")
              PresentWorkingDirectory = Root;
            else
              PresentWorkingDirectory = PresentWorkingDirectory.Directories.Where(e => e.Name == s).First();
        }
    }
}