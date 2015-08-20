package io.bluerain.globr.web.extend;

import org.codehaus.jackson.map.JsonSerializer;

import java.io.IOException;

public class NullSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object value,
			org.codehaus.jackson.JsonGenerator jgen,
			org.codehaus.jackson.map.SerializerProvider provider)
			throws IOException, org.codehaus.jackson.JsonProcessingException {
		jgen.writeString("");
	}

}
