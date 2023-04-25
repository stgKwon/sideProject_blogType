package com.blogtype.sideproject.controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"QnA API"})
@RestController
@RequestMapping("/blog-board")
@RequiredArgsConstructor
@Slf4j
public class QnaController {
}
