package io.geekya215.bocchi;

import java.util.Objects;

// Byte util for reading class file byte stream or code attribute
public final class ByteReader {
    private final byte[] bytes;
    private int cursor;

    private ByteReader(byte[] bytes) {
        Objects.requireNonNull(bytes);
        this.bytes = bytes;
        this.cursor = 0;
    }

    public static ByteReader create(byte[] bytes) {
        return new ByteReader(bytes);
    }

    public int readU1() {
        Objects.checkIndex(cursor, bytes.length);
        int u1 = bytes[cursor];
        cursor += 1;
        return u1;
    }

    public int readU1(int index) {
        Objects.checkIndex(index, bytes.length);
        return bytes[index];
    }

    public int readU2() {
        Objects.checkIndex(cursor + 1, bytes.length);
        int u2 = bytes[cursor] << 8 | bytes[cursor + 1];
        cursor += 2;
        return u2;
    }

    public int readU2(int index) {
        Objects.checkIndex(index + 1, bytes.length);
        return bytes[index + 1] << 8 | bytes[index];
    }

    public long readU4() {
        Objects.checkIndex(cursor + 3, bytes.length);
        long u4 = bytes[cursor    ] & 0xffffffffL << 24 |
                  bytes[cursor + 1] & 0xffffffffL << 16 |
                  bytes[cursor + 2] & 0xffffffffL <<  8 |
                  bytes[cursor + 3] & 0xffffffffL;
        cursor += 3;
        return u4;
    }

    public long readU4(int index) {
        Objects.checkIndex(index + 3, bytes.length);
        return bytes[index    ] & 0xffffffffL << 24 |
               bytes[index + 1] & 0xffffffffL << 16 |
               bytes[index + 2] & 0xffffffffL <<  8 |
               bytes[index + 3] & 0xffffffffL;
    }

    public byte[] readNBytes(int len) {
        Objects.checkFromIndexSize(cursor, len, bytes.length);

        byte[] NBytes = new byte[len];
        System.arraycopy(bytes, cursor, NBytes, 0, len);

        cursor += len;

        return NBytes;
    }

    public byte[] readNBytes(int index, int len) {
        Objects.checkFromIndexSize(index, len, bytes.length);

        byte[] NBytes = new byte[len];
        System.arraycopy(bytes, index, NBytes, 0, len);

        return NBytes;
    }

    public byte[] readAllBytes() {
        return readNBytes(bytes.length - cursor);
    }

    public byte[] readAllBytes(int index) {
        return readNBytes(index, bytes.length - index);
    }
}
