package cn.edu.njupt.forum;

import cn.edu.njupt.forum.util.EncryptUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ForumApplicationTests {

    @Test
    void contextLoads() throws IOException {
        System.out.println(EncryptUtil.encode("123"));
        System.out.println(EncryptUtil.decode("MTIz"));
    }

}
