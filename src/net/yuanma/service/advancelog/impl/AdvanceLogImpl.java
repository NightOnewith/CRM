package net.yuanma.service.advancelog.impl;

import java.util.List;

import net.yuanma.dto.AdvanceLogDto;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.AdvanceLogMapper;
import net.yuanma.model.AdvanceLog;
import net.yuanma.service.advancelog.AdvanceLogI;

public class AdvanceLogImpl implements AdvanceLogI {
	/**
	 * 添加推进
	 */
	@Override
	public int add(AdvanceLog advanceLog) {
		AdvanceLogMapper advanceLogMapper = (AdvanceLogMapper) ObjectFactory.getObject("advanceLogMapper");
		int row = advanceLogMapper.insert(advanceLog);
		return row;
	}

	@Override
	public List<AdvanceLogDto> findAll(AdvanceLogDto advanceLogDto) {
		AdvanceLogMapper advanceLogMapper = (AdvanceLogMapper) ObjectFactory.getObject("advanceLogMapper");
		List<AdvanceLogDto> advanceLogDtos = advanceLogMapper.findAll(advanceLogDto);
		return advanceLogDtos;
	}

}
