package com.hta.vue.service;

import java.util.List;

import com.hta.vue.domain.DTO;

public interface VueService {

	List<DTO> getList(int page, int limit);
	
	int getListCount();
	
	int insert(DTO dto);
	
	void update(DTO dto);
	
	void delete(int id);
}
