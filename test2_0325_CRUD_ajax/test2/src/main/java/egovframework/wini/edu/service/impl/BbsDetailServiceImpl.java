package egovframework.wini.edu.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.wini.edu.service.BbsDetailService;
import egovframework.wini.edu.service.BbsDetailVO;

@Service("DetailService")
public class BbsDetailServiceImpl extends EgovAbstractServiceImpl implements BbsDetailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BbsDetailServiceImpl.class);
	
	/** SampleDAO */
	// TODO ibatis 사용
	@Resource(name = "BbsDetailDAO")
	private BbsDetailDAO BbsDetailDAO;
	// TODO mybatis 사용
	// @Resource(name="sampleMapper")
	// private SampleMapper sampleDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	/**
	 * 글을 수정한다.
	 * 
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void updateSample(BbsDetailVO BbsDetailVO) throws Exception {
		BbsDetailDAO.updateSample(BbsDetailVO);
	}

	/**
	 * 글을 삭제한다.
	 * 
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void deleteSample(String bbsSeq) throws Exception {
		BbsDetailDAO.deleteSample(bbsSeq);
	}
	
	@Override
	public void deleteFile(String bbsSeq) throws Exception {
		BbsDetailDAO.deleteFile(bbsSeq);
	}

	/**
	 * 글을 조회한다.
	 * 
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	@Override
	public BbsDetailVO selectSample(BbsDetailVO BbsDetailVO) throws Exception {
		BbsDetailVO resultVO = BbsDetailDAO.selectSample(BbsDetailVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}
	
	@Override
	public BbsDetailVO contentBoard(String bbsSeq) {
		try {
			return BbsDetailDAO.selectBoardContent(bbsSeq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BbsDetailVO fileDown(String bbsSeq) throws Exception {
		return BbsDetailDAO.fileDown(bbsSeq);
	}

	@Override
	public void updateFile(BbsDetailVO bbsDetailVO) throws Exception {
		BbsDetailDAO.updateFile(bbsDetailVO);
	}

	@Override
	public int selectSeqNum() throws Exception {
		return BbsDetailDAO.selectSeqNum();
	}
	
	/*조회수 증가*/
	@Override
	public void increaseViewCount(String bbsSeq)  throws Exception {
		BbsDetailDAO.increaseViewCount(bbsSeq);
	}

	/*이전글*/
	@Override
	public int selectPrevNo(Map<String, Object> paramMap) throws Exception {
		return BbsDetailDAO.selectPrevNo(paramMap);
	}

	/*다음글*/
	@Override
	public int selectNextNo(String bbsSeq) throws Exception {
		return BbsDetailDAO.selectNextNo(bbsSeq);
	}

}
