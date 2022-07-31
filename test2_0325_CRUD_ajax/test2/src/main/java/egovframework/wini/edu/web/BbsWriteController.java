package egovframework.wini.edu.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.example.cmmn.EgovSampleExcepHndlr;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.wini.edu.service.BbsListService;
import egovframework.wini.edu.service.BbsListVO;
import egovframework.wini.edu.service.BbsWriteService;
import egovframework.wini.edu.service.SampleDefaultVO;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
public class BbsWriteController {
	
	/** EgovSampleService */
	@Resource(name = "sampleWriteService")
	private BbsWriteService bbsWriteService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	private static final Logger LOGGER = LoggerFactory.getLogger(BbsWriteController.class);
	
	/**
	 * 글 등록 화면을 조회한다.
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addSample.do", method = RequestMethod.GET)
	public String addSampleView(@ModelAttribute("BbsListVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("BbsListVO", new BbsListVO());
		   return "../bbs/BbsWrite";
	}

	/**
	 * 글을 등록한다.
	 * @param sampleVO - 등록할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	
	@RequestMapping(value = "/addSample.do", method = RequestMethod.POST) //참고:파일 업로드/다운로드시에는 반드시 post로 전송되야한다
	@ResponseBody
	public Map<String, Object> addSample
	(		
			@RequestParam Map<String, Object> commandMap,
			@RequestParam("fileInfo") MultipartFile files
			, @ModelAttribute("searchVO") SampleDefaultVO searchVO
			, BbsListVO BbsListVO
			, BindingResult bindingResult
			, ModelMap model
			, SessionStatus status
	)throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		// Server-Side Validation
		//beanValidator.validate(BbsListVO, bindingResult);// 제대로 했는지 체크
		try {
			if (BbsListVO.getNotice_yn() == null) //check box 에 안눌러져 있으면 null이니깐
			{
				BbsListVO.setNotice_yn("N");
			}
			
			/*
			 * if (bindingResult.hasErrors()) { System.out.println("에러발생");
			 * model.addAttribute("BbsListVO", BbsListVO); return "../bbs/BbsWrite"; }
			 */
			bbsWriteService.insertSample(BbsListVO);// insert실행
	
			if (files.getOriginalFilename() != null) 			//여기부터 파일 업로드 만약에 업로드가 안되있으면 (파일명이 null일경우) 그냥 무시
			{
				String enFileName = files.getOriginalFilename(); 
				//파일 업로드시 중복 이름 가능성을 줄이기 위해 해당 파일명 뒤에 현재 시간(초로 계산된거)을 넣음
				/*
				 * String enFileName = files.getOriginalFilename() + System.currentTimeMillis();
				 */
				//hash function + salt 이용하는게 더 좋으나 빠른 진행을 위해 간단하게 구현
				String path = "C://down/";						//경로 실제 경로는 WEB-INF 같이 외부사용자가 접근 할수 없는 경로로 지정하는것을 추천
				File Filepath = new File(path + enFileName);	//파일 경로와 저장될 파일명 지정
				files.transferTo(Filepath);						//업로드 실행
				BbsListVO.setFile_nm(enFileName.toString());	//File 자료형을 String으로 변환(VO에 지정된대로)						//DB에 정보 저장
				BbsListVO.setOrg_file_nm(files.getOriginalFilename());		//2
				BbsListVO.setFile_path(path);								//3
				BbsListVO.setBbs_seq(bbsWriteService.selectSeqNum());		//4 
				//해당 bbs_seq 값을 들고와야하는데 다른방식을 찾지못해 가장 마지막 시퀸스 번호(글 생성시 가장 마지막 시퀸스 번호를 저장하고 있으니깐)를 조회해서 VO객체에 값을 보관
				bbsWriteService.insertFile(BbsListVO);	
				//insert 실행
			} 
			resultMap.put("resultCode", 1);
		}catch(Exception e){
			resultMap.put("resultCode", 0);
			LOGGER.info(e.getMessage());
		}
		return resultMap;
	}

}
