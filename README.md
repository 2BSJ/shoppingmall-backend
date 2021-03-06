# ⚡[shoppingmall 작업계획서 바로가기](https://github.com/2BSJ/cafe24-shoppingmall/wiki)⚡
# shoppingmall 프로젝트 구성도

## src/main/java 구성

### com.cafe24 packages

#### config.app
  * DBConfig - MariaDb 연결 설정
    * com/cafe24/config/**app**/properties/jdbc.properties파일에 url,driver 등 db셋팅설정
  * MyBatisConfig - Mybatis 설정
    * com/cafe24/config/**app**/mybatis/configuration.xml 파일에 mapper파일 위치와 Vo alias 설정  
#### config.web
  * FileuploadConfig - MultipartResolver 설정
    * com/cafe24/config/**web**/properties/multipart.properties파일에 업로드 사이즈 및 가상경로 매핑
  * MessageConfig - Validate 할때 쓸 메세지 설정
  * MVCConfig - ViewResolver 설정, Default Servlet Handler 설정, Message Converter(Json) 설정
  * SecurityConfig - Interceptor 경로 및 환경설정
  * SwaggerConfig - Swagger base package 및 path 설정, swagger-ui.html 파일설정

#### pjsho.controller.admin.api
  * AdminCategoryController - 관리자 카테고리 관리 API
  * AdminOrderController - 관리자 주문 정보 관리 API
  * AdminProductController - 관리자 상품 관리 API
  * AdminUserController - 관리자 회원 관리 API
  * AdminCouponController - 관리자 쿠폰 관리 API
  * AdminOptionController - 관리자 옵션 관리 API

#### pjshop.controller.api
###### url 및 json형식 데이터 확인 api
  * OrderController - 주문 API
  * ProductController - 상품 API
  * UserController - 유저 API
  * CartController - 유저 장바구니 API

#### pjshop.dto
  * JSONResult - Json Class 정의  
   **result,message,(Object)data**  
   `성공,실패시 result에 success,fail 삽입`  
   `성공시 data에 Object 삽입 message에 null값 넣은뒤 JSONResult return`  
   `실패시 data에 null 삽입 message에 실패메세지 넣은뒤 JSONResult return`

#### pjshop.repository
  * AgreementDao
  * CartDao
  * CategoryDao
  * CouponDao
  * FixedOptionDao
  * ImageDao
  * OptionDao
  * OrderDao
  * OrderDetailDao
  * PaymentDao
  * ReviewDao
  * UserDao

#### pjshop.security
  * Auth - @Auth 설정
  * AuthInterceptor - `@Auth` 어노테이션 처리
  * AuthLoginInterceptor - login url 가져와서 세션처리
  * AuthLogoutInterceptor - logout url 가져와서 세션처리
  * AuthUser - @AuthUser 설정
  * AuthUserHandlerMethodArgumentResolver

#### pjshop.service
  * AdminService
  * OrderService
  * ProductService
  * UserService

#### pjshop.vo
  * AgreementVo
  * CartVo
  * CategoryVo
  * CouponVo
  * FixedOptionVo
  * ImageVo
  * OptionVo
  * OrderVo
  * OrderDetailVo
  * PaymentVo
  * ReviewVo
  * UserVo

## src/test/java 구성

#### com.cafe24.config.app
  * TestDBConfig - TestDatabase initialize용 메서드(shhema.sql,data.sql) 추가 및 TestDatabase 연결
#### com.cafe24.pjshop.config
  * TestAppConfig - TestDBConfig,Mybatis 설정
  * TestWebConfig - test용 webconfig만 설정
#### com.cafe24.pjshop.controller.admin.api
  * AdminCategoryControllerTest
  * AdminOrderControllerTest
  * AdminProductControllerTest
  * AdminUserControllerTest
#### com.cafe24.pjshop.controller.api
  * CartControllerTest
  * OrderControleerTest
  * ProductControllerTest
  * UserControllerTest

## src/test/resources

#### com.cafe24.config.app.properties
  * data.sql - 초기 테스트용 데이터 insert 설정
  * schema.sql - 초기 테스트용 테이블 schema 설정
  * jdbc.properties - testDatabase 연결 설정
---

## Swagger API - localhost:8080/cafe24-shoppingmall/swagger-ui.html
<img src='./Readme image/swagger-admin.png'>   
<img src='./Readme image/swagger-user.png'>
