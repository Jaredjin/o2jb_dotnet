// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: driver.proto

package proto.driver;

public final class Driver {
  private Driver() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface LoadDriverRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.driver.LoadDriverRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string className = 1;</code>
     */
    boolean hasClassName();
    /**
     * <code>optional string className = 1;</code>
     */
    java.lang.String getClassName();
    /**
     * <code>optional string className = 1;</code>
     */
    com.google.protobuf.ByteString
        getClassNameBytes();
  }
  /**
   * Protobuf type {@code proto.driver.LoadDriverRequest}
   */
  public static final class LoadDriverRequest extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.driver.LoadDriverRequest)
      LoadDriverRequestOrBuilder {
    // Use LoadDriverRequest.newBuilder() to construct.
    private LoadDriverRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private LoadDriverRequest(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final LoadDriverRequest defaultInstance;
    public static LoadDriverRequest getDefaultInstance() {
      return defaultInstance;
    }

    public LoadDriverRequest getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private LoadDriverRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              className_ = bs;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return proto.driver.Driver.internal_static_proto_driver_LoadDriverRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return proto.driver.Driver.internal_static_proto_driver_LoadDriverRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              proto.driver.Driver.LoadDriverRequest.class, proto.driver.Driver.LoadDriverRequest.Builder.class);
    }

    public static com.google.protobuf.Parser<LoadDriverRequest> PARSER =
        new com.google.protobuf.AbstractParser<LoadDriverRequest>() {
      public LoadDriverRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new LoadDriverRequest(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<LoadDriverRequest> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int CLASSNAME_FIELD_NUMBER = 1;
    private java.lang.Object className_;
    /**
     * <code>optional string className = 1;</code>
     */
    public boolean hasClassName() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional string className = 1;</code>
     */
    public java.lang.String getClassName() {
      java.lang.Object ref = className_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          className_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string className = 1;</code>
     */
    public com.google.protobuf.ByteString
        getClassNameBytes() {
      java.lang.Object ref = className_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        className_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      className_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getClassNameBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getClassNameBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static proto.driver.Driver.LoadDriverRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static proto.driver.Driver.LoadDriverRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static proto.driver.Driver.LoadDriverRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static proto.driver.Driver.LoadDriverRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static proto.driver.Driver.LoadDriverRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static proto.driver.Driver.LoadDriverRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static proto.driver.Driver.LoadDriverRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static proto.driver.Driver.LoadDriverRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static proto.driver.Driver.LoadDriverRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static proto.driver.Driver.LoadDriverRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(proto.driver.Driver.LoadDriverRequest prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code proto.driver.LoadDriverRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.driver.LoadDriverRequest)
        proto.driver.Driver.LoadDriverRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return proto.driver.Driver.internal_static_proto_driver_LoadDriverRequest_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return proto.driver.Driver.internal_static_proto_driver_LoadDriverRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                proto.driver.Driver.LoadDriverRequest.class, proto.driver.Driver.LoadDriverRequest.Builder.class);
      }

      // Construct using proto.driver.Driver.LoadDriverRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        className_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return proto.driver.Driver.internal_static_proto_driver_LoadDriverRequest_descriptor;
      }

      public proto.driver.Driver.LoadDriverRequest getDefaultInstanceForType() {
        return proto.driver.Driver.LoadDriverRequest.getDefaultInstance();
      }

      public proto.driver.Driver.LoadDriverRequest build() {
        proto.driver.Driver.LoadDriverRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public proto.driver.Driver.LoadDriverRequest buildPartial() {
        proto.driver.Driver.LoadDriverRequest result = new proto.driver.Driver.LoadDriverRequest(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.className_ = className_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof proto.driver.Driver.LoadDriverRequest) {
          return mergeFrom((proto.driver.Driver.LoadDriverRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(proto.driver.Driver.LoadDriverRequest other) {
        if (other == proto.driver.Driver.LoadDriverRequest.getDefaultInstance()) return this;
        if (other.hasClassName()) {
          bitField0_ |= 0x00000001;
          className_ = other.className_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        proto.driver.Driver.LoadDriverRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (proto.driver.Driver.LoadDriverRequest) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object className_ = "";
      /**
       * <code>optional string className = 1;</code>
       */
      public boolean hasClassName() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional string className = 1;</code>
       */
      public java.lang.String getClassName() {
        java.lang.Object ref = className_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            className_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string className = 1;</code>
       */
      public com.google.protobuf.ByteString
          getClassNameBytes() {
        java.lang.Object ref = className_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          className_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string className = 1;</code>
       */
      public Builder setClassName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        className_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string className = 1;</code>
       */
      public Builder clearClassName() {
        bitField0_ = (bitField0_ & ~0x00000001);
        className_ = getDefaultInstance().getClassName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string className = 1;</code>
       */
      public Builder setClassNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        className_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.driver.LoadDriverRequest)
    }

    static {
      defaultInstance = new LoadDriverRequest(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.driver.LoadDriverRequest)
  }

  public interface LoadDriverResponseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.driver.LoadDriverResponse)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 majorVersion = 1;</code>
     */
    boolean hasMajorVersion();
    /**
     * <code>optional int32 majorVersion = 1;</code>
     */
    int getMajorVersion();

    /**
     * <code>optional int32 minorVersion = 2;</code>
     */
    boolean hasMinorVersion();
    /**
     * <code>optional int32 minorVersion = 2;</code>
     */
    int getMinorVersion();
  }
  /**
   * Protobuf type {@code proto.driver.LoadDriverResponse}
   */
  public static final class LoadDriverResponse extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.driver.LoadDriverResponse)
      LoadDriverResponseOrBuilder {
    // Use LoadDriverResponse.newBuilder() to construct.
    private LoadDriverResponse(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private LoadDriverResponse(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final LoadDriverResponse defaultInstance;
    public static LoadDriverResponse getDefaultInstance() {
      return defaultInstance;
    }

    public LoadDriverResponse getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private LoadDriverResponse(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              majorVersion_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              minorVersion_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return proto.driver.Driver.internal_static_proto_driver_LoadDriverResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return proto.driver.Driver.internal_static_proto_driver_LoadDriverResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              proto.driver.Driver.LoadDriverResponse.class, proto.driver.Driver.LoadDriverResponse.Builder.class);
    }

    public static com.google.protobuf.Parser<LoadDriverResponse> PARSER =
        new com.google.protobuf.AbstractParser<LoadDriverResponse>() {
      public LoadDriverResponse parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new LoadDriverResponse(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<LoadDriverResponse> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int MAJORVERSION_FIELD_NUMBER = 1;
    private int majorVersion_;
    /**
     * <code>optional int32 majorVersion = 1;</code>
     */
    public boolean hasMajorVersion() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 majorVersion = 1;</code>
     */
    public int getMajorVersion() {
      return majorVersion_;
    }

    public static final int MINORVERSION_FIELD_NUMBER = 2;
    private int minorVersion_;
    /**
     * <code>optional int32 minorVersion = 2;</code>
     */
    public boolean hasMinorVersion() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 minorVersion = 2;</code>
     */
    public int getMinorVersion() {
      return minorVersion_;
    }

    private void initFields() {
      majorVersion_ = 0;
      minorVersion_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, majorVersion_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, minorVersion_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, majorVersion_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, minorVersion_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static proto.driver.Driver.LoadDriverResponse parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static proto.driver.Driver.LoadDriverResponse parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static proto.driver.Driver.LoadDriverResponse parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static proto.driver.Driver.LoadDriverResponse parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static proto.driver.Driver.LoadDriverResponse parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static proto.driver.Driver.LoadDriverResponse parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static proto.driver.Driver.LoadDriverResponse parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static proto.driver.Driver.LoadDriverResponse parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static proto.driver.Driver.LoadDriverResponse parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static proto.driver.Driver.LoadDriverResponse parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(proto.driver.Driver.LoadDriverResponse prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code proto.driver.LoadDriverResponse}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.driver.LoadDriverResponse)
        proto.driver.Driver.LoadDriverResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return proto.driver.Driver.internal_static_proto_driver_LoadDriverResponse_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return proto.driver.Driver.internal_static_proto_driver_LoadDriverResponse_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                proto.driver.Driver.LoadDriverResponse.class, proto.driver.Driver.LoadDriverResponse.Builder.class);
      }

      // Construct using proto.driver.Driver.LoadDriverResponse.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        majorVersion_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        minorVersion_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return proto.driver.Driver.internal_static_proto_driver_LoadDriverResponse_descriptor;
      }

      public proto.driver.Driver.LoadDriverResponse getDefaultInstanceForType() {
        return proto.driver.Driver.LoadDriverResponse.getDefaultInstance();
      }

      public proto.driver.Driver.LoadDriverResponse build() {
        proto.driver.Driver.LoadDriverResponse result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public proto.driver.Driver.LoadDriverResponse buildPartial() {
        proto.driver.Driver.LoadDriverResponse result = new proto.driver.Driver.LoadDriverResponse(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.majorVersion_ = majorVersion_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.minorVersion_ = minorVersion_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof proto.driver.Driver.LoadDriverResponse) {
          return mergeFrom((proto.driver.Driver.LoadDriverResponse)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(proto.driver.Driver.LoadDriverResponse other) {
        if (other == proto.driver.Driver.LoadDriverResponse.getDefaultInstance()) return this;
        if (other.hasMajorVersion()) {
          setMajorVersion(other.getMajorVersion());
        }
        if (other.hasMinorVersion()) {
          setMinorVersion(other.getMinorVersion());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        proto.driver.Driver.LoadDriverResponse parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (proto.driver.Driver.LoadDriverResponse) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int majorVersion_ ;
      /**
       * <code>optional int32 majorVersion = 1;</code>
       */
      public boolean hasMajorVersion() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 majorVersion = 1;</code>
       */
      public int getMajorVersion() {
        return majorVersion_;
      }
      /**
       * <code>optional int32 majorVersion = 1;</code>
       */
      public Builder setMajorVersion(int value) {
        bitField0_ |= 0x00000001;
        majorVersion_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 majorVersion = 1;</code>
       */
      public Builder clearMajorVersion() {
        bitField0_ = (bitField0_ & ~0x00000001);
        majorVersion_ = 0;
        onChanged();
        return this;
      }

      private int minorVersion_ ;
      /**
       * <code>optional int32 minorVersion = 2;</code>
       */
      public boolean hasMinorVersion() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 minorVersion = 2;</code>
       */
      public int getMinorVersion() {
        return minorVersion_;
      }
      /**
       * <code>optional int32 minorVersion = 2;</code>
       */
      public Builder setMinorVersion(int value) {
        bitField0_ |= 0x00000002;
        minorVersion_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 minorVersion = 2;</code>
       */
      public Builder clearMinorVersion() {
        bitField0_ = (bitField0_ & ~0x00000002);
        minorVersion_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.driver.LoadDriverResponse)
    }

    static {
      defaultInstance = new LoadDriverResponse(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.driver.LoadDriverResponse)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_driver_LoadDriverRequest_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_driver_LoadDriverRequest_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_driver_LoadDriverResponse_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_driver_LoadDriverResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014driver.proto\022\014proto.driver\"&\n\021LoadDriv" +
      "erRequest\022\021\n\tclassName\030\001 \001(\t\"@\n\022LoadDriv" +
      "erResponse\022\024\n\014majorVersion\030\001 \001(\005\022\024\n\014mino" +
      "rVersion\030\002 \001(\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_proto_driver_LoadDriverRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_driver_LoadDriverRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_driver_LoadDriverRequest_descriptor,
        new java.lang.String[] { "ClassName", });
    internal_static_proto_driver_LoadDriverResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_proto_driver_LoadDriverResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_driver_LoadDriverResponse_descriptor,
        new java.lang.String[] { "MajorVersion", "MinorVersion", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
