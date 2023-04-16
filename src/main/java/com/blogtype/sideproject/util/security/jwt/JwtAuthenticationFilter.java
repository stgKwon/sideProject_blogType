package com.blogtype.sideproject.util.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    String token = jwtTokenProvider.resolveToken(request);

    if (token != null && !token.isEmpty()) {
      token = token.replaceAll("Bearer", "");
    }

    if (token != null && jwtTokenProvider.validateToken(token).equals(JwtReturn.EXPIRED)) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
      return;
    }
    if (token != null && jwtTokenProvider.validateToken(token).equals(JwtReturn.FAIL)) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN); //403
      return;
    }
    if (token != null && jwtTokenProvider.validateToken(token).equals(JwtReturn.SUCCESS)) {
      Authentication authentication = jwtTokenProvider.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }
}
