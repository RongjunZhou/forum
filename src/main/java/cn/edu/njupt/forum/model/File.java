package cn.edu.njupt.forum.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Data
@NoArgsConstructor
public class File {
    @TableId(type = IdType.INPUT)
    private String key;
    private Blob content;

    public File(String key, MultipartFile content) throws IOException, SQLException {
        this.key = key;
        this.content = new SerialBlob(content.getBytes());
    }
}
