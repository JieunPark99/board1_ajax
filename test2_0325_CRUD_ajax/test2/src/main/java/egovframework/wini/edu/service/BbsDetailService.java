package egovframework.wini.edu.service;

import java.util.Map;

public interface BbsDetailService {
	/**
	 * 글을 수정한다.
	 * @param sampleVO - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	void updateSample(BbsDetailVO BbsListVO) throws Exception;

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	void deleteSample(String id) throws Exception;

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	BbsDetailVO selectSample(BbsDetailVO bbsListVO) throws Exception;
	
	BbsDetailVO contentBoard(String bbsSeq);
	
	/*파일 다운로드*/
	BbsDetailVO fileDown(String bbsSeq) throws Exception;
	void updateFile(BbsDetailVO bbsDetailVO) throws Exception;
	void deleteFile(String id) throws Exception;
	int selectSeqNum() throws Exception;

	/*조회수*/
	void increaseViewCount(String id) throws Exception;

	/*이전글*/
	int selectPrevNo(Map<String, Object> paramMap) throws Exception;
	
	/*다음글*/
	int selectNextNo(String id) throws Exception;
}
