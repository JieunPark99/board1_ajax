<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <c:set var="registerFlag" value="${empty temp2.title ? 'create' : 'modify'}"/>
    
    <title>BbsDetail <c:if test="${registerFlag == 'create'}"><spring:message code="button.create" /></c:if>
                  <c:if test="${registerFlag == 'modify'}"><spring:message code="button.modify" /></c:if>
    </title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
    
    <!-- jQuery -->
    <!-- 아래 jQuery를 꼭 선언해줘야, 자바스크립트 인식이 된다. -->
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/js/jquery/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/js/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/js/jquery/jquery.form.js"></script>
    <!--For Commons Validator Client Side-->
 <%--    <script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script> --%>
  <%--   <validator:javascript formName="BbsListVO" staticJavascript="false" xhtml="true" cdata="false"/> --%>
    
    <script type="text/javaScript" language="javascript" defer="defer">
        
        /* 글 목록 화면 function */
        function fn_egov_selectList() {
           	/* document.detailForm.action = "<c:url value='/egovSampleList.do'/>";
           	document.detailForm.submit(); */
           	
           	location.href="/egovSampleList.do";
        }
        
        /* 글 삭제 function */
        function fn_egov_delete(inputPassWd,bbsSeq) 
        {
        	/* var password = document.getElementById('passwd').value; */
        	var password = $("#passwd").val();
        	document.detailForm.bbs_seq.value = bbsSeq;
        	
        	if(password == null || password==""){
        		alert("비밀번호를 입력해주세요.")
        		return false;
        	}
        	else if(password !=  inputPassWd)
        	{
        		alert("비밀번호가 틀렸습니다!");
                return;
            }
        	else{      
        		if(confirm("정말 삭제하시겠습니까?")==true) 
        		{
                   	/* document.detailForm.action = "<c:url value='/deleteSample.do'/>";
                   	document.detailForm.submit(); */
                   	var paramData = $("#detailForm")[0];
                   	paramData = new FormData(paramData);
                   	
                   	$.ajax({
                   		type:"POST",
                   		url:"/deleteSample.do",
                   		async:false,
                   		data:paramData,
                   		dataType:'html',
                   		enctype:'multipart/form-data',
                   		cache:false,
                   		contentType:false,
                   		processData:false,
                   		error:function(request, status, error){
        		        	alert("삭제중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
        		        },
                   		success:function(jdata){
                   			if(jdata.resultCode==0){
                   				alert("삭제 중 에러!");
                   				return false;
                   			}else{
                   				alert("삭제 되었습니다.")
                       			location.href="/egovSampleList.do";
                   			}
                   		}
                   	});
                   	
                }else{
                   		return false;
         			 }
            	}
        }
        
        
        /* 글 등록=수정 function */
        function fn_egov_update(inputPassWd,bbsSeq) {
        	var password = document.getElementById('passwd').value;
        	document.detailForm.bbs_seq.value = bbsSeq;
        	if(password !=  inputPassWd){
        		alert("비밀번호가 틀렸습니다!");
                return;
            }
        	else{      
        		document.detailForm.action = "<c:url value='/boardUpdate.do'/>";
        		document.detailForm.submit();
            }
        }
       
        
        /*파일 다운로드*/
        function fn_egov_fileDown(fileNm,bbsSeq)
        {
	        if(fileNm != null)
	        {
	        	document.detailForm.fileDownload.value = fileNm;
	        	document.detailForm.bbs_seq.value = bbsSeq;
	        	document.detailForm.action = "<c:url value='/fileDown.do'/>";
	        	document.detailForm.submit();
	        }
	        else 
	        {
	        	return;
	        }
        }
        
        /*이전글*/
        function fn_egov_selectBeforePost(bbsSeq){
        	document.detailForm.bbs_seq.value = bbsSeq;
        	document.detailForm.preNextCode.value="Pre";
        	document.detailForm.action = "<c:url value='/updateSampleView.do'/>";
        	document.detailForm.submit();
        }
        
        /*다음글*/
        function fn_egov_selectAfterPost(bbsSeq){
        	document.detailForm.bbs_seq.value = bbsSeq;
        	document.detailForm.preNextCode.value ="Next";
        	document.detailForm.action = "<c:url value='/updateSampleView.do'/>";
        	document.detailForm.submit();
        }
        
    </script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">

<form:form commandName="BbsListVO" id="detailForm" name="detailForm" enctype="multipart/form-data">
 <input type="hidden" name="bbs_seq" value="${temp2.bbs_seq }"/>
 <input type="hidden" name="preNextCode"/>
 <input type="hidden" id="resultCode" value="${resultMap.resultCode }"/>
 <input type="hidden" name="fileDownload" />
  <div class="wrapper" style="align-items:center;display: flex;justify-content: center;">
    <div id="content_pop">
    	<div id="title">
    		<ul>
    			<li><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt=""/>
                    <c:if test="${registerFlag == 'create'}">게시글  상세보기-<spring:message code="button.create" /></c:if>
                    <c:if test="${registerFlag == 'modify'}">게시글  상세보기-<spring:message code="button.modify" /></c:if>
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
    		<!-- 수정일때만 나타나는 카테고리아이디 행 -->
    		<%-- <c:if test="${registerFlag == 'modify'}">
        		<tr>
        			<td class="tbtd_caption"><label for="id"><spring:message code="title.sample.id" /></label></td>
        			<td class="tbtd_content">
        				<form:input path="id" cssClass="essentiality" maxlength="10" readonly="true" />
        			</td>
        		</tr>
    		</c:if> --%>
    		<tr>
    			<td class="tbtd_caption"><label>게시글 번호</label></td>
    			<td class="tbtd_content">
    				<c:out value="${temp2.bbs_seq}"/>
    			</td>
    		</tr>
    		<!-- 작성자 -->
    		<tr>
    			<td class="tbtd_caption"><label>작성자</label></td>
    			<td class="tbtd_content">
    				<c:out value="${temp2.name}"/>
    			</td>
    		</tr>
    		<!-- 제목 -->
    		<tr>
    			<td class="tbtd_caption"><label>제목</label></td>
    			<td class="tbtd_content">
    				<c:out value="${temp2.title}"/>
    			</td>
    		</tr>
    		<!-- 내용 -->
    		<tr>
    			<td class="tbtd_caption"><label>내용</label></td>
                <td class="tbtd_content">
    				<c:out value="${temp2.content}"/>
                </td>
    		</tr>
    		<!-- 첨부파일 -->
    		<tr>
    			<td class="tbtd_caption"><label>첨부파일</label></td>
    			<td class="tbtd_content">
    				<a href="javascript:fn_egov_fileDown('<c:out value="${temp2.file_nm}"/>','<c:out value="${temp2.bbs_seq}"/>');">
    				<c:out value="${temp2.org_file_nm}"/>
    				</a>
    			
                </td>
    		</tr>
    		<!-- 비밀번호 -->
    		<tr>
    			<td class="tbtd_caption"><label>(확인용)비밀번호</label></td>
    			<td class="tbtd_content">
    				<c:out value="${temp2.passwd}"/>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label>비밀번호 입력</label></td>
    			<td class="tbtd_content">
    				<input id = "passwd" type="password" minlength="4" maxlength="30" cssClass="txt"/>
    			</td>
    		</tr>
    		<tr>
    			<td class="tbtd_caption"><label>공개여부</label></td>
    			<td class="tbtd_content">
    				<c:out value="${temp2.open_yn}"/>
    			</td>
    		</tr>
    	</table>
      </div>
    	<div id="sysbtn">
    		<ul>
    			<li>
                    <span class="btn_blue_jenny" style="background-color:#ff9eff">
                        <a href="javascript:fn_egov_selectBeforePost('<c:out value="${temp2.bbs_seq}"/>')">이전글</a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
    			<li>
                    <span class="btn_blue_l" style="text-align:center;">
                        <a href="javascript:fn_egov_selectList()">목록</a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
                <li>
                    <span class="btn_blue_l" style="text-align:center;">
                       	<a href="javascript:fn_egov_update('<c:out value="${temp2.passwd}"/>','<c:out value="${temp2.bbs_seq}"/>');">수정</a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
                <li>
                    <span class="btn_blue_l" style="text-align:center;">
                        <a href="javascript:fn_egov_delete('<c:out value="${temp2.passwd}"/>','<c:out value="${temp2.bbs_seq}"/>');"><spring:message code="button.delete" /></a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
                <li>
                    <span class="btn_blue_jenny" style="background-color:#b0fff3">
                        <a href="javascript:fn_egov_selectAfterPost('<c:out value="${temp2.bbs_seq}"/>')">다음글</a>
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
<script>
debugger;
let test="${temp2.title}";
let test2="${temp2.preNextCode }";
</script>
</body>
</html>
