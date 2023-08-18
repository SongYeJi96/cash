package com.goodee.cash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.cash.mapper.AnswerMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AnswerService {

	@Autowired
	private AnswerMapper answerMapper;
}
