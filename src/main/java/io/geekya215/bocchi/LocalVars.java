package io.geekya215.bocchi;

public final class LocalVars {
    private final Value[] vars = new Value[64];

    Value iload(byte index) {
        return vars[index & 0xFF];
    }

    void istore(byte index, Value v) {
        vars[index & 0xFF] = v;
    }

    void iinc(byte index, byte amount) {
        Value v = vars[index & 0xFF];
        if (v.type() != Type.I) {
            return ;
        }
        vars[index & 0xFF] = Value.of((int) (amount + v.value()));
    }
}
