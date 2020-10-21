package com.whyisee.getdata.web;

import com.whyisee.getdata.core.Result;
import com.whyisee.getdata.core.ResultGenerator;
import com.whyisee.getdata.model.TcGdConfigmain;
import com.whyisee.getdata.service.TcGdConfigmainService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* Created by zoukh on 2020/10/21.
*/
@RestController
@RequestMapping("/configmain")
public class TcGdConfigmainController {
    @Resource
    private TcGdConfigmainService tcGdConfigmainService;

    @PostMapping
    public Result add(@RequestBody TcGdConfigmain tcGdConfigmain) {
        tcGdConfigmainService.save(tcGdConfigmain);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        tcGdConfigmainService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody TcGdConfigmain tcGdConfigmain) {
        tcGdConfigmainService.update(tcGdConfigmain);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        TcGdConfigmain tcGdConfigmain = tcGdConfigmainService.findById(id);
        return ResultGenerator.genSuccessResult(tcGdConfigmain);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TcGdConfigmain> list = tcGdConfigmainService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @RequestMapping(value="/search",method = {RequestMethod.GET})
    public @ResponseBody
    Result searchForGet(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, @RequestParam Map<String,Object> data) throws Exception {
        PageHelper.startPage(page, size);
        List<TcGdConfigmain> list = tcGdConfigmainService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
