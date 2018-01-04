package com.ral.service.mail;

import java.io.File;
import java.util.List;

public interface IJMailService {

	void send(String to, String subject, String html) throws Exception;
	
	void send(String to, String subject, String html, List<File> files)throws Exception;
	
	void send(List<String> to, String subject, String html, List<File> files)throws Exception;
	
	void send(List<String> to, List<String> copy, String subject, String html, List<File> files)throws Exception;
	
}
