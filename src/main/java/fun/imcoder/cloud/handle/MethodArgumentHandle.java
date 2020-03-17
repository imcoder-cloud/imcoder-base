package fun.imcoder.cloud.handle;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import fun.imcoder.cloud.annotation.ModelParam;
import fun.imcoder.cloud.common.PageRequest;
import fun.imcoder.cloud.enums.ModelParamType;
import fun.imcoder.cloud.utils.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Component
public class MethodArgumentHandle implements HandlerMethodArgumentResolver, HandlerInterceptor {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ModelParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        ModelParamType type = parameter.getParameterAnnotation(ModelParam.class).value();
        // 分页 get 请求
        if (type.equals(ModelParamType.PAGE)) {
            Map<String, String[]> map = webRequest.getParameterMap();
            Map<String, Object> params = new HashMap<>();
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                if ("pageNum".equals(entry.getKey()) || "pageSize".equals(entry.getKey())) {
                    continue;
                }

                params.put(entry.getKey().replace("params.", ""), entry.getValue().length > 1 ? entry.getValue() :
                        entry.getValue()[0]);
            }

            HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
            Class<?> modelClass = (Class) request.getAttribute("ModelClass");

            PageRequest pageRequest = new PageRequest();
            String current = webRequest.getParameter("pageNum");
            String size = webRequest.getParameter("pageSize");

            if (StringUtils.isNotBlank(current)) {
                pageRequest.setPageNum(Integer.parseInt(current));
            }

            if (StringUtils.isNotBlank(size)) {
                pageRequest.setPageSize(Integer.parseInt(size));
            }

            pageRequest.setParams(BeanUtils.mapToBean(params, modelClass));

            return pageRequest;

        }

        // 普通 get 请求
        String name = ModelFactory.getNameForParameter(parameter);
        Object attribute = (mavContainer.containsAttribute(name) ? mavContainer.getModel().get(name) :
                org.springframework.beans.BeanUtils.instantiateClass(parameter.getParameterType()));

        WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);
        if (binder.getTarget() != null) {
            if (!mavContainer.isBindingDisabled(name)) {
                MutablePropertyValues mpv = new MutablePropertyValues(webRequest.getParameterMap());
                binder.bind(mpv);
            }
        }

        return binder.convertIfNecessary(binder.getTarget(), parameter.getParameterType(), parameter);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        Class controllerClass = ((HandlerMethod) handler).getBeanType();
        Type genericSuperclass = controllerClass.getGenericSuperclass();
        String genericSuperclassName = genericSuperclass.getTypeName();
        if (!"java.lang.Object".equals(genericSuperclassName) && !"org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController".equals(genericSuperclassName)) {
            ParameterizedType pType = (ParameterizedType) genericSuperclass;
            Type[] arguments = pType.getActualTypeArguments();
            Class modelClass = (Class) arguments[0];
            request.setAttribute("ModelClass", modelClass);
        }
        return true;
    }

}
