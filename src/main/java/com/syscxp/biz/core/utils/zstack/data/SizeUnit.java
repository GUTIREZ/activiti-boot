package com.syscxp.biz.core.utils.zstack.data;

public enum SizeUnit {
    BYTE {
        @Override
        public long toByte(long s) {
            return s;
        }
        @Override
        public long toKiloByte(long s) {
            return (s / (k / b));
        }
        @Override
        public long toMegaByte(long s) {
            return (s / (m / b));
        }
        @Override
        public long toGigaByte(long s) {
            return (s / (g / b));
        }
        @Override
        public long toTeraByte(long s) {
            return (s / (t / b));
        }
        @Override
        public long convert(long s, SizeUnit src) {
            return src.toByte(s);
        }
    },
    KILOBYTE {
        @Override
        public long toByte(long s) {
            return (s * (k / b));
        }
        @Override
        public long toKiloByte(long s) {
            return s;
        }
        @Override
        public long toMegaByte(long s) {
            return (s / (m / k));
        }
        @Override
        public long toGigaByte(long s) {
            return (s / (g / k));
        }
        @Override
        public long toTeraByte(long s) {
            return (s / (t / k));
        }
        @Override
        public long convert(long s, SizeUnit src) {
            return src.toKiloByte(s);
        }
    },
    MEGABYTE {
        @Override
        public long toByte(long s) {
            return (s * (m / b));
        }
        @Override
        public long toKiloByte(long s) {
            return (s * (m / k));
        }
        @Override
        public long toMegaByte(long s) {
            return s;
        }
        @Override
        public long toGigaByte(long s) {
            return (s / (g / m));
        }
        @Override
        public long toTeraByte(long s) {
            return (s / (t / m));
        }
        @Override
        public long convert(long s, SizeUnit src) {
            return src.toMegaByte(s);
        }
    },
    GIGABYTE {
        @Override
        public long toByte(long s) {
            return (s * (g / b));
        }
        @Override
        public long toKiloByte(long s) {
            return (s * (g / k));
        }
        @Override
        public long toMegaByte(long s) {
            return (s * (g / m));
        }
        @Override
        public long toGigaByte(long s) {
            return s;
        }
        @Override
        public long toTeraByte(long s) {
            return (s / (t / g));
        }
        @Override
        public long convert(long s, SizeUnit src) {
            return src.toGigaByte(s);
        }
    },
    TERABYTE {
        @Override
        public long toByte(long s) {
            return (s * (t / b));
        }
        @Override
        public long toKiloByte(long s) {
            return (s * (t / k));
        }
        @Override
        public long toMegaByte(long s) {
            return (s * (t / m));
        }
        @Override
        public long toGigaByte(long s) {
            return (s * (t / g));
        }
        @Override
        public long toTeraByte(long s) {
            return s;
        }
        @Override
        public long convert(long s, SizeUnit src) {
            return src.toTeraByte(s);
        }

    };

    public static SizeUnit fromString(String s) {
        if ("b".equalsIgnoreCase(s)) {
            return BYTE;
        } else if ("k".equalsIgnoreCase(s)) {
            return KILOBYTE;
        } else if ("m".equalsIgnoreCase(s)) {
            return MEGABYTE;
        } else if ("g".equalsIgnoreCase(s)) {
            return GIGABYTE;
        } else if ("t".equalsIgnoreCase(s)) {
            return TERABYTE;
        } else {
            throw new IllegalArgumentException(String.format("unknown size unit[%s]", s));
        }
    }

    private static final long b = 1;
    private static final long k = b * 1024;
    private static final long m = k * 1024;
    private static final long g = m * 1024;
    private static final long t = g * 1024;

    public long toByte(long s) {
        throw new AbstractMethodError();
    }
    public long toKiloByte(long s) {
        throw new AbstractMethodError();
    }
    public long toMegaByte(long s) {
        throw new AbstractMethodError();
    }
    public long toGigaByte(long s) {
        throw new AbstractMethodError();
    }
    public long toTeraByte(long s) {
        throw new AbstractMethodError();
    }

    public long convert(long s, SizeUnit src) {
        throw new AbstractMethodError();
    }


}
