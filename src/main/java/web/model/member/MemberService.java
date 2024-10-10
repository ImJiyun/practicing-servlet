package web.model.member;

import web.controller.XmlBeanFactory;

public class MemberService {
	
	MemberDao memberDao;
	
	public MemberService() throws MyException {
		// memberDao = new MemberDao();
		memberDao = (MemberDao) XmlBeanFactory.getBeans2().get("memberDao");
	}
	
	public Member login(String id, String pw) throws MyException {
		return memberDao.login(id, pw);
	}

}
