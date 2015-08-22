package io.bluerain.globr.web.interceptor;

import io.bluerain.globr.web.core.RepBuilder;
import io.bluerain.converter.JsonUtil;
import io.bluerain.globr.enties.bean.Result;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BaseExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        e.printStackTrace();
        Result er = null;
        try {
            er = JsonUtil.readJson(e.getMessage(), Result.class);
            if (er == null)
                return RepBuilder.buildException(e);
        } catch (Exception e1) {
            return RepBuilder.buildException(e1);
        }
        return RepBuilder.build(er);
    }
}
