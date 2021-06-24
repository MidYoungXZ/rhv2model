package cn.demo.rhv2model.controller;

import cn.demo.rhv2model.req.CrosRhModelReq;
import cn.demo.rhv2model.req.RhModelReq;
import cn.demo.rhv2model.resp.CrosRhModelRes;
import cn.demo.rhv2model.resp.RhGradeModelResp;
import cn.demo.rhv2model.resp.RhModelRes;
import cn.demo.rhv2model.service.RhModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@Slf4j
public class RhModelController {

    @Autowired
    private RhModelService rhModelService;

    @PostMapping(value = "/rhModelRes")
    public RhModelRes getRhModelRes(@RequestBody RhModelReq rhModelReq) {
        try {
            return rhModelService.getRhModelRes(rhModelReq);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "系统忙不过来了", e);
        }
    }

    @PostMapping(value = "/v1/rhModelRes")
    public RhGradeModelResp getV1RhModelRes(@RequestBody RhModelReq rhModelReq) {
        return rhModelService.getRhGradeModelResp(rhModelReq);
    }

    @PostMapping(value = "/cros/rhModelRes")
    public CrosRhModelRes getCrosRhModelRes(@RequestBody CrosRhModelReq crosRhModelReq) {
        return rhModelService.getCrosRhModelRes(crosRhModelReq);
    }


    @PostMapping(value = "/cros/shns")
    public CrosRhModelRes getCrosShns(@RequestBody CrosRhModelReq crosRhModelReq) {
        return rhModelService.getCrosRhModelRes(crosRhModelReq);
    }


}
