package com.cafe24.pjshop.controller.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.pjshop.config.TestAppConfig;
import com.cafe24.pjshop.config.TestWebConfig;
import com.cafe24.pjshop.vo.CartVo;
import com.cafe24.pjshop.vo.OrderVo;
import com.google.gson.Gson;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class,TestWebConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before	
	public void setup() {
		mockMvc = MockMvcBuilders.
			webAppContextSetup(webApplicationContext).
			build();
		
		//테스트 데이터베이스의 테이블들 초기화시켜주고 값 세팅시키기.
	}

	//회원이 주문했을떄 test
	@Test
	public void testAAddOrder() throws Exception{
		
		ResultActions resultActions;
	// 1. 회원이 상품을 주문했을때 성공 case
	// 1.1 정상적 성공 case
		List<CartVo> cartList = new ArrayList<CartVo>();
		CartVo cartVo1 = new CartVo();
		cartVo1.setNo(1L);
		cartVo1.setAmount(2);
		cartVo1.setOptionNo(1L);
		cartVo1.setMemberNo(1L);
		cartList.add(cartVo1);
		
		OrderVo orderVo = new OrderVo();
		orderVo.setCsStatus("b");
		orderVo.setPayStatus("t");
		orderVo.setOrderStatus("b");
		orderVo.setAddress("평내");
		orderVo.setPhoneNumber("010-9136-4365");
		orderVo.setMessage("경비실에 놔주세영");
		orderVo.setMemberStatus("t");
		orderVo.setMemberNo(1L);
		
		//orderVo.setHomeNumber("0");
		//orderVo.setPassword("1234");
		orderVo.setCartList(cartList);
		
		
		resultActions = 
		mockMvc
		.perform(post("/api/order")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(orderVo)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	
	}
	
	//회원이 주문한 전체내역
	@Test
	public void testBGetList() throws Exception{
		
		ResultActions resultActions;
	// 1. 회원이 주문목록을 봤을때 성공 case
	// 1.1 정상적 성공 case
		resultActions = 
		mockMvc
		.perform(get("/api/order")
		.param("userNo", "1")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	
	}
	
	//회원이 주문한 상세내역
	@Test
	public void testCGetVo() throws Exception{
		
		ResultActions resultActions;
	// 1. 회원이 주문목록을 봤을때 성공 case
	// 1.1 정상적 성공 case
		resultActions = 
		mockMvc
		.perform(get("/api/order/1")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	}
	
	@Test
	public void testDModifyVo() throws Exception{
		
		ResultActions resultActions;
	// 1. 회원이 주문중에 주소나 message를 수정했을때 case
	// 1.1 정상적 성공 case
		OrderVo orderVo1 = new OrderVo();
		orderVo1.setNo(1L);
		orderVo1.setMessage("집앞이었다가 다른데로 수정해주세요");
		
		resultActions = 
		mockMvc
		.perform(put("/api/order")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(orderVo1)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	}
	
	
}
