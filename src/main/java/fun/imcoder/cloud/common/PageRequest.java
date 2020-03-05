package fun.imcoder.cloud.common;

import lombok.Data;

@Data
public class PageRequest<T> {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private T params;

    public PageRequest() {
    }

    public PageRequest(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

}
