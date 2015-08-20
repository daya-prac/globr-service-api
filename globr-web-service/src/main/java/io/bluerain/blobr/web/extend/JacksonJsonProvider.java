package io.bluerain.blobr.web.extend;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.scala.DefaultScalaModule;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonJsonProvider extends JacksonJaxbJsonProvider {
	public JacksonJsonProvider() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new DefaultScalaModule());
		// mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		// mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		super.setMapper(mapper);
	}
}
