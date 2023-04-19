# SOS Mall

- 데모 백엔드 프로젝트
- 쇼핑몰 API

# 프로젝트 목표

- 긴급상황에 대비할 수 있는 생존 장비 및 정보 제공
- 기본적인 쇼핑몰 기능 구축
- 상품 정보 공유 및 SNS 연계 기능 구축

# 주요 기능

- 정보제공 : 재난정보, 구호물품, 생존장비 등 유용한 정보 간략히 제공
- 상품관리 : 관리자는 상품 등록, 수정, 삭제가 가능
- 로그인 : 기본 로그인 및 SNS와 연계한 로그인 기능 제공
- 장바구니 및 결제 : 사용자는 상품을 장바구니에 담아 상품을 구매할 수 있음
- 상품 검색 및 추천 : 사용자는 상품을 검색하고 추천하거나 SNS에 공유할 수 있음
- 그외 : 상품 리뷰, F&Q 등

# 서비스 흐름도

https://drive.google.com/file/d/1P16nTvxoMfAeLMNoArkmTeEqnGrxkILx/view?usp=sharing

# ERD

https://drive.google.com/file/d/1OG_mQGNBObsi_on2_3IjunA8zCPq3rkw/view?usp=sharing

# 개발기술 & 도구 (백엔드)

- Java
- Spring boot
- Spring JPA(Hibernate), MyBatis
  - 하다 보니까 어차피 DTO도 설정해줘야 하고 테이블도 만들어야 해서 JPA와 함께 쓰는 게 나은 것 같다. 간단한 CRUD는 JPA를 사용하고, 복잡한 R은 MyBatis를 사용했다.
- AWS RDS (db.t2.micro), MariaDB
  - EC2는 설정이 필요해서 관리가 용이한 RDS 사용
- DBeaver, PostMan
- OpenAPI (Swagger)
- OAuth
- Visual Studio Code

# 제약사항 설정

- RDB 설계시 최대한 핵심 기능에 초점을 맞추기 위해 몇 가지 비즈니스 요구사항을 단순화 했다.

1. Member 속성 중 14세 미만, 권한, 등급은 고려안함
2. 삭제 기본 정책은 소프트 딜리트
3. 성능보다 유연성을 고려해 인조키(Auto Increment), 비식별 관계 지향
4. text vs. varchar -> 수정성능보다는 조회성능을 고려해 varchar 지향
5. 등록하는 Admin은 1개의 provider이며 백오피스단 고려하지 않음(사이즈가 너무 커지기 때문에)
6. 제품 속성은 컬러, 사이즈로 제한
7. 교환, 교환 기능은 제외함
8. 1개 주문 당 1개 리뷰 가능

# 상품 기획

### 생존장비

- 아웃도어 장비
  - 방수 가방, 기능성 의류, 기능성 신발, 선글라스, 기타
- 생존도구
  - 부싯돌, 조명, 휴대용 정수기, 나이프, 구조신호 도구, 연료, 나침반, 조리도구, 끈, 무전기, 낚시도구, 기타
- 피난처
  - 텐트, 침낭

### 약품 및 식량

- 비상식량
- 응급약품 키트
- 위생용품

### 기획제품

- 생존키트
  - 7일/14일/30일/60일/180일 패키지, 열대/한대/고산 기후 패키지
- 특가상품
- 생존매뉴얼
