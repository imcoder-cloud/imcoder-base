package fun.imcoder.cloud.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class BaseModel {
    private String createTime;
    private String modifyTime;
    @TableField(exist = false)
    private String sort;
}
