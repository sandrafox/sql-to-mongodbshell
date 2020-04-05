public class Query {
    private Fields fields;
    private Collection collection;
    private Where where;
    private Skip skip;
    private Limit limit;

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Where getWhere() {
        return where;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    public Skip getSkip() {
        return skip;
    }

    public void setSkip(Skip skip) {
        this.skip = skip;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "db." + collection.toString() + ".find({" + (where == null ? "" : where.toString()) + "}" +
                (fields == null ? "" : fields.toString()) + (skip == null ? "" : skip.toString()) +
                (limit == null ? "" : limit.toString());
    }
}
