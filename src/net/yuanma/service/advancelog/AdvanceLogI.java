package net.yuanma.service.advancelog;

import java.util.List;

import net.yuanma.dto.AdvanceLogDto;
import net.yuanma.model.AdvanceLog;

public interface AdvanceLogI {
	// 增加
	int add(AdvanceLog advanceLog);

	// 查询所有
	List<AdvanceLogDto> findAll(AdvanceLogDto advanceLogDto);
	// //根据id查询
	// AdvanceLog select(int id);
}
