﻿using System;
using System.Data.Common;

namespace dmstar
{
    public class SQLConnectionStringBuilder: DbConnectionStringBuilder
    {
        #region Properties
        public TimeSpan ConnectionTimeout
        {
            get => TimeSpan.FromMilliseconds(GetValue<double>(nameof(ConnectionTimeout)));
            set => SetValue(nameof(ConnectionTimeout), value.TotalMilliseconds);
        }

        public int FetchSize
        {
            get => GetValue<int>(nameof(FetchSize));
            set => SetValue(nameof(FetchSize), value);
        }

        public int ChunkSize
        {
            get => GetValue<int>(nameof(ChunkSize));
            set => SetValue(nameof(ChunkSize), value);
        }

        public string DataSource
        {
            get => GetValue<string>(nameof(DataSource));
            set => SetValue(nameof(DataSource), value);
        }
        #endregion

        #region Constructor
        public SQLConnectionStringBuilder()
        {
            FetchSize = 10;
            ChunkSize = 1000;
            ConnectionTimeout = TimeSpan.FromSeconds(15);
        }

        public SQLConnectionStringBuilder(string connectionString) : this()
        {
            ConnectionString = connectionString;
        }
        #endregion

        #region Private Methods
        private T GetValue<T>(string key)
        {
            if (TryGetValue(key, out var value))
                return (T)Convert.ChangeType(value, typeof(T));

            return default;
        }

        private void SetValue<T>(string key, T value)
        {
            if (!string.IsNullOrEmpty(value?.ToString()))
                this[key] = value;
            else
                Remove(key);
        }
        #endregion
    }
}
