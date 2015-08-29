package jp.float1251.twtd.test;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

import jp.float1251.twtd.listener.GameNotify;

/**
 * Created by t-iwatani on 2015/08/29.
 */
public class GameNotifyTest {

    @Test
    public void addListener() {
        GameNotify notify = new GameNotify();
        notify.addListener("msg", null);
        assertEquals(1, notify.getListnerCount("msg"));
        notify.removeAllListener("msg");
        assertEquals(0, notify.getListnerCount("msg"));
    }

    @Test
    public void sendMessage() throws InterruptedException {
        final GameNotify notify = new GameNotify();
        final CountDownLatch lock = new CountDownLatch(2);
        notify.addListener("test", new GameNotify.Runnable() {
            @Override
            public void run(Object... args) {
                assertEquals(1, (float) args[0], 0);
                lock.countDown();
            }
        });
        assertEquals(1, notify.getListnerCount("test"));

        notify.addListener("aaa", new GameNotify.Runnable() {
            @Override
            public void run(Object... args) {
                assertEquals(0, args.length, 0);
                lock.countDown();
            }
        });
        notify.sendMessage("aaa");
        notify.sendMessage("test", 1f);
        lock.await(1000, TimeUnit.MILLISECONDS);

    }
}
