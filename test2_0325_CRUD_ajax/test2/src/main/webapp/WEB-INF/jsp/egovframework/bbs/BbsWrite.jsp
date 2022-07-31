<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>BbsWrite</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
    
    <!-- jQuery -->
	<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/js/jquery/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/js/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/js/jquery/jquery.form.js"></script>
   
    <!--For Commons Validator Client Side-->
    <script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
    <validator:javascript formName="BbsListVO" staticJavascript="false" xhtml="true" cdata="false"/>
    
    <script type="text/javaScript">
        
        /* 글 목록 화면 function */
        function fn_egov_selectList() {
          /*  	document.WriteForm.action = "<c:url value='/egovSampleList.do'/>";
           	document.WriteForm.submit(); */
           	
           	location.href="/egovSampleList.do";
        }
        
        /* 글 등록=수정 function */
        function fn_egov_save() {
        	var paramData = $("#WriteForm")[0];
        	//[0]은 뭐지??
        	paramData = new FormData(paramData);
        	//formdata가 뭐지?
        	
        	if($("#name").val() == null || $("#name").val() == "") {
				alert("작성자를 입력해주세요");
				return false;
			}
			if($("#title").val() == null || $("#title").val() == "") {
				alert("제목을 입력해주세요");
				return false;
			}
			//title 이라는 이름을 가진 id가 두개가 있으면 인식을 못함.!!!!
			if($("#content").val() == null || $("#content").val() == "") {
				alert("내용을 입력해주세요");
				return false;
			}
			if($("#passwd").val() == null || $("#passwd").val() == "") {
				alert("비밀번호를 입력해주세요");
				return false;
			}
			if(confirm("등록하시겠습니까?")) {
				
				$.ajax({
			        type:"POST",
			        url:"/addSample.do",
			        data:paramData,
			        async: false,
			        dataType:'html',
			        enctype: 'multipart/form-data',
			        cache: false,
			        contentType: false,
			        processData: false,
			        error:function(request,status,error){
			        	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			        	alert("등록중 통신 에러!");
			        },
			        success:function(jdata) {
			        	if(jdata.resultCode == 0){
			        		alert("등록중 에러!");
			        	}
			        	else {
			        		alert("jdata.resultCode: "+jdata.resultCode);
			        		location.href="/egovSampleList.do";
				        	alert("등록 되었습니다.");
			        	}
			        }
			    });
			} else {
				return false;
			}
        }
        
    </script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">



<form:form commandName="BbsListVO" id="WriteForm" name="WriteForm" enctype="multipart/form-data">
<input type="hidden" name="selectId"/>
 <div class="wrapper" style="align-items:center;display: flex;justify-content: center;">
    <div id="content_pop">
    	<div>
    	<!-- id=title -->
    		<ul>
    			<li style="font-weight:bold; font-size:20px; padding:5px;"><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt=""/>게시글 등록
                </li>
    		</ul>
    	</div>
    	<!-- // 타이틀 -->
    	<div id="table">
    	<table width="100%" border="1" cellpadding="0" cellspacing="0" style="bordercolor:#D3E2EC; bordercolordark:#FFFFFF; BORDER-TOP:#C2D0DB 2px solid; BORDER-LEFT:#ffffff 1px solid; BORDER-RIGHT:#ffffff 1px solid; BORDER-BOTTOM:#C2D0DB 1px solid; border-collapse: collapse;">
    		<colgroup>
    			<col width="150"/>
    			<col width="?"/>
    		</colgroup>
    		<!-- 작성자 -->
    		<tr>
    			<td class="tbtd_caption"><label for="name">작성자</label></td>
    			<td class="tbtd_content">
    				<form:input path="name" id="name" value="" maxlength="30" cssClass="txt"/>
    				&nbsp;<form:errors path="name" />
    			</td>
    		</tr>
    		<!-- 제목 -->
    		<tr>
    			<td class="tbtd_caption"><label for="title">제목</label></td>
    			<td class="tbtd_content">
    				<form:input path="title" value="" maxlength="30" cssClass="txt"/>
    				&nbsp;<form:errors path="title" />
    			</td>
    		</tr>
    		<!-- 내용 -->
    		<tr>
    			<td class="tbtd_caption"><label for="content">내용</label></td>
    			<td class="tbtd_content">
    				<form:textarea path="content" id="content" rows="8" cols="58" />&nbsp;<form:errors path="content" />
                </td>
    		</tr>
    		<!-- 비밀번호 -->
    		<tr>
    			<td class="tbtd_caption"><label for="passwd">비밀번호</label></td>
    			<td class="tbtd_content">
    				<form:input path="passwd" id="passwd" minlength="4" maxlength="10" cssClass="txt"/>
    				&nbsp;<form:errors path="passwd" />
    			</td>
    		</tr>
    		<!-- 공개여부 (라디오버튼)-->
    		<tr>
    			<td class="tbtd_caption"><label for="open_yn">공개여부</label></td>
    			<td class="tbtd_content">
    				<input type="radio" name="open_yn" id="open_yn" value="Y" checked="checked">공개</input>
    				<input type="radio" name="open_yn" id="open_yn" value="N">비공개</input>
    			</td>
    		</tr>
    		<!-- 상단공지여부 (체크박스)-->
    		<tr>
    			<td class="tbtd_caption"><label for="notice_yn">상단공개여부</label></td>
    			<td class="tbtd_content">
    				<form:checkbox  path="notice_yn" value="Y"/>&nbsp;<form:errors path="notice_yn" />
    			</td>
    		</tr>
    		<!-- 첨부파일 -->
    		<tr>
    			<td class="tbtd_caption"><label for="file_nm">첨부파일</label></td>
    			<td class="tbtd_content">
    				<input name="fileInfo" type="file" />
    				<form:errors path="file_nm" />
                </td>
    		</tr>
    		<form:hidden path="bbs_seq" />
    	</table>
      </div>
    	<div id="sysbtn">
    		<ul>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_egov_save();"><spring:message code="button.create" /></a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_egov_selectList()">취소</a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
            </ul>
    	</div>
    </div>
   </div>
    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <%-- <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/> --%>
    <input type="hidden" name="pageIndex" value="<c:out value='1'/>"/>
</form:form>
</body>
</html>