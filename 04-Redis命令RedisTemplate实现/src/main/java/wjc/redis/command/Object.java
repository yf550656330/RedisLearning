package wjc.redis.command;

import org.junit.Test;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-07 07:30
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Object extends Command<String, String> {
    @Test
    public void test() {
        template.opsForList().leftPush("mylist", "Hello World");

//        template.getConnectionFactory().getConnection().getConnection
    }
}
