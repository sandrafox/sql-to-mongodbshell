public class Value {
    private int n;
    private boolean b;
    private String s;
    private ValueType type;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
        type = ValueType.NUMBER;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
        type = ValueType.BOOL;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
        type = ValueType.STRING;
    }

    @Override
    public String toString() {
        switch (type) {
            case BOOL:
                return b ? "true" : "false";
            case NUMBER:
                return "" + n;
            case STRING:
                return s;
        }
        return "";
    }
}
