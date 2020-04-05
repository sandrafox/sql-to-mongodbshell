import org.junit.Assert;
import org.junit.Before;

public class Test {
    private Parser parser;
    @Before
    public void init() {
        parser = new Parser();
    }

    @org.junit.Test
    public void simpleTest() throws ParserException {
        Assert.assertEquals("db.collection.find({})", parser.parse("select * from collection").toString());
    }

    @org.junit.Test
    public void testWithWithWhereAndSkipAndLimit() throws ParserException {
        Assert.assertEquals("db.collection.find({field: 5}).skip(3).limit(5)", parser.parse("select * from collection where field = 5 offset 3 limit 5").toString());
    }
}
