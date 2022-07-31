package egovframework.wini.edu.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.wini.edu.service.BbsDetailService;
import egovframework.wini.edu.service.BbsDetailVO;
import egovframework.wini.edu.service.SampleDefaultVO;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springmodules.validation.commons.DefaultBeanValidator;


@Controller
public class BbsDetailController {

	/** EgovSampleService */
	@Resource(name = "DetailService")
	private BbsDetailService BbsdetailService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BbsDetailController.class);

	/**
	 * 글 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/updateSampleView.do")
	public String updateSampleView
	(
			@RequestParam("bbs_seq") String id
			, @ModelAttribute("BbsDetailVO") BbsDetailVO searchVO
			, Model model
			, @RequestParam Map<String, Object> commandMap
			, HttpServletRequest request
			, HttpServletResponse response
	) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			/*쿠키를 이용한 조회수 중복 방지*/
			Cookie oldCookie = null; 
			// oldCookie에 null을 넣어둔다.
		    Cookie[] cookies = request.getCookies();
		    // HttpServletRequest로부터 클라이언트의 쿠키를 가져온다.
		    if (cookies != null) {
		    	// 쿠키가 null인지 먼저 검사한다.
		        for (Cookie cookie : cookies) {
		        	// 쿠키가 null이 아니라면 
		        	// 반복문 돌면서 해당 쿠키들 중 postView라는 이름의 쿠키가 있는지 검사한다.
		            if (cookie.getName().equals("postView")) {
		            	//만약 존재한다면 oldCookie라는 이름으로 가져온다.
		                oldCookie = cookie;
		            }
		        }
		    }
		    if (oldCookie != null) {
		    	//oldCookie가 null이 아니라면
		    	//즉, postView가 존재한다면
		        if (!oldCookie.getValue().contains("[" + id.toString() + "]"))
		       // 해당 쿠키의 value가 현재 접근한 게시글의 id를 포함하고 있는지 검사한다.
		        {
		        	/*상세 페이지 확인시마다 조회수 증가*/
					BbsdetailService.increaseViewCount(id);
					// 만약 그 id를 포함한다면 아무일도 일어나지 않겠지만
					// ! 으로 받았으므로, 가지고 있지 않다면 조회수를 증가시킨다.
		            oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
		            // 게시글 id를 괄호로 감싸 oldCookie에 추가하고
		            
		            /*왜 id를 괄호로 감싸나???
		             *해당 게시글을 이미 조회한 적 있는지 확인할 때 contains() 함수를 사용했음.
		             *단순히 숫자만 넣는다면 문제가 생길 수 있다.
		             *에를 들어 101번 게시글을 조회해서 쿠키를 검사해서 postView에 101이 있는데
		             *이때 10번 게시글을 조회한다면 101에 포함되어 있는 값이기 때문에
		             *중복된 조회로 여기고, 조회수가 증가하지 않는 문제가 생길 수 있다.
		             *이를 방지하기 위해 [101],[10]과 같은 식으로 포장하여 검사 및 갱신하는 것!! */
		            
		            oldCookie.setPath("/");
		            oldCookie.setMaxAge(60 * 60 * 24);
		            response.addCookie(oldCookie);
		            // HttpServletResponse에게 전달한다.
		        }
		    } else {// oldCookie가 null이라면
		    	/*상세 페이지 확인시마다 조회수 증가*/
				BbsdetailService.increaseViewCount(id);
		        Cookie newCookie = new Cookie("postView","[" + id + "]");
		        newCookie.setPath("/");
		        newCookie.setMaxAge(60 * 60 * 24);
		        response.addCookie(newCookie);
		    }
			
		    /*java에서 alert을 사용하기 위해 선언함.*/
		    PrintWriter out=response.getWriter();
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=utf-8");
		    
		    /*2022-03-22
		     * 한글 깨지는 문제 있음 (???로 나옴)
		     * alert은 잘 뜸*/
		    
			/*이전글, 다음글 체크 여부 확인*/
		    Map<String, Object> paramMap = new HashMap<>();
		    paramMap.put("bbs_seq", id);
			if("Pre".equals(searchVO.getPreNextCode())) {
				System.out.println("result: "+ BbsdetailService.selectPrevNo(paramMap));
				searchVO.setBbs_seq(BbsdetailService.selectPrevNo(paramMap));
				if(BbsdetailService.selectPrevNo(paramMap)==-1){
					out.println("<script language='javascript'>");
					out.println("alert('이전 게시글이 없습니다!');");
					out.println("</script>");
					out.flush();
					searchVO.setBbs_seq(Integer.parseInt(id));
				}
				//String을 받아서 int로 변환하므로 Int형변환 필요 X
			}else if("Next".equals(searchVO.getPreNextCode())) {
				searchVO.setBbs_seq(BbsdetailService.selectNextNo(id));
				//String을 받아서 int로 변환하므로 Int형변환 필요 X
				if(BbsdetailService.selectNextNo(id)==-1){
					out.println("<script language='javascript'>");
					out.println("alert('다음 게시글이 없습니다!');");
					out.println("</script>");
					out.flush();
					searchVO.setBbs_seq(Integer.parseInt(id));
				}
			}else {
				searchVO.setBbs_seq(Integer.parseInt(id));
				//id는 String인데 VO에 선언된 bbs_seq는 int이므로 형변환 필요
			}
			
			BbsDetailVO vo= BbsdetailService.selectSample(searchVO);
			model.addAttribute("temp2",vo);
			
			resultMap.put("resultCode", 1);
			//resultCode 목적: 컨트롤러 로직을 제대로 잘 탔는지 에러인지 구분하여 ajax에 결과값 전달
		}catch(Exception e) {
			resultMap.put("resultCode", 0);
			LOGGER.info(e.getMessage());
		}
		model.addAttribute("resultMap", resultMap); 
		return "../bbs/BbsDetail";
	}
	
	/**
	 * 글을 조회한다.
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	public BbsDetailVO selectSample(
			BbsDetailVO BbsDetailVO
			, @ModelAttribute("BbsListVO") SampleDefaultVO searchVO) 
	throws Exception {
		return BbsdetailService.selectSample(BbsDetailVO);
	}
	
	//수정 화면 이동
	@RequestMapping(value = "/boardUpdate.do")
	public String GoUpdate
	(
		@RequestParam("bbs_seq") String bbsSeq 
		,@ModelAttribute("BbsDetailVO") BbsDetailVO BbsDetailVO
		,ModelMap model
	) throws Exception {
		BbsDetailVO boardList = BbsdetailService.contentBoard(bbsSeq);
		
		model.addAttribute("temp", boardList);
		model.addAttribute("registerFlag", "modify");
		//수정화면 이동할때 실제 등록화면을 수정으로 재활용하는거니깐 flag 값을 별도로 지정
		return "../bbs/BbsUpdate";
	}
	
	/**
	 * 글을 수정한다.
	 * @param sampleVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateSample.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateSample
	(
			@RequestParam Map<String, Object> commandMap
			, @RequestParam("fileInfo") MultipartFile files
			, @RequestParam("bbs_seq") String id
			, @ModelAttribute("searchVO") SampleDefaultVO searchVO
			, BbsDetailVO BbsDetailVO
			, BindingResult bindingResult
			, Model model
			, SessionStatus status
	)throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			if (BbsDetailVO.getNotice_yn() == null) //check box 에 안눌러져 있으면 null이니깐
			{
				BbsDetailVO.setNotice_yn("N");
			}
	
			BbsDetailVO.setBbs_seq(Integer.parseInt(id));
			beanValidator.validate(BbsDetailVO, bindingResult);
	
			/*
			 * if (bindingResult.hasErrors()) { System.out.println("에러발생"); return
			 * "../bbs/BbsWrite"; }
			 */
			BbsdetailService.updateSample(BbsDetailVO);
			
			if (files.getOriginalFilename() != null) 
			{
				String enFileName = files.getOriginalFilename() + System.currentTimeMillis();
				String path = "C://down/";
				File Filepath = new File(path + enFileName);
				files.transferTo(Filepath);
				BbsDetailVO.setFile_nm(enFileName.toString());
				//File형태를 String으로 넣어주기 위해서(VO에 file_nm이 string형태임)
				BbsDetailVO.setOrg_file_nm(files.getOriginalFilename());
				BbsDetailVO.setFile_path(path);//파일 경로 설정
				BbsdetailService.updateFile(BbsDetailVO);//update문 실행
			}
			resultMap.put("resultCode", 1); 
		}catch(Exception e) {
			resultMap.put("resultCode", 0); 
			LOGGER.info(e.getMessage());
		}
		return resultMap;//원래는 완료후 목록으로 돌아갔음.
	}
		

	/**
	 * 글을 삭제한다.
	 * @param sampleVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteSample.do")
	@ResponseBody
	public Map<String, Object> deleteSample(
			BbsDetailVO sampleVO
			, @ModelAttribute("searchVO") SampleDefaultVO searchVO
			, SessionStatus status
			, @RequestParam("bbs_seq") String bbsSeq
			, Model model
	)throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			BbsdetailService.deleteSample(bbsSeq);
			BbsdetailService.deleteFile(bbsSeq);/*파일도 같이 삭제되도록 추가*/
			status.setComplete();
			resultMap.put("resultCode", 1);
		}catch(Exception e) {
			LOGGER.info(e.getMessage());
			resultMap.put("resultCode", 0);
		}
		/* return "forward:/egovSampleList.do"; */
		//return type : string
		model.addAttribute("resultMap", resultMap); 
		return resultMap;
	}
	
	@RequestMapping(value = "/fileDown.do", method = RequestMethod.POST)
	@ResponseBody
	public void fileDown
	(
		@RequestParam("bbs_seq") String bbsSeq
		,Model model
		,HttpServletResponse response
	) throws Exception {
		BbsDetailVO fileDown = BbsdetailService.fileDown(bbsSeq);
		//detailService의 fileDown 함수 실행
		
		String fileName = fileDown.getFile_nm();
		//인코딩전 원래 파일 이름
		
		File downFile = new File(fileDown.getFile_path()+fileName); 
		// downFile = 파일경로 + 파일이름
		
		fileDown.setOrg_file_nm(new String(fileDown.getOrg_file_nm().getBytes("utf-8"), "iso-8859-1"));
		//한글깨지는거 때문에 사용
		
		response.setContentType("application/octet-stream");
		//그냥 인코딩처리만 해주면 이상한 문자가 출력됨.
		//왜? 브라우저마다 기본적으로 문자코드를 해석하는 default가 다르기 때문임.
		//브라우저에게 우리는 application/octet-stream을 사용하겠다, 라는 메세지를 전달해주는 과정.
		//application/octet-stream 말고 다른 것 넣어도 된다.
		//application/octet-stream은 8비트 단위의 binary data라는 것을 표현함.
		
		response.setHeader("Content-Disposition", "attachment; filename=" + fileDown.getOrg_file_nm());
		//Content-Disposition 헤더에 파일이름을 세팅한다.
		
		response.setContentLength((int) downFile.length());
		//파일 콘텐츠 길이 설정
		//파일크기를 브라우저에 알려준다.
		
		if(downFile.length()>0 && downFile.isFile()) {
		//0바이트 이상이고, 해당파일이 존재할 경우
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(downFile));
			//사용자에게 보내주기 위해 스트림객체 생성 (인풋객체)
			
			FileCopyUtils.copy(in, response.getOutputStream());
			//아웃풋 스트림 객체 생성
			
			//FileCopyUtils: 스프링프레임워크 유틸 패키지안에 존재하는 클래스
			//파일 및 스트림 복사를 위한 간단한 유틸리티 메소드의 집합체이다.
			//메소드 copy(byte, file) : byte 타입 배열을 지정한 후에 File에 복사한다.
			
			in.close();
			//FileCopyUtils.copy를 사용한 다음엔 무조건 스트림객체를 닫아야 한다.
			//인풋스트림 객체 닫기
		}
		response.getOutputStream().flush(); //메모리 비우기(보낸다 라는 뜻이기도 함)
		response.getOutputStream().close(); //아웃풋스트림 객체 닫기
	}
	
}
