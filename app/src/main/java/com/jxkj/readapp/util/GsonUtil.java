package com.jxkj.readapp.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jxkj.readapp.bean.TestBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 将json 数据转化为bean实体类
 * @author Administrator
 *
 */
public class GsonUtil{
	public static String BeanToJson(Object object) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		//        addSerializationExclusionStrategy
//        addDeserializationExclusionStrategy().
		Gson gson = gsonBuilder.serializeNulls().excludeFieldsWithoutExposeAnnotation()
				.excludeFieldsWithModifiers(Modifier.PROTECTED).create();
		return gson.toJson(object);
	}
	public static <T> T json2Bean(String json, Class<T> clazz){
		Gson gson = new Gson();
		return gson.fromJson(json,clazz);
	}

	public static <T> T objectFromData(String str, Class<T> clazz) {
		return new Gson().fromJson(str, clazz);
	}

	public static TestBean objectFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);

			return new Gson().fromJson(jsonObject.getString(str), TestBean.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<TestBean> arrayBeanFromData(String str) {

		Type listType = new TypeToken<ArrayList<TestBean>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public static List<TestBean> arrayBeanFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<TestBean>>() {
			}.getType();

			return new Gson().fromJson(jsonObject.getString(str), listType);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ArrayList();


	}
}
