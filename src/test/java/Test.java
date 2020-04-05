import org.junit.Assert;
import org.junit.Before;

public class Test {
    private Parser parser;
    @Before
    void init() {
        parser = new Parser();
    }

    @org.junit.Test
    public void simpleTest() throws ParserException {
        Assert.assertEquals("db.collection.find({})", parser.parse("select * from collection"));
    }
}
