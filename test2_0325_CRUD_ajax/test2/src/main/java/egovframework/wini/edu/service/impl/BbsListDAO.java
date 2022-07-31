
package egovframework.wini.edu.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.wini.edu.service.BbsListVO;
import egovframework.wini.edu.service.SampleDefaultVO;

import org.springframework.stereotype.Repository;


@Repository("BbsListDAO")
public class BbsListDAO extends EgovAbstractDAO {

	/**
	 * 글 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 목록
	 * @exception Exception
	 */
	
	  public List<?> selectSampleList(SampleDefaultVO searchVO) throws Exception {
		  System.out.println("===>BbsListDAO의  selectSampleList 실행");
		  return list("BbsListDAO.selectSampleList", searchVO); 
	  }
	
	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 총 갯수
	 * @exception
	 */
	public int selectSampleListTotCnt(SampleDefaultVO searchVO)  throws Exception {
		System.out.println("===> selectSampleListTotCnt 실행");
		return (Integer) select("BbsListDAO.selectSampleListTotCnt", searchVO);
	}
	
}
