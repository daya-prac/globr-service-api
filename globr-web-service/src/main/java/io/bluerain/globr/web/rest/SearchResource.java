package io.bluerain.globr.web.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import io.bluerain.globr.core.Search;
import io.bluerain.globr.web.core.RepBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by foredawn on 15-8-22.
 * 搜索API资源.
 */

@Api("搜索API")
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/search")
public class SearchResource {

    @GET
    @ApiOperation(value = "根据关键字获取搜索结果")
    public Response index(@ApiParam(value = "关键字", defaultValue = "呵呵哒") @QueryParam("keyword") String keyword,
                          @ApiParam(value = "页数", defaultValue = "1") @QueryParam("pagNum") Integer pagNUm) {
        if (pagNUm == null)
            pagNUm = 0;
        return RepBuilder.build(Search.by(keyword, pagNUm));
    }
}
