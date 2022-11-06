package com.millky.blog.presentation.controller.rest;

import com.millky.blog.domain.userapi.AiNaverDemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
//@RestController
@Slf4j
public class AiNaverDemoController {
	private final AiNaverDemoService aiNaverDemoService;

	@GetMapping("objectDetection")
	public void objectDetection() {
		aiNaverDemoService.objectDetection();
	}

	@GetMapping("translation")
	public void translation() {
		aiNaverDemoService.translation();
	}

	@GetMapping("textSummary")
	public void textSummary() {
		aiNaverDemoService.textSummary();
	}

	@GetMapping("searchTrend")
	public void searchTrend() {
		aiNaverDemoService.searchTrend();
	}
}
