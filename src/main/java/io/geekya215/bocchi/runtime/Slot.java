package io.geekya215.bocchi.runtime;

// Todo
// each slot will use double size to store object ref
// how can we reduce space usage ?
public final class Slot {
    private int value;
    private Object ref;

    public Slot() {
        this(0, null);
    }

    public Slot(int value, Object ref) {
        this.value = value;
        this.ref = ref;
    }

    public static Slot ofValue(int value) {
        return new Slot(value, null);
    }

    public static Slot ofRef(Object ref) {
        return new Slot(0, ref);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Object getRef() {
        return ref;
    }

    public void setRef(Object ref) {
        this.ref = ref;
    }
}
