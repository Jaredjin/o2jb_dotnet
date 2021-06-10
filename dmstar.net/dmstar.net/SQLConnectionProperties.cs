using System.Collections.Generic;

namespace dmstar.net.Data
{
    public class SQLConnectionProperties : Dictionary<string, string>
    {
        public SQLConnectionProperties()
        {
        }

        public SQLConnectionProperties(int capacity) : base(capacity)
        {
        }

        public SQLConnectionProperties(IEqualityComparer<string>? comparer) : base(comparer)
        {
        }

        public SQLConnectionProperties(int capacity, IEqualityComparer<string>? comparer) : base(capacity, comparer)
        {
        }

        public SQLConnectionProperties(IDictionary<string, string> dictionary) : base(dictionary)
        {
        }

        public SQLConnectionProperties(IDictionary<string, string> dictionary, IEqualityComparer<string>? comparer) : base(dictionary, comparer)
        {
        }

        public SQLConnectionProperties(IEnumerable<KeyValuePair<string, string>> collection) : base(collection)
        {
        }

        public SQLConnectionProperties(IEnumerable<KeyValuePair<string, string>> collection, IEqualityComparer<string>? comparer) : base(collection, comparer)
        {
        }
    }
}
