package com.company.project.service.impl;

import com.company.project.dao.TcGdDatasourceMapper;
import com.company.project.model.TcGdDatasource;
import com.company.project.service.TcGdDatasourceService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/10/20.
 */
@Service
@Transactional
public class TcGdDatasourceServiceImpl extends AbstractService<TcGdDatasource> implements TcGdDatasourceService {
    @Resource
    private TcGdDatasourceMapper tcGdDatasourceMapper;

}
