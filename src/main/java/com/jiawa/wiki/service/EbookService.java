package com.jiawa.wiki.service;

import com.jiawa.wiki.domain.Ebook;
import com.jiawa.wiki.domain.EbookExample;
import com.jiawa.wiki.mapper.EbookMapper;
import com.jiawa.wiki.req.EbookQueryReq;
import com.jiawa.wiki.util.CopyUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookQueryReq> list(EbookQueryReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");

        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);


//        List<EbookQueryReq> respList = new ArrayList<>();
//        for (Ebook ebook : ebookList) {
////            EbookQueryReq ebookResp = new EbookQueryReq();
////            BeanUtils.copyProperties(ebook, ebookResp);
//            EbookQueryReq ebookResp = CopyUtil.copy(ebook, EbookQueryReq.class);
//
//            respList.add(ebookResp);
//        }

        return CopyUtil.copyList(ebookList, EbookQueryReq.class);
    }
}
