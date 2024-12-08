package com.karsten.assessment.config;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

// !!Explainer!! This custom Tracing class increases visibility into the behaviour of incoming requests and responses.
// A similar design can also be followed or extended for metrics. This implementation does expect/assume HTTP as the
// communication layer for this application. I prefer outsourcing other protocols like TCP or HTTPS to dedicated
// solutions like a Load Balancer or Ingress Gateway. This way microservices and their developers just need to worry
// about relatively simple API contracts and business logic, instead of more complex TCP performance tweaking or HTTPS
// certificate lifecycles for example. 
@Component
public class HttpTracing implements Filter, AuthenticationEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpTracing.class);

    // To ensure tracing of requests that pass authentication.
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            chain.doFilter(request, response);
        } catch (Exception exception) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOGGER.error(Trace.from(httpRequest, httpResponse).toString(), exception);
            return;
        }

        LOGGER.info(Trace.from(httpRequest, httpResponse).toString());
    }

    // To ensure tracing of requests that have authentication issues.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        LOGGER.info(Trace.from(request, response).toString());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    // Custom Trace that standardises the format used for logging.
    // Can be easily extended to also include body, redirects, timing etc.
    private record Trace(HttpServletRequest request, HttpServletResponse response) {
        static final String TRACING_FORMAT = "%s|%s|%d|%s|%s";

        @Override
        public String toString() {
            return String.format(TRACING_FORMAT,
                    request.getRequestedSessionId(),
                    UUID.randomUUID(),
                    response.getStatus(),
                    request.getMethod(),
                    request.getRequestURL());
        }

        static public Trace from(HttpServletRequest request, HttpServletResponse response) {
            return new Trace(request, response);
        }
    }

}

