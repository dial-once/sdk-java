package com.dialonce.sdk;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

            IVR ivr = new IVR(app, "+336000000", "+336000000");

            assertTrue(ivr.log(IVR.LogType.CALL_START));
            assertTrue(ivr.log(IVR.LogType.CALL_END));
            assertTrue(ivr.log(IVR.LogType.ANSWER_GET_SMS));
            assertTrue(ivr.log(IVR.LogType.ANSWER_NO_SMS));

            assertFalse(ivr.isEligible());

            assertFalse(ivr.isMobile(null));

            assertTrue(ivr.serviceRequest());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
