package com.dialonce.sdk;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 */
public class IVRTest {

    @Test
    public void baseTest() {

        try {

            Application app = new Application(
                    "qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");

            IVR ivr = new IVR(app, "+33640000000", "+33640000000");

            assertTrue(ivr.log(IVR.LogType.CALL_START));
            assertTrue(ivr.log(IVR.LogType.CALL_END));
            assertTrue(ivr.log(IVR.LogType.ANSWER_GET_SMS));
            assertTrue(ivr.log(IVR.LogType.ANSWER_NO_SMS));

            assertTrue(ivr.isEligible());

            assertTrue(ivr.isMobile(null));

            assertTrue(ivr.serviceRequest());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testNullApplication() {

        try {
            new IVR(null);
        } catch (RuntimeException npe) {
            assertThat(npe.getMessage(), is("Application can't be null!"));
        }
    }

    @Test
    public void testInvalidCredentials() {
        try {
            new Application("", "");
        } catch (Exception ivrException) {
            assertThat(ivrException.getMessage(), is("401: Unauthorized"));
        }
    }

    @Test
    public void testValidCredentials() {
        try {
            Application app = new Application("qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
            assertTrue(app.getToken().isValid());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testValidToken() {
        try {
            Application app = new Application("39a6110a-775a-4ccd-bf05-ad645f5f1b09");
            app.getToken().isValid();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testInvalidToken() {
        try {
            Application app = new Application("");
            // token validity can be determined only once the connection request is done
            new IVR(app, "+33640000000", "+33640000000").log(IVR.LogType.CALL_START);
        } catch (Exception ivrException) {
            assertThat(ivrException.getMessage(), is("401: Unauthorized"));
        }
    }

    @Test
    public void testValidPhoneNumbers() {
        try {
            Application app = new Application("qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
            new IVR(app, "+33640000000", "+33640000000").log(IVR.LogType.CALL_START);
            new IVR(app, "0033640000000", "0033640000000").log(IVR.LogType.CALL_START);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testInvalidPhoneNumbers() {
        try {
            Application app = new Application("qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
            new IVR(app, "", "").log(IVR.LogType.CALL_START);
        } catch (Exception ivrException) {
            assertThat(ivrException.getMessage(), is("400: Bad request"));
        }
    }

    @Test
    public void testLogCallStart() {
        try {
            Application app = new Application("qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
            new IVR(app, "+33640000000", "+33640000000").log(IVR.LogType.CALL_START);
            new IVR(app, "0033640000000", "+33640000000").log(IVR.LogType.CALL_START);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testLogCallEnd() {
        try {
            Application app = new Application("qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
            new IVR(app, "+33640000000", "+33640000000").log(IVR.LogType.CALL_END);
            new IVR(app, "0033640000000", "0033640000000").log(IVR.LogType.CALL_END);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testLogAnswerGetSms() {
        try {
            Application app = new Application("qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
            new IVR(app, "+33640000000", "+33640000000").log(IVR.LogType.ANSWER_GET_SMS);
            new IVR(app, "0033640000000", "0033640000000").log(IVR.LogType.ANSWER_GET_SMS);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testLogAnswerNoSms() {
        try {
            Application app = new Application("qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
            new IVR(app, "+33640000000", "+33640000000").log(IVR.LogType.ANSWER_NO_SMS);
            new IVR(app, "0033640000000", "0033640000000").log(IVR.LogType.ANSWER_NO_SMS);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testIsEligible() {
        try {
            Application app = new Application("qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
            assertTrue(new IVR(app, "+33640000000", "+336400000000").isEligible());
            assertTrue(new IVR(app, "0033640000000", "00336400000000").isEligible());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testNotEligible() {
        try {
            Application app = new Application("qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
            assertFalse(new IVR(app, "+336000000", "+336000000").isEligible());
            assertFalse(new IVR(app, "00336000000", "00336000000").isEligible());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testIsMobile() {
        try {
            Application app = new Application("qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
            assertTrue(new IVR(app, "+33640000000", "+336400000000").isMobile(null));
            assertTrue(new IVR(app, "0033640000000", "00336400000000").isMobile(null));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testIsMobileCulture() {
        try {
            Application app = new Application("qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
            assertFalse(new IVR(app, "+336000000", "+336000000").isMobile("fr"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testNotMobile() {
        try {
            Application app = new Application("qpvao53b1x10z7u3906wvgzmvexuxwxj",
                    "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
            assertFalse(new IVR(app, "+336000000", "+336000000").isMobile(null));
            assertFalse(new IVR(app, "00336000000", "00336000000").isMobile(null));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
