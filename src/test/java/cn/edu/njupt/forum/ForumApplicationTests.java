package cn.edu.njupt.forum;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootTest
class ForumApplicationTests {

    @Test
    void contextLoads() throws IOException {
        File file = new File(ResourceUtils.getURL("classpath:").getPath() + "1.txt");
        file.createNewFile();
        file.setWritable(true);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("111");
    }

}
