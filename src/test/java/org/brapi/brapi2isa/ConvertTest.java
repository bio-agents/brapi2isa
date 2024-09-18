package org.brapi.brapi2isa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.brapi.client.ApiClient;
import org.brapi.client.ApiException;
import org.brapi.client.api.GermplasmApi;
import org.brapi.client.api.StudyApi;
import org.brapi.client.model.StudyResult;
import org.genesys.isa.model.v1.InvestigationSchema;
import org.genesys.isa.model.v1.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConvertTest {

	private GermplasmApi germplasmApi;
	private StudyApi studyApi;

	@Before
	public void setup() {
		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath("https://pippa.psb.ugent.be/pippa_experiments");
		apiClient.setDebugging(true);
		
		this.germplasmApi = new GermplasmApi(apiClient);
		this.studyApi = new StudyApi(apiClient);
	}

	@After
	public void teardown() {
		this.germplasmApi = null;
	}

	@Test
	public void convert1() throws JsonProcessingException, ApiException {
		
		InvestigationSchema investigation=new InvestigationSchema();
		
		investigation.setDescription("This is the description");
		investigation.setIdentifier(UUID.randomUUID().toString());
		investigation.setPeople(people());
		
		ObjectMapper mapper=new ObjectMapper();
		System.err.println(mapper.writeValueAsString(investigation));
		
		StudyResult study = studyApi.studyDetails("VIB_study___52");
		System.err.println(mapper.writeValueAsString(study));
	}

	private List<Person> people() {
		ArrayList<Person> people = new ArrayList<Person>();
		Person person = new Person();
		person.setFirstName("Matija");
		person.setLastName("Obreza");
		person.setEmail("matija.obreza@croptrust.org");
		people.add(person);
		return people;
	}

}