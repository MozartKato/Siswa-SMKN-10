package com.mesoraios.siswasmkn10.model;

import com.google.gson.annotations.SerializedName;

public class UNResponse{

	@SerializedName("hasilUN")
	private HasilUN hasilUN;

	public void setHasilUN(HasilUN hasilUN){
		this.hasilUN = hasilUN;
	}

	public HasilUN getHasilUN(){
		return hasilUN;
	}

	@Override
 	public String toString(){
		return 
			"UNResponse{" + 
			"hasilUN = '" + hasilUN + '\'' + 
			"}";
		}
}