package io.geekya215.bocchi;

public record Value(
    Type type,
    long value
) {
    static Value of(int i) {
        return new Value(Type.I, i);
    }

    Value copy() {
        return new Value(type, value);
    }
}
