package com.example.springbooth2.controllerr;

import com.example.springbooth2.dao.InfoRepository;
import com.example.springbooth2.domain.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class InfoController {

    @Autowired
    InfoRepository infoRepository;

    /**
     * 保存数据
     *
     * @return
     */
    @RequestMapping("/save")
    public String save() {
        Info info = new Info();
        //info.setId(2370);
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        info.setIsStatus(1);

        infoRepository.save(info);
        return "ok";
    }

    @RequestMapping("/findAll")
    public Iterable<Info> findAll() {

        return infoRepository.findAll();
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String post(@RequestBody List<Info> list) {
        infoRepository.saveAll(list);

        return "ok";
    }

    @RequestMapping("/del")
    public String del() {
        infoRepository.deleteAll();

        return "ok";
    }

    @RequestMapping("/del2")
    public String del2(int id) {
        infoRepository.deleteById(id);

        return "ok";
    }
}
