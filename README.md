# board1_ajax
전자정부프레임워크, 게시판 만들기 1차 과제 > ajax 추가 사용

일시: 2022년 3월

학습목표
1. Spring MVC 패턴 숙지
2. 파일업로드/다운로드 숙지
3. 전자정부프레임워크 학습

# 테이블 스키마
tcom_bbs (게시글)
- bbs_seq : 게시글 일련번호 (key, not null)
- title : 제목 (not null)
- name : 작성자명 (not null)
- content : 내용 (not null)
- passwd : 비밀번호 (not null)
- open_yn : 공개여부 (비공개시 비밀번호 입력 요구)
- notice_yn : 상단공지여부
- reg_dt : 등록일시
- mod_dt : 수정일시

tcom_atch_file (첨부파일)
- bbs_seq : 게시글 일련번호 (key, not null)
- atch_seq : 첨부파일 일련번호 (key, not null)
- file_path : 파일경로 (not null)
- org_file_nm : 원본파일명
- reg_dt : 등록일시

# 1. 게시글 리스트
[화면설명]
1. 검색 구분 : 전체, 제목, 작성자
2. 조회 : 검색 조건에 해당하는 게시글 목록을 조회한다.
3. 상단공지의 경우 텍스트를 bold로 보여준다.
  - 공지글의 경우 공지에도 있고, 목록에도 존재한다.
5. 리스트를 선택하면 상세보기 화면으로 이동한다.
6. 글쓰기
  - 글쓰기 화면으로 이동한다.
7. 조회수 기능


[파일정보]
1. 패키지명
  - egovframework.wini.edu
2. JAVA 파일명
  - BbsListController
  - BbsListService
  - BbsListServiceImpl
  - BbsListDAO
  - BbsListVO
3. Mapper 파일명
  - BbsListDAO_SQL.xml
4. JSP 파일명
  - 위치 : egovframework\bbs
  - 파일명 : BbsList.jsp


# 2. 게시글 작성
[화면설명]
1. 비밀번호
  - 최소 4자리 이상 10자리 미만
  - 수정일 경우 입력한 비밀번호 update
2. 공개구분
  - default 공개
3. 상단공지여부
  - default false
4. 등록
  - 입력한 내용 저장
5. 취소
  - 게시글 리스트로 이동


[파일정보]
1. 패키지명
  - egovframework.wini.edu
2. JAVA 파일명
  - BbsWriteController
  - BbsWriteService
  - BbsWriteServiceImpl
  - BbsWriteDAO
  - BbsWriteVO (직접추가)
3. Mapper 파일명
  - BbsWriteDAO_SQL.xml
4. JSP 파일명
  - 위치 : egovframework\bbs
  - 파일명 : BbsWrite.jsp


# 3. 게시글 상세보기
[화면설명]
1. 첨부파일
  - 첨부파일명 클릭 시 파일 다운로드
2. 수정
  - 게시글 수정화면으로 이동(비밀번호 체크 후 이동가능)
3. 삭제
  - 게시글 삭제 (비밀번호 체크 후 삭제가능)


[파일정보]
1. 패키지명
  - egovframework.wini.edu
2. JAVA 파일명
  - BbsDetailController
  - BbsDetailService
  - BbsDetailServiceImpl
  - BbsDetailDAO
  - BbsDetailVO
3. Mapper 파일명
  - BbsDetailDAO_SQL.xml
4. JSP 파일명
  - 위치 : egovframework\bbs
  - 파일명 : BbsDetail.jsp



