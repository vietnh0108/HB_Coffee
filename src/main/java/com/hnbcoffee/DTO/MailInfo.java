package com.hnbcoffee.DTO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {

	String from;
	String to;
	String[] cc;
	String[] bcc;
	String subject;
	String body;
	List<File> files = new ArrayList<>();
	
	public MailInfo(String to, String subject, String body) {
		this.from = "H&B COFFEE <poly@fpt.edu.vn>";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
}
