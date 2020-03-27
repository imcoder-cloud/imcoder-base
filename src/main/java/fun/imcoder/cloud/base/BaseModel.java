package fun.imcoder.cloud.base;

import com.baomidou.mybatisplus.annotation.TableField;

public class BaseModel {
    private String createTime;
    private String modifyTime;
    @TableField(exist = false)
    private String order;
    @TableField(exist = false)
    private String orderType = "asc";

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
