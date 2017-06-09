package com.hk.learn.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.hk.learn.dataprovider.TestData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * Load and optional rendering according to data object whether null. Currentlly
 * support load from
 * <ul>
 * <li>Classpath
 * <li>File
 * <li>String
 * <li>Custom Loader
 * </ul>
 * 
 * @author 
 * @see TemplateRendererBuilder
 */

public class TemplateRenderer {
	
	private Configuration configuration;

	private static TemplateRenderer instance;

	protected TemplateRenderer() {

	}

	public TemplateRenderer(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public String getSource(String path) {
		try {
			Template template = configuration.getTemplate(path);

			return template.toString();
		} catch (IOException e) {
			throw new DataPrepareException(e);
		}
	}

	/**
	 * Generally data would be a Map object,but TempateRenderer will handle it
	 * as normal Object via ONGL.
	 * 
	 * @param path
	 * @param data
	 * @return
	 */
	public String render(String path, Object data) {
		try {

			Template template = configuration.getTemplate(path);
			StringWriter sw = new StringWriter();
			template.process(data, sw);
			sw.flush();
			return sw.toString();
		} catch (IOException | TemplateException e) {
			throw new DataPrepareException(e);
		}
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public Object[][] source2JsonArrayData(String path) {
		String source = getSource(path);
		JsonElement gson = new JsonParser().parse(source);
		if (gson.isJsonArray()) {
			return source2JsonArrayData(path, new TypeToken<List<TestData>>() {
			});
		} else {
			return source2JsonArrayData(path, TestData.class);
		}
	}

	/**
	 * 
	 * @param path
	 * @param data
	 * @return
	 */
	public <T> Object[][] renderJsonArrayData(String path, Object data) {

		String source = getSource(path);
		JsonElement gson = new JsonParser().parse(source);
		if (gson.isJsonArray()) {
			return renderJsonArrayData(path, data,
					new TypeToken<List<TestData>>() {
					});
		} else {
			return renderJsonArrayData(path, data, TestData.class);
		}
	}

	public TestData source2Json(String path) {
		return source2Json(path, TestData.class);
	}

	/**
	 * 
	 * @param path
	 * @param type
	 * @return
	 */
	public <T> T source2Json(String path, Class<T> type) {
		String source = getSource(path);

		Gson gson = new Gson();
		T ds = gson.fromJson(source, type);

		return ds;

	}

	/**
	 * 
	 * @param path
	 * @param type
	 * @return
	 */
	public <T> List<T> source2JsonArray(String path, TypeToken<T> typeToken) {
		String source = getSource(path);

		Gson gson = new Gson();
		List<T> ds = gson.fromJson(source, typeToken.getType());

		return ds;

	}

	/**
	 * 
	 * @param path
	 * @param type
	 * @return
	 */
	public <T> Object[][] source2JsonArrayData(String path,
			TypeToken<T> typeToken) {
		List<T> datas = source2JsonArray(path, typeToken);
		Object[][] ps = new Object[datas.size()][1];
		for (int i = 0; i < datas.size(); i++) {
			Object json = datas.get(i);
			ps[i] = new Object[] { json };
		}
		return ps;
	}

	public <T> Object[][] source2JsonArrayData(String path, Class<T> type) {
		T data = source2Json(path, type);
		Object[][] ps = new Object[][] { { data } };
		return ps;
	}

	/**
	 * 
	 * @param path
	 * @param data
	 * @param type
	 * @return
	 */
	public <T> T renderJson(String path, Object data, Class<T> type) {
		String source = render(path, data);

		Gson gson = new Gson();
		T ds = gson.fromJson(source, type);

		return ds;

	}

	/**
	 * 
	 * @param path
	 * @param data
	 * @param type
	 * @return
	 */
	public <T> List<T> renderJsonArray(String path, Object data,
			TypeToken<T> typeToken) {

		String source = render(path, data);
		Gson gson = new Gson();

		List<T> ds = gson.fromJson(source, typeToken.getType());

		return ds;

	}

	public <T> Object[][] renderJsonArrayData(String path, Object data,
			Class<T> type) {
		T d = renderJson(path, data, type);

		return new Object[][] { { d } };
	}

	public <T> Object[][] renderJsonArrayData(String path, Object data,
			TypeToken<T> typeToken) {
		List<T> datas = renderJsonArray(path, data, typeToken);
		Object[][] ps = new Object[datas.size()][1];
		for (int i = 0; i < datas.size(); i++) {
			Object json = datas.get(i);
			ps[i] = new Object[] { json };
		}
		return ps;
	}

	/**
	 * mostly called on BeforeSuite
	 * 
	 * @param render
	 */
	public static void provideInstance(TemplateRenderer render) {
		instance = render;
	}

	public static synchronized TemplateRenderer getInstance() {
		if (instance == null) {
			instance = TemplateRendererBuilder.createDefaultTemplateRenderer();
		}
		return instance;
	}

}
