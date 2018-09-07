package wjc.redis.command;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import wjc.redis.Command;

import java.util.concurrent.TimeUnit;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-07 18:51
 **/
public class Persist extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(Persist.class);

    @Test
    public void test() {
        template.opsForValue().set("mykey", "Hello");
        template.expire("mykey", 10, TimeUnit.SECONDS);
        RedisSerializer<String> serializer = (RedisSerializer<String>) template.getKeySerializer();
        Long ttl = template.getConnectionFactory().getConnection().keyCommands().ttl(
                serializer.serialize("mykey"));
        System.out.println(ttl);
        Assert.assertEquals(Long.valueOf(10), ttl);

        template.persist("mykey");
        ttl = template.getConnectionFactory().getConnection().keyCommands().ttl(
                serializer.serialize("mykey"));
        System.out.println(ttl);

        Assert.assertEquals(Long.valueOf(-1), ttl);
    }
}
