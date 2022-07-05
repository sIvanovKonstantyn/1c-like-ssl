package main.java.com.home.demos.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

import static net.logstash.logback.marker.Markers.append;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut(value = "execution(* com.home.demos.documents.LoggingDemoDocument.*(..))")
    public void executing() {
    }

    @Around(value = "executing()")
    public Object recordSuccessfulExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info(append("context", takeCommonCallFields(joinPoint)), "Call started");

        try {
            Object object = joinPoint.proceed();
            log.info(append("context", takeCommonCallFields(joinPoint)), "Call finished");

            return object;

        } catch (Exception e) {
            log.error(append("context", takeCommonCallFields(joinPoint)), "Call error", e);
            throw e;
        }

    }

    private Map<String, String> takeCommonCallFields(ProceedingJoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Map<String, String> result = new LinkedHashMap<>();
        result.put("user", request.getUserPrincipal() == null ? "" : request.getUserPrincipal().getName());
        result.put("ip", request.getLocalAddr());
        result.put("clientType", "Web");
        result.put("traceId", request.getHeader("traceId"));
        result.put("methodName", joinPoint.getSignature().getName());
        result.put("object", joinPoint.getTarget().toString());

        return result;
    }
}
