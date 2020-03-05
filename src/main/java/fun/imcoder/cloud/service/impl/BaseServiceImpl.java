package fun.imcoder.cloud.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.imcoder.cloud.common.PageRequest;
import fun.imcoder.cloud.service.BaseService;
import fun.imcoder.cloud.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T extends BaseMapper<M>, M> extends ServiceImpl<T, M> implements BaseService<M> {

    @Override
    public List<M> customList(M m) {
        return this.baseMapper.customList(m);
    }

    @Override
    public List<M> customList(Map<String, Object> param) {
        return this.baseMapper.customList(param);
    }

    @Override
    public IPage<M> customPage(PageRequest<M> pageRequest) {
        Page page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<M> list = this.baseMapper.customPage(page, pageRequest.getParams());
        return page.setRecords(list);
    }

    @Override
    public Boolean insertBatch(List<M> list) {
        return this.baseMapper.insertBatch(list);
    }

}
