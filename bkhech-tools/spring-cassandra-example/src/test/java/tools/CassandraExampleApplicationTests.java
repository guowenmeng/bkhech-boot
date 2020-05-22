package tools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import tools.cassandra.Person;

import java.util.List;

@SpringBootTest
public class CassandraExampleApplicationTests {

	@Autowired
	private CassandraTemplate cassandraTemplate;

	@Test
	public void cassandraTest() {
		String table = "info";

		Person person = new Person("333", "guo", 3);
		Person insert = cassandraTemplate.insert(person);
		System.out.println(insert);
		Query query = Query.query(Criteria.where("id").is(person.getId()));
		List<Person> personList = cassandraTemplate.select(query, Person.class);
//		List<Person> personList = cassandraTemplate.select("select * from info", Person.class);
		System.out.println(personList);

	}

}
