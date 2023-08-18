package com.goodee.cash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.cash.mapper.LoginMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginService {

	@Autowired
	private LoginMapper loginMapper;
}
