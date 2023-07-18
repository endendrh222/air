package com.air.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "air_img")
@Getter
@Setter
public class AirImg extends BaseEntity{
	
	@Id
	@Column(name = "air_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String imgName;	// 바뀐 이미지 파일명(중복방지)
	
	private String oriImgName;	// 원본 이미지 파일명
	
	private String imgUrl;	// 이미지 조회 경로
	
	private String repimgYn;	// 대표이미지 여부

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "air_id")
	private Air air;
	
	public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
		this.oriImgName = oriImgName;
		this.imgName = imgName;
		this.imgUrl = imgUrl;
	}
}
