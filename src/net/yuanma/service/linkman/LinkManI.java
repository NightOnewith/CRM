package net.yuanma.service.linkman;

import java.util.List;

import com.github.pagehelper.PageInfo;

import net.yuanma.model.LinkMain;

public interface LinkManI {
	//添加联系人
	int add(LinkMain linkMain);
	
	//查询所有
	List<LinkMain> findAll(LinkMain linkMain);
	
	PageInfo<LinkMain> findAllByPage(LinkMain linkMain, Integer pageNum);
	
	//根据ID查询
	LinkMain selectById(int id);
	
	//修改
	void update(LinkMain linkMain);
}
