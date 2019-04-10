/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.a.g.ws;

import javax.inject.Inject;
import javax.jws.WebService;

import net.a.g.data.Person;
import net.a.g.data.PersonReply;
import net.a.g.jbpm.JBPMService;

@WebService(serviceName = "HelloWorldService", portName = "HelloWorld", name = "HelloWorld", endpointInterface = "net.a.g.ws.HelloWorldService", targetNamespace = "urn://helloworld/HelloWorld")
public class HelloWorldServiceImpl implements HelloWorldService {

	@Inject
	private JBPMService service;

	@Override
	public String sayHello() {
		return sayHelloToName("World!");
	}

	@Override
	public String sayHelloToName(final String name) {
		return sayHelloToPerson(new Person(name));
	}

	@Override
	public String sayHelloToPerson(final Person person) {

		service.sayHelloToPerson(person);

		return "Hello " + person.getName();
	}

	@Override
	public PersonReply replyHelloToPerson(Person person) {
		return new PersonReply(sayHelloToPerson(person));
	}

}
