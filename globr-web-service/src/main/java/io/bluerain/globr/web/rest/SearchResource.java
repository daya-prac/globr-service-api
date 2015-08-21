package io.bluerain.globr.web.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
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
    public Response index(@QueryParam("keyword") String keyword) {
        return RepBuilder.build(Search.by(keyword));
    }
}
