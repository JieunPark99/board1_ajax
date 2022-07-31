package egovframework.wini.edu.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.wini.edu.service.BbsListService;
import egovframework.wini.edu.service.BbsListVO;
import egovframework.wini.edu.service.BbsWriteService;
import egovframework.wini.edu.service.BbsWriteVO;

@Service("sampleWriteService")
public class BbsWriteServiceImpl extends EgovAbstractServiceImpl implements BbsWriteService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BbsWriteServiceImpl.class);

	/** SampleDAO */
	// TODO ibatis 사용
	@Resource(name = "BbsWriteDAO")
	private BbsWriteDAO BbsWriteDAO;
	// TODO mybatis 사용
	// @Resource(name="sampleMapper")
	// private SampleMapper sampleDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	/**
	 * 글을 등록한다.
	 * 
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public void insertSample(BbsListVO BbsListVO) throws Exception {
		System.out.println("BbsWriteServiceImpl=====insert Sample 실행");
		BbsWriteDAO.insertSample(BbsListVO);
	}

	@Override
	public void insertFile(BbsListVO BbsListVO) throws Exception {
		System.out.println("BbsWriteServiceImpl=====insert File 실행");
		BbsWriteDAO.insertFile(BbsListVO);
	}

	@Override
	public int selectSeqNum() throws Exception {
		return BbsWriteDAO.selectSeqNum();
	}
}