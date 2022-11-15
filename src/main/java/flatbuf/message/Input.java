// automatically generated by the FlatBuffers compiler, do not modify

package flatbuf.message;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Input extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_22_10_26(); }
  public static Input getRootAsInput(ByteBuffer _bb) { return getRootAsInput(_bb, new Input()); }
  public static Input getRootAsInput(ByteBuffer _bb, Input obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public Input __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public String inputname() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer inputnameAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer inputnameInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }
  public String value() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer valueAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer valueInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }

  public static int createInput(FlatBufferBuilder builder,
      int inputnameOffset,
      int valueOffset) {
    builder.startTable(2);
    Input.addValue(builder, valueOffset);
    Input.addInputname(builder, inputnameOffset);
    return Input.endInput(builder);
  }

  public static void startInput(FlatBufferBuilder builder) { builder.startTable(2); }
  public static void addInputname(FlatBufferBuilder builder, int inputnameOffset) { builder.addOffset(0, inputnameOffset, 0); }
  public static void addValue(FlatBufferBuilder builder, int valueOffset) { builder.addOffset(1, valueOffset, 0); }
  public static int endInput(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public Input get(int j) { return get(new Input(), j); }
    public Input get(Input obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}
