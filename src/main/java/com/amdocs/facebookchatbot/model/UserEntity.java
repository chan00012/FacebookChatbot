package com.amdocs.facebookchatbot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import util.Utils;

@Data
@JsonInclude(Include.NON_NULL)
public class UserEntity {
	
	
	private Long id;
	
	@Override
	public String toString() {
		return Utils.toJson(this);
	}

}
