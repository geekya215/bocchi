package io.geekya215.bocchi.runtime;

// NOTICE
// all method do not check index
public final class OperandStack {
    private int top;
    private final Slot[] slots;

    public OperandStack(int maxStack) {
        this.top = 0;
        this.slots = new Slot[maxStack];
    }

    public void pushBoolean(boolean value) {
        pushInt(value ? 1 : 0);
    }

    public boolean popBoolean() {
        return popInt() == 1;
    }

    public void pushInt(int value) {
        slots[top++] = Slot.ofValue(value);
    }

    public int popInt() {
        return slots[top--].getValue();
    }

    public void pushFloat(float value) {
        pushInt(Float.floatToIntBits(value));
    }

    public float popFloat() {
        return Float.intBitsToFloat(popInt());
    }

    public void pushLong(long value) {
        pushInt((int) (value >> 32));
        pushInt((int) value);
    }

    public long popLong() {
        int i1 = popInt();
        int i2 = popInt();
        return ((i2 & 0xffffffffL) << 32) | (i1 & 0xffffffffL);
    }

    public void pushDouble(double value) {
        long l = Double.doubleToLongBits(value);
        pushInt((int) (l >> 32));
        pushInt((int) l);
    }

    public double popDouble() {
        return Double.longBitsToDouble(popLong());
    }

    public void pushRef(Object ref) {
        slots[top++] = Slot.ofRef(ref);
    }

    public Object popRef() {
        return slots[top--];
    }

    public void pushSlot(Slot slot) {
        slots[top++] = slot;
    }

    public Slot popSlot() {
        return slots[top--];
    }
}