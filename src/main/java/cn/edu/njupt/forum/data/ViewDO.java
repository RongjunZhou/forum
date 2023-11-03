package cn.edu.njupt.forum.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewDO {
    private Integer postId;
    private String title;
    private LocalDateTime time;
}
