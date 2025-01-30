package com.mesoraios.siswasmkn10.model;

import com.google.gson.annotations.SerializedName;

public class HasilUN{

	@SerializedName("indo")
	private int indo;

	@SerializedName("NISN")
	private int nISN;

	@SerializedName("english")
	private int english;

	@SerializedName("Math")
	private int math;

	@SerializedName("kejuruan")
	private int kejuruan;

	@SerializedName("Name")
	private String name;

	public void setIndo(int indo){
		this.indo = indo;
	}

	public int getIndo(){
		return indo;
	}

	public void setNISN(int nISN){
		this.nISN = nISN;
	}

	public int getNISN(){
		return nISN;
	}

	public void setEnglish(int english){
		this.english = english;
	}

	public int getEnglish(){
		return english;
	}

	public void setMath(int math){
		this.math = math;
	}

	public int getMath(){
		return math;
	}

	public void setKejuruan(int kejuruan){
		this.kejuruan = kejuruan;
	}

	public int getKejuruan(){
		return kejuruan;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"HasilUN{" + 
			"indo = '" + indo + '\'' + 
			",nISN = '" + nISN + '\'' + 
			",english = '" + english + '\'' + 
			",math = '" + math + '\'' + 
			",kejuruan = '" + kejuruan + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}