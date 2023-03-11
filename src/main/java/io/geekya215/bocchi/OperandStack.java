package io.geekya215.bocchi;

import java.util.ArrayDeque;

public class OperandStack extends ArrayDeque<Value> {
    void iconst(int i) {
        push(Value.of(i));
    }

    void iadd() {
        Value v1 = pop();
        Value v2 = pop();
        int res = (int) (v1.value() + v2.value());
        push(Value.of(res));
    }

    void isub() {
        Value v1 = pop();
        Value v2 = pop();
        int res = (int) (v1.value() - v2.value());
        push(Value.of(res));
    }

    void imul() {
        Value v1 = pop();
        Value v2 = pop();
        int res = (int) (v1.value() * v2.value());
        push(Value.of(res));
    }

    void idiv() {
        Value v1 = pop();
        Value v2 = pop();
        int res = (int) (v1.value() / v2.value());
        push(Value.of(res));
    }

    void irem() {
        Value v1 = pop();
        Value v2 = pop();
        int res = (int) (v1.value() % v2.value());
        push(Value.of(res));
    }

    void ineg() {
        Value res = pop();
        push(Value.of((int) -res.value()));
    }

    void iand() {
        Value v1 = pop();
        Value v2 = pop();
        int res = (int) (v1.value() & v2.value());
        push(Value.of(res));
    }

    void ior() {
        Value v1 = pop();
        Value v2 = pop();
        int res = (int) (v1.value() | v2.value());
        push(Value.of(res));
    }

    void ixor() {
        Value v1 = pop();
        Value v2 = pop();
        int res = (int) (v1.value() ^ v2.value());
        push(Value.of(res));
    }
}
