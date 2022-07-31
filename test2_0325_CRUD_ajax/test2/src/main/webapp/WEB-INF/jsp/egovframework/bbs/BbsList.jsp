<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="title.sample" /></title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
    
    <!-- jQuery -->
     <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/js/jquery/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/js/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/js/jquery/jquery.form.js"></script>
    <script type="text/javaScript">
       
 	// Back Forward Cache로 브라우저가 로딩될 경우 혹은 브라우저 뒤로가기 했을 경우
	// event.persisted(true)는 사파리에서 뒤로가기하면 캐시를 이용해 화면에 보여줌
	// window.performance && window.performance.navigation.type == 2 는 크롬에서 뒤로가기 체크할 때 씀
    	window.onpageshow = function(event){
    		if(event.persisted || (window.performance && window.performance.navigation.type==2)){
    			location.reload();
    		}
    	}	
    
        /* 글 상세 화면 function */
        function fn_egov_select(bbsSeq) {
        	document.listForm.bbs_seq.value = bbsSeq;
           	document.listForm.action = "<c:url value='/updateSampleView.do'/>";
           	document.listForm.submit();
        }
        
        /* 글쓰기 화면 function */
        function fn_egov_addView() {
           	document.listForm.action = "<c:url value='/addSample.do'/>";
           	document.listForm.submit();
        }
        
        /* 글 목록,검색 function */
        function fn_egov_selectList() {
        	if($("#searchKeyword").val()==null || $("#searchKeyword").val()==''){
        		alert("검색어를 입력해주세요.");
        		return false;
        	}
        	// @ResponseBody를 컨트롤러에 붙이기
        	/* var paramData = $("#listForm")[0];
           	paramData = new FormData(paramData);
        	 
           	$.ajax({
           		type:"POST",
           		url:"/egovSampleList.do",
           		async:false,
           		data:paramData,
           		dataType:'html',
           		enctype:'multipart/form-data',
           		cache:false,
           		contentType:false,
           		processData:false,
           		error:function(request, status, error){
		        	alert("검색중 통신 에러!\n" + request.status + "\n" + request.responseText + "\n" + error);
		        },
           		success:function(jdata){
               		location.href="/egovSampleList.do";
           		}
           	}); */
           	
        	document.listForm.action = "<c:url value='/egovSampleList.do'/>";
           	document.listForm.submit();
        }
        
        /* pagination 페이지 링크 function */
        function fn_egov_link_page(pageNo){
        	document.listForm.pageIndex.value = pageNo;
        	document.listForm.action = "<c:url value='/egovSampleList.do'/>";
           	document.listForm.submit();
        }
        
        //
    </script>
</head>

<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
    <form:form commandName="searchVO" id="listForm" name="listForm" method="post">
        <input type="hidden" name="bbs_seq" />
        
      <div class="wrapper" style="align-items:center;display: flex;justify-content: center;">
        <div id="content_pop">
        	<!-- 타이틀 -->
        	<div id="title">
        		<ul>
        			<li><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt=""/>게시글 리스트</li>
        		</ul>
        	</div>
        	<!-- // 타이틀 -->
        	<div id="search">
        		<ul>
        			<li>
        			    <label for="searchCondition" style="visibility:hidden;"><spring:message code="search.choose" /></label>
        				<form:select path="searchCondition" cssClass="use">
        					<form:option value="0" label="전체" />
        					<form:option value="1" label="제목" />
        					<form:option value="2" label="작성자" />
        				</form:select>
        			</li>
        			<li><label for="searchKeyword" style="visibility:hidden;display:none;"><spring:message code="search.keyword" /></label>
                        <form:input path="searchKeyword" cssClass="txt" placeholder="search..."/>
                    </li>
        			<li>
        	            <span class="btn_blue_l">
        	                <a href="javascript:fn_egov_selectList();">조회</a>
        	                <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
        	            </span>
        	        </li>
                </ul>
        	</div>
        	<!-- List -->
        	<div id="table">
        		<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="제목, 작성자, 등록일, 조회수">
        			<caption style="visibility:hidden">제목, 작성자, 등록일, 조회수</caption>
        			<colgroup>
        				<col width="40"/>
        				<col width="200"/>
        				<col width="50"/>
        				<col width="80"/>
        				<col width="70"/>
        				<col width="50"/>
        			</colgroup>
        			<tr>
        				<th align="center">번호</th>
        				<th align="center">제목</th>
        				<th align="center">작성자</th>
        				<th align="center">등록일</th>
        				<th align="center">조회수</th>
        			</tr>    		
        			<c:set var="noticeCnt" value="0"/>	
        			<c:forEach var="result" items="${resultList}" varStatus="status">
            			<tr>
            			<c:choose>
            				<c:when test="${result.topyn eq '*'}">
            				<c:set var="noticeCnt" value="${noticeCnt+1 }"/>
            					<td align="center" class="l	isttd"><b>*&nbsp;</b></td>
            					<td align="center" class="listtd"><b><a href="javascript:fn_egov_select('<c:out value="${result.bbsSeq}"/>')"><c:out value="${result.title}"/></a>&nbsp;</b></td>
            				<td align="center" class="listtd"><b><c:out value="${result.name}"/>&nbsp;</b></td>
            				<td align="center" class="listtd"><b><c:out value="${result.regDt}"/>&nbsp;</b></td>
            				<td align="center" class="listtd"><b><c:out value="${result.viewCount}"/>&nbsp;</b></td>
            				</c:when>
            				<c:otherwise>
            					<td align="center" class="listtd"><c:out value="${paginationInfo.totalRecordCount+1 - ((searchVO.pageIndex-1) * searchVO.pageSize + (status.count-noticeCnt))}"/></td>
            					<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.bbsSeq}"/>')"><c:out value="${result.title}"/></a>&nbsp;</td>
            				<td align="center" class="listtd"><c:out value="${result.name}"/>&nbsp;</td>
            				<td align="center" class="listtd"><c:out value="${result.regDt}"/>&nbsp;</td>
            				<td align="center" class="listtd"><c:out value="${result.viewCount}"/>&nbsp;</td>
            				</c:otherwise>
            			</c:choose>
            			</tr>
        			</c:forEach>
        		</table>
        	</div>
        	<!-- /List -->
        	<div id="paging">
        		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
        		<form:hidden path="pageIndex" />
        	</div>
        	<div id="sysbtn">
        	  <ul>
        	      <li>
        	          <span class="btn_blue_l">
        	              <a href="/addSample.do">글쓰기</a>
                          <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                      </span>
                  </li>
              </ul>
        	</div>
        </div>
       </div>
    </form:form>
<script>
debugger;
let test = "${temp.title}";
</script>
</body>
</html>
