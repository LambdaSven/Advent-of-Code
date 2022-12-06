namespace nDay06
{
    class Datastream
    {
        List<Packet> Packets;
        List<Message> Messages;
        String RawData;

        public Datastream(string i)
        {
            RawData = i;
            Packets = ProcessPackets(i);
            Messages = ProcessMessages(i);
        }

        private List<Message> ProcessMessages(string s)
        {
            const int IndicatorLength = 14;
            List<Message> Output = new();
            
            String temp = "";
            for(int i = 0; i < s.Length-IndicatorLength; i++)
            {
                String window = s.Substring(i, IndicatorLength);
                if(window.Distinct().Count() == IndicatorLength) // 4 unique chars
                {
                    temp += window;
                    i += IndicatorLength-1; // -1 to account for the increment at end of loop
                    Output.Add(new Message(temp));
                    temp = "" + s[i];
                }
                else
                {
                    temp += s[i];
                }
            }
            return Output;
        }
        private List<Packet> ProcessPackets(string s)
        {
            const int IndicatorLength = 4;
            List<Packet> Output = new();
            
            String temp = "";
            for(int i = 0; i < s.Length-IndicatorLength; i++)
            {
                String window = s.Substring(i, IndicatorLength);
                if(window.Distinct().Count() == IndicatorLength) // 4 unique chars
                {
                    temp += window;
                    i += IndicatorLength-1;
                    Output.Add(new Packet(temp));
                    temp = "" + s[i];
                }
                else
                {
                    temp += s[i];
                }
            }
            return Output;
        }

        public Packet GetPacket(int i) => Packets[i];
        internal Message GetMessage(int i) => Messages[i];
    }
}