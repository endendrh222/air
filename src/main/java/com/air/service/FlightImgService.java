package com.air.service;

import org.springframework.beans.factory.annotation.Value;
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
	@Value("${airImgLocation}")
	private String airImgLocation;
	
	private final AirImgRepository airImgRepository;
	
	private final FileService fileService;
	
	public void saveAirImg(AirImg airImg, MultipartFile airImgFile) throws Exception {
		String oriImgName = airImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";
		
		// 1. 파일을 airImgLocation에 저장
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(airImgLocation, oriImgName, airImgFile.getBytes());
			
			imgUrl = "/images/air/" + imgName;
		}
		airImg.updateAirImg(oriImgName, imgName, imgUrl);
		airImgRepository.save(airImg);
	}
	
	// 이미지 업데이트 메소드
	public void updateAirImg(Long airImgid, MultipartFile airImgFile) throws Exception {
		if(!airImgFile.isEmpty()) { //첨부한 이미지 파일이 있으면
			//해당 이미지르 가져오고
			AirImg savedAirImg = airImgRepository.findById(airImgid).orElseThrow(EntityNotFoundException::new);
			
			//기존 이미지 파일 C:/air 폴더에서 삭제
			if(!StringUtils.isEmpty(savedAirImg.getImgName())) {
				fileService.deleteFile(airImgLocation + "/" + savedAirImg.getImgName());
			}
			//수정된 이미지 파일 C:/air에 업로드
			String oriImgName = airImgFile.getOriginalFilename();
			String imgName = fileService.uploadFile(airImgLocation, oriImgName, airImgFile.getBytes());
			String imgUrl = "/images/air/" + imgName;
			
			savedAirImg.updateAirImg(oriImgName, imgName, imgUrl);
		}
	}
	
}
