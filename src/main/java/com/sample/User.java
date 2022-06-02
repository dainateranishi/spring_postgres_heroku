package com.sample;

import lombok.Data;

/**
 * userテーブルの1件のデータを表すDomainクラス.
 * 
 * @author teranishidaina
 *
 */
@Data
public class User {
	
	/** ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** 年齢 */
	private Integer age;
}
