package io.bluerain.blobr.web.core;

import javax.ws.rs.core.Response;

import com.essential.core.Result;

public class RepBuilder {

	public static Response build(Object entity) {
		return Response.ok(entity).build();
	}

	public static Response SUCCESS() {
		return Response.ok(Result.SUCCESS()).build();
	}

	public static Response SUCCESS(Object attachData) {
		return Response.ok(new Result(0, Result.SUCCESS, "", attachData))
				.build();
	}

	public static Response buildException(Exception e) {
		return ok(new Result(500, "Server Error.", e.getMessage()), 500);
	}

	private static Response ok(Object entity, int httpStatus) {
		return Response.ok(entity).status(httpStatus).build();
	}
}
