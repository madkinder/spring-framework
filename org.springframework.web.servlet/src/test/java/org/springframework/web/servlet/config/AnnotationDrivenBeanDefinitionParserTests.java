/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.web.servlet.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerExceptionResolver;

/**
 * Test fixture for the configuration in mvc-config-annotation-driven.xml.
 * @author Rossen Stoyanchev
 */
public class AnnotationDrivenBeanDefinitionParserTests {

	private static GenericWebApplicationContext appContext;

	@BeforeClass
	public static void setup() {
		appContext = new GenericWebApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(appContext);
		reader.loadBeanDefinitions(new ClassPathResource("mvc-config-annotation-driven.xml",
				AnnotationDrivenBeanDefinitionParserTests.class));
		appContext.refresh();
	}

	@Test
	public void testMessageCodesResolver() {
		AnnotationMethodHandlerAdapter adapter = appContext.getBean(AnnotationMethodHandlerAdapter.class);
		assertNotNull(adapter);
		Object initializer = new DirectFieldAccessor(adapter).getPropertyValue("webBindingInitializer");
		assertNotNull(initializer);
		MessageCodesResolver resolver = ((ConfigurableWebBindingInitializer) initializer).getMessageCodesResolver();
		assertNotNull(resolver);
		assertEquals(TestMessageCodesResolver.class, resolver.getClass());
	}

	@Test
	public void testMessageConverters() {
		verifyMessageConverters(appContext.getBean(AnnotationMethodHandlerAdapter.class));
		verifyMessageConverters(appContext.getBean(AnnotationMethodHandlerExceptionResolver.class));
	}


	private void verifyMessageConverters(Object bean) {
		assertNotNull(bean);
		Object converters = new DirectFieldAccessor(bean).getPropertyValue("messageConverters");
		assertNotNull(converters);
		assertTrue(converters instanceof HttpMessageConverter<?>[]);
		assertEquals(2, ((HttpMessageConverter<?>[]) converters).length);
		assertTrue(((HttpMessageConverter<?>[]) converters)[0] instanceof StringHttpMessageConverter);
		assertTrue(((HttpMessageConverter<?>[]) converters)[1] instanceof ResourceHttpMessageConverter);
	}

	private static class TestMessageCodesResolver implements MessageCodesResolver {

		public String[] resolveMessageCodes(String errorCode, String objectName) {
			throw new IllegalStateException("Not expected to be invoked");
		}

		@SuppressWarnings("rawtypes")
		public String[] resolveMessageCodes(String errorCode, String objectName, String field, Class fieldType) {
			throw new IllegalStateException("Not expected to be invoked");
		}

	}

}
