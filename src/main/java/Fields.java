import java.util.ArrayList;
import java.util.List;

public class Fields {
    private List<Id> ids = new ArrayList<Id>();

    public void addId(Id id) {
        ids.add(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(", {");
        for (Id id : ids) {
            sb.append(id.toString() + ": 1,");
        }
        sb.deleteCharAt(sb.length() - 1).append("}");
        return sb.toString();
    }
}
