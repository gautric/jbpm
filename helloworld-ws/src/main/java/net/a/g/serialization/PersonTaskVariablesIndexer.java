package net.a.g.serialization;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.services.task.audit.impl.model.TaskVariableImpl;
import org.kie.internal.task.api.TaskVariable;
import org.kie.internal.task.api.TaskVariableIndexer;

import net.a.g.data.Person;

public class PersonTaskVariablesIndexer
		implements TaskVariableIndexer {

	@Override
	public boolean accept(Object variable) {
		if (variable instanceof Person) {
			return true;
		}

		return false;
	}

	@Override
	public List<TaskVariable> index(String name, Object variable) {
		Person person = (Person) variable;
		List<TaskVariable> indexed = new ArrayList<TaskVariable>();

		TaskVariableImpl personNameVar = new TaskVariableImpl();
		personNameVar.setName("person.name");
		personNameVar.setValue(person.getName());

		indexed.add(personNameVar);

		TaskVariableImpl personAgeVar = new TaskVariableImpl();
		personAgeVar.setName("person.age");
		personAgeVar.setValue(person.getAge() + "");

		indexed.add(personAgeVar);

		return indexed;
	}
}