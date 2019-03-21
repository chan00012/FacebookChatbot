package com.amdocs.facebookchatbot.dto;

import java.util.List;

import com.amdocs.facebookchatbot.enums.ObjectType;
import com.amdocs.facebookchatbot.model.Entry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import util.Utils;

@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class ClientRequest {

	ObjectType object;
	List<Entry> entry;
	
	
	@Override
	public String toString() {
		return Utils.toJson(this);
	}
	
	
}
