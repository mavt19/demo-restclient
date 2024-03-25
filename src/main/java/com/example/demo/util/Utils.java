package com.example.demo.util;

import com.google.gson.Gson;

public class Utils {

	
	public static String ObjectToJsonString(Object object) {
			return new Gson().toJson(object);
	}
}
