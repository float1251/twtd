package jp.float1251.twtd.test;

import com.badlogic.gdx.Game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.invocation.MockitoMethod;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import jp.float1251.twtd.GameLog;
import jp.float1251.twtd.listener.GameNotify;

import static org.junit.Assert.assertEquals;

/**
 * Created by t-iwatani on 2015/08/29.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(GameLog.class)
public class GameNotifyTest {

    @Test
    public void addListener() {
        GameNotify notify = new GameNotify();
        notify.addListener("msg", null);
        assertEquals(1, notify.getListenerCount("msg"));
        notify.removeAllListener("msg");
        assertEquals(0, notify.getListenerCount("msg"));
    }

    @Test
    public void sendMessage() throws InterruptedException {
        final GameNotify notify = new GameNotify();
        final CountDownLatch lock = new CountDownLatch(2);

        // GameLog.dはGdx.app.logを読んでおり、testでは初期化されていないため、
        // mockを作成し、何もさせないようにする
        PowerMockito.mockStatic(GameLog.class);
        PowerMockito.doNothing().when(GameLog.class);

        notify.addListener("test", new GameNotify.Runnable() {
            @Override
            public void run(Object... args) {
                assertEquals(1, (float) args[0], 0);
                lock.countDown();
            }
        });
        assertEquals(1, notify.getListenerCount("test"));

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
