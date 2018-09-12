package wjc.redis.command.lists;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;

import java.util.List;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class LPush extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(LPush.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForList().leftPush("mylist", "world");
        template.opsForList().leftPush("mylist", "hello");

        List<String> range = template.opsForList().range("mylist", 0, -1);
        System.out.println(range);
        List<String> expect = Lists.newArrayList("hello", "world");
        Assert.assertTrue(expect.containsAll(range) && range.containsAll(expect));
    }

    @Test
    @Override
    public void testConnection() {
        connection.lPush(
                keySerializer.serialize("mylist"),
                valueSerializer.serialize("world"),
                valueSerializer.serialize("hello"));

        List<String> range = template.opsForList().range("mylist", 0, -1);
        System.out.println(range);
        List<String> expect = Lists.newArrayList("hello", "world");
        Assert.assertTrue(expect.containsAll(range) && range.containsAll(expect));
    }
}
