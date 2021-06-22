package cn.demo.rhv2model.service.converter;

import cn.demo.rhv2model.domain.personal.ReportMessage;

public interface RhInputParseConverter<T> {

    ReportMessage parseRhReport(T input);

}
