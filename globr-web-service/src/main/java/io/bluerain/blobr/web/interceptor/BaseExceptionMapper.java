package io.bluerain.blobr.web.interceptor;

import com.essential.core.Result;
import com.essential.utils.ConvertUtil;
import com.essential.web.utils.RepBuilder;

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
			er = ConvertUtil.readJson(e.getMessage(), Result.class);
			if(er == null)
				return RepBuilder.buildException(e);
		} catch (Exception e1) {
			return RepBuilder.buildException(e1);
		}
		return RepBuilder.build(er);
	}
}
