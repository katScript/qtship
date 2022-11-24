package com.spring.app.price.service;

import com.spring.app.helper.csv.CSVHelper;
import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.price.models.Rule;
import com.spring.app.price.models.RuleArea;
import com.spring.app.price.models.RulePriceList;
import com.spring.app.price.payload.RuleAreaData;
import com.spring.app.price.payload.RuleData;
import com.spring.app.price.payload.RulePriceListData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {
    CSVHelper csvHelper = new CSVHelper();

    public void importCSVRuleArea(MultipartFile file) {
        csvHelper.setFile(file);
    }

    public void importCSVRulePriceList(MultipartFile file) {
        csvHelper.setFile(file);
    }

    public RuleData processRuleDataResponse(Rule rule) {
        List<RuleAreaData> ruleAreaData = new ArrayList<>();
        for (RuleArea ra : rule.getRuleAreas())
            ruleAreaData.add(processRuleAreaDataResponse(ra));

        List<RulePriceListData> rulePriceListData = new ArrayList<>();
        for (RulePriceList rpl : rule.getRulePriceLists())
            rulePriceListData.add(processRulePriceListResponse(rpl));

        return new RuleData(
                rule.getId(),
                rule.getName(),
                rule.getDescription(),
                ruleAreaData,
                rulePriceListData,
                DateFormatHelper.dateToString(rule.getCreatedAt()),
                DateFormatHelper.dateToString(rule.getUpdatedAt())
        );
    }

    public RuleAreaData processRuleAreaDataResponse(RuleArea ruleArea) {
        return new RuleAreaData(
                ruleArea.getId(),
                ruleArea.getRule().getId(),
                ruleArea.getProvinceId(),
                ruleArea.getProvinceName(),
                ruleArea.getAreaCode(),
                ruleArea.getCenterAreaShippingTime(),
                ruleArea.getOutskirtsAreaShippingTime(),
                DateFormatHelper.dateToString(ruleArea.getCreatedAt()),
                DateFormatHelper.dateToString(ruleArea.getUpdatedAt())
        );
    }

    public RulePriceListData processRulePriceListResponse(RulePriceList rp) {
        return new RulePriceListData(
                rp.getId(),
                rp.getRule().getId(),
                rp.getAreaCode(),
                rp.getPriceList(),
                rp.getAdvancePrice(),
                rp.getWeightFactor(),
                rp.getAdvanceWeightFactor(),
                DateFormatHelper.dateToString(rp.getCreatedAt()),
                DateFormatHelper.dateToString(rp.getUpdatedAt())
        );
    }
}
