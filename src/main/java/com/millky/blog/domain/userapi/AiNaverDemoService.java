package com.millky.blog.domain.userapi;


import com.millky.blog.domain.dto.userapi.ainaver.constant.LangEnum;
import com.millky.blog.domain.dto.userapi.ainaver.req.SearchTrendRequestDto;
import com.millky.blog.domain.dto.userapi.ainaver.req.SentimentRequestDto;
import com.millky.blog.domain.dto.userapi.ainaver.req.TextSummaryRequestDto;
import com.millky.blog.domain.dto.userapi.ainaver.req.TranslationRequestDto;
import com.millky.blog.domain.dto.userapi.ainaver.res.SearchTrendResponseDto;
import com.millky.blog.domain.dto.userapi.ainaver.res.SentimentResponseDto;
import com.millky.blog.domain.dto.userapi.ainaver.res.TextSummaryResponseDto;
import com.millky.blog.domain.dto.userapi.ainaver.res.TranslationResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiNaverDemoService {
	private final AiNaverService aiNaverService;

	public void objectDetection() {
		aiNaverService.objectDetection();
	}

	public void translation() {
		final TranslationRequestDto requestDto = TranslationRequestDto.builder().sourceLang(LangEnum.KO).targetLang(
			LangEnum.EN).text("오늘도 좋은 하루되세요.").build();
		final TranslationResponseDto responseDto = aiNaverService.translation(requestDto);

		System.out.println("Plan Text : " + requestDto.getText());
		System.out.println("Translation Text : " + responseDto.getMessage().getResult().getTranslatedText());
	}

	public void textSummary() {
		final TextSummaryRequestDto requestDto = TextSummaryRequestDto.builder().document(
		TextSummaryRequestDto.DocumentRequestDto.builder().content(
			"넷플릭스 한국 드라마 '오징어 게임' 출연진이 미국 NBC 유명 토크쇼 '지미 팰런쇼' 출연을 예고한 가운데 진행자이자 유명 코미디언 지미 팰런이 달고나 게임에 직접 도전했다."
				+ "6일 지미 팰런은 자신의 사회관계망서비스(SNS)를 통해 'Squid game Cookie'라는 제목의 짧은 영상을 게재했다."
				+ "영상에 따르면 지미 팰런이 직접 프라이팬에 소다와 설탕을 붓고 달고나 제조한다. 이후 지미 팰런은 자신의 이름 이니셜인 'JF'가 새겨진 달고나를 직접 손에 들고 있다. 열심히 달고나를 핥아봤지만 바늘을 대는 순간 달고나가 부서지면서 총소리가 울린다. 이어 지미 팰런은 바닥에 쓰러지면서 영상은 마무리된다."
				+ "'오징어 게임'에서 주연 배우로 활약한 이정재, 박해수, 정호연, 위하준은 6일(현지시간 5일) 지미 팰런쇼 녹화에 참여했다. 다만 신종 코로나바이러스 감염증(코로나19) 상황을 고려해 한국과 미국을 화상으로 연결해 인터뷰하는 방식으로 진행됐다. 해당 방송은 7일 오후에 공개될 예정이다."
				+ "한편, 지난 9월 17일 공개된 오징어 게임은 456억 원의 상금이 걸린 의문의 서바이벌 게임에 참가한 사람들이 최후의 승자가 되기 위해 목숨을 걸고 극한의 게임에 도전하는 내용을 담고 있다. 연일 기준 글로벌 OTT 콘텐츠 순위 집계에서도 1위를 차지하며 흥행을 이어가고 있다."
				+ "'오징어 게임'의 흥행에 힘입어 출연 배우들의 인스타그램 팔로워는 폭증했다."
				+ "새벽 역의 정호연은 7일 9시 기준 1562만 명이 인스타그램을 팔로우했다. 이는 한국 여자 배우 중 팔로워 최고 기록이다. '오징어 게임' 공개 전 그의 팔로워는 약 40만명이었다."
				+ "이 놀라운 효과에 주인공 기훈 역의 이정재, 상우 역의 박해수도 뒤늦게 인스타그램 계정을 개설했다. 이정재 인스타그램은 개설 5일만에 500만 팔로우로 껑충 뛰었다."
		).build()).option(TextSummaryRequestDto.OptionRequestDto.builder().language("ko").build()).build();

		final TextSummaryResponseDto responseDto = aiNaverService.textSummary(requestDto);

		System.out.println("Plan Text : " + requestDto.getDocument().getContent());
		System.out.println("Summary Text : " + responseDto.getSummary());
	}

	public void sentiment() {
		final SentimentRequestDto requestDto = SentimentRequestDto.builder().content(
				"평소 먼지나 반려동물 털 알레르 평소 먼지나 반려동물 털 알레르기, 비염이 심해서 공기청정기를 알아보고 있었는데, TV 광고를 통해서 LG 에어로타워가 눈에 들어오더라고요! 그렇게 신혼집 입주를 하고 추가적으로 구입한 공기청정기라서, 정말 열심히 성능, 가격 비교해보고 선택했어요. 요즘 가장 핫한 공기청정기라서 구입을 하고 나서, 배송이 조금 지연된다는 연락을 받았는데 다행히도 7일만에 받아보았어요! 설치기사님께서 오셔서 정말 친절하게 설치 + 안내를 해주셔서 더 기분 좋게 받아볼 수 있었답니다! 역시 비주얼 + 성능 중에 가장 TOP인 . 저희 신혼집에 정말 잘 어울리는 ! 저희 집에서는 음식할 때 환풍기가 제대로 작동이 안되서 냄새 빼는데 시간이 정말 오래걸렸었는데, 에어로타워를 들이고 나니깐 . 온풍까지 되는 기종을 선택했더니 요즘처럼 추운 겨울엔 아주 딱인 것 같아요. 어플 다운 받아서 UP가전 업그레이드부터 . 정말 만족 그 자체인 LG 에어로타워 :-) 고민마시고 구입하세요!!"
				).build();

		final SentimentResponseDto responseDto = aiNaverService.sentiment(requestDto);

		System.out.println("Plan Text : " + requestDto.getContent());
		System.out.println("Summary Text : " + responseDto.getDocument().getSentiment());
	}


	public void searchTrend() {
		final SearchTrendRequestDto requestDto = SearchTrendRequestDto.builder()
																		.startDate("2021-09-09")
																		.endDate("2021-10-09")
																		.timeUnit("date")
																		.keywordGroups(Arrays.asList(
																			SearchTrendRequestDto.KeywordRequestDto.builder().groupName("오징어게임").keywords(Arrays.asList("오징어게임")).build()))
																		.build();

		final SearchTrendResponseDto responseDto = aiNaverService.searchTrend(requestDto);

		System.out.println("Search Trend Keywords : " +  requestDto.getKeywordGroups());
		System.out.println("Search Trend Result");
		System.out.println(responseDto.getResults().get(0).getData().toString().replaceAll("\\),", "\\)\n"));

	}
}
