package main.java.leiDina.tec.core.messages;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author vitor.alves
 */
public class MessageDelegateTest {

    private final String NO_PARAM_TEST = "Test for no param.";

    private final String ONE_PARAM_TEST = "Test for one param Test.";

    @Test
    public void testNotNull() {
        MessageHolder messageHolder = MessagesEnumTest.NO_PARAM_TEST.create();
        Assertions.assertNotNull(messageHolder);
    }

    @Test
    public void testNoParam() {
        MessageHolder messageHolder = MessagesEnumTest.NO_PARAM_TEST.create();
        String message = messageHolder.getMessage();
        Assertions.assertEquals(message, NO_PARAM_TEST);
    }

    @Test
    public void testOneParam() {
        MessageHolder messageHolder = MessagesEnumTest.ONE_PARAM_TEST.create("Test");
        String message = messageHolder.getMessage();
        Assertions.assertEquals(message, ONE_PARAM_TEST);
    }

    @Test
    public void testWrongNumArgs() {
        MessageHolder messageHolder = MessagesEnumTest.ONE_PARAM_TEST.create();
        String message = messageHolder.getMessage();
        Assertions.assertEquals(message, "Number of arguments wrong for key: " + MessagesEnumTest.ONE_PARAM_TEST.getKey());
    }
}
