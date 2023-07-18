package com.air.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.air.entity.AirImg;
import com.air.repository.AirImgRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightImgService {

	private String airImgLocation = "C:/air/flight";
	
	private final AirImgRepository airImgRepository;
	
	private final FileService fileService;
	
	public void saveAirImg(AirImg airImg, MultipartFile airImgFile) throws Exception {
		String oriImgName = airImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";
		
		// 1. 파이을 airImgLocation에 저장
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(airImgLocation, oriImgName, airImgFile.getBytes());
			
			imgUrl = "/images/air/" + imgName;
		}
		airImg.updateItemImg(oriImgName, imgName, imgUrl);
		airImgRepository.save(airImg);
	}
	
}
