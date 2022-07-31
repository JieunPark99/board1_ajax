package egovframework.wini.edu.service;

public interface BbsWriteService {
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	void insertSample(BbsListVO BbsListVO) throws Exception;
	
	/*파일 업로드*/
	void insertFile(BbsListVO bbsListVO)  throws Exception;
	int selectSeqNum() throws Exception;
}
