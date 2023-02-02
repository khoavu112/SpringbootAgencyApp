package com.agencyapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.agencyapp.dao.iam.AgenciesDao;
import com.agencyapp.dao.iam.User2Dao;
import com.agencyapp.dao.messaging.MessagesDao;
import com.agencyapp.dao.properties.PropertiesDao;
import com.agencyapp.dao.properties.PropertiesSubTypeDao;
import com.agencyapp.dao.properties.PropertiesTypeDao;
import com.agencyapp.dao.system.CountriesDao;
import com.agencyapp.dao.system.DistrictsDao;
import com.agencyapp.dao.system.ProvincesDao;
import com.agencyapp.dao.system.WardsDao;
import com.agencyapp.dto.ReponseUserAgencyDTO;
import com.agencyapp.dto.iam.AgenciesDTO;
import com.agencyapp.dto.messaging.ResponseMessagesDTO;
import com.agencyapp.dto.system.DistrictsDTO;
import com.agencyapp.dto.system.ProvincesDTO;
import com.agencyapp.dto.system.WardsDTO;
import com.agencyapp.model.iam.AgenciesEntity;
import com.agencyapp.model.iam.UsersEntity;
import com.agencyapp.model.messaging.MessagesEntity;
import com.agencyapp.model.properties.PropertiesEntity;
import com.agencyapp.model.properties.PropertiesSubtypesEntity;
import com.agencyapp.model.properties.PropertiesTypesEntity;
import com.agencyapp.model.system.CountriesEntity;
import com.agencyapp.model.system.DistrictsEntity;
import com.agencyapp.model.system.ProvincesEntity;
import com.agencyapp.model.system.WardsEntity;
import com.agencyapp.service.IParticipantsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AgencyAppApplicationTests {
	private SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	User2Dao userDao;
	@Autowired
	AgenciesDao agenciesDao;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private MessagesDao messagesDao;
	@Autowired
	private IParticipantsService iParticipantsService;
	@Autowired
	private PropertiesDao propertiesDao;
	@Autowired
	private CountriesDao countriesDao;
	@Autowired
	private ProvincesDao provincesDao;
	@Autowired
	private DistrictsDao districtsDao;
	@Autowired
	private WardsDao wardsDao;

	@Autowired
	private PropertiesTypeDao propertiesTypeDao;

	@Autowired
	private PropertiesSubTypeDao propertiesSubTypeDao;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

//	@Test
//	public void testReadAllUsers() {
//		List<UsersEntity> userList = userDao.findAll();
//		assertThat(userList).size().isGreaterThan(0);
//	}

	// Test Agencies
	@WithMockUser
	@Test
	public void testReadAllAgencies() throws Exception {
		MvcResult result = mockMvc.perform(get("/agencies").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAgencyById() throws Exception {
		MvcResult result = mockMvc.perform(get("/agencies/" + "2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAgencyByIdFail() throws Exception {
		MvcResult result = mockMvc.perform(get("/agencies/" + "1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		assertEquals(404, result.getResponse().getStatus());
	}

//	@Test
//	public void testServiceAllAgencies() {
//		List<AgenciesDTO> agenciesList = agenciesDao.findAllAgenciesDTO();
//		assertThat(agenciesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testServiceAllAgenciesEqual() {
//		List<AgenciesDTO> agenciesList = agenciesDao.findAllAgenciesDTO();
//		assertThat(agenciesList).size().isEqualByComparingTo(5);
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId1() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(1);
//		String nameAgencyDTO = agencies.getName();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(1);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getName());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId1Phone() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(1);
//		String nameAgencyDTO = agencies.getPhone();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(1);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getPhone());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId1Email() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(1);
//		String nameAgencyDTO = agencies.getEmail();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(1);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getEmail());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId1Web() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(1);
//		String nameAgencyDTO = agencies.getWebsite();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(1);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getWebsite());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId1Address1() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(1);
//		String nameAgencyDTO = agencies.getAddress1();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(1);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getAddress1());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId2() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(2);
//		String nameAgencyDTO = agencies.getName();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(2);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getName());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId2Phone() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(2);
//		String nameAgencyDTO = agencies.getPhone();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(2);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getPhone());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId2Email() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(2);
//		String nameAgencyDTO = agencies.getEmail();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(2);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getEmail());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId2Web() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(2);
//		String nameAgencyDTO = agencies.getWebsite();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(2);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getWebsite());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId2Address1() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(2);
//		String nameAgencyDTO = agencies.getAddress1();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(2);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getAddress1());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId3() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(3);
//		String nameAgencyDTO = agencies.getName();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(3);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getName());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId3Phone() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(3);
//		String nameAgencyDTO = agencies.getPhone();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(3);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getPhone());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId3Email() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(3);
//		String nameAgencyDTO = agencies.getEmail();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(3);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getEmail());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId3Web() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(3);
//		String nameAgencyDTO = agencies.getWebsite();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(3);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getWebsite());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId3Address1() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(3);
//		String nameAgencyDTO = agencies.getAddress1();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(3);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getAddress1());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId4() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(4);
//		String nameAgencyDTO = agencies.getName();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(4);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getName());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId4Phone() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(4);
//		String nameAgencyDTO = agencies.getPhone();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(4);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getPhone());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId4Web() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(4);
//		String nameAgencyDTO = agencies.getWebsite();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(4);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getWebsite());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId4Address1() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(4);
//		String nameAgencyDTO = agencies.getAddress1();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(4);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getAddress1());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId4Email() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(4);
//		String nameAgencyDTO = agencies.getEmail();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(4);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getEmail());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId5() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(5);
//		String nameAgencyDTO = agencies.getName();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(5);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getName());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId5Phone() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(5);
//		String nameAgencyDTO = agencies.getPhone();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(5);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getPhone());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId5Web() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(5);
//		String nameAgencyDTO = agencies.getWebsite();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(5);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getWebsite());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId5Address1() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(5);
//		String nameAgencyDTO = agencies.getAddress1();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(5);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getAddress1());
//	}
//
//	@Test
//	public void testServiceAllAgenciesDetailId5Email() {
//		AgenciesDTO agencies = agenciesDao.findAgenciesDTOById(5);
//		String nameAgencyDTO = agencies.getEmail();
//		AgenciesEntity agencyEntity = agenciesDao.findByid(5);
//		assertThat(nameAgencyDTO).isEqualTo(agencyEntity.getEmail());
//	}

	// Test Users
	@WithMockUser
	@Test
	public void testReadAllUsers2() throws Exception {
		MvcResult result = mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadUserById() throws Exception {
		MvcResult result = mockMvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadUserByIdFail() throws Exception {
		MvcResult result = mockMvc.perform(get("/users/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		assertEquals(404, result.getResponse().getStatus());
	}

//	@Test
//	public void testServiceAllUserDetailId1() {
//		UsersEntity users = userDao.findByid(1);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(1);
//		String username = users.getUsername();
//		assertThat(username).isEqualTo(userDTO.getUsername());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId1FirstName() {
//		UsersEntity users = userDao.findByid(1);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(1);
//		String username = users.getFirst_name();
//		assertThat(username).isEqualTo(userDTO.getFirst_name());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId1LastName() {
//		UsersEntity users = userDao.findByid(1);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(1);
//		String username = users.getLast_name();
//		assertThat(username).isEqualTo(userDTO.getLast_name());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId1JobTitle() {
//		UsersEntity users = userDao.findByid(1);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(1);
//		String username = users.getJob_title();
//		assertThat(username).isEqualTo(userDTO.getJob_title());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId1Email() {
//		UsersEntity users = userDao.findByid(1);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(1);
//		String username = users.getEmail();
//		assertThat(username).isEqualTo(userDTO.getEmail());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId2() {
//		UsersEntity users = userDao.findByid(2);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(2);
//		String username = users.getUsername();
//		assertThat(username).isEqualTo(userDTO.getUsername());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId2JobTitle() {
//		UsersEntity users = userDao.findByid(2);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(2);
//		String username = users.getJob_title();
//		assertThat(username).isEqualTo(userDTO.getJob_title());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId2FirstName() {
//		UsersEntity users = userDao.findByid(2);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(2);
//		String username = users.getFirst_name();
//		assertThat(username).isEqualTo(userDTO.getFirst_name());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId2LastName() {
//		UsersEntity users = userDao.findByid(2);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(2);
//		String username = users.getLast_name();
//		assertThat(username).isEqualTo(userDTO.getLast_name());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId2Email() {
//		UsersEntity users = userDao.findByid(2);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(2);
//		String username = users.getEmail();
//		assertThat(username).isEqualTo(userDTO.getEmail());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId3() {
//		UsersEntity users = userDao.findByid(3);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(3);
//		String username = users.getUsername();
//		assertThat(username).isEqualTo(userDTO.getUsername());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId3FirstName() {
//		UsersEntity users = userDao.findByid(3);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(3);
//		String username = users.getFirst_name();
//		assertThat(username).isEqualTo(userDTO.getFirst_name());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId3JobTitle() {
//		UsersEntity users = userDao.findByid(3);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(3);
//		String username = users.getJob_title();
//		assertThat(username).isEqualTo(userDTO.getJob_title());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId3LastName() {
//		UsersEntity users = userDao.findByid(3);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(3);
//		String username = users.getLast_name();
//		assertThat(username).isEqualTo(userDTO.getLast_name());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId3Email() {
//		UsersEntity users = userDao.findByid(3);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(3);
//		String username = users.getEmail();
//		assertThat(username).isEqualTo(userDTO.getEmail());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId4() {
//		UsersEntity users = userDao.findByid(4);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(4);
//		String username = users.getUsername();
//		assertThat(username).isEqualTo(userDTO.getUsername());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId4JobTitle() {
//		UsersEntity users = userDao.findByid(4);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(4);
//		String username = users.getJob_title();
//		assertThat(username).isEqualTo(userDTO.getJob_title());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId4LastName() {
//		UsersEntity users = userDao.findByid(4);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(4);
//		String username = users.getLast_name();
//		assertThat(username).isEqualTo(userDTO.getLast_name());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId4Email() {
//		UsersEntity users = userDao.findByid(4);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(4);
//		String username = users.getEmail();
//		assertThat(username).isEqualTo(userDTO.getEmail());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId4FirstName() {
//		UsersEntity users = userDao.findByid(4);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(4);
//		String username = users.getFirst_name();
//		assertThat(username).isEqualTo(userDTO.getFirst_name());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId5() {
//		UsersEntity users = userDao.findByid(5);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(5);
//		String username = users.getUsername();
//		assertThat(username).isEqualTo(userDTO.getUsername());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId5JobTitle() {
//		UsersEntity users = userDao.findByid(5);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(5);
//		String username = users.getJob_title();
//		assertThat(username).isEqualTo(userDTO.getJob_title());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId5LastName() {
//		UsersEntity users = userDao.findByid(5);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(5);
//		String username = users.getLast_name();
//		assertThat(username).isEqualTo(userDTO.getLast_name());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId5Email() {
//		UsersEntity users = userDao.findByid(5);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(5);
//		String username = users.getEmail();
//		assertThat(username).isEqualTo(userDTO.getEmail());
//	}
//
//	@Test
//	public void testServiceAllUserDetailId5FirstName() {
//		UsersEntity users = userDao.findByid(5);
//		ReponseUserAgencyDTO userDTO = userDao.findUserByIdDTO(5);
//		String username = users.getFirst_name();
//		assertThat(username).isEqualTo(userDTO.getFirst_name());
//	}

	@Test
	public void testServiceAllUserDetailId6() {
		UsersEntity users = userDao.findByid(6);
		String username = users.getUsername();
		assertThat(username).isEqualTo("thanhnguyen");
	}

	@Test
	public void testServiceAllUserDetailIdFirstName() {
		UsersEntity users = userDao.findByid(6);
		String username = users.getFirst_name();
		assertThat(username).isEqualTo("Thanh");
	}

	@Test
	public void testServiceAllUserDetailIdLastName() {
		UsersEntity users = userDao.findByid(6);
		String username = users.getLast_name();
		assertThat(username).isEqualTo("Nguyễn");
	}

	@Test
	public void testServiceAllUserDetailIdEmail() {
		UsersEntity users = userDao.findByid(6);
		String username = users.getEmail();
		assertThat(username).isEqualTo("thanhnguyen@gmail.com");
	}

	@Test
	public void testServiceAllUsers() {
		List<UsersEntity> usersList = userDao.findAll();
		assertThat(usersList).size().isGreaterThan(0);
	}

	@Test
	public void testServiceAllUsersEqual() {
		List<UsersEntity> usersList = userDao.findAll();
		assertThat(usersList).size().isEqualByComparingTo(8);
	}

//	@WithMockUser
//	@Test
//	public void testReadUserUsersInfo() throws Exception {
//		MvcResult result = mockMvc.perform(get("/usersInfo").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn();
//		assertEquals(200, result.getResponse().getStatus());
//	}
//	@WithMockUser
//	@Test
//	@Order(4)
//	public void testSaveUsers() throws Exception {
//		UsersEntity usersEntity = new UsersEntity();
//		usersEntity.setAgency_id(1);
//		usersEntity.setUsername("example");
//		usersEntity.setFirst_name("ex");
//		usersEntity.setLast_name("ample");
//		usersEntity.setEmail("example@gmail.com");
//		usersEntity.setPassword("12356");
//		usersEntity.setJob_title("test");
//		usersEntity.setUpdated(new Date());
//		usersEntity.setLast_login_date(new Date());
//		usersEntity.setCreated(new Date());
//		usersEntity.setPic_user("test");
//		String jsonRequest = mapper.writeValueAsString(usersEntity);
//		MvcResult result = mockMvc.perform(post("/register").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn();
//		assertEquals(200, result.getResponse().getStatus());
//	}
	
//	@WithMockUser
//	@Test
//	@Order(5)
//	public void testUpdateUsers() throws Exception {
//		int lastId = userDao.findLastIDForTest();
//		UsersEntity usersEntity = userDao.findByid(lastId);
//		usersEntity.setCreated(new Date());
//		String jsonRequest = mapper.writeValueAsString(usersEntity);
//		MvcResult result = mockMvc.perform(put("/users/update/"+lastId).content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn();
//		assertEquals(200, result.getResponse().getStatus());
//	}
//	
//	@WithMockUser
//	@Test
//	@Order(6)
//	public void testDeleteUsers() throws Exception {
//		int lastId = userDao.findLastIDForTest();
//		iParticipantsService.deleteParticipantsByUserID(lastId);
//		MvcResult result = mockMvc.perform(delete("/users/delete/"+lastId).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn();
//		assertEquals(200, result.getResponse().getStatus());
//	}

	// Test Countries
	@WithMockUser
	@Test
	public void testReadAllCountries() throws Exception {
		MvcResult result = mockMvc.perform(get("/countries").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void testServiceAllCountries() {
		List<CountriesEntity> countriesList = countriesDao.findAll();
		assertThat(countriesList).size().isGreaterThan(0);
	}

	@Test
	public void testServiceAllCountriesEqual() {
		List<CountriesEntity> countriesList = countriesDao.findAll();
		assertThat(countriesList).size().isEqualByComparingTo(4);
	}

	@Test
	public void testServiceAllCountriesEqualDetail1() {
		List<CountriesEntity> countriesList = countriesDao.findAll();
		String nameCountry = "Việt Nam";
		boolean flag = false;
		for (int i = 0; i < countriesList.size(); i++) {
			if (nameCountry.equalsIgnoreCase(countriesList.get(i).getName())) {
				flag = true;
			}
		}
		assertThat(flag);
	}

	@Test
	public void testServiceAllCountriesEqualDetail2() {
		List<CountriesEntity> countriesList = countriesDao.findAll();
		String nameCountry = "Taiwan";
		boolean flag = false;
		for (int i = 0; i < countriesList.size(); i++) {
			if (nameCountry.equalsIgnoreCase(countriesList.get(i).getName())) {
				flag = true;
			}
		}
		assertThat(flag);
	}

	@Test
	public void testServiceAllCountriesEqualDetail3() {
		List<CountriesEntity> countriesList = countriesDao.findAll();
		String nameCountry = "Singapore";
		boolean flag = false;
		for (int i = 0; i < countriesList.size(); i++) {
			if (nameCountry.equalsIgnoreCase(countriesList.get(i).getName())) {
				flag = true;
			}
		}
		assertThat(flag);
	}

	@Test
	public void testServiceAllCountriesEqualDetail4() {
		List<CountriesEntity> countriesList = countriesDao.findAll();
		String nameCountry = "China";
		boolean flag = false;
		for (int i = 0; i < countriesList.size(); i++) {
			if (nameCountry.equalsIgnoreCase(countriesList.get(i).getName())) {
				flag = true;
			}
		}
		assertThat(flag);
	}

	// Test Districts
	@WithMockUser
	@Test
	public void testReadAllDistricts() throws Exception {
		MvcResult result = mockMvc.perform(get("/districts").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void testServiceDetailDistricts1() {
		Optional<DistrictsEntity> districtsList = districtsDao.findById(1);
		assertThat(districtsList.get().getName()).isEqualTo("Quận 1");
	}

	@Test
	public void testServiceDetailDistricts2() {
		Optional<DistrictsEntity> districtsList = districtsDao.findById(2);
		assertThat(districtsList.get().getName()).isEqualTo("Quận 2");
	}

	@Test
	public void testServiceDetailDistricts3() {
		Optional<DistrictsEntity> districtsList = districtsDao.findById(3);
		assertThat(districtsList.get().getName()).isEqualTo("Quận 3");
	}

	@Test
	public void testServiceDetailDistricts4() {
		Optional<DistrictsEntity> districtsList = districtsDao.findById(4);
		assertThat(districtsList.get().getName()).isEqualTo("Quận 4");
	}

	@Test
	public void testServiceDetailDistricts5() {
		Optional<DistrictsEntity> districtsList = districtsDao.findById(5);
		assertThat(districtsList.get().getName()).isEqualTo("Quận 5");
	}

	@Test
	public void testServiceDetailDistricts6() {
		Optional<DistrictsEntity> districtsList = districtsDao.findById(6);
		assertThat(districtsList.get().getName()).isEqualTo("Quận 6");
	}

	@Test
	public void testServiceDetailDistricts7() {
		Optional<DistrictsEntity> districtsList = districtsDao.findById(7);
		assertThat(districtsList.get().getName()).isEqualTo("Quận 7");
	}

	@Test
	public void testServiceDetailDistricts8() {
		Optional<DistrictsEntity> districtsList = districtsDao.findById(8);
		assertThat(districtsList.get().getName()).isEqualTo("Quận 8");
	}

	@Test
	public void testServiceDetailDistricts9() {
		Optional<DistrictsEntity> districtsList = districtsDao.findById(9);
		assertThat(districtsList.get().getName()).isEqualTo("Quận 9");
	}

	@Test
	public void testServiceDetailDistricts10() {
		Optional<DistrictsEntity> districtsList = districtsDao.findById(10);
		assertThat(districtsList.get().getName()).isEqualTo("Quận 10");
	}

	@Test
	public void testServiceAllDistricts() {
		List<DistrictsEntity> districtsList = districtsDao.findAll();
		assertThat(districtsList).size().isGreaterThan(0);
	}

	@Test
	public void testServiceAllDistrictsEqual() {
		List<DistrictsEntity> districtsList = districtsDao.findAll();
		assertThat(districtsList).size().isEqualByComparingTo(10);
	}

	@Test
	public void testServiceAllDistrictsByProvince() {
		List<DistrictsEntity> districtsList = districtsDao.findByProvincesID(1);
		assertThat(districtsList).size().isGreaterThan(0);
	}

	@Test
	public void testServiceAllDistrictsByProvinceEqual() {
		List<DistrictsEntity> districtsList = districtsDao.findByProvincesID(1);
		assertThat(districtsList).size().isEqualByComparingTo(10);
	}

	@Test
	public void testServiceAllDistrictsByProvinceName() {
		boolean flag = true;
		List<DistrictsDTO> districtsList = districtsDao.findAllDistrictsByProviceIdDTO(1);
		for (int i = 0; i < districtsList.size(); i++) {
			if (!districtsList.get(i).getProvince_name().equalsIgnoreCase("TP.Hồ Chí Minh")) {
				 flag = false;
			}
		}
		assertThat(flag);
	}

	@WithMockUser
	@Test
	public void testReadAllDistrictsByPrivinceId() throws Exception {
		MvcResult result = mockMvc.perform(get("/districts/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllDistrictsByPrivinceIdFail() throws Exception {
		MvcResult result = mockMvc.perform(get("/districts/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		assertEquals(404, result.getResponse().getStatus());
	}

	// Test Provinces
	@Test
	public void testServiceAllProvinces() {
		List<ProvincesEntity> provincesList = provincesDao.findAll();
		assertThat(provincesList).size().isGreaterThan(0);
	}

	@Test
	public void testServiceAllProvincesEqual() {
		List<ProvincesEntity> provincesList = provincesDao.findAll();
		assertThat(provincesList).size().isEqualByComparingTo(6);
	}

	@Test
	public void testServiceAllProvincesByProvince() {
		List<ProvincesEntity> provincesList = provincesDao.findByCountryID(1);
		assertThat(provincesList).size().isGreaterThan(0);
	}

	@Test
	public void testServiceAllProvincesByProvinceEqual() {
		List<ProvincesEntity> provincesList = provincesDao.findByCountryID(1);
		assertThat(provincesList).size().isEqualByComparingTo(6);
	}
	
	@Test
	public void testServiceDetailProvinceEqual() {
		List<ProvincesDTO> provincesListDTO = provincesDao.findAllProvincesDTO();
		List<ProvincesEntity> provincesListEntity = provincesDao.findAll();
		assertThat(provincesListDTO.get(0).getName()).isEqualTo(provincesListEntity.get(0).getName());
	}
	
	@Test
	public void testServiceDetailProvinceNameEqual1() {
		String[] countryName = {"TP.Hồ Chí Minh","Đà Nẵng","Long An","Đắk Lắk"};
		boolean flag = true;
		for(int i = 0 ; i < countryName.length ; i++) {
			String provincesName = provincesDao.findNameByid(1);
			String test = countryName[i];
			if(!provincesName.equalsIgnoreCase(test)) {
				flag = false;
			}
		}
		assertThat(flag);
	}
	
	@Test
	public void testServiceAllProvincesByProvinceName() {
		List<ProvincesDTO> provincesList = provincesDao.findProvincesByCountryIdDTO(1);
		String countryName = provincesList.get(0).getCountry_name();
		assertThat(countryName).isEqualTo("Việt Nam");
	}

	@WithMockUser
	@Test
	public void testReadAllProvinces() throws Exception {
		MvcResult result = mockMvc.perform(get("/provinces").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllProvincesByCountryId() throws Exception {
		MvcResult result = mockMvc.perform(get("/provinces/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllProvincesByCountryIdFail() throws Exception {
		MvcResult result = mockMvc.perform(get("/provinces/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		assertEquals(404, result.getResponse().getStatus());
	}

	// Test Wards
	@Test
	public void testServiceAllWards() {
		List<WardsEntity> wardsList = wardsDao.findAll();
		assertThat(wardsList).size().isGreaterThan(0);
	}

	@Test
	public void testServiceAllWardsEqual() {
		List<WardsEntity> wardsList = wardsDao.findAll();
		assertThat(wardsList).size().isEqualByComparingTo(26);
	}

	@Test
	public void testServiceAllWardsByDistrict() {
		List<WardsEntity> wardsList = wardsDao.findByDistrictsID(1);
		assertThat(wardsList).size().isGreaterThan(0);
	}

	@Test
	public void testServiceAllWardsByDistrictEqual() {
		List<WardsEntity> wardsList = wardsDao.findByDistrictsID(1);
		assertThat(wardsList).size().isEqualByComparingTo(5);
	}

	@Test
	public void testServiceAllWardsByDistrictEqual2() {
		List<WardsEntity> wardsList = wardsDao.findByDistrictsID(2);
		assertThat(wardsList).size().isEqualByComparingTo(9);
	}

	@Test
	public void testServiceAllWardsByDistrictEqual3() {
		List<WardsEntity> wardsList = wardsDao.findByDistrictsID(3);
		assertThat(wardsList).size().isEqualByComparingTo(12);
	}

	@Test
	public void testServiceAllWardsByDistrictsName() {
		List<WardsDTO> wardsList = wardsDao.findWardsByDistrictIdDTO(1);
		String districtsName = wardsList.get(0).getDistrict_name();
		assertThat(districtsName).isEqualTo("Quận 1");
	}

	@Test
	public void testServiceAllWardsByDistrictsName2() {
		List<WardsDTO> wardsList = wardsDao.findWardsByDistrictIdDTO(2);
		String districtsName = wardsList.get(0).getDistrict_name();
		assertThat(districtsName).isEqualTo("Quận 2");
	}

	@Test
	public void testServiceAllWardsByDistrictsName3() {
		List<WardsDTO> wardsList = wardsDao.findWardsByDistrictIdDTO(3);
		String districtsName = wardsList.get(0).getDistrict_name();
		assertThat(districtsName).isEqualTo("Quận 3");
	}

	@WithMockUser
	@Test
	public void testReadAllWards() throws Exception {
		MvcResult result = mockMvc.perform(get("/wards").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllWardsByDistrictsId() throws Exception {
		MvcResult result = mockMvc.perform(get("/wards/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllWardsByDistrictsIdFail() throws Exception {
		MvcResult result = mockMvc.perform(get("/wards/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		assertEquals(404, result.getResponse().getStatus());
	}

	// Test Messages
	@Test
	public void testServiceAllMessages() {
		List<MessagesEntity> messagesList = messagesDao.findAll();
		assertThat(messagesList).size().isGreaterThan(0);
	}

	@Test
	public void testServiceAllMessagesEqual() {
		List<MessagesEntity> messagesList = messagesDao.findAll();
		assertThat(messagesList).size().isEqualByComparingTo(6);
	}

	@Test
	public void testServiceAllMessagesByMessages1() {
		MessagesEntity messagesList = messagesDao.findById(1);
		String messageContent = messagesList.getMessage();
		assertThat(messageContent).isEqualTo(
				"Xin chào. Tên tôi là ninguyen và tôi đang gọi về việc cho thuê căn hộ mà bạn đã quảng cáo trên báo. Nó vẫn còn phải không?");
	}

	@Test
	public void testServiceAllMessagesByMessages2() {
		MessagesEntity messagesList = messagesDao.findById(2);
		String messageContent = messagesList.getMessage();
		assertThat(messageContent).isEqualTo("Tôi muốn xem ngôi nhà của bạn đang bán.");
	}

	@Test
	public void testServiceAllMessagesByMessages3() {
		MessagesEntity messagesList = messagesDao.findById(4);
		String messageContent = messagesList.getMessage();
		assertThat(messageContent).isEqualTo("test");
	}

	@Test
	public void testServiceAllMessagesByMessages4() {
		MessagesEntity messagesList = messagesDao.findById(5);
		String messageContent = messagesList.getMessage();
		assertThat(messageContent).isEqualTo("test2");
	}

	@Test
	public void testServiceAllMessagesByMessages5() {
		MessagesEntity messagesList = messagesDao.findById(7);
		String messageContent = messagesList.getMessage();
		assertThat(messageContent).isEqualTo("xinchao");
	}

//	@Test
//	public void testServiceAllMessagesByConversation1() {
//		List<MessagesEntity> messagesList = messagesDao.findByConversationsID(1);
//		assertThat(messagesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testServiceAllMessagesByConversation3() {
//		List<MessagesEntity> messagesList = messagesDao.findByConversationsID(3);
//		assertThat(messagesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testServiceAllMessagesByConversation1Equal() {
//		List<MessagesEntity> messagesList = messagesDao.findByConversationsID(1);
//		assertThat(messagesList).size().isEqualByComparingTo(5);
//	}
//
//	@Test
//	public void testServiceAllMessagesByConversation3Euqal() {
//		List<MessagesEntity> messagesList = messagesDao.findByConversationsID(3);
//		assertThat(messagesList).size().isEqualByComparingTo(1);
//	}

//	@Test
//	public void testServiceAllMessagesByTitleDetail1() {
//		List<ResponseMessagesDTO> messagesList = messagesDao.findAllMessageByConversationIdDTO(1);
//		String conversationName = messagesList.get(1).getConversation_name();
//		assertThat(conversationName).isEqualTo("Thuê dài hạn");
//	}
//
//	@Test
//	public void testServiceAllMessagesByTitleDetail3() {
//		List<ResponseMessagesDTO> messagesList = messagesDao.findAllMessageByConversationIdDTO(3);
//		String conversationName = messagesList.get(0).getConversation_name();
//		assertThat(conversationName).isEqualTo("Mua nhà");
//	}

	@WithMockUser
	@Test
	public void testReadAllMessages() throws Exception {
		MvcResult result = mockMvc.perform(get("/messages").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllMessagesByConversationId() throws Exception {
		MvcResult result = mockMvc.perform(get("/messages/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllMessagesByConversationIdFail() throws Exception {
		MvcResult result = mockMvc.perform(get("/messages/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		assertEquals(404, result.getResponse().getStatus());
	}

//	@WithMockUser
//	@Test
//	@Order(1)
//	public void testSaveMessages() throws Exception {
//		MessagesEntity messagesEntity = new MessagesEntity(6, 1, 1 ,"text","xinchao","sent","2022/10/10","2022/10/10");
//		String jsonRequest = mapper.writeValueAsString(messagesEntity);
//		MvcResult result = mockMvc.perform(post("/messages/send").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn();
//		assertEquals(200, result.getResponse().getStatus());
//	}
//	@WithMockUser
//	@Test
//	@Order(2)
//	public void testUpdateMessages() throws Exception {
//		int lastId = messagesDao.findLastIDForTest();
//		MessagesEntity messagesEntity = messagesDao.findById(lastId);
//		messagesEntity.setCreated("2022/11/15");
//		String jsonRequest = mapper.writeValueAsString(messagesEntity);
//		MvcResult result = mockMvc.perform(put("/messages/update/"+lastId).content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn();
//		assertEquals(200, result.getResponse().getStatus());
//	}
//	@WithMockUser
//	@Test
//	@Order(3)
//	public void testDeleteMessages() throws Exception {
//		int lastId = messagesDao.findLastIDForTest();
//		MvcResult result = mockMvc.perform(delete("/messages/delete/"+lastId).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn();
//		assertEquals(200, result.getResponse().getStatus());
//	}

	// Test Properties

	@Test
	public void testAllPropertiesType() {
		List<PropertiesTypesEntity> propertiesTypeList = propertiesTypeDao.findAll();
		assertThat(propertiesTypeList).size().isGreaterThan(0);
	}

	@Test
	public void testAllPropertiesTypeEqual() {
		List<PropertiesTypesEntity> propertiesTypeList = propertiesTypeDao.findAll();
		assertThat(propertiesTypeList).size().isEqualByComparingTo(10);
	}

	@Test
	public void testAllSubPropertiesType() {
		List<PropertiesSubtypesEntity> propertiesSubTypeList = propertiesSubTypeDao.findAll();
		assertThat(propertiesSubTypeList).size().isGreaterThan(0);
	}

	@Test
	public void testAllSubPropertiesTypeEqual() {
		List<PropertiesSubtypesEntity> propertiesSubTypeList = propertiesSubTypeDao.findAll();
		assertThat(propertiesSubTypeList).size().isEqualByComparingTo(3);
	}

	@Test
	public void testAllProperties() {
		List<PropertiesEntity> propertiesList = propertiesDao.findAll();
		assertThat(propertiesList).size().isGreaterThan(0);
	}

	@Test
	public void testAllPropertiesEqual() {
		List<PropertiesEntity> propertiesList = propertiesDao.findAll();
		assertThat(propertiesList).size().isEqualByComparingTo(11);
	}

	@Test
	public void testAllPropertiesByAddress1() {
		PropertiesEntity properties = propertiesDao.findById(1);
		String address = properties.getAddress1();
		assertThat(address).isEqualTo("65 Lê Lợi, phường Bến Nghé, quận 1, Thành phố Hồ Chí Minh");
	}

	@Test
	public void testAllPropertiesByAddress2() {
		PropertiesEntity properties = propertiesDao.findById(2);
		String address = properties.getAddress1();
		assertThat(address).isEqualTo("Số 2 Tôn Đức Thắng, phường Bến Thành, quận 1, thành phố Hồ Chí Minh");
	}

	@Test
	public void testAllPropertiesByAddress3() {
		PropertiesEntity properties = propertiesDao.findById(3);
		String address = properties.getAddress1();
		assertThat(address).isEqualTo("Số 140, Lương Định Của, phường An Khánh, quận 2.");
	}

	@Test
	public void testAllPropertiesByAddress4() {
		PropertiesEntity properties = propertiesDao.findById(4);
		String address = properties.getAddress1();
		assertThat(address).isEqualTo("Số 12 Trần Não, phường Bình An, Quận 2");
	}

	@Test
	public void testAllPropertiesByAddress5() {
		PropertiesEntity properties = propertiesDao.findById(5);
		String address = properties.getAddress1();
		assertThat(address).isEqualTo("số 15 Nguyễn Thiện Thuật, phường 1, quận 3, thành phố Hồ Chí Minh");
	}

	@Test
	public void testAllPropertiesByAddress6() {
		PropertiesEntity properties = propertiesDao.findById(6);
		String address = properties.getAddress1();
		assertThat(address).isEqualTo(" phường 7, quận 3, thành phố Hồ Chí Minh");
	}

	@Test
	public void testAllPropertiesByAddress9() {
		PropertiesEntity properties = propertiesDao.findById(9);
		String address = properties.getAddress1();
		assertThat(address).isEqualTo(" số 15 Tôn Thất Thuyết, Phường 1, Quận 4, TP Hồ Chí Minh");
	}

//	@Test
//	public void testAllPropertiesByAddress10() {
//		PropertiesEntity properties = propertiesDao.findById(10);
//		String address = properties.getAddress1();
//		assertThat(address).isEqualTo("số 15 Vĩnh Hội, Phường 4, Quận 4, TP Hồ Chí Minh");
//	}
//
//	@Test
//	public void testSearchPropertiesByParam() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 2, 2, 2, null, null, null, null,
//				null, null, null, null, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 2, 2, 2, null, null, null, null,
//				null, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(1);
//	}
//	
//	@Test
//	public void testSearchServicePropertiesByParamDetail() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 2, 2, 2, null, null, null, null,
//				null, null, null, null, null, null);
//		Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(0).getId());
//		assertThat(propertiesEntity.get().getAddress1()).isEqualTo(propertiesList.get(0).getAddress1());
//	}
//
//
//	@Test
//	public void testSearchPropertiesByParam2() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 2, 2, 2, null, null, null, null,
//				null, null, null, null, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual2() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 2, 3, 3, null, null, null, null,
//				null, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(2);
//	}
//	
//	@Test
//	public void testSearchServicePropertiesByParamDetail2() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 2, 3, 3, null, null, null, null,
//				null, null, null, null, null, null);
//		Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(0).getId());
//		assertThat(propertiesEntity.get().getAddress1()).isEqualTo(propertiesList.get(0).getAddress1());
//	}
//
//	@Test
//	public void testSearchServicePropertiesByParamDetail2_1() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 2, 3, 3, null, null, null, null,
//				null, null, null, null, null, null);
//		Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(1).getId());
//		assertThat(propertiesEntity.get().getAddress1()).isEqualTo(propertiesList.get(1).getAddress1());
//	}
//
//	@Test
//	public void testSearchPropertiesByParam3() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual3() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(8);
//	}
//
//	@Test
//	public void testSearchServicePropertiesByParamDetail3() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, null, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}	
//	
//	@Test
//	public void testSearchPropertiesByParam4() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", null, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual4() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", null, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(2);
//	}
//
//	@Test
//	public void testSearchServicePropertiesByParamDetail4() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", null, null, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}	
//	
//	@Test
//	public void testSearchPropertiesByParam5() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", 2, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual5() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", 2, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(1);
//	}
//
//	@Test
//	public void testSearchServicePropertiesByParamDetail5() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", 2, null, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}
//	
//	@Test
//	public void testSearchPropertiesByParam6() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", 3, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual6() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", 3, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(1);
//	}
//
//	@Test
//	public void testSearchServicePropertiesByParamDetail6() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", 3, null, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}
//	
//	@Test
//	public void testSearchPropertiesByParam7() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", null, 3, null, null, null, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual7() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", null, 3, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(1);
//	}
//
//	@Test
//	public void testSearchServicePropertiesByParamDetail7() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", null, 3, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}
//	
//	
//	
//	@Test
//	public void testSearchPropertiesByParam8() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", null, 1, null, null, null, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual8() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", null, 1, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(1);
//	}
//	
//	
//	@Test
//	public void testSearchServicePropertiesByParamDetail8() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, "%quận 2%", null, 1, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}
//
//	// address/type/floor/agencyid/minLong/maxLong
//	@Test
//	public void testSearchPropertiesByParam9() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, 1, null, null, null, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual9() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, 1, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(5);
//	}
//
//	@Test
//	public void testSearchServicePropertiesByParamDetail9() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, 1, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}
//	
//	@Test
//	public void testSearchPropertiesByParam10() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, 2, null, null, null, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual10() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, 2, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(2);
//	}
//
//	@Test
//	public void testSearchServicePropertiesByParamDetail10() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, 2, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}
//	
//	
//	@Test
//	public void testSearchPropertiesByParam11() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, 3, null, null, null, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//	
//	@Test
//	public void testSearchServicePropertiesByParamDetail11() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, 3, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}
//	
//
//	@Test
//	public void testSearchPropertiesByParamEqual11() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, 3, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(1);
//	}
//
//	@Test
//	public void testSearchPropertiesByParam12() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, null, null, 2000000l, 2000000000l, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual12() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, null, null, 2000000l, 2000000000l, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(6);
//	}
//	
//	@Test
//	public void testSearchServicePropertiesByParamDetail12() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(2, 100, 2, 100, 2000000000l,
//				2000000000000l, null, null, null, null, 2000000l, 2000000000l, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}
//	
//
//	@Test
//	public void testSearchPropertiesByParam13() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(3, 100, 2, 100, 4000000000l,
//				2000000000000l, null, null, null, null, 2000000l, 2000000000l, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual13() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(3, 100, 2, 100, 4000000000l,
//				2000000000000l, null, null, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(4);
//	}
//	
//	@Test
//	public void testSearchServicePropertiesByParamDetail13() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(3, 100, 2, 100, 4000000000l,
//				2000000000000l, null, null, null, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}
//
//	@Test
//	public void testSearchPropertiesByParam14() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(3, 100, 2, 100, 0l, 3000000000l, null,
//				null, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isGreaterThan(0);
//	}
//
//	@Test
//	public void testSearchPropertiesByParamEqual14() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(3, 100, 2, 100, 0l, 3000000000l, null,
//				null, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(3);
//	}
//	
//	@Test
//	public void testSearchServicePropertiesByParamDetail14() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(3, 100, 2, 100, 0l, 3000000000l, null,
//				null, null, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}
//	
//	@Test
//	public void testSearchPropertiesByParamEqual15() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(1, 100, 1, 100, 0l, 3000000000l, null,
//				null, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(6);
//	}
//	@Test
//	public void testSearchServicePropertiesByParamDetail15() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(1, 100, 1, 100, 0l, 3000000000l, null,
//				null, null, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}
//	
//	@Test
//	public void testSearchPropertiesByParamEqual16() {
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(1, 100, 1, 100, 3000000000l, 300000000000l, null,
//				null, null, null, null, null, null, null);
//		assertThat(propertiesList).size().isEqualByComparingTo(6);
//	}
//	@Test
//	public void testSearchServicePropertiesByParamDetail16() {
//		boolean flag = true;
//		List<PropertiesEntity> propertiesList = propertiesDao.findByMultiFilter(1, 100, 1, 100, 3000000000l, 300000000000l, null,
//				null, null, null, null, null, null, null);
//		for(int i = 0 ; i < propertiesList.size() ;i++) {
//			Optional<PropertiesEntity> propertiesEntity = propertiesDao.findById(propertiesList.get(i).getId());
//			if(!propertiesEntity.get().getAddress1().equalsIgnoreCase(propertiesList.get(i).getAddress1())){
//				flag = false;
//			}
//		}
//		
//		assertThat(flag);
//	}

	@WithMockUser
	@Test
	public void testReadAllTypes() throws Exception {
		MvcResult result = mockMvc.perform(get("/types").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllSubTypes() throws Exception {
		MvcResult result = mockMvc.perform(get("/subtypes").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllPropertySubTypesControllerByPropertiesTypeId() throws Exception {
		MvcResult result = mockMvc.perform(get("/subtypesByPropertiesTypeID/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllPropertySubTypesControllerByPropertiesTypeIdFail() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/subtypesByPropertiesTypeID/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		assertEquals(404, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllPropertiesById() throws Exception {
		MvcResult result = mockMvc.perform(get("/properties/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllPropertiesByIdFail() throws Exception {
		MvcResult result = mockMvc.perform(get("/properties/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		assertEquals(404, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	public void testReadAllProperties() throws Exception {
		MvcResult result = mockMvc.perform(get("/properties").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@WithMockUser
	@Test
	@Order(7)
	public void testSaveProperties() throws Exception {
		PropertiesEntity propertiesEntity = new PropertiesEntity();
		propertiesEntity.setUser_id(1);
		propertiesEntity.setForSale(true);
		propertiesEntity.setForRentLong(true);
		propertiesEntity.setForRentShort(true);
		propertiesEntity.setPriceSale(1000000l);
		propertiesEntity.setPriceRentLong(1000000l);
		propertiesEntity.setPriceRentShort(1000000l);
		propertiesEntity.setType_id(1);
		propertiesEntity.setSubtype_id(7);
		propertiesEntity.setStatus("Avaiable");
		propertiesEntity.setFloor(2);
		propertiesEntity.setDescription("test");
		propertiesEntity.setAddress1("test");
		propertiesEntity.setAddress2("test");
		propertiesEntity.setCountry_id(1);
		propertiesEntity.setProvince_id(1);
		propertiesEntity.setDistrict_id(1);
		propertiesEntity.setWard_id(1);
		propertiesEntity.setCommission(100000l);
		propertiesEntity.setCommission_pct(5);
		propertiesEntity.setCommission_list_agent(100000l);
		propertiesEntity.setCommission_list_agent_pct(5);
		propertiesEntity.setCommission_sell_agent(100000l);
		propertiesEntity.setCommission_sell_agent_pct(5);
		propertiesEntity.setBeds(3);
		propertiesEntity.setBaths(2);
		propertiesEntity.setBuilt_space(1);
		propertiesEntity.setGarden_space(2);
		propertiesEntity.setTerrace_space(3);
		propertiesEntity.setCurrency("Vnd");
		propertiesEntity.setDimension("4x10m");
		propertiesEntity.setCreated(new Date());
		propertiesEntity.setUpdated(new Date());
		String jsonRequest = mapper.writeValueAsString(propertiesEntity);
		MvcResult result = mockMvc.perform(post("/properties/add").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	@WithMockUser
	@Test
	@Order(8)
	public void testUpdateProperties() throws Exception {
		int lastId = propertiesDao.findLastIDForTest();
		PropertiesEntity propertiesEntity = propertiesDao.findById(lastId);
		propertiesEntity.setAddress1("test function");
		String jsonRequest = mapper.writeValueAsString(propertiesEntity);
		MvcResult result = mockMvc.perform(put("/properties/update/"+lastId).content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	@WithMockUser
	@Test
	@Order(9)
	public void testDeleteProperties() throws Exception {
		int lastId = propertiesDao.findLastIDForTest();
		MvcResult result = mockMvc.perform(delete("/properties/delete/"+lastId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	// Test Participants
	@WithMockUser
	@Test
	public void testReadAllParticipantsById() throws Exception {
		MvcResult result = mockMvc.perform(get("/participants/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	// Search properties

}
