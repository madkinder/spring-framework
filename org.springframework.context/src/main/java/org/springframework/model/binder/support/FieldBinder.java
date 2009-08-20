/*
 * Copyright 2004-2009 the original author or authors.
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
package org.springframework.model.binder.support;

import org.springframework.model.binder.BindingResult;

/**
 * Binder callback interface for binding a single field value.
 * @author Keith Donald
 * @since 3.0
 * @see AbstractBinder#createFieldBinder(Object)
 */
public interface FieldBinder {

	/**
	 * Bind a single field.
	 * @param fieldName the field name
	 * @param value the field value
	 * @return the binding result
	 */
	BindingResult bind(String fieldName, Object value);
}