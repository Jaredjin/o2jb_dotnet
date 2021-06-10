// <auto-generated>
//     Generated by the protocol buffer compiler.  DO NOT EDIT!
//     source: reader.proto
// </auto-generated>
#pragma warning disable 1591, 0612, 3021
#region Designer generated code

using pb = global::Google.Protobuf;
using pbc = global::Google.Protobuf.Collections;
using pbr = global::Google.Protobuf.Reflection;
using scg = global::System.Collections.Generic;
namespace dmstar.net.Proto {

  /// <summary>Holder for reflection information generated from reader.proto</summary>
  public static partial class ReaderReflection {

    #region Descriptor
    /// <summary>File descriptor for reader.proto</summary>
    public static pbr::FileDescriptor Descriptor {
      get { return descriptor; }
    }
    private static pbr::FileDescriptor descriptor;

    static ReaderReflection() {
      byte[] descriptorData = global::System.Convert.FromBase64String(
          string.Concat(
            "CgxyZWFkZXIucHJvdG8SDHByb3RvLnJlYWRlchoMY29tbW9uLnByb3RvIj4K",
            "FFJlYWRSZXN1bHRTZXRSZXF1ZXN0EhMKC3Jlc3VsdFNldElkGAEgASgJEhEK",
            "CWNodW5rU2l6ZRgCIAEoBSJOChVSZWFkUmVzdWx0U2V0UmVzcG9uc2USIAoE",
            "cm93cxgBIAMoCzISLnByb3RvLkpkYmNEYXRhUm93EhMKC2lzQ29tcGxldGVk",
            "GAIgASgIIiwKFUNsb3NlUmVzdWx0U2V0UmVxdWVzdBITCgtyZXN1bHRTZXRJ",
            "ZBgBIAEoCUITqgIQZG1zdGFyLm5ldC5Qcm90b2IGcHJvdG8z"));
      descriptor = pbr::FileDescriptor.FromGeneratedCode(descriptorData,
          new pbr::FileDescriptor[] { global::dmstar.net.Proto.CommonReflection.Descriptor, },
          new pbr::GeneratedClrTypeInfo(null, null, new pbr::GeneratedClrTypeInfo[] {
            new pbr::GeneratedClrTypeInfo(typeof(global::dmstar.net.Proto.ReadResultSetRequest), global::dmstar.net.Proto.ReadResultSetRequest.Parser, new[]{ "ResultSetId", "ChunkSize" }, null, null, null, null),
            new pbr::GeneratedClrTypeInfo(typeof(global::dmstar.net.Proto.ReadResultSetResponse), global::dmstar.net.Proto.ReadResultSetResponse.Parser, new[]{ "Rows", "IsCompleted" }, null, null, null, null),
            new pbr::GeneratedClrTypeInfo(typeof(global::dmstar.net.Proto.CloseResultSetRequest), global::dmstar.net.Proto.CloseResultSetRequest.Parser, new[]{ "ResultSetId" }, null, null, null, null)
          }));
    }
    #endregion

  }
  #region Messages
  public sealed partial class ReadResultSetRequest : pb::IMessage<ReadResultSetRequest> {
    private static readonly pb::MessageParser<ReadResultSetRequest> _parser = new pb::MessageParser<ReadResultSetRequest>(() => new ReadResultSetRequest());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<ReadResultSetRequest> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::dmstar.net.Proto.ReaderReflection.Descriptor.MessageTypes[0]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReadResultSetRequest() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReadResultSetRequest(ReadResultSetRequest other) : this() {
      resultSetId_ = other.resultSetId_;
      chunkSize_ = other.chunkSize_;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReadResultSetRequest Clone() {
      return new ReadResultSetRequest(this);
    }

    /// <summary>Field number for the "resultSetId" field.</summary>
    public const int ResultSetIdFieldNumber = 1;
    private string resultSetId_ = "";
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public string ResultSetId {
      get { return resultSetId_; }
      set {
        resultSetId_ = pb::ProtoPreconditions.CheckNotNull(value, "value");
      }
    }

    /// <summary>Field number for the "chunkSize" field.</summary>
    public const int ChunkSizeFieldNumber = 2;
    private int chunkSize_;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int ChunkSize {
      get { return chunkSize_; }
      set {
        chunkSize_ = value;
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as ReadResultSetRequest);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(ReadResultSetRequest other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (ResultSetId != other.ResultSetId) return false;
      if (ChunkSize != other.ChunkSize) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      if (ResultSetId.Length != 0) hash ^= ResultSetId.GetHashCode();
      if (ChunkSize != 0) hash ^= ChunkSize.GetHashCode();
      if (_unknownFields != null) {
        hash ^= _unknownFields.GetHashCode();
      }
      return hash;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override string ToString() {
      return pb::JsonFormatter.ToDiagnosticString(this);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void WriteTo(pb::CodedOutputStream output) {
      if (ResultSetId.Length != 0) {
        output.WriteRawTag(10);
        output.WriteString(ResultSetId);
      }
      if (ChunkSize != 0) {
        output.WriteRawTag(16);
        output.WriteInt32(ChunkSize);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      if (ResultSetId.Length != 0) {
        size += 1 + pb::CodedOutputStream.ComputeStringSize(ResultSetId);
      }
      if (ChunkSize != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(ChunkSize);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(ReadResultSetRequest other) {
      if (other == null) {
        return;
      }
      if (other.ResultSetId.Length != 0) {
        ResultSetId = other.ResultSetId;
      }
      if (other.ChunkSize != 0) {
        ChunkSize = other.ChunkSize;
      }
      _unknownFields = pb::UnknownFieldSet.MergeFrom(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(pb::CodedInputStream input) {
      uint tag;
      while ((tag = input.ReadTag()) != 0) {
        switch(tag) {
          default:
            _unknownFields = pb::UnknownFieldSet.MergeFieldFrom(_unknownFields, input);
            break;
          case 10: {
            ResultSetId = input.ReadString();
            break;
          }
          case 16: {
            ChunkSize = input.ReadInt32();
            break;
          }
        }
      }
    }

  }

  public sealed partial class ReadResultSetResponse : pb::IMessage<ReadResultSetResponse> {
    private static readonly pb::MessageParser<ReadResultSetResponse> _parser = new pb::MessageParser<ReadResultSetResponse>(() => new ReadResultSetResponse());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<ReadResultSetResponse> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::dmstar.net.Proto.ReaderReflection.Descriptor.MessageTypes[1]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReadResultSetResponse() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReadResultSetResponse(ReadResultSetResponse other) : this() {
      rows_ = other.rows_.Clone();
      isCompleted_ = other.isCompleted_;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReadResultSetResponse Clone() {
      return new ReadResultSetResponse(this);
    }

    /// <summary>Field number for the "rows" field.</summary>
    public const int RowsFieldNumber = 1;
    private static readonly pb::FieldCodec<global::dmstar.net.Proto.JdbcDataRow> _repeated_rows_codec
        = pb::FieldCodec.ForMessage(10, global::dmstar.net.Proto.JdbcDataRow.Parser);
    private readonly pbc::RepeatedField<global::dmstar.net.Proto.JdbcDataRow> rows_ = new pbc::RepeatedField<global::dmstar.net.Proto.JdbcDataRow>();
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public pbc::RepeatedField<global::dmstar.net.Proto.JdbcDataRow> Rows {
      get { return rows_; }
    }

    /// <summary>Field number for the "isCompleted" field.</summary>
    public const int IsCompletedFieldNumber = 2;
    private bool isCompleted_;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool IsCompleted {
      get { return isCompleted_; }
      set {
        isCompleted_ = value;
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as ReadResultSetResponse);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(ReadResultSetResponse other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if(!rows_.Equals(other.rows_)) return false;
      if (IsCompleted != other.IsCompleted) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      hash ^= rows_.GetHashCode();
      if (IsCompleted != false) hash ^= IsCompleted.GetHashCode();
      if (_unknownFields != null) {
        hash ^= _unknownFields.GetHashCode();
      }
      return hash;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override string ToString() {
      return pb::JsonFormatter.ToDiagnosticString(this);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void WriteTo(pb::CodedOutputStream output) {
      rows_.WriteTo(output, _repeated_rows_codec);
      if (IsCompleted != false) {
        output.WriteRawTag(16);
        output.WriteBool(IsCompleted);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      size += rows_.CalculateSize(_repeated_rows_codec);
      if (IsCompleted != false) {
        size += 1 + 1;
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(ReadResultSetResponse other) {
      if (other == null) {
        return;
      }
      rows_.Add(other.rows_);
      if (other.IsCompleted != false) {
        IsCompleted = other.IsCompleted;
      }
      _unknownFields = pb::UnknownFieldSet.MergeFrom(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(pb::CodedInputStream input) {
      uint tag;
      while ((tag = input.ReadTag()) != 0) {
        switch(tag) {
          default:
            _unknownFields = pb::UnknownFieldSet.MergeFieldFrom(_unknownFields, input);
            break;
          case 10: {
            rows_.AddEntriesFrom(input, _repeated_rows_codec);
            break;
          }
          case 16: {
            IsCompleted = input.ReadBool();
            break;
          }
        }
      }
    }

  }

  public sealed partial class CloseResultSetRequest : pb::IMessage<CloseResultSetRequest> {
    private static readonly pb::MessageParser<CloseResultSetRequest> _parser = new pb::MessageParser<CloseResultSetRequest>(() => new CloseResultSetRequest());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<CloseResultSetRequest> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::dmstar.net.Proto.ReaderReflection.Descriptor.MessageTypes[2]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public CloseResultSetRequest() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public CloseResultSetRequest(CloseResultSetRequest other) : this() {
      resultSetId_ = other.resultSetId_;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public CloseResultSetRequest Clone() {
      return new CloseResultSetRequest(this);
    }

    /// <summary>Field number for the "resultSetId" field.</summary>
    public const int ResultSetIdFieldNumber = 1;
    private string resultSetId_ = "";
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public string ResultSetId {
      get { return resultSetId_; }
      set {
        resultSetId_ = pb::ProtoPreconditions.CheckNotNull(value, "value");
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as CloseResultSetRequest);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(CloseResultSetRequest other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (ResultSetId != other.ResultSetId) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      if (ResultSetId.Length != 0) hash ^= ResultSetId.GetHashCode();
      if (_unknownFields != null) {
        hash ^= _unknownFields.GetHashCode();
      }
      return hash;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override string ToString() {
      return pb::JsonFormatter.ToDiagnosticString(this);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void WriteTo(pb::CodedOutputStream output) {
      if (ResultSetId.Length != 0) {
        output.WriteRawTag(10);
        output.WriteString(ResultSetId);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      if (ResultSetId.Length != 0) {
        size += 1 + pb::CodedOutputStream.ComputeStringSize(ResultSetId);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(CloseResultSetRequest other) {
      if (other == null) {
        return;
      }
      if (other.ResultSetId.Length != 0) {
        ResultSetId = other.ResultSetId;
      }
      _unknownFields = pb::UnknownFieldSet.MergeFrom(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(pb::CodedInputStream input) {
      uint tag;
      while ((tag = input.ReadTag()) != 0) {
        switch(tag) {
          default:
            _unknownFields = pb::UnknownFieldSet.MergeFieldFrom(_unknownFields, input);
            break;
          case 10: {
            ResultSetId = input.ReadString();
            break;
          }
        }
      }
    }

  }

  #endregion

}

#endregion Designer generated code