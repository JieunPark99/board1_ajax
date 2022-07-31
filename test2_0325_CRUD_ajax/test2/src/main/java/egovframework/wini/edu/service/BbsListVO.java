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
package egovframework.wini.edu.service;

//TCOM_BBS(게시글)
public class BbsListVO extends SampleDefaultVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int test=1;    	//게시글 일련번호
	private int bbs_seq;    	//게시글 일련번호
	private String title;		//제목
	private String name;		//작성자명
	private String content;		//내용
	private String passwd;		//비밀번호
	private String open_yn;		//공개여부
	private String notice_yn;	//상단공지여부
	private String reg_dt;		//등록일시
	private String mod_dt;		//수정일시
	private int view_count; 	//조회수
	private String id;		//아이디
	private String description;		
	private String useYn;		
	private String regUser;
	
	/*지금은 안 쓰는데 게시글 번호 할때 사용했던 rownum*/
	private String rnum;
	
	/*파일 업로드, 다운로드 관련*/
	private String atch_seq;
	private String file_path;
	private String file_nm;
	private String org_file_nm;
	
	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public int getTest() {
		return test;
	}

	public void setTest(int test) {
		this.test = test;
	}
	
	public int getBbs_seq() {
		return bbs_seq;
	}



	public void setBbs_seq(int bbs_seq) {
		this.bbs_seq = bbs_seq;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getPasswd() {
		return passwd;
	}



	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}



	public String getOpen_yn() {
		return open_yn;
	}



	public void setOpen_yn(String open_yn) {
		this.open_yn = open_yn;
	}



	public String getNotice_yn() {
		return notice_yn;
	}



	public void setNotice_yn(String notice_yn) {
		this.notice_yn = notice_yn;
	}

	public String getReg_dt() {
		return reg_dt;
	}



	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}



	public String getMod_dt() {
		return mod_dt;
	}



	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}



	public int getView_count() {
		return view_count;
	}



	public void setView_count(int view_count) {
		this.view_count = view_count;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getUseYn() {
		return useYn;
	}



	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}



	public String getRegUser() {
		return regUser;
	}



	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

	public String getAtch_seq() {
		return atch_seq;
	}

	public void setAtch_seq(String atch_seq) {
		this.atch_seq = atch_seq;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_nm() {
		return file_nm;
	}

	public void setFile_nm(String file_nm) {
		this.file_nm = file_nm;
	}

	public String getOrg_file_nm() {
		return org_file_nm;
	}

	public void setOrg_file_nm(String org_file_nm) {
		this.org_file_nm = org_file_nm;
	}

	@Override
	public String toString() {
		return "BbsListVO [test=" + test + ", bbs_seq=" + bbs_seq + ", title=" + title + ", name=" + name + ", content="
				+ content + ", passwd=" + passwd + ", open_yn=" + open_yn + ", notice_yn=" + notice_yn + ", reg_dt="
				+ reg_dt + ", mod_dt=" + mod_dt + ", view_count=" + view_count + ", id=" + id + ", description="
				+ description + ", useYn=" + useYn + ", regUser=" + regUser + ", rnum=" + rnum + ", atch_seq="
				+ atch_seq + ", file_path=" + file_path + ", file_nm=" + file_nm + ", org_file_nm=" + org_file_nm + "]";
	}

	
}
