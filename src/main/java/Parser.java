import java.util.List;

public class Parser {
    private LexicalAnalyzer analyzer;

    public Query parse(String s) throws ParserException {
        analyzer = new LexicalAnalyzer();
        s = s.toLowerCase();
        analyzer.analyze(s);
        analyzer.nextToken();
        return query();
    }

    private Query query() throws ParserException {
        Query query = new Query();
        query.setFields(fields());
        query.setCollection(collection());
        query.setWhere(where());
        query.setSkip(skip());
        query.setLimit(limit());
        return query;
    }

    private void skipSpaces() throws ParserException {
        while (analyzer.getCurToken() == Token.SPACE) analyzer.nextToken();
    }

    private Fields fields() throws ParserException {
        skipSpaces();
        if (analyzer.getCurToken() != Token.SELECT) throw new SyntaxError();
        analyzer.nextToken();
        skipSpaces();
        if (analyzer.getCurToken() == Token.STAR) {
            analyzer.nextToken();
            return null;
        }
        if (analyzer.getCurToken() != Token.ID)  throw new SyntaxError();
        Fields fields = new Fields();
        while (analyzer.getCurToken() == Token.ID) {
            fields.addId(new Id(analyzer.getCurString()));
            analyzer.nextToken();
            skipSpaces();
            if (analyzer.getCurToken() == Token.COMMA) {
                analyzer.nextToken();
                skipSpaces();
            }
        }
        return fields;
    }

    private Collection collection() throws ParserException {
        skipSpaces();
        if (analyzer.getCurToken() != Token.FROM) throw new SyntaxError();
        analyzer.nextToken();
        skipSpaces();
        if (analyzer.getCurToken() != Token.ID) throw new SyntaxError();
        Collection collection = new Collection(analyzer.getCurString());
        analyzer.nextToken();
        return collection;
    }

    private Where where() throws ParserException {
        skipSpaces();
        if (analyzer.getCurToken() != Token.WHERE) return null;
        analyzer.nextToken();
        Where where = new Where();
        where.addPredicate(predicate());
        skipSpaces();
        while (analyzer.getCurToken() == Token.AND) {
            analyzer.nextToken();
            skipSpaces();
            where.addPredicate(predicate());
            skipSpaces();
        }
        return where;
    }

    private Predicate predicate() throws ParserException {
        skipSpaces();
        if (analyzer.getCurToken() != Token.ID) throw new SyntaxError();
        Predicate predicate = new Predicate();
        predicate.setId(new Id(analyzer.getCurString()));
        analyzer.nextToken();
        skipSpaces();
        predicate.setOperation(operation());
        analyzer.nextToken();
        skipSpaces();
        predicate.setValue(value());
        return predicate;
    }

    private Value value() throws ParserException {
        skipSpaces();
        Value value = new Value();
        switch (analyzer.getCurToken()) {
            case NUMBER:
                value.setN(Integer.parseInt(analyzer.getCurString()));
                break;
            case STRING:
                value.setS(analyzer.getCurString());
                break;
            case BOOL:
                value.setB(analyzer.getCurString().equals("true"));
                break;
            default:
                throw new SyntaxError();
        }
        analyzer.nextToken();
        return value;
    }

    private Operation operation() throws ParserException {
        switch (analyzer.getCurToken()) {
            case EQ:
                return Operation.EQ;
            case NE:
                return Operation.NE;
            case LT:
                return Operation.LT;
            case GT:
                return Operation.GT;
        }
        throw new SyntaxError();
    }

    private Skip skip() throws ParserException {
        skipSpaces();
        if (analyzer.getCurToken() != Token.OFFSET) return null;
        analyzer.nextToken();
        skipSpaces();
        if (analyzer.getCurToken() != Token.NUMBER) throw new SyntaxError();
        Skip skip = new Skip();
        skip.setN(Integer.parseInt(analyzer.getCurString()));
        analyzer.nextToken();
        return skip;
    }

    private Limit limit() throws ParserException {
        skipSpaces();
        if (analyzer.getCurToken() != Token.LIMIT) return null;
        analyzer.nextToken();
        skipSpaces();
        if (analyzer.getCurToken() != Token.NUMBER) throw new SyntaxError();
        Limit limit = new Limit();
        limit.setN(Integer.parseInt(analyzer.getCurString()));
        analyzer.nextToken();
        return limit;
    }
}
