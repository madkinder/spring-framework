/*
 * Copyright 2002-2008 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.springframework.conversation;

import org.springframework.conversation.annotation.EndConversation;
import org.springframework.conversation.manager.ConversationManager;

/**
 * A conversation is ended in different ways:
 * <ul>
 * <li>explicitly ended using the {@link EndConversation} annotation</li>
 * <li>explicitly ended using the
 * {@link ConversationManager#endCurrentConversation(ConversationEndingType)}
 * method</li>
 * <li>implicitly ended while creating a new conversation</li>
 * <li>implicitly ended if timed out</li>
 * </ul>
 * This enumeration qualifies the ending type to be passed on to the
 * {@link ConversationListener}'s ending method.
 * 
 * @author Micha Kiener
 * @since 3.1
 */
public enum ConversationEndingType {
	/** Explicitly ended using a success path. */
	SUCCESS,

	/** Explicitly ended using a cancel path. */
	CANCEL,

	/**
	 * Explicitly ended using a success path, but an exception occurred while
	 * committing.
	 */
	FAILURE_SUCCESS,

	/**
	 * Explicitly ended using a cancel path, but an exception occurred while
	 * canceling.
	 */
	FAILURE_CANCEL,

	/** Implicitly ended through a time out of the conversation object. */
	TIMED_OUT,

	/**
	 * Implicitly ended while creating a new conversation and ending the curent
	 * one.
	 */
	TRANSCRIBED;
}
