package com.whyisee.getdata.service.impl;

import com.whyisee.getdata.dao.TcGdConfigmainMapper;
import com.whyisee.getdata.model.TcGdConfigmain;
import com.whyisee.getdata.service.TcGdConfigmainService;
import com.whyisee.getdata.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zoukh on 2020/10/21.
 */
@Service
@Transactional
public class TcGdConfigmainServiceImpl extends AbstractService<TcGdConfigmain> implements TcGdConfigmainService {
    @Resource
    private TcGdConfigmainMapper tcGdConfigmainMapper;

}
