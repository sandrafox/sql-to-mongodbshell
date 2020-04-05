import java.util.ArrayList;
import java.util.List;

public class Where {
    private List<Predicate> predicates = new ArrayList<Predicate>();

    public void addPredicate(Predicate predicate) {
        predicates.add(predicate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (Predicate p : predicates) {
            sb.append(p.toString() + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
