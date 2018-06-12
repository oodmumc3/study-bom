package com.crdev.bom.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    /**
     * services 패키지
     */
    @Pointcut("execution(* com.crdev.bom.services..*.*(..))")
    public void serviceMethod() {}

    /**
     * 커스텀 어노테이션 기반
     */
    @Pointcut("@annotation(com.crdev.bom.config.aop.Loggable)")
    public void annotationDriven() {}

    /**
     * 'services' 패키지 하위 모든 클래스의 메소드에 대해서는 메소드 호출 이력을 출력한다.
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("serviceMethod()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String signatureInfo = this.getSignatureInfo(joinPoint);

        System.out.println(String.format(">>> LOGGING START >>> %s", signatureInfo));
        Object retVal = joinPoint.proceed();
        System.out.println(String.format("<<< LOGGING END <<< %s : %s", signatureInfo, retVal != null ? retVal : ""));

        return retVal;
    }

    /**
     * 커스텀 어노테이션을 이용하여 해당 어노테이션이 적용된 모든 클래스의 메소드에 대해서는 메소드 소요시간을 출력한다.
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("annotationDriven()")
    public Object annotationAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String signatureName = joinPoint.getSignature().getName();

        System.out.println("##### annotation driven logging!!!");
        long currentTime = System.currentTimeMillis();
        Object retVal = joinPoint.proceed();
        System.out.println(String.format("%s.%s() >>> runtime : %dms", className, signatureName, System.currentTimeMillis() - currentTime));

        return retVal;
    }

    private String getSignatureInfo(JoinPoint joinPoint) {
        String signatureName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        StringBuilder sb = new StringBuilder();

        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String){
                    sb.append('\"');
                }
                sb.append(args[i]);
                if (args[i] instanceof String){
                    sb.append('\"');
                }
                if (i < args.length - 1) {
                    sb.append(',');
                }
            }
        }
        return String.format("%s.%s(%s)", className, signatureName, sb.toString());
    }

}
