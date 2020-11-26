package cn.bdqn;

import cn.bdqn.web.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest(classes = FirstSpringBootApplication.class)
public class ContextLoads {

    private static Logger log= LoggerFactory.getLogger(ContextLoads.class);

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    void TestLog(){
        log.info("=============================================");
    }


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testRedis(){
        // string
        stringRedisTemplate.opsForValue().set("string","redis-string");
        String str1 = stringRedisTemplate.opsForValue().get("string");
        System.out.println("string:"+str1);
        // list
        stringRedisTemplate.opsForList().leftPushAll("list","redis-list1","redis-list1");
        List<String> list = stringRedisTemplate.opsForList().range("list", 0, 1);
        System.out.println("list"+list.toString());

        // hash
        stringRedisTemplate.opsForHash().put("hash","h1","redis-hash");
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries("hash");
        System.out.println("map:"+map);
        // set
        stringRedisTemplate.opsForSet().add("set","redis-hash1","redis-hash2");
        Set<String> set = stringRedisTemplate.opsForSet().members("set");
        System.out.println("set:"+set);
        // zset
        BoundZSetOperations zSetOperations = redisTemplate.boundZSetOps("zset");
        zSetOperations.add("v1",80.00);
        zSetOperations.add("v2",60.00);
        System.out.println("zet"+zSetOperations.range(0,-1));
    }

    @Test
    void testRedis2(){
        redisTemplate.opsForValue().set("book","book");
        Object book1 = redisTemplate.opsForValue().get("book");
        System.out.println(book1);
    }

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void testReids3(){
        redisUtil.set("test-RedisUtil","没毛病！");
        String o = redisUtil.get("test-RedisUtil").toString();
        redisUtil.del("a");
        System.out.println(o);
    }

    @Test
    void testRedis4(){
        redisUtil.set("book","book");
        System.out.println(redisUtil.get("book"));
    }
}
