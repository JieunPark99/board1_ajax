/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.wini.edu.service.impl;

import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.wini.edu.service.BbsListService;
import egovframework.wini.edu.service.BbsListVO;
import egovframework.wini.edu.service.SampleDefaultVO;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("ListService")
public class BbsListServiceImpl extends EgovAbstractServiceImpl implements BbsListService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BbsListServiceImpl.class);

	/** SampleDAO */
	// TODO ibatis 사용
	@Resource(name = "BbsListDAO")
	private BbsListDAO BbsListDAO;
	// TODO mybatis 사용
	// @Resource(name="sampleMapper")
	// private SampleMapper sampleDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	/**
	 * 글 목록을 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	@Override
	public List<?> selectSampleList(SampleDefaultVO searchVO) throws Exception {
		return BbsListDAO.selectSampleList(searchVO);
	}

	/**
	 * 글 총 갯수를 조회한다. @param searchVO - 조회할 정보가 담긴 VO @return 글 총 갯수 @exception
	 */
	///여기 여기 원래 BbsListVO 라고 되어있던걸 SampleDefaultVO로 수정함.
	//BbsListDAO에 BbsListVO로 되어있었는데 SampleDefaultVO로 수정했음.
	@Override
	public int selectSampleListTotCnt(SampleDefaultVO searchVO)  throws Exception {
		return BbsListDAO.selectSampleListTotCnt(searchVO);
	}

	
}
