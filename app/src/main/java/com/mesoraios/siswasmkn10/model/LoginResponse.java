package com.mesoraios.siswasmkn10.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("hasil:")
	private String hasil;

	public String getHasil(){
		return hasil;
	}
}