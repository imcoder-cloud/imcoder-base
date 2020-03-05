package fun.imcoder.cloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.imcoder.cloud.common.PageRequest;

import java.util.List;
import java.util.Map;

public interface BaseService<M> extends IService<M> {

    /**
     * 自定义查询 实体 M
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
     * @param pageRequest
     * @return
     */
    IPage<M> customPage(PageRequest<M> pageRequest);

    /**
     * 自定义批量插入
     *
     * @param list
     * @return
     */
    Boolean insertBatch(List<M> list);

}
