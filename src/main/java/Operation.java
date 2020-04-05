public enum Operation {
    LT, GT, EQ, NE;

    @Override
    public String toString() {
        switch (this) {
            case EQ:
                return "=";
            case GT:
                return "gt";
            case LT:
                return "lt";
            case NE:
                return "ne";
        }
        return "";
    }
}
