using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Text;

namespace dmstar
{
    public sealed class SQLException : DbException
    {
        public SQLException(string message) : base(message)
        {
        }
    }
}
