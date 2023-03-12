package io.geekya215.bocchi;

import java.util.ArrayDeque;

public class OperandStack extends ArrayDeque<Value> {
    void iconst(int i) {
        push(Value.of(i));
    }

    void pop2() {
        Value discard = pop();
        if (discard.type() == Type.J || discard.type() == Type.D) {
            return;
        }
        pop();
    }

    void dup() {
        assert peek() != null;
        Value v = peek().copy();
        push(v);
    }

    void swap() {
        Value v1 = pop();
        Value v2 = pop();
        push(v1);
        push(v2);
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

    void ishl() {
        Value v1 = pop();
        Value v2 = pop();
        int res = (int) (v1.value() << v2.value());
        push(Value.of(res));
    }

    void ishr() {
        Value v1 = pop();
        Value v2 = pop();
        int res = (int) (v1.value() >> v2.value());
        push(Value.of(res));
    }

    void iushr() {
        Value v1 = pop();
        Value v2 = pop();
        int res = (int) (v1.value() >>> v2.value());
        push(Value.of(res));
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
