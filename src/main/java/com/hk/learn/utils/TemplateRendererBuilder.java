package com.hk.learn.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

//import com.ctrip.cap.exception.DataPrepareException;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

/**
 * More config item will be exposed on demand
 * 
 * <pre class="code">
 * 
 * TemplateRenderer rr = TemplateRendererBuilder.create()
 *         .addClassPath(TemplateRendererTest.class)
 *         .addFileLocation(new File(System.getProperty("user.dir"))
 *         .addFileLocation("c:\\data")
 * .build();
 * 
 * <pre/>
 * 
 * @author 
 *
 */

public class TemplateRendererBuilder {
	private MultiTemplateLoader multiTemplateLoader;
	private Set<TemplateLoader> loaders = new HashSet<TemplateLoader>();

	private TemplateRendererBuilder() {

	}

	public static TemplateRendererBuilder create() {
		return new TemplateRendererBuilder();
	}

	public static TemplateRenderer createDefaultTemplateRenderer()  {
		Configuration config = new Configuration(Configuration.VERSION_2_3_21);
		config.setDefaultEncoding("UTF-8");
		MultiTemplateLoader multiTemplateLoader = null;
		try {
			multiTemplateLoader = new MultiTemplateLoader(
					new TemplateLoader[] { new ClassTemplateLoader(
							TemplateRenderer.class, "/") ,new FileTemplateLoader()});
		} catch (IOException e) {
			throw new DataPrepareException("Template Render", e);
		}
		config.setTemplateLoader(multiTemplateLoader);
		return new TemplateRenderer(config);
	}

	public TemplateRenderer build() {
		Configuration config = new Configuration(Configuration.VERSION_2_3_21);
		config.setDefaultEncoding("UTF-8");
		multiTemplateLoader = new MultiTemplateLoader(
				loaders.toArray(new TemplateLoader[loaders.size()]));
		config.setTemplateLoader(multiTemplateLoader);
		return new TemplateRenderer(config);
	}

	public TemplateRendererBuilder addFileLocation(String path) {
		try {
			FileTemplateLoader fl = new FileTemplateLoader(new File(path));
			loaders.add(fl);
		} catch (IOException e) {
			throw new DataPrepareException(e);
		}
		return this;
	}

	public TemplateRendererBuilder addFileLocation(File path) {
		try {
			FileTemplateLoader ftl = new FileTemplateLoader(path);
			loaders.add(ftl);
		} catch (IOException e) {
			throw new DataPrepareException(e);
		}
		return this;
	}

	/**
	 * Default is root path for current class loader
	 * 
	 * @param cl
	 * @return
	 */
	public TemplateRendererBuilder addClassPath(Class<?> cl) {
		ClassTemplateLoader ctl = new ClassTemplateLoader(cl, "/");
		loaders.add(ctl);
		return this;
	}

	public TemplateRendererBuilder addClassPath(Class<?> cl, String path) {
		ClassTemplateLoader ctl = new ClassTemplateLoader(cl, path);
		loaders.add(ctl);
		return this;
	}
}
