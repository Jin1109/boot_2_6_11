package com.hta.vue.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hta.vue.domain.DTO;

@Mapper
public interface VueMapper {
	public List<DTO> getList(HashMap<String, Integer> map);
	public int getListCount();
	public int insert(DTO dto);
	public void update(DTO dto);
	public void delete(int id);
}
