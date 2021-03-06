package com.cafe24.pjshop.controller.admin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.AdminProductService;
import com.cafe24.pjshop.vo.ProductVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/admin/product")
@RestController("AdminProductAPIController")
@Api(value = "AdminProductController", description ="관리자 상품관리 컨트롤러")
public class AdminProductController {

	@Autowired
	private AdminProductService adminProductService;	
		
	/*
	 * 
	 *  
	 */
	@ApiOperation(value="상품등록", notes ="상품등록 API")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<JSONResult> addProduct(
			@RequestBody ProductVo productVo) {
		
			//0 실패 1 성공 400 catetoryNo 잘못된요청
		int status = adminProductService.insertProduct(productVo);
		if(status == 1)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		else if(status == 400)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청입니다."));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("상품등록에 실패하였습니다"));
				
	}
	/*
	 * 
	 *  관리자 상품 목록조회에서는 image와 option없이 간략한 상품정보들이 List로 쭈욱 보여지게됨
	 *  관리자는 들어간후 option 선택으로 자신이 원하는 셋팅으로 이름이나 판매중인상품 등을 조회할 수 있음.
	 */
	@ApiOperation(value="관리자 상품 목록 조회", notes ="관리자 상품 전체목록 조회API")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getProductList(
			@RequestParam(required=false) String name,
			@RequestParam(required=false, defaultValue="0") Long categoryNo,
			@RequestParam(required=false) String displayStatus,
			@RequestParam(required=false) String salesStatus) {
		
		//getList 메서드에 전부다 null,0,null,null인 경우 -> 처음에 관리자 상품목록조회를 클릭해서 들어왔을때 전체 목록 조회
		//getList 메서드에 파라메타값이 존재할경우 -> 조회옵션을 설정하였을경우
		List<ProductVo> productList = adminProductService.getList(name,categoryNo,displayStatus,salesStatus);
		if(!productList.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productList));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("상품목록 조회에 실패하였습니다"));
	}
	
	/*
	 * 
	 *  getProductDetail 안에 countByNo 수정해야함.(해결)
	 *  목록에서 관리자가 상품을 클릭했을때 상세 detail 정보 가져오는 컨트롤러
	 */
	@ApiOperation(value="관리자 상품 상세 정보 보기", notes ="관리자 상품 상세 정보 조회API")
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getProductDetail(
			@PathVariable(value="no") Long no) {
		
		
		ProductVo productVo = adminProductService.getProductDetail(no);
		
		if(productVo != null)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productVo));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청으로 상품상세정보 조회에 실패하였습니다"));
	}
	
	@ApiOperation(value="관리자 상품 상세정보 수정 하기", notes ="관리자 상품 상세 정보 수정API")
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyProduct(
			@RequestBody ProductVo productVo) {
		

		int status = adminProductService.modifyProduct(productVo);
		
		if(status == 1)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		else if(status == 400)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청입니다."));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("상품수정에 실패하였습니다"));
	}
	
	/*
	 * 
	 * 관리자 상품 삭제의 경우 delete하는게 아니라
	 * 컬럼에 status 상태코드를 두고 update로 상태코드를 바꿔주는게 훨씬 낫다.
	 * 상품 뿐만 아니라 다른테이블들도 마찬가지.
	 * on cascade로 참조하고있는 외래키 컬럼도 다 삭제해버리는건 굉장히 안좋은 방법.
	 * List<Long>으로 mybatis mapper에서 받았을때 값을 빼는 방법은 
	 * <foreach collection="list" item="item"  separator=";">
	 * 되어있다면 item.value로 가져오면된다...
	 * item.Long item.long등으로 헤맴
	 */
	@ApiOperation(value="관리자 상품 삭제", notes ="관리자 상품 삭제")
	@RequestMapping(value = "/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteProduct(
			@PathVariable(value="no") Long no) {
		

		int status = adminProductService.deleteProduct(no);
		
		if(status == 1)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		else if(status == 400)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청입니다."));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("상품수정에 실패하였습니다"));
	}
	

	
	
}
