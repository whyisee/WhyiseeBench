package com.whyisee.zsql.zdb.web;

import com.whyisee.zsql.zdb.store.imp.KVStoreBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/24 17:28
 * @version 1.0
 * @Modified By:
 * @used in: zdb
 */
@Controller
@RequestMapping("/cli")
public class ZdbConsoleController {
    @Autowired
    private KVStoreBase kvStoreBase;

    @RequestMapping(value="/put",method = {RequestMethod.POST})
    public @ResponseBody String put(ModelMap modelMap, HttpServletRequest request, @RequestBody Map<String, Object> map) throws IOException {
        kvStoreBase.put(map.get("key").toString(),map.get("value").toString());
        return map.toString();
    }

    @RequestMapping(value="/put",method = {RequestMethod.GET})
    public @ResponseBody String putForGet(ModelMap modelMap, HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException {
        kvStoreBase.put(map.get("key").toString(),map.get("value").toString());
        return map.toString();
    }

    @RequestMapping(value="/get",method = {RequestMethod.POST})
    public @ResponseBody String get(ModelMap modelMap, HttpServletRequest request, @RequestBody Map<String, Object> map) throws IOException {

        return kvStoreBase.get(map.get("key").toString());
    }

    @RequestMapping(value="/get",method = {RequestMethod.GET})
    public @ResponseBody String getForGet(ModelMap modelMap, HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException {
        return kvStoreBase.get(map.get("key").toString());
    }



}
