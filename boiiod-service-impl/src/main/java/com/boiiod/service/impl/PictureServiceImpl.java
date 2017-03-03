package com.boiiod.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boiiod.entity.Picture;
import com.boiiod.entity.PictureExample;
import com.boiiod.mapper.PictureMapper;
import com.boiiod.service.PictureService;

@Service("pictureService")
public class PictureServiceImpl implements PictureService {

	private PictureMapper pictureMapper;


	public PictureMapper getPictureMapper() {
		return this.pictureMapper;
	}

	@Autowired
	public void setPictureMapper(PictureMapper pictureMapper) {
		this.pictureMapper = pictureMapper;
	}
}