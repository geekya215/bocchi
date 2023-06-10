package io.geekya215.bocchi;

public final class BuildInfo {
    private BuildInfo() {
    }

    // VERSION and LABEL will construct a build tag
    // e.g. for VERSION = {1, 1, 4} and LABEL = "mur"
    // will generate tag v1.1.4-mur
    public static final int MAJOR = 0;
    public static final int MINOR = 1;
    public static final int BUILD = 1;
    public static final String LABEL = "mur";
}
