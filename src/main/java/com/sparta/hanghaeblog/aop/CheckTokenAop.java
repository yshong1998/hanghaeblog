//package com.sparta.hanghaeblog.aop;
//
//import com.sparta.hanghaeblog.entitiy.Message;
//import com.sparta.hanghaeblog.exception.ErrorCode;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//
//
//@Aspect
//public class CheckTokenAop {
//
//    @Pointcut("execution(public * com.sparta.hanghaeblog.jwt.JwtAuthFilter..*(..))")
//    private void cutJwtAuthFilter(){}
//
//    @Pointcut("execution(public * com.sparta.hanghaeblog.Dto.SignupRequestDto..*(..))")
//    private void cutSignupRequestDto(){}
//
//    @Pointcut("execution(public * com.sparta.hanghaeblog..*(..))")
//    private void cutController(){}
//
//    @AfterThrowing(value = "cutController()", throwing = "ex")
//    public void afterThrowing(JoinPoint joinPoint, Exception ex){
//        System.out.println("발생한 에러의 위치는 " + joinPoint.getSignature() + " 입니다.");
//        System.out.println("발생한 에러는 " + ex + " 입니다.");
//        throw new RuntimeException();
//    }
//
//
//
//}
