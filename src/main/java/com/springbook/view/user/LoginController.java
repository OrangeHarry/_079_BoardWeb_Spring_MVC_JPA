package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class LoginController {
	// GET방식으로 요청이 오면 로그인 화면으로 POST방식으로 요청이 오면 로그인 처리
//	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	@GetMapping(value="/login.do") //동일한 방식
	//userVO 이름으로 Command객체 생성
	//이름을 지정 안할 시에 자동으로 첫글자가 소문자로 변경된 이름으로 생성됨
	//Command의 이름 지정 : @ModelAttribute("user") -> user라는 이름의 Command객체 생성
	public String loginView(@ModelAttribute("user")UserVO vo) {
		System.out.println("로그인 화면으로 이동");
		// 로그인 화면으로 이동시 처음 입력값 세팅
		vo.setId("test");
		vo.setPassword("test123");
		return "login.jsp";
	}

//	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	@PostMapping(value="/login.do")
	public String login(UserVO vo, UserDAO userDAO, HttpSession session ) {
		System.out.println("로그인 처리");
		UserVO user = userDAO.getUser(vo);
		
		if(vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
		}
		
		if (user != null) {
			//UserVO의 name 변수를 session 데이터 보관소에 userName 이라는 키 값으로 저장
			session.setAttribute("userName", user.getName());
			//UserVO 자체를 session 데이터 보관소에 user라는 키값으로 저장
//			session.setAttribute("user", user);
			return "redirect:getBoardList.do";
		}
		else return "login.jsp";
	}

}
