package com.cafe24.pjshop.controller.admin.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@RequestMapping("/api/admin/coupon")
//@RestController("AdminCouponAPIController")
//@Api(value = "AdminCouponController", description ="관리자  쿠폰관리 컨트롤러")
public class AdminCouponController {

	
	@ApiOperation(value="메인", notes ="상품 전체목록 조회API")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public JSONResult list() {
		
		return null;
			
	}
}
