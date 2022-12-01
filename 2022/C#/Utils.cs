using System.Collections;

namespace aoc
{
    public static class Utils
    {
        public static List<String> ParseInputToStringList (int d) 
        {
            List<String> l = new List<string>();

            String path = $"Inputs/Day{d.ToString("00")}.txt";
            if(!File.Exists(path))
                throw new Exception("Could not find file input");

            
            return File.ReadAllLines(path).ToList();
        }
    }
}
