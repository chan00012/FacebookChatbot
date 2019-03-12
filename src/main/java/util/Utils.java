package util;

import java.io.IOException;
import java.util.Random;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Utils {
	
	private Utils() {
		
	}
	
//	public static String convert(Object o) {
//		Gson gson = new Gson();
//		return gson.toJson(o);
//	}
	
	public static String toJson(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();

		String jsonFormat = "";
		try {
			jsonFormat = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonFormat;
	}
	
	public static boolean isJsonType(String contentType) {
		return (MediaType.APPLICATION_JSON_VALUE.equals(contentType)) ? true : false;
	}
	
	public static boolean isXmlType(String contentType) {
		return (MediaType.APPLICATION_XML_VALUE.equals(contentType)) ? true : false;
	}
	
	public static Object toDto(String string) {
		ObjectMapper mapper = new ObjectMapper();
		Object o = null;
		try {
			o = mapper.readValue(string, Object.class);
			return o;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public static long delayReply() {
		Random rand = new Random();
		long delay = (long) rand.nextInt(2000);
		
		return delay;
	}
}
