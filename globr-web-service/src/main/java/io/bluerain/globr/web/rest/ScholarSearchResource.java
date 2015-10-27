package io.bluerain.globr.web.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import io.bluerain.globr.core.ScholarSearch;
import io.bluerain.globr.core.Search;
import io.bluerain.globr.web.core.ConfigInfo;
import io.bluerain.globr.web.core.RepBuilder;
import io.bluerain.proxy.Socks5;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by foredawn on 15-8-22.
 * 学术搜索API资源.
 */

@Api("学术搜索API")
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/search/scholar")
public class ScholarSearchResource {

    @GET
    @ApiOperation(value = "根据关键字获取搜索结果")
    public Response index(@ApiParam(value = "关键字", defaultValue = "呵呵") @QueryParam("keyword") String keyword,
                          @ApiParam(value = "页码", defaultValue = "1") @QueryParam("pagNum") Integer pagNum) {
        if (pagNum == null)
            pagNum = 0;
        return RepBuilder.build(ScholarSearch.by(keyword, pagNum));
    }
}
