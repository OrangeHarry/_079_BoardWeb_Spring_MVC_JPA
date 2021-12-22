package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserVO;

@Service
@Aspect
public class AfterReturningAdvice {
	//returnObj : 포인트컷 메소드 실행 후 리턴값을 받을 객체
	//@Before("getPointcut()")
	//JoinPoint 객체를 제외한 매개변수가 존재할 때는 속성값을 따로 지정해줘야함
	@AfterReturning(pointcut="PointcutCommon.getPointcut()", returning="returnObj")
	public void afterReturningAdvice(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		if(returnObj instanceof UserVO) {
			UserVO user = (UserVO) returnObj;
			if(user.getRole().equals("Admin")) {
				System.out.println(user.getName() + "로그인(Admin)");
			}
		}
		
		System.out.println("[사후 처리] " + method + "()메소드 리턴값 : " + returnObj.toString());
	}

}
