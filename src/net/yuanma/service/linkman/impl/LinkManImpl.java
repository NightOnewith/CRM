package net.yuanma.service.linkman.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.yuanma.consts.ConstVal;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.LinkMainMapper;
import net.yuanma.model.LinkMain;
import net.yuanma.service.linkman.LinkManI;

public class LinkManImpl implements LinkManI {
	/**
	 * 添加员工
	 */
	@Override
	public int add(LinkMain linkMain) {
		LinkMainMapper linkMainMapper = (LinkMainMapper) ObjectFactory.getObject("linkMainMapper");
		int row = linkMainMapper.insert(linkMain);
		return row;
	}

	/**
	 * 查询所有
	 */
	@Override
	public List<LinkMain> findAll(LinkMain linkMain) {
		LinkMainMapper linkMainMapper = (LinkMainMapper) ObjectFactory.getObject("linkMainMapper");
		List<LinkMain> list = linkMainMapper.findAll(linkMain);
		return list;
	}

	@Override
	public PageInfo<LinkMain> findAllByPage(LinkMain linkMain, Integer pageNum) {
		LinkMainMapper linkMainMapper = (LinkMainMapper) ObjectFactory.getObject("linkMainMapper");
		PageHelper.startPage(pageNum, ConstVal.PAGE_SIZE);
		List<LinkMain> list = linkMainMapper.findAll(linkMain);
		return new PageInfo<LinkMain>(list);
	}

	/**
	 * 根据id查询
	 */
	@Override
	public LinkMain selectById(int id) {
		LinkMainMapper linkMainMapper = (LinkMainMapper) ObjectFactory.getObject("linkMainMapper");
		LinkMain linkMain = linkMainMapper.selectById(id);
		return linkMain;
	}

	/**
	 * 修改
	 */
	@Override
	public void update(LinkMain linkMain) {
		LinkMainMapper linkMainMapper = (LinkMainMapper) ObjectFactory.getObject("linkMainMapper");
		linkMainMapper.updateByPrimaryKeySelective(linkMain);

	}

}
