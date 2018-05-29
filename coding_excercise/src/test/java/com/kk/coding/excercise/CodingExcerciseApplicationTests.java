package com.kk.coding.excercise;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kk.coding.excercise.controller.URLLookupService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = URLLookupService.class, secure = false)
public class CodingExcerciseApplicationTests {

	@Autowired
	private URLLookupService urlLookupService;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private URLCheckHelper helper;

	// This testcase ensures that the spring context loads w/o any issues
	@Test
	public void contextLoads() {
		assertThat(urlLookupService).isNotNull();
	}

	@Test
	public void shouldReturnSuccess() throws Exception {

		Mockito.when(helper.isURLSafe(Mockito.anyString())).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/urlinfo/1/mm:8090/kkpath")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "{\"status\":\"SUCCESS\",\"message\":\"URL is safe to use! \"}";

		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("SUCCESS")));

		System.out.println("Test response..........................: " + result.getResponse().getContentAsString());

		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public void shouldReturnFail() throws Exception {

		Mockito.when(helper.isURLSafe(Mockito.anyString())).thenReturn(false);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/urlinfo/1/threatdomain.com/testPathpath")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"status\":\"FAIL\",\"message\":\"URL is not safe to use! \"}";

		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("FAIL")));

		System.out.println("Test response..........................: " + result.getResponse().getContentAsString());

		assertEquals(expected, result.getResponse().getContentAsString());

	}

}
