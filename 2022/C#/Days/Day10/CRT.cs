namespace nDay10
{
    class CRT
    {
        List<char> screen;

        public CRT()
        {
            screen = new();
        }

        public void Draw(int sprite)
        {
            char c = Math.Abs(sprite - (screen.Count % 40)) < 2 ? '#': ' ';
            screen.Add(c);
        }

        public String getDisplay() => String.Join("", screen);
    }
}