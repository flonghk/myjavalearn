package com.hk.learn.api;

import java.net.URI;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.hk.learn.utils.TemplateRenderer;
import com.hk.learn.utils.TemplateRendererBuilder;

/**
 * Currently build on Http 1.1 ,API test has many types,but Http stand in the
 * breach
 * 
 * relieves you from having to deal with connection management and resource
 * deallocation.
 * 
 * This Class doesn't hold good for all cases
 * 
 * @author 
 *
 */

public class APITest {

	protected static APIDriver apiDriver;

	protected APIResponse execute(APIRequest request) {
		long current = System.currentTimeMillis();
		URI uri = request.getServiceURI();
		String requestType = uri.getScheme() + "://" + uri.getHost()
				+ uri.getPath();

		try {
			APIResponse response = apiDriver.execute(request);
			return response;
		} finally {
			long cost = System.currentTimeMillis() - current;
		}

	}

	@BeforeSuite
	public void prepareHttpExecutor() {
		apiDriver = APIDriver.newInstance();
	}

	@AfterSuite
	public void releaseExecutor() {
	}

	@BeforeSuite
	protected void prepareDataCenter() {
		TemplateRenderer render = TemplateRendererBuilder
				.createDefaultTemplateRenderer();
		TemplateRenderer.provideInstance(render);
	}



}
