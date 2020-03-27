package fun.imcoder.cloud.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.imcoder.cloud.annotation.ModelParam;
import fun.imcoder.cloud.common.PageRequest;
import fun.imcoder.cloud.common.ResponseData;
import fun.imcoder.cloud.enums.ModelParamType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BaseController<M extends BaseModel, S extends BaseService> {

    @Autowired
    public S service;

    @GetMapping
    @ApiOperation("查询信息")
    public ResponseData<List<M>> list(@ModelParam M m) {
        QueryWrapper<M> queryWrapper = new QueryWrapper<>(m);
        setOrder(m, queryWrapper);
        return ResponseData.success(service.list(queryWrapper));
    }

    @GetMapping("/list")
    @ApiOperation("自定义查询信息")
    public ResponseData<List<M>> customList(@ModelParam M m) {
        return ResponseData.success(service.customList(m));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询信息")
    public ResponseData<M> getById(@PathVariable Integer id) {
        return ResponseData.success((M) service.getById(id));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询信息")
    public ResponseData<IPage<M>> page(@ModelParam(ModelParamType.PAGE) PageRequest<M> pageRequest) {
        Page<M> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        M m = pageRequest.getParams();
        QueryWrapper<M> queryWrapper = new QueryWrapper<>(m);
        setOrder(m, queryWrapper);
        IPage rtn = service.page(page, queryWrapper);
        return ResponseData.success(rtn);
    }

    /**
     * 自定义分页
     *
     * @param pageRequest
     * @return
     */
    @GetMapping("/page-plus")
    @ApiOperation("自定义分页查询信息")
    public ResponseData<IPage<M>> pagePlus(@ModelParam(ModelParamType.PAGE) PageRequest<M> pageRequest) {
        IPage rtn = service.customPage(pageRequest);
        return ResponseData.success(rtn);
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseData<M> add(@RequestBody M m) {
        service.save(m);
        return ResponseData.success(m);
    }

    @PutMapping
    @ApiOperation("修改")
    public ResponseData<M> update(@RequestBody M m) {
        service.updateById(m);
        return ResponseData.success(m);
    }

    @PostMapping("/batch")
    @ApiOperation("批量新增")
    public ResponseData<List<M>> addBatch(@RequestBody List<M> list) {
        service.saveBatch(list);
        return ResponseData.success(list);
    }

    @PutMapping("/batch")
    @ApiOperation("批量修改")
    public ResponseData<List<M>> updateBatch(@RequestBody List<M> list) {
        service.updateBatchById(list);
        return ResponseData.success(list);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除信息")
    public ResponseData<Boolean> deleteById(@PathVariable Serializable id) {
        return ResponseData.success(service.removeById(id));
    }

    @DeleteMapping
    @ApiOperation("多条件删除信息")
    public ResponseData<Boolean> deleteByModel(@ModelParam M m) {
        QueryWrapper<M> queryWrapper = new QueryWrapper<>(m);
        return ResponseData.success(service.remove(queryWrapper));
    }

    /**
     * 自定义批量插入
     *
     * @param list
     * @return
     */
    @PostMapping("/batch-plus")
    @ApiOperation("自定义批量新增")
    public ResponseData<List<M>> insertBatch(@RequestBody List<M> list) {
        service.insertBatch(list);
        return ResponseData.success(list);
    }

    @DeleteMapping("/batch")
    @ApiOperation("根据多个id批量删除")
    public ResponseData<Boolean> deleteByIds(@RequestParam String ids) {
        List<Integer> list = Arrays.stream(ids.split(",")).mapToInt(s -> Integer.parseInt(s)).boxed().collect(Collectors.toList());
        return ResponseData.success(service.removeByIds(list));
    }

    private void setOrder(M m, QueryWrapper queryWrapper) {
        if (m.getOrder() != null) {
            if ("asc".equals(m.getOrderType())) {
                queryWrapper.orderByAsc(m.getOrder());
            } else {
                queryWrapper.orderByDesc(m.getOrder());
            }
        }
        queryWrapper.orderByDesc("create_time");
    }

}
