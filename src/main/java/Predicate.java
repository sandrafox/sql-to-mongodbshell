public class Predicate {
    private Id id;
    private Operation operation;
    private Value value;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String  res = id.toString() + ": ";
        if (operation == Operation.EQ) {
                return res + value.toString();
        }
        return res + "{$" + operation.toString() + ": " + value.toString() + "}";
    }
}
