package org.greenrobot.eventbus;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by coleman on 16-6-29.
 */
public class EventBusEventIdTest extends AbstractEventBusTest {
    private String str;

    @Subscribe(eventId = 100, sticky = true)
    public void sc(String str) {
        this.str = str;
    }

    @Subscribe(eventId = 100, sticky = true)
    public void sc(Integer v) {
        str = "" + v;
    }

    @Test
    public void testPostSticky1() {
        String msg = "111";
        eventBus.register(this);
        eventBus.postSticky(100, msg);
        assertEquals(msg, str);
    }

    @Test
    public void testPostSticky2() {
        String msg = "222";
        eventBus.postSticky(100, msg);
        eventBus.register(this);
        assertEquals(msg, str);
    }

    @Test
    public void testPostSticky3() {
        String msg = "333";
        eventBus.postSticky(msg);
        eventBus.postSticky(101, msg);
        eventBus.post(100, 2);
        eventBus.register(this);
        assertNull(str);
    }

    @Test
    public void testGetSticky() {
        String msg = "aaa";
        eventBus.register(this);
        eventBus.postSticky(100, msg);
        String s1 = eventBus.getStickyEvent(String.class);
        assertNull(s1);
        String s2 = eventBus.getStickyEvent(String.class, 100);
        assertEquals(msg, s2);
    }

    @Test
    public void testRemoveSticky() {
        String msg = "aaa";
        eventBus.register(this);
        eventBus.postSticky(100, msg);
        String s2 = eventBus.getStickyEvent(String.class, 100);
        assertEquals(msg, s2);

        assertFalse(eventBus.removeStickyEvent("abc"));
        assertTrue(eventBus.removeStickyEvent(msg));
        eventBus.postSticky(100, msg);
        s2 = eventBus.removeStickyEvent(String.class, 100);
        assertEquals(s2, msg);
        s2 = eventBus.removeStickyEvent(String.class);
        assertNull(s2);
    }

}
