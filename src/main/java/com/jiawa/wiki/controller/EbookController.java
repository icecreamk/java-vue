package com.jiawa.wiki.controller;

import com.jiawa.wiki.req.EbookQueryReq;
import com.jiawa.wiki.resp.CommonResp;
import com.jiawa.wiki.service.EbookService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    private static final Logger log = LoggerFactory.getLogger(EbookController.class);
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookQueryReq req) {
        CommonResp<List<EbookQueryReq>> resp = new CommonResp<>();
        List<EbookQueryReq> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}
