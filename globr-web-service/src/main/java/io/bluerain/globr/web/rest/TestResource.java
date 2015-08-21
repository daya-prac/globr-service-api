package io.bluerain.globr.web.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import io.bluerain.globr.web.core.RepBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by foredawn on 15-8-21.
 * 测试API资源.
 */

@Api("测试API")
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/test")
public class TestResource {

    @Path("/{id}")
    @GET
    @ApiOperation(value = "测试获取传递的数字")
    public Response index(@PathParam("id") Long id) {
        return RepBuilder.SUCCESS(id);
    }
}
