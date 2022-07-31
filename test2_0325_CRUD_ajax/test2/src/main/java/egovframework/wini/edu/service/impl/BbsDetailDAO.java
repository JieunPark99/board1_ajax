package egovframework.wini.edu.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.wini.edu.service.BbsDetailVO;

@Repository("BbsDetailDAO")
public class BbsDetailDAO extends EgovAbstractDAO{

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateSample(BbsDetailVO vo) throws Exception {
		System.out.println("===> BbsListDAO의 updateSample 실행");
		update("BbsDetailDAO.updateSample", vo);
	}

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	public void deleteSample(String bbsSeq) throws Exception {
		System.out.println("===>BbsListDAO의  deleteSample 실행");
		delete("BbsDetailDAO.deleteSample", bbsSeq);
	}

	public void deleteFile(String bbsSeq) throws Exception {
		System.out.println("===>BbsListDAO의  deleteFile 실행");
		delete("BbsDetailDAO.deleteFile", bbsSeq);
	}
	
	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	public BbsDetailVO selectSample(BbsDetailVO vo) throws Exception {
		System.out.println("===>BbsDetailDAO의  selectSample 실행");
		return (BbsDetailVO) select("BbsDetailDAO.selectSample", vo);
	}
	
	public BbsDetailVO selectBoardContent(String bbsSeq) throws Exception {
		return (BbsDetailVO)select("BbsDetailDAO.selectSample", bbsSeq);
	}

	public BbsDetailVO fileDown(String bbsSeq) throws Exception{
		return (BbsDetailVO)select("BbsDetailDAO.fileDown",bbsSeq);
	}

	public void updateFile(BbsDetailVO bbsDetailVO) throws Exception {
		update("BbsDetailDAO.updateFile", bbsDetailVO);
	}

	public int selectSeqNum() {
		System.out.println("BbsDetailDAO===> selectSeqNum");
		return (int) select("BbsDetailDAO.seqFind");
	}
	
	/*조회수 증가*/
	public void increaseViewCount(String bbsSeq) throws Exception {
		System.out.println("===>BbsListDAO의 increaseViewCount 실행");
		update("BbsDetailDAO.increaseViewCount",bbsSeq);
	}
	
	/*이전글*/
	public int selectPrevNo(Map<String, Object> paramMap) throws Exception{
		System.out.println("===>BbsListDAO의 selectPrevNo 실행");
		return (int) select("BbsDetailDAO.selectPrevNo",paramMap);
	}
	
	/*다음글*/
	public int selectNextNo(String bbsSeq) throws Exception{
		System.out.println("===>BbsListDAO의 selectNextNo 실행");
		return (int) select("BbsDetailDAO.selectNextNo",bbsSeq);
	}
}
