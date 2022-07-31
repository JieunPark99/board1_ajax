package egovframework.wini.edu.service.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.wini.edu.service.BbsListVO;
import egovframework.wini.edu.service.BbsWriteVO;
import egovframework.wini.edu.service.SampleDefaultVO;

@Repository("BbsWriteDAO")
public class BbsWriteDAO extends EgovAbstractDAO{
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSample(BbsListVO vo) throws Exception {
		System.out.println("BbsWriteDAO===> insertSample 등록");
		return (String) insert("BbsWriteDAO.insertSample", vo);
	}
	
	/**
	 * 파일을 등록한다.
	 */
	public void insertFile(BbsListVO vo) throws Exception {
		System.out.println("BbsWriteDAO===> insert File");
		insert("BbsWriteDAO.insertFile", vo);
	}

	/**
	 * 얘는 뭐지? 일련번호?..
	 * string 대신 int로 받음. 나는 bbs_seq가 int라서.
	 */
	public int selectSeqNum() {
		System.out.println("BbsWriteDAO===> selectSeqNum");
		return (int) select("BbsWriteDAO.seqFind");
	}
}
