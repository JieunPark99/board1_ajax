<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <c:set var="registerFlag" value="${empty temp.title ? 'create' : 'modify'}"/>
    
    <title>BbsUpdate <c:if test="${registerFlag == 'create'}"><spring:message code="button.create" /></c:if>
                  <c:if test="${registerFlag == 'modify'}"><spring:message code="button.modify" /></c:if>
    </title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
    
    <!--For Commons Validator Client Side-->
 <%--    <script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script> --%>
  <%--   <validator:javascript formName="BbsListVO" staticJavascript="false" xhtml="true" cdata="false"/> --%>
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/js/jquery/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/js/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/js/jquery/jquery.form.js"></script>
    <script type="text/javaScript">
        
	    function fn_egov_selectList() {
	    	if(confirm("목록화면으로 돌아가시겠습니까?")==true){
	    		location.href="/egovSampleList.do";	
	    	}else{
	    		return false;
	    	}
	    }
    
        /* 글 삭제 function */
        function fn_egov_delete(bbsSeq) {
        	if(confirm("정말 삭제하시겠습니까?")==true) {
        	document.detailForm.bbs_seq.value = bbsSeq;
           	document.detailForm.action = "<c:url value='/deleteSample.do'/>";
           	document.detailForm.submit();
           	}else{
           		return;
           	}
        }
        
        /* 글 등록=수정 function */
        function fn_egov_update(bbsSeq) {
        	var resultCode = $("#resultCode").val();
        	var bbs_seq = $("#bbs_seq").val();
        	
        	var paramData = $("#detailForm")[0];
        	paramData = new FormData(paramData);
        	
        	if($("#name").val()==null || $("#name").val()==""){
        		alert("작성자를 입력해주세요");
        		return false;
        	}
        	if($("#title").val() == null || $("#title").val() == "") {
    			alert("제목을 입력해주세요");
    			return false;
    		}
        	if($("#content").val() == null || $("#content").val() == "") {
    			alert("내용을 입력해주세요");
    			return false;
    		}
        	
        	if(confirm("수정하시겠습니까?")){
        		$.ajax({
        			type:"POST",
        			url:"./updateSample.do",
        			data:paramData,
        			async:false,
        			dataType:'html',
        			enctype:'multipart/form-data',
        			cache:false,
        			contentType:false,
        			processData:false,
        			error:function(request, status, error){
    		        	alert("수정중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
    		        },
    		        success:function(jdata){
    		        	if(jdata.resultCode==0){/*jdata.resultCode*/
    		        		alert("resultCode==0");
    		        		alert("수정중 에러!");
    		        	}
    		        	/* 2022.3.18 jdata에 데이터가 안 넘어오는 문제점이 있음. 일단 컨트롤러는 타므로 기능은 작동한다. */
    		        	else{
    		        		//jdata 찍어보면 jdata자체가 에러임. why why...
    		        		alert("jdata.resultCode: "+ jdata.resultCode);
    		        		/* alert("jdata.bbs_seq: "+jdata.bbs_seq); */
    		        		
    		        		/*목록이 아닌 상세 화면으로 넘어가게 하고 싶을때*/
    		        		//해당 컨트롤러가 get 방식이어야 가능한 방식
    		        		location.href="/updateSampleView.do?bbs_seq="+bbsSeq;
    		        		
    		        		/*목록으로 넘기고 싶을 때*/
    		        		/* location.href="/egovSampleList.do"; */
    		        		alert("수정 되었습니다.");
    		        	}
    		        }
        		});
        	}else{
        		return false;
        	}
        	/* 
        	document.detailForm.bbs_seq.value = bbsSeq;
           	document.detailForm.action = "<c:url value='/updateSample.do'/>";
           	document.detailForm.submit(); */
        }
        
    </script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">

<form:form commandName="BbsDetailVO" id="detailForm" name="detailForm" enctype="multipart/form-data">
 <input type="hidden" name="bbs_seq" value="${temp.bbs_seq}"/>
  <div class="wrapper" style="align-items:center;display: flex;justify-content: center;">
    <div id="content_pop">
    	<div>
    		<ul>
    			<li style="font-weight:bold; font-size:20px; padding:5px;"><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt=""/>
                    <c:if test="${registerFlag == 'create'}">게시글 수정 -- 실패</c:if>
                    <c:if test="${registerFlag == 'modify'}">게시글 수정 -- 성공</c:if>
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
    		<tr>
    			<td class="tbtd_caption" style="text-align:center;width:250px;"><label>게시글 번호</label></td>
    			<td class="tbtd_content">
    				<c:out value="${temp.bbs_seq}" />
    			</td>
    		</tr>
    		<!-- 작성자 -->
    		<tr>
    			<td class="tbtd_caption" style="text-align:center;"><label>작성자</label></td>
    			<td class="tbtd_content">
    				<form:input path="name" maxlength="30" cssClass="txt" value="${temp.name}"/>
    				&nbsp;<form:errors path="name" />
    			</td>
    		</tr>
    		<!-- 제목 -->
    		<tr>
    			<td class="tbtd_caption" style="text-align:center;"><label>제목</label></td>
    			<td class="tbtd_content">
    				<form:input path="title" maxlength="30" cssClass="txt" value="${temp.title}"/>
    				&nbsp;<form:errors path="title"/>
    			</td>
    		</tr>
    		<!-- 내용 -->
    		<tr>
    			<td class="tbtd_caption" style="text-align:center;"><label>내용</label></td>
                <td class="tbtd_content">
    				<form:input path="content" maxlength="30" cssClass="txt" value="${temp.content}"/>
    				&nbsp;<form:errors path="content" />
    			</td>
    		</tr>
    		<!-- 상단공개여부 -->
    		<tr>
    			<td class="tbtd_caption" style="text-align:center;"><label for="notice_yn">상단공개여부</label></td>
    			<td class="tbtd_content">
    				<form:checkbox  path="notice_yn" value="Y"/>&nbsp;<form:errors path="notice_yn" />
    			</td>
    		</tr>
    		<!-- 첨부파일 -->
    		<tr>
    			<td class="tbtd_caption" style="text-align:center;"><label for="file_nm">첨부파일</label></td>
    			<td class="tbtd_content">
    				<input name="fileInfo" type="file" />
    				<p><c:out value="${temp.org_file_nm }"></c:out></p>
    				<form:errors path="file_nm" />
                </td>
    		</tr>
    		<!-- 비밀번호 -->
    		<tr>
    			<td class="tbtd_caption" style="text-align:center;"><label>비밀번호</label></td>
    			<td class="tbtd_content">
    				<form:input path="passwd" minlength="4" maxlength="10" cssClass="txt" value="${temp.passwd}"/>
    				&nbsp;<form:errors path="passwd" />
    			</td>
    		</tr>
    	</table>
      </div>
    	<div id="sysbtn">
    		<ul>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_egov_selectList()">취소</a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
                <li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_egov_update('<c:out value="${temp.bbs_seq}"/>');"><spring:message code="button.modify" /></a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
             </ul>
                <!-- 삭제 기능은 BbsDetail.jsp에서 처리하므로 여기선 비활성화 -->
                <%-- <li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_egov_delete('<c:out value="${temp.bbs_seq}"/>');"><spring:message code="button.delete" /></a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li> --%>
    	</div>
    </div>
   </div>
    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <%-- <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/> --%>
    <input type="hidden" name="pageIndex" value="<c:out value='1'/>"/>
</form:form>
<script>
debugger;
let test="${temp}";
</script>
</body>
</html>
