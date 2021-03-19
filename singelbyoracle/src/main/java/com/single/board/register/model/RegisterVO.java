package com.single.board.register.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVO {
	private String userid;
	private String userpw;
	private String email1;
	private String email2;
	private String salt;
	private String path;
}
