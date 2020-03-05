package fun.imcoder.cloud.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseMapper<M> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<M> {
    /**
     * 自定义查询
     * 需要自己写sql
     *
     * @param m
     * @return
     */
    List<M> customList(M m);

    /**
     * 自定义查询 Map 参数
     * 需要自己写sql
     *
     * @param param
     * @return
     */
    List<M> customList(Map<String, Object> param);

    /**
     * 自定义分页查询
     * 需要自己写sql
     *
     * @param m
     * @return
     */
    List<M> customPage(Page page, @Param(value = "params") M m);

    /**
     * 自定义批量插入
     *
     * @param list
     * @return
     */
    Boolean insertBatch(List<M> list);
}
