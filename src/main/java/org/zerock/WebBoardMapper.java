package org.zerock;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebBoardMapper {
	List<Map> all();
}
