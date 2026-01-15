package org.example.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.nacos.shaded.com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Result;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    /**
     * BlockExceptionHandler是Sentinel基础的处理类，只要重写这个类方法，拦截器自动处理网页中的流控配置
     */
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s, BlockException e) throws Exception {

        // 设置响应头 状态码以及返回格式
        httpServletResponse.setStatus(429);
        httpServletResponse.setContentType("application/json;charset=UTF-8");

        // 获取输出流对象
        PrintWriter out = httpServletResponse.getWriter();

        // 设置响应结果并转换为json
        Result<Object> tooManyRequest = Result.error(500, s + " 被Sentinel限制了，原因：" + e.getClass());

        // 输出对象输出json到网页
        out.write(new Gson().toJson(tooManyRequest));
        out.flush();
        out.close();
    }
}
